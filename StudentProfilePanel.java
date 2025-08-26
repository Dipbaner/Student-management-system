package com.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class StudentProfilePanel extends javax.swing.JFrame {

    private DatabaseConnection dbConnection;
    private String username;  // now instance-level, not static

    public StudentProfilePanel(String username) {
        this.username = username;
        dbConnection = new DatabaseConnection();  // Initialize if needed
        initComponents();
        setSize(810, 580);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void viewProfile() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM students WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, this.username);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String uname = rs.getString("username");
                String phone = rs.getString("phone");
                String rollNumber = rs.getString("roll_number");
                String branch = rs.getString("branch");
                int semester = rs.getInt("semester");
                String section = rs.getString("section");

                String profileDetails = "Name: " + name + "\n" +
                                        "Email: " + email + "\n" +
                                        "Username: " + uname + "\n" +
                                        "Phone: " + phone + "\n" +
                                        "Roll Number: " + rollNumber + "\n" +
                                        "Branch: " + branch + "\n" +
                                        "Semester: " + semester + "\n" +
                                        "Section: " + section;

                JOptionPane.showMessageDialog(this, profileDetails, "Student Profile", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Student not found.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching profile: " + ex.getMessage());
        }
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonViewProfile = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonBack = new javax.swing.JButton(); // Adding back button
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(230, 240, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24));
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("        Update Your Information");

        jButtonViewProfile.setBackground(new java.awt.Color(21, 101, 192));
        jButtonViewProfile.setFont(new java.awt.Font("Segoe UI Black", 1, 18));
        jButtonViewProfile.setForeground(new java.awt.Color(255, 255, 255));
        jButtonViewProfile.setText("View Profile");

        jButtonUpdate.setBackground(new java.awt.Color(21, 101, 192));
        jButtonUpdate.setFont(new java.awt.Font("Segoe UI Black", 1, 18));
        jButtonUpdate.setForeground(new java.awt.Color(255, 255, 255));
        jButtonUpdate.setText("Update Password");

        jButtonBack.setBackground(new java.awt.Color(255, 87, 34)); // Styling the back button
        jButtonBack.setFont(new java.awt.Font("Segoe UI Black", 1, 18));
        jButtonBack.setForeground(new java.awt.Color(255, 255, 255));
        jButtonBack.setText("Back");

        // Layout setup
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addContainerGap())
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButtonViewProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)) // Adding back button
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(50, 50, 50)
                    .addComponent(jButtonViewProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE) // Adding back button in layout
                    .addContainerGap(50, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(340, 20, 400, 400);

        jLabel9.setIcon(new javax.swing.ImageIcon("C:\\Users\\dipba\\OneDrive\\navigation\\OneDrive\\Desktop\\Images\\ready-back-school_1134-12.jpg"));
        jLabel9.setBounds(0, 0, 800, 550);
        getContentPane().add(jLabel9);

        // Action listeners
        jButtonViewProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewProfile1();
            }
        });

        jButtonUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	 new UpdatePasswordDialog(StudentProfilePanel.this,  username);
//                JOptionPane.showMessageDialog(null, "Update Username and Password functionality is not yet implemented.");
            }
        });

        // Action listener for the "Back" button
        jButtonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the current window or navigate back (depends on your navigation flow)
                  // This will close the current frame
            	StudentDashboard StudentDashboard = new StudentDashboard(username);
            	StudentDashboard.setVisible(true);

                ((JFrame) SwingUtilities.getWindowAncestor(jButtonBack)).dispose();
            }
        });
    }

    public void viewProfile1() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT name, email, username, phone, roll_number, branch, semester, section FROM students WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, this.username);  // Use instance variable

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String uname = rs.getString("username");
                String phone = rs.getString("phone");
                String rollNumber = rs.getString("roll_number");
                String branch = rs.getString("branch");
                int semester = rs.getInt("semester");
                String section = rs.getString("section");

                String profileDetails = "Name: " + name + "\n" +
                                        "Email: " + email + "\n" +
                                        "Username: " + uname + "\n" +
                                        "Phone: " + phone + "\n" +
                                        "Roll Number: " + rollNumber + "\n" +
                                        "Branch: " + branch + "\n" +
                                        "Semester: " + semester + "\n" +
                                        "Section: " + section;

                JOptionPane.showMessageDialog(this, profileDetails, "Student Profile", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No profile found for username: " + username, "Not Found", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            new StudentProfilePanel("defaultUsername").setVisible(true); // Replace with real username
        });
    }

    // Variables declaration
    private javax.swing.JButton jButtonViewProfile;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JButton jButtonBack;  // Declare the back button
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
}
