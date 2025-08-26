package com.UI;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ConsolidatedAttendanceUI extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private Connection conn;
    private AttendanceUI parentWindow;  // Reference to parent

    public ConsolidatedAttendanceUI(AttendanceUI parentWindow) {
        this.parentWindow = parentWindow;
        if (this.parentWindow != null) {
            this.parentWindow.dispose(); // Close AttendanceUI
        }

        connectToDatabase();

        setTitle("Consolidated Attendance");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(null);

        // Load background image
        ImageIcon backgroundIcon = new ImageIcon("C:\\Users\\dipba\\OneDrive\\navigation\\OneDrive\\Desktop\\Images\\ready-back-school_1134-12.jpg");
        Image img = backgroundIcon.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(img));
        backgroundLabel.setBounds(0, 0, 800, 600);
        setContentPane(backgroundLabel);
        backgroundLabel.setLayout(null);

        JLabel headingLabel = new JLabel("Consolidated Attendance");
        headingLabel.setBounds(270, 20, 300, 30);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headingLabel.setForeground(Color.WHITE);
        backgroundLabel.add(headingLabel);

        String[] columns = {"Student ID", "Name", "Present", "Absent"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(60, 80, 670, 400);
        backgroundLabel.add(scrollPane);

        loadConsolidatedData();

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setBounds(50, 500, 100, 30);
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backgroundLabel.add(backButton);
        backButton.addActionListener(e -> {
            dispose();
            new AttendanceUI().setVisible(true);  // Always creates a new AttendanceUI
        });

        setVisible(true);
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

    private String selectedCourseCode = "CS101"; // Replace this dynamically from UI if needed

    private void loadConsolidatedData() {
        try {
            String sql = "SELECT s.student_id, s.name, " +
                         "SUM(CASE WHEN a.status = 'Present' THEN 1 ELSE 0 END) AS Present, " +
                         "SUM(CASE WHEN a.status = 'Absent' THEN 1 ELSE 0 END) AS Absent " +
                         "FROM students s " +
                         "JOIN student_courses sc ON s.student_id = sc.student_id " +
                         "LEFT JOIN attendance a ON s.student_id = a.student_id AND a.course_code = ? " +
                         "WHERE sc.course_code = ? " +
                         "GROUP BY s.student_id, s.name " +
                         "ORDER BY s.student_id";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, selectedCourseCode); // For filtering attendance by course
            ps.setString(2, selectedCourseCode); // For selecting students enrolled in course

            ResultSet rs = ps.executeQuery();

            model.setRowCount(0); // Clear existing rows
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("student_id"),
                    rs.getString("name"),
                    rs.getInt("Present"),
                    rs.getInt("Absent")
                });
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading consolidated data: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConsolidatedAttendanceUI(null));
    }
}

