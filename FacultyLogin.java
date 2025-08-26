package com.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class FacultyLogin extends javax.swing.JFrame {

    private static String username;
	// Removed the BackButton declaration

    /**
     * Creates new form FacultyLogin
     */
    public FacultyLogin(String username) {
    	this.username = username;
        initComponents();
        setSize(810, 560);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        StudentImage = new javax.swing.JLabel();
        AttendanceImage = new javax.swing.JLabel();
        StudentButton = new javax.swing.JButton();
        AttendanceButton = new javax.swing.JButton();
        SettingImage = new javax.swing.JLabel();
        SettingButton = new javax.swing.JButton();
        CourseImage = new javax.swing.JLabel();
        CourseButton = new javax.swing.JButton();
        LogoutButton = new javax.swing.JButton();
        FacultyImage = new javax.swing.JLabel();
        FacultyLabel = new javax.swing.JLabel();
        BackgroundImage = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel6.setText("jLabel6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        StudentImage.setIcon(new javax.swing.ImageIcon("C:\\Users\\dipba\\OneDrive\\navigation\\OneDrive\\Desktop\\Images\\Student.png"));

        AttendanceImage.setIcon(new javax.swing.ImageIcon("C:\\Users\\dipba\\OneDrive\\navigation\\OneDrive\\Desktop\\Images\\Attendance.png"));

        StudentButton.setBackground(new java.awt.Color(255, 102, 0));
        StudentButton.setFont(new java.awt.Font("Segoe UI Black", 1, 12));
        StudentButton.setForeground(new java.awt.Color(255, 255, 255));
        StudentButton.setText("Student");
        StudentButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        StudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // System.out.println("Student Button Clicked"); // Logic for student button
                if (evt.getSource() == StudentButton) {
                    // Add functionality here
                }
            }
        });

        AttendanceButton.setBackground(new java.awt.Color(153, 0, 255));
        AttendanceButton.setFont(new java.awt.Font("Segoe UI Black", 1, 12));
        AttendanceButton.setForeground(new java.awt.Color(255, 255, 255));
        AttendanceButton.setText("Attendance");
        AttendanceButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        AttendanceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("Attendance Button Clicked");
            }
        });

        SettingImage.setIcon(new javax.swing.ImageIcon("C:\\Users\\dipba\\OneDrive\\navigation\\OneDrive\\Desktop\\Images\\ChatGPT Image Apr 19, 2025, 08_09_26 PM.png"));

        SettingButton.setBackground(new java.awt.Color(204, 0, 204));
        SettingButton.setFont(new java.awt.Font("Segoe UI Black", 1, 14));
        SettingButton.setForeground(new java.awt.Color(255, 255, 255));
        SettingButton.setText("Settings");
        SettingButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        SettingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("Settings Button Clicked");
            }
        });

        CourseImage.setIcon(new javax.swing.ImageIcon("C:\\Users\\dipba\\OneDrive\\navigation\\OneDrive\\Desktop\\Images\\course_image.png"));

        CourseButton.setBackground(new java.awt.Color(0, 153, 153));
        CourseButton.setFont(new java.awt.Font("Segoe UI Black", 1, 14));
        CourseButton.setForeground(new java.awt.Color(255, 255, 255));
        CourseButton.setText("Course");
        CourseButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        CourseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("Course Button Clicked");
            }
        });

        LogoutButton.setBackground(new java.awt.Color(51, 0, 102));
        LogoutButton.setFont(new java.awt.Font("Segoe UI Black", 1, 14));
        LogoutButton.setForeground(new java.awt.Color(255, 255, 255));
        LogoutButton.setText("Logout");
        LogoutButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        LogoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("Logout Button Clicked");
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(SettingImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(StudentButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(StudentImage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SettingButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(AttendanceImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AttendanceButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CourseImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CourseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(51, 51, 51))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(LogoutButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StudentImage)
                    .addComponent(AttendanceImage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StudentButton)
                    .addComponent(AttendanceButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SettingImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CourseImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SettingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CourseButton))
                .addGap(28, 28, 28)
                .addComponent(LogoutButton)
                .addContainerGap())
        );

        jPanel1.setBounds(330, 40, 370, 400);
        getContentPane().setLayout(null);
        getContentPane().add(jPanel1);

        FacultyImage.setIcon(new javax.swing.ImageIcon("C:\\Users\\dipba\\OneDrive\\navigation\\OneDrive\\Desktop\\Images\\FacultyLogin.png"));
        setLayout(null);
        FacultyImage.setBounds(40, 70, 120, 120);
        add(FacultyImage);

        FacultyLabel.setFont(new java.awt.Font("Segoe UI Black", 2, 14));
        FacultyLabel.setText("Faculty Login");
        setLayout(null);
        FacultyLabel.setBounds(50, 200, FacultyLabel.getPreferredSize().width, FacultyLabel.getPreferredSize().height);
        add(FacultyLabel);

        BackgroundImage.setIcon(new javax.swing.ImageIcon("C:\\Users\\dipba\\OneDrive\\navigation\\OneDrive\\Desktop\\Images\\ready-back-school_1134-12.jpg"));
        BackgroundImage.setText("jLabel8");
        setLayout(null);
        BackgroundImage.setBounds(0, 0, 810, 560);
        add(BackgroundImage);

        pack();
        LogoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(FacultyLogin.this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    dispose(); // Close current FacultyLogin window
                    SwingUtilities.invokeLater(() -> {
                        new LoginUI(); // Open LoginUI
                    });
                }
            }
        });
       CourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MyCourseUI  MyCourseUI  = new  MyCourseUI ();
                MyCourseUI.setVisible(true);

                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(LogoutButton);
                if (parentFrame != null) {
                    parentFrame.dispose(); // Close the current window
                }
            }
        });
       
       SettingButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               ConfigUI  ConfigUI   = new ConfigUI(username);
               ConfigUI .setVisible(true);

               JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(LogoutButton);
               if (parentFrame != null) {
                   parentFrame.dispose(); // Close the current window
               }
           }
       });

        AttendanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AttendanceUI studentUi = new AttendanceUI();
                studentUi.setVisible(true);

                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(LogoutButton);
                if (parentFrame != null) {
                    parentFrame.dispose(); // Close the current window
                }
            }
        });

        StudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Experiment studentUi = new Experiment();
                studentUi.setVisible(true);

                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(LogoutButton);
                if (parentFrame != null) {
                    parentFrame.dispose(); // Close the current window
                }
            }
        });

    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(FacultyLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FacultyLogin(username);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel AttendanceImage;
    private javax.swing.JButton AttendanceButton;
    private javax.swing.JLabel BackgroundImage;
    private javax.swing.JButton CourseButton;
    private javax.swing.JLabel CourseImage;
    private javax.swing.JLabel FacultyImage;
    private javax.swing.JLabel FacultyLabel;
    private javax.swing.JButton LogoutButton;
    private javax.swing.JLabel SettingImage;
    private javax.swing.JButton SettingButton;
    private javax.swing.JButton StudentButton;
    private javax.swing.JLabel StudentImage;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration
}
