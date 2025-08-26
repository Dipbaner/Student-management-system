package com.UI;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyCourseUI extends JFrame {

    JLabel background;
	private String username;

    public MyCourseUI() {
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

        // === Table (Course Info) ===
        String[] columns = {"Course Code", "Course Name", "Semester"};
        Object[][] data = {
            {"CS101", "Introduction to Programming", "1st Semester"},
            {"CS201", "Data Structures", "2nd Semester"},
            {"CS301", "Algorithms", "3rd Semester"},
            {"CS401", "Operating Systems", "4th Semester"}
        };

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MyCourseUI());
    }
}

