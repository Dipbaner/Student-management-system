package com.UI;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.impl.SqlDateModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Properties;
import java.util.HashMap;

public class AttendanceManagementUI extends JFrame {

    private JComboBox<String> courseDropdown;
    private JDatePicker datePicker;  // Use JDatePicker instead of JDateChooser
    private JButton viewAttendanceButton, generateReportButton, backButton;
    private JTable table;
    private JLabel courseLabel, dateLabel;

    public AttendanceManagementUI() {
        setTitle("Attendance Management");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        ImageIcon bgIcon = new ImageIcon("C:\\Users\\dipba\\OneDrive\\navigation\\OneDrive\\Desktop\\Images\\ready-back-school_1134-12.jpg");
        Image scaledImage = bgIcon.getImage().getScaledInstance(1000, 650, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(scaledImage));
        background.setBounds(0, 0, 1000, 650);
        setContentPane(background);
        background.setLayout(null);

        Font labelFont = new Font("Arial", Font.BOLD, 18);
        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        courseLabel = new JLabel("Course:");
        courseLabel.setBounds(50, 20, 70, 25);
        courseLabel.setForeground(Color.MAGENTA);
        courseLabel.setFont(labelFont);
        background.add(courseLabel);

        dateLabel = new JLabel("Date:");
        dateLabel.setBounds(330, 20, 50, 25);
        dateLabel.setForeground(Color.MAGENTA);
        dateLabel.setFont(labelFont);
        background.add(dateLabel);

        Map<String, String> courses = fetchRecentCourses();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(courses.values().toArray(new String[0]));
        courseDropdown = new JComboBox<>(comboBoxModel);
        courseDropdown.setBounds(120, 20, 150, 25);
        background.add(courseDropdown);

        // Initialize JDatePicker for date input
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new org.jdatepicker.impl.DateComponentFormatter());

        ((Component) datePicker).setBounds(380, 20, 160, 25);
        background.add((Component) datePicker);

//        UtilDateModel model = new UtilDateModel();
//        Properties p = new Properties();
//        p.put("text.today", "Today");
//        p.put("text.month", "Month");
//        p.put("text.year", "Year");
//        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
//        datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

        // And then add to your UI
        ((Component) datePicker).setBounds(380, 20, 120, 25);
        background.add((Component) datePicker);
        ((Component) datePicker).setBounds(380, 20, 120, 25);
        background.add((Component) datePicker);

        viewAttendanceButton = new JButton("View Attendance");
        viewAttendanceButton.setBounds(530, 20, 200, 25);
        viewAttendanceButton.setBackground(new Color(100, 149, 237));
        viewAttendanceButton.setForeground(Color.WHITE);
        viewAttendanceButton.setFont(buttonFont);
        background.add(viewAttendanceButton);

        String[] columns = {"Student ID", "Name", "Status"};
        Object[][] data = {};
        table = new JTable(new DefaultTableModel(data, columns));

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(230, 230, 250));
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Arial", Font.BOLD, 13));

        table.setOpaque(true);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 70, 900, 400);
        scrollPane.setOpaque(false);
        background.add(scrollPane);

        generateReportButton = new JButton("Generate Report");
        generateReportButton.setBounds(520, 500, 180, 40);
        generateReportButton.setBackground(new Color(178, 34, 34));
        generateReportButton.setForeground(Color.WHITE);
        generateReportButton.setFont(buttonFont);
        background.add(generateReportButton);

        backButton = new JButton("Back");
        backButton.setBounds(20, 570, 100, 30);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        background.add(backButton);

        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GenerateReportUI generateReportUI = new GenerateReportUI();
                generateReportUI.setVisible(true);
                dispose();
            }
        });

        viewAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCourseName = (String) courseDropdown.getSelectedItem();
                String courseCode = null;
                for (Map.Entry<String, String> entry : courses.entrySet()) {
                    if (entry.getValue().equals(selectedCourseName)) {
                        courseCode = entry.getKey();
                        break;
                    }
                }

                // Fetch the selected date from the JDatePicker
                java.util.Date selectedDate = (java.util.Date) datePicker.getModel().getValue();
                if (selectedDate == null) {
                    JOptionPane.showMessageDialog(null, "Please select a valid date.");
                    return;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(selectedDate);

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);

                String query = "SELECT a.student_id, s.name, a.status FROM attendance a JOIN students s ON a.student_id = s.student_id WHERE a.course_code = ? AND a.date = ?";

                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(query)) {

                    stmt.setString(1, courseCode);
                    stmt.setString(2, date);

                    ResultSet rs = stmt.executeQuery();

                    boolean dataFound = false;
                    while (rs.next()) {
                        String studentId = rs.getString("student_id");
                        String name = rs.getString("name");
                        String status = rs.getString("status");
                        model.addRow(new Object[]{studentId, name, status});
                        dataFound = true;
                    }

                    if (!dataFound) {
                        JOptionPane.showMessageDialog(null, "No attendance data found for the selected course and date.");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error fetching attendance data.");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminLogin AdminLogin = new AdminLogin();
                AdminLogin.setVisible(true);
                dispose();
            }
        });
    }

    private boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public void loadAttendanceForCourseAndDate(String courseCode, String date) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        String query = "SELECT a.student_id, s.name, a.status FROM attendance a JOIN students s ON a.student_id = s.student_id WHERE a.course_code = ? AND a.date = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, courseCode);
            stmt.setString(2, date);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String studentId = rs.getString("student_id");
                String name = rs.getString("name");
                String status = rs.getString("status");
                model.addRow(new Object[]{studentId, name, status});
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading attendance: " + ex.getMessage());
        }
    }

    private Map<String, String> fetchRecentCourses() {
        Map<String, String> courseMap = new HashMap<>();
        String query = "SELECT course_code, course_name FROM courses";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String courseCode = rs.getString("course_code");
                String courseName = rs.getString("course_name");
                courseMap.put(courseCode, courseName);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching courses from the database.");
        }

        return courseMap;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AttendanceManagementUI ui = new AttendanceManagementUI();
            ui.setVisible(true);
        });
    }
}// (The rest of the code remains unchanged)

