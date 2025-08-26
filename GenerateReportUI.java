package com.UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdatepicker.impl.*;

import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Properties;

public class GenerateReportUI extends JFrame {

    private JComboBox<String> courseCodeComboBox;
    private JTextField thresholdField;
    private JDatePickerImpl fromDatePicker, toDatePicker;
    private JTable reportTable;
    private DefaultTableModel tableModel;
    private Image backgroundImage;

    public GenerateReportUI() {
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\dipba\\OneDrive\\navigation\\OneDrive\\Desktop\\Images\\ready-back-school_1134-12.jpg"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to load background image: " + e.getMessage());
        }

        setTitle("Attendance Defaulter Report");
        setSize(1000, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel backgroundPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);

        JPanel filterPanel = new JPanel(new FlowLayout());
        filterPanel.setBounds(50, 20, 900, 60);
        filterPanel.setBackground(new Color(255, 255, 255, 240));
        filterPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        courseCodeComboBox = new JComboBox<>();
        thresholdField = new JTextField("75", 5);
        fromDatePicker = createDatePicker();
        toDatePicker = createDatePicker();

        fromDatePicker.setPreferredSize(new Dimension(120, 30));
        toDatePicker.setPreferredSize(new Dimension(120, 30));

        filterPanel.add(new JLabel("Course Code:"));
        filterPanel.add(courseCodeComboBox);
        filterPanel.add(new JLabel("Threshold (%):"));
        filterPanel.add(thresholdField);
        filterPanel.add(new JLabel("From Date:"));
        filterPanel.add(fromDatePicker);
        filterPanel.add(new JLabel("To Date:"));
        filterPanel.add(toDatePicker);
        backgroundPanel.add(filterPanel);

        tableModel = new DefaultTableModel(new String[]{
                "Roll Number", "Name", "Branch", "Semester", "Attendance (%)"
        }, 0);
        reportTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(reportTable);
        scrollPane.setBounds(50, 90, 900, 300);
        backgroundPanel.add(scrollPane);

        JButton generateButton = new JButton("Generate Report");
        generateButton.setBounds(790, 450, 160, 35);
        generateButton.addActionListener(this::generateReport);
        backgroundPanel.add(generateButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(50, 450, 160, 35);
        backButton.addActionListener(e -> {
            AttendanceManagementUI attendanceManagementUI = new AttendanceManagementUI();
            attendanceManagementUI.setVisible(true);
            this.dispose();
        });
        backgroundPanel.add(backButton);

        JButton exportButton = new JButton("Export to Excel");
        exportButton.setBounds(420, 450, 160, 35);
        exportButton.addActionListener(e -> exportToExcel());
        backgroundPanel.add(exportButton);

        loadCourseCodes();
        setVisible(true);
    }

    private JDatePickerImpl createDatePicker() {
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        return new JDatePickerImpl(datePanel, new DateLabelFormatter());
    }

    private void exportToExcel() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Excel file");
        fileChooser.setSelectedFile(new File("StudentsReport.xlsx"));
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Students");

                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < tableModel.getColumnCount(); i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(tableModel.getColumnName(i));
                    CellStyle style = workbook.createCellStyle();
                    Font font = workbook.createFont();
                    font.setBold(true);
                    style.setFont(font);
                    cell.setCellStyle(style);
                }

                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 1);
                    for (int j = 0; j < tableModel.getColumnCount(); j++) {
                        Cell cell = row.createCell(j);
                        Object value = tableModel.getValueAt(i, j);
                        if (value instanceof Integer) {
                            cell.setCellValue((Integer) value);
                        } else if (value instanceof Double) {
                            cell.setCellValue((Double) value);
                        } else if (value != null) {
                            cell.setCellValue(value.toString());
                        }
                    }
                }

                for (int i = 0; i < tableModel.getColumnCount(); i++) {
                    sheet.autoSizeColumn(i);
                }

                try (FileOutputStream fos = new FileOutputStream(fileToSave)) {
                    workbook.write(fos);
                }

                showMessage("Report exported successfully to " + fileToSave.getAbsolutePath());
            } catch (Exception ex) {
                ex.printStackTrace();
                showError("Error exporting report: " + ex.getMessage());
            }
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private void loadCourseCodes() {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT course_code FROM courses ORDER BY course_code");
             ResultSet rs = pst.executeQuery()) {

            courseCodeComboBox.removeAllItems();

            while (rs.next()) {
                String code = rs.getString("course_code");
                courseCodeComboBox.addItem(code);
            }

        } catch (SQLException e) {
            showError("Error loading course codes: " + e.getMessage());
        }
    }

    private void generateReport(ActionEvent event) {
        String courseCode = (String) courseCodeComboBox.getSelectedItem();
        String thresholdStr = thresholdField.getText();

        Object fromDateObj = fromDatePicker.getModel().getValue();
        Object toDateObj = toDatePicker.getModel().getValue();

        if (fromDateObj == null || toDateObj == null) {
            showError("Please select both From and To dates.");
            return;
        }

        LocalDate startDate = ((java.util.Date) fromDateObj).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = ((java.util.Date) toDateObj).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

        if (endDate.isBefore(startDate)) {
            showError("End date cannot be before start date.");
            return;
        }

        if (courseCode == null || thresholdStr.isEmpty()) {
            showError("Please fill all filter fields.");
            return;
        }

        int threshold;
        try {
            threshold = Integer.parseInt(thresholdStr);
        } catch (NumberFormatException e) {
            showError("Threshold must be a valid number.");
            return;
        }

        tableModel.setRowCount(0);

        String query = """
            SELECT 
                s.roll_number,
                s.name,
                s.branch,
                s.semester,
                ROUND(SUM(CASE WHEN a.status = 'Present' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS attendance_percent
            FROM 
                attendance a
            JOIN 
                students s ON a.student_id = s.student_id
            WHERE 
                a.course_code = ?
                AND a.date BETWEEN ? AND ?
            GROUP BY 
                s.roll_number, s.name, s.branch, s.semester
            HAVING 
                attendance_percent < ?
        """;

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, courseCode);
            pst.setDate(2, Date.valueOf(startDate));
            pst.setDate(3, Date.valueOf(endDate));
            pst.setInt(4, threshold);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    tableModel.addRow(new Object[]{
                            rs.getString("roll_number"),
                            rs.getString("name"),
                            rs.getString("branch"),
                            rs.getString("semester"),
                            String.format("%.2f", rs.getDouble("attendance_percent"))
                    });
                }

                if (tableModel.getRowCount() == 0) {
                    showMessage("No defaulters found in the selected range.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            showError("Error generating report: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GenerateReportUI::new);
    }
}

class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
    private final String datePattern = "yyyy-MM-dd";
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parse(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }
        return "";
    }
}
