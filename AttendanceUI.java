package com.UI;



import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;
import java.awt.Image;

public class AttendanceUI extends JFrame {

    private JTable table;
    private JTextField searchField;
    private JButton submitButton;
    private JButton backButton;
    private DefaultTableModel model;
    private TableRowSorter<DefaultTableModel> rowSorter;

    private Connection conn;
	private String username;

    public AttendanceUI() {
        connectToDatabase();

        setTitle("Mark Attendance");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(null);

        ImageIcon backgroundIcon = new ImageIcon("C:\\Users\\dipba\\OneDrive\\navigation\\OneDrive\\Desktop\\Images\\ready-back-school_1134-12.jpg");
        Image img = backgroundIcon.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(img));
        backgroundLabel.setBounds(0, 0, 800, 600);
        setContentPane(backgroundLabel);
        backgroundLabel.setLayout(null);

        JLabel headingLabel = new JLabel("Student Attendance");
        headingLabel.setBounds(300, 10, 200, 30);
        backgroundLabel.add(headingLabel);

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setBounds(50, 50, 60, 25);
        backgroundLabel.add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(120, 50, 200, 25);
        backgroundLabel.add(searchField);

        String[] columns = {"Student ID", "Name", "Date", "Attendance"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        JComboBox<String> comboBox = new JComboBox<>(new String[]{"Present", "Absent"});
        table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBox));

        rowSorter = new TableRowSorter<>(model);
        table.setRowSorter(rowSorter);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 90, 680, 400);
        backgroundLabel.add(scrollPane);

        loadStudentData();

        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filter(); }

            private void filter() {
                String text = searchField.getText().trim();
                if (text.length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.setBounds(350, 500, 100, 30);
        backgroundLabel.add(submitButton);
        submitButton.addActionListener(e -> submitAttendance());

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(50, 500, 100, 30);
        backgroundLabel.add(backButton);
        backButton.addActionListener(e -> {
            dispose();
            new FacultyLogin(username).setVisible(true);
        });

        // Consolidated Button
        JButton consolidatedButton = new JButton("Consolidated");
        consolidatedButton.setBounds(650, 500, 120, 30);
        backgroundLabel.add(consolidatedButton);
        consolidatedButton.addActionListener(e -> {
            new ConsolidatedAttendanceUI(this).setVisible(true);
        });
    }

    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/studentmanagementsystem", "root", "dip220804");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage());
            System.exit(1);
        }
    }

    private String selectedCourseCode = "CS101"; // You might retrieve this from a dropdown in the UI

    private void loadStudentData() {
        try {
            String sql = "SELECT s.student_id, s.name " +
                         "FROM students s " +
                         "JOIN student_courses sc ON s.student_id = sc.student_id " +
                         "WHERE sc.course_code = ? " +
                         "ORDER BY s.student_id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, selectedCourseCode);
            ResultSet rs = ps.executeQuery();
            String date = LocalDate.now().toString();

            model.setRowCount(0); // Clear existing rows if necessary
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("student_id"),
                    rs.getString("name"),
                    date,
                    "Present" // Default status
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to load student data: " + e.getMessage());
        }
    }

    private void submitAttendance() {
        try {
            String sql = "REPLACE INTO attendance (student_id, course_code, date, status) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            for (int i = 0; i < model.getRowCount(); i++) {
                int studentId = (int) model.getValueAt(i, 0);
                String date = (String) model.getValueAt(i, 2);
                String status = (String) model.getValueAt(i, 3);

                ps.setInt(1, studentId);
                ps.setString(2, selectedCourseCode); // Add course_code
                ps.setDate(3, Date.valueOf(date));
                ps.setString(4, status);
                ps.addBatch();
            }

            ps.executeBatch();
            JOptionPane.showMessageDialog(this, "Attendance submitted successfully.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to submit attendance: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AttendanceUI().setVisible(true));
    }
}
