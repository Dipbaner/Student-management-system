package com.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Test extends JFrame {

    JLabel background;
    private String username;

    public Test(String username) {
        this.username = username;

        setTitle("My Courses");
        setSize(730, 510);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // === Background Image ===
        background = new JLabel(new ImageIcon("C:\\Users\\dipba\\OneDrive\\navigation\\OneDrive\\Desktop\\Images\\ready-back-school_1134-12.jpg"));
        background.setBounds(0, 0, 720, 490);
        getContentPane().add(background);

        // === Heading ===
        JLabel courseLabel = new JLabel("📘 My Courses");
        courseLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
        courseLabel.setForeground(Color.WHITE);
        courseLabel.setBounds(50, 30, 300, 30);
        background.add(courseLabel);

        // === Dynamic Course Data from DB ===
        String[] columns = {"Course Code", "Course Name", "Schedule"};
        Object[][] data = getCoursesByFaculty(username);
        JTable courseTable = new JTable(data, columns);
        courseTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        courseTable.setRowHeight(30);
        courseTable.setBackground(new Color(240, 240, 240));
        JScrollPane scrollPane = new JScrollPane(courseTable);
        scrollPane.setBounds(50, 90, 630, 200);
        background.add(scrollPane);

        // === Back Button ===
        JButton backButton = new JButton("← Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setBounds(600, 20, 90, 35);
        backButton.setBackground(new Color(255, 102, 102));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        background.add(backButton);

        backButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                backButton.setBackground(new Color(240, 70, 70));
            }

            public void mouseExited(MouseEvent e) {
                backButton.setBackground(new Color(255, 102, 102));
            }
        });

        backButton.addActionListener(e -> {
            dispose();
            new FacultyLogin(username).setVisible(true);
        });

        setVisible(true);
    }

    /**
     * Retrieves courses assigned to a faculty member using their username.
     */
    private Object[][] getCoursesByFaculty(String facultyUsername) {
       ArrayList<Object[]> courseList = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/studentmanagementsystem"; // Replace with your DB name
        String dbUser = "root"; // Your DB username
        String dbPass = "dip220804"; // Your DB password

        String query = """
                SELECT c.course_code, c.course_name, c.schedule
                FROM courses c
                JOIN faculty f ON c.faculty_id = f.faculty_id
                WHERE f.username = ?
                """;

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, facultyUsername);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String code = rs.getString("course_code");
                String name = rs.getString("course_name");
                String schedule = rs.getString("schedule");
                courseList.add(new Object[]{code, name, schedule});
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading courses: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        if (courseList.isEmpty()) {
            courseList.add(new Object[]{"N/A", "No courses found", "-"});
        }

        return courseList.toArray(new Object[0][]);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Test("jdoe")); // test with your username
    }
}
