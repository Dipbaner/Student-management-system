package com.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;

public class Experiment extends JFrame {
    private DefaultListModel<String> availableStudentsModel;
    private DefaultListModel<String> selectedStudentsModel;
    private JList<String> availableStudentsList;
    private JList<String> selectedStudentsList;

    // Hardcoded course code for this example (this can be dynamically set)
    private String courseCode = "CS101"; // Example course code
	private String username;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/studentmanagementsystem";
    private static final String USER = "root";
    private static final String PASS = "dip220804";

    public Experiment() {
        setTitle("Student Selector");
        setSize(810, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load and scale background image
        ImageIcon bgIcon = new ImageIcon("C:\\Users\\dipba\\OneDrive\\navigation\\OneDrive\\Desktop\\Images\\ready-back-school_1134-12.jpg");
        Image scaledImage = bgIcon.getImage().getScaledInstance(810, 560, Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(scaledImage));
        backgroundLabel.setLayout(new GridBagLayout()); // Use GridBagLayout for full control
        setContentPane(backgroundLabel);

        // Initialize models
        availableStudentsModel = new DefaultListModel<>();
        selectedStudentsModel = new DefaultListModel<>();
        loadAvailableStudents();

        // Initialize lists
        availableStudentsList = new JList<>(availableStudentsModel);
        selectedStudentsList = new JList<>(selectedStudentsModel);
        availableStudentsList.setVisibleRowCount(20);
        selectedStudentsList.setVisibleRowCount(20);
        availableStudentsList.setFixedCellWidth(300);
        selectedStudentsList.setFixedCellWidth(300);

        JScrollPane scrollAvailable = new JScrollPane(availableStudentsList);
        JScrollPane scrollSelected = new JScrollPane(selectedStudentsList);

        // Buttons
        JButton addButton = new JButton(">>");
        JButton removeButton = new JButton("<<");
        JButton backButton = new JButton("Back");

        // Add ActionListeners and pass courseCode to moveSelectedStudents
        addButton.addActionListener(e -> moveSelectedStudents(availableStudentsList, availableStudentsModel, selectedStudentsModel, courseCode));
        removeButton.addActionListener(e -> moveSelectedStudents(selectedStudentsList, selectedStudentsModel, availableStudentsModel, courseCode));
        backButton.addActionListener(e -> {
            dispose();
            new FacultyLogin(username); // Make sure this class exists
        });

        // Main panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20); // Padding around all components
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(scrollAvailable, gbc);

        gbc.gridx = 1;
        mainPanel.add(scrollSelected, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, gbc);

        // Add to background
        backgroundLabel.add(mainPanel);
    }

    private void loadAvailableStudents() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name FROM students")) {

            while (rs.next()) {
                String name = rs.getString("name");
                availableStudentsModel.addElement(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading students: " + e.getMessage());
        }
    }

    private void moveSelectedStudents(JList<String> sourceList, DefaultListModel<String> sourceModel, DefaultListModel<String> targetModel, String courseCode) {
        boolean toSelectedList = (targetModel == selectedStudentsModel);

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            for (String selectedValue : sourceList.getSelectedValuesList()) {
                // Assuming selectedValue is the student's name, we first get the student ID using the username
                String studentUsernameQuery = "SELECT student_id FROM students WHERE name = '" + selectedValue + "'";
                ResultSet rs = stmt.executeQuery(studentUsernameQuery);

                if (rs.next()) {
                    int studentId = rs.getInt("student_id");

                    // Move the student to the target model (either selected or not)
                    targetModel.addElement(selectedValue);
                    sourceModel.removeElement(selectedValue);

                    if (toSelectedList) {
                        // Insert into student_courses table with student_id and course_code
                        String insertSQL = "INSERT INTO student_courses (student_id, course_code) VALUES (" + studentId + ", '" + courseCode + "')";
                        stmt.executeUpdate(insertSQL);
                    } else {
                        // Delete from student_courses table
                        String deleteSQL = "DELETE FROM student_courses WHERE student_id = " + studentId + " AND course_code = '" + courseCode + "'";
                        stmt.executeUpdate(deleteSQL);
                    }
                }
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "DB Update Failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Experiment frame = new Experiment();
            frame.setVisible(true);
        });
    }
}
