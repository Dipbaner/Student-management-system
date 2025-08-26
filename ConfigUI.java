package com.UI;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ConfigUI extends JFrame {
    private JLabel background;
    private JTextField usernameField, nameField;
    private JPasswordField passwordField;
    private JButton updateButton, backButton;
    private String currentUsername;

    public ConfigUI(String username) {
        this.currentUsername = username;
        setTitle("Configuration");
        setSize(730, 510);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        background = new JLabel(new ImageIcon("C:\\Users\\dipba\\OneDrive\\navigation\\OneDrive\\Desktop\\Images\\ready-back-school_1134-12.jpg"));
        background.setBounds(0, 0, 720, 490);
        getContentPane().add(background);

        JLabel configLabel = new JLabel("Settings / Config");
        configLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
        configLabel.setForeground(Color.WHITE);
        configLabel.setBounds(50, 30, 200, 30);
        background.add(configLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(50, 80, 100, 30);
        background.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 80, 200, 30);
        usernameField.setText(username);
        usernameField.setEditable(false);
        background.add(usernameField);

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(50, 120, 100, 30);
        background.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 120, 200, 30);
        background.add(nameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(50, 160, 100, 30);
        background.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 160, 200, 30);
        background.add(passwordField);

        updateButton = new JButton("Update Info");
        updateButton.setBounds(50, 200, 150, 30);
        updateButton.setBackground(new Color(204, 0, 204));
        updateButton.setForeground(Color.WHITE);
        background.add(updateButton);

        // 🔴 BACK BUTTON
        backButton = new JButton("Back");
        backButton.setBounds(50, 240, 150, 30);
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        background.add(backButton);

        updateButton.addActionListener(e -> updateUserInfo());

        // Back button action
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FacultyLogin facultyLogin = new FacultyLogin(username);
                
                facultyLogin.setVisible(true);
                dispose();
            }
        });

        loadUserInfo();
        setVisible(true);
    }

    private void loadUserInfo() {
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT name, password FROM faculty WHERE username = ?");
            pst.setString(1, currentUsername);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                nameField.setText(rs.getString("name"));
                passwordField.setText(rs.getString("password"));
            } else {
                JOptionPane.showMessageDialog(this, "Error: User not found!");
            }

            rs.close();
            pst.close();
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void updateUserInfo() {
        String name = nameField.getText();
        String password = new String(passwordField.getPassword());

        if (name.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fields cannot be empty!");
            return;
        }

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("UPDATE faculty SET name = ?, password = ? WHERE username = ?");
            pst.setString(1, name);
            pst.setString(2, password);
            pst.setString(3, currentUsername);

            int result = pst.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Information updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Update failed.");
            }

            pst.close();
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConfigUI("faculty_username"));
    }
}
