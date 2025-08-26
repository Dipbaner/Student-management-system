package com.UI;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ViewAllStudentUI extends JFrame {
    private JTable studentTable;
    private DefaultTableModel model;
    private BufferedImage backgroundImage;

    public ViewAllStudentUI() {
        setTitle("Student Information");
        setSize(950, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\dipba\\OneDrive\\navigation\\OneDrive\\Desktop\\Images\\ready-back-school_1134-12.jpg"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading background: " + e.getMessage());
        }

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setLayout(null);

        // Table setup
        String[] cols = {"Roll Number", "Name", "E-mail", "phone", "Branch", "Semester", "Section"};
        model = new DefaultTableModel(cols, 0);
        studentTable = new JTable(model);
        studentTable.setOpaque(true);
        studentTable.setBackground(Color.WHITE);
        studentTable.setFillsViewportHeight(true);
        studentTable.setRowHeight(25);
        studentTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        studentTable.getTableHeader().setForeground(Color.BLACK);
        studentTable.getTableHeader().setOpaque(true);
        studentTable.getTableHeader().setBackground(new Color(230, 230, 230));

        // Set cell renderer opaque
        DefaultTableCellRenderer cellRenderer = (DefaultTableCellRenderer) studentTable.getDefaultRenderer(Object.class);
        cellRenderer.setOpaque(true);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBounds(10, 10, 910, 220);
        scrollPane.setOpaque(true);
        scrollPane.getViewport().setOpaque(true);
        scrollPane.getViewport().setBackground(Color.WHITE);
        backgroundPanel.add(scrollPane);

        // Panel with heading and buttons
        JPanel infoPanel = new JPanel(null);
        infoPanel.setBounds(600, 240, 300, 200);
        infoPanel.setBackground(new Color(204, 204, 255));  // Light purple

        JLabel titleLabel = new JLabel("View All Student Information");
        titleLabel.setBounds(10, 10, 280, 30);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        titleLabel.setForeground(Color.RED);
        infoPanel.add(titleLabel);

        JButton viewBtn = new JButton("View All");
        JButton cancelBtn = new JButton("Cancel");
        styleRedButton(viewBtn);
        styleRedButton(cancelBtn);

        viewBtn.setBounds(30, 60, 100, 40);
        cancelBtn.setBounds(160, 60, 100, 40);

        infoPanel.add(viewBtn);
        infoPanel.add(cancelBtn);

//        cancelBtn.addActionListener(e -> dispose());
        cancelBtn.addActionListener(new ActionListener() { // Student
            public void actionPerformed(ActionEvent e) {
            
            	StudentUi studentUi = new StudentUi();
            	studentUi.setVisible(true);

                ((JFrame) SwingUtilities.getWindowAncestor(cancelBtn)).dispose();  
                // new StudentManagement(); // Replace with your actual frame/class
            }
        });

        backgroundPanel.add(infoPanel);
        setContentPane(backgroundPanel);
        viewBtn.addActionListener(e -> {
            model.setRowCount(0); // Clear previous data

            try (Connection con = DatabaseConnection.getConnection();
                 Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {

                while (rs.next()) {
                    Object[] row = {
                        rs.getString("roll_number"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("branch"),
                        rs.getString("semester"),
                        rs.getString("section")
                    };
                    model.addRow(row);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

    }

    private void styleRedButton(JButton button) {
        button.setBackground(new Color(153, 0, 0));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createBevelBorder(1));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewAllStudentUI().setVisible(true));
    }
}
