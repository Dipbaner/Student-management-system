package com.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CourseEnrollmentPanel extends JFrame {

	static String username;
	static int id ;
    private JTable courseTable;
    private JButton backButton;
    private int studentId;

    public CourseEnrollmentPanel(int studentId, String username) {
        this.studentId = studentId;
        this.username  =username;
        setTitle("My Courses");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JLabel headerLabel = new JLabel("Courses Allotted", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        String[] columnNames = {"Course Name", "Faculty", "Schedule"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        courseTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(courseTable);

        backButton = new JButton("Back");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);

        setLayout(new BorderLayout());
        add(headerLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loadStudentCourses(studentId);
        backButton.addActionListener(new ActionListener() { // Logout
            public void actionPerformed(ActionEvent e) {
              
                	StudentDashboard StudentDashboard = new  StudentDashboard(username);
                	StudentDashboard.setVisible(true);
                	 ((JFrame) SwingUtilities.getWindowAncestor(backButton)).dispose();  
                    // Close current window
                    // Optionally open login screen
                    // new LoginScreen(); 
                }
          
        });
    }

    private void loadStudentCourses(int studentId) {
        String url = "jdbc:mysql://localhost:3306/studentmanagementsystem";
        String user = "root";
        String pass = "dip220804";

        String query = """
            SELECT c.course_name, f.name AS faculty_name, c.schedule
            FROM student_courses sc
            JOIN courses c ON sc.course_code = c.course_code
            LEFT JOIN faculty f ON c.faculty_id = f.faculty_id
            WHERE sc.student_id = ?;
        """;

        try (
            Connection conn = DriverManager.getConnection(url, user, pass);
            PreparedStatement pst = conn.prepareStatement(query)
        ) {
            pst.setInt(1, studentId);
            ResultSet rs = pst.executeQuery();

            DefaultTableModel model = (DefaultTableModel) courseTable.getModel();
            model.setRowCount(0); // clear previous data

            while (rs.next()) {
                String courseName = rs.getString("course_name");
                String facultyName = rs.getString("faculty_name");
                String schedule = rs.getString("schedule");

                model.addRow(new Object[]{courseName, facultyName, schedule});
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load courses.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Example usage with student_id = 1
        SwingUtilities.invokeLater(() -> new CourseEnrollmentPanel(id, username).setVisible(true));
    }
}
