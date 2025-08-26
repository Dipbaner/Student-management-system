package com.UI;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdatePasswordDialog extends JDialog {
    private final JPasswordField passwordField = new JPasswordField(15);
    private final JPasswordField confirmPasswordField = new JPasswordField(15);
    private final JButton updateButton = new JButton("Update");
    private final JButton cancelButton = new JButton("Cancel");
    private final String username;

    public UpdatePasswordDialog(JFrame parent, String username) {
        super(parent, "Update Password", true);
        this.username = username;

        setLayout(new BorderLayout(10, 10));
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("New Password:"));
        inputPanel.add(passwordField);
        inputPanel.add(new JLabel("Confirm Password:"));
        inputPanel.add(confirmPasswordField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        buttonPanel.add(cancelButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        updateButton.addActionListener(e -> updatePassword());
        cancelButton.addActionListener(e -> dispose());

        pack(); // Ensures correct sizing
        setLocationRelativeTo(parent); // Centered on screen
        setVisible(true); // Now safe to show
    }

    private void updatePassword() {
        String pass = new String(passwordField.getPassword());
        String confirmPass = new String(confirmPasswordField.getPassword());

        if (pass.isEmpty() || confirmPass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fields cannot be empty!");
            return;
        }

        if (!pass.equals(confirmPass)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!");
            return;
        }

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(
                "UPDATE students SET password = ? WHERE username = ?"
            );
            pst.setString(1, pass);
            pst.setString(2, username);

            int result = pst.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Password updated successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Update failed.");
            }

            pst.close();
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
