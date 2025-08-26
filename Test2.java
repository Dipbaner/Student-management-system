package com.UI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class Test2 extends JFrame {
    private JComboBox<String> facultyComboBox;
    private JPanel coursePanel;
    private ArrayList<JCheckBox> courseCheckboxes = new ArrayList<>();
    private JButton assignButton, cancelButton;

    private Connection con;

    public Test2() {
        setTitle("Assign Courses to Faculty");
        setSize(810, 560);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("C:\\Users\\dipba\\OneDrive\\navigation\\OneDrive\\Documents\\NetBeansProjects\\SMS\\src\\sms\\ready-back-school_1134-12.jpg");
        BackgroundPanel bgPanel = new BackgroundPanel(icon.getImage());
        bgPanel.setLayout(null);

        setContentPane(bgPanel);

        initDB();
        initUI(bgPanel);
    }

    private void initDB() {
        try {
            con = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection failed.");
            System.exit(1);
        }
    }

    private void initUI(JPanel bgPanel) {
        facultyComboBox = new JComboBox<>();
        loadFaculty();

        // Inner panel
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setBackground(new Color(255, 255, 255, 220));
        innerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        innerPanel.setBounds(300, 30, 460, 460);
        bgPanel.add(innerPanel);

        // Title
        JLabel titleLabel = new JLabel("Assign Courses to Faculty", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        innerPanel.add(titleLabel);
        innerPanel.add(Box.createVerticalStrut(24));

        // Faculty dropdown
        JLabel facultyLabel = new JLabel("Select Faculty:");
        facultyLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        innerPanel.add(facultyLabel);

        facultyComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
        innerPanel.add(facultyComboBox);
        innerPanel.add(Box.createVerticalStrut(20));

        // Courses
        JLabel courseLabel = new JLabel("Select Courses:");
        courseLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        innerPanel.add(courseLabel);

        // Course checkboxes panel
        coursePanel = new JPanel();
        coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));
        coursePanel.setBackground(new Color(255, 255, 255, 0)); // Transparent

        JScrollPane scrollPane = new JScrollPane(coursePanel);
        scrollPane.setPreferredSize(new Dimension(400, 150));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        innerPanel.add(scrollPane);

        loadCourses(); // Load and add checkboxes here
        for (JCheckBox cb : courseCheckboxes) {
            cb.setFont(new Font("Arial", Font.PLAIN, 18));
            coursePanel.add(cb);
        }

        innerPanel.add(Box.createVerticalStrut(20));

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);

        assignButton = new JButton("Assign");
        assignButton.setFont(new Font("Arial", Font.BOLD, 18));
        assignButton.setPreferredSize(new Dimension(120, 40));
        assignButton.setBackground(new Color(0, 153, 204));
        assignButton.setForeground(Color.WHITE);
        assignButton.addActionListener(e -> assignCourses());

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 18));
        cancelButton.setPreferredSize(new Dimension(120, 40));
        cancelButton.setBackground(new Color(204, 0, 0));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(e -> {
            CourseUI courseUI = new CourseUI();
            courseUI.setVisible(true);
            dispose();
        });

        buttonPanel.add(assignButton);
        buttonPanel.add(cancelButton);
        innerPanel.add(buttonPanel);
    }

    private void loadFaculty() {
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name FROM faculty")) {
            while (rs.next()) {
                facultyComboBox.addItem(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadCourses() {
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT course_name FROM courses")) {
            while (rs.next()) {
                JCheckBox cb = new JCheckBox(rs.getString("course_name"));
                courseCheckboxes.add(cb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void assignCourses() {
        try {
            String selectedFaculty = (String) facultyComboBox.getSelectedItem();
            if (selectedFaculty == null) {
                JOptionPane.showMessageDialog(this, "Please select a faculty.");
                return;
            }

            PreparedStatement ps = con.prepareStatement("SELECT faculty_id FROM faculty WHERE name = ?");
            ps.setString(1, selectedFaculty);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "Faculty not found.");
                return;
            }
            int facultyId = rs.getInt("faculty_id");

            for (JCheckBox cb : courseCheckboxes) {
                if (cb.isSelected()) {
                    String courseName = cb.getText();
                    ps = con.prepareStatement("SELECT course_code FROM courses WHERE course_name = ?");
                    ps.setString(1, courseName);
                    rs = ps.executeQuery();
                    if (!rs.next()) continue;
                    String courseCode = rs.getString("course_code");

                    ps = con.prepareStatement("INSERT INTO course_assignments (course_code, faculty_id) VALUES (?, ?)");
                    ps.setString(1, courseCode);
                    ps.setInt(2, facultyId);
                    ps.executeUpdate();
                }
            }

            JOptionPane.showMessageDialog(this, "Courses assigned successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error while assigning courses.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Test2().setVisible(true));
    }
}
