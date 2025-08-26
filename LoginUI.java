package com.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

public class LoginUI extends javax.swing.JFrame {
	private boolean isPasswordVisible = false;

	public LoginUI() {
		initComponents();

		setSize(810, 560);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@SuppressWarnings("unchecked")

	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jDialog1 = new javax.swing.JDialog();
		jDialog2 = new javax.swing.JDialog();
		jPanel3 = new javax.swing.JPanel();
		jPanel4 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jPasswordField1 = new javax.swing.JPasswordField();
		jComboBox1 = new javax.swing.JComboBox<>();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jToggleButton1 = new javax.swing.JToggleButton();
		jLabel1 = new javax.swing.JLabel();

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 100, Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 100, Short.MAX_VALUE));

		javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
		jDialog1.getContentPane().setLayout(jDialog1Layout);
		jDialog1Layout.setHorizontalGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 400, Short.MAX_VALUE));
		jDialog1Layout.setVerticalGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 300, Short.MAX_VALUE));

		javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
		jDialog2.getContentPane().setLayout(jDialog2Layout);
		jDialog2Layout.setHorizontalGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 400, Short.MAX_VALUE));
		jDialog2Layout.setVerticalGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 300, Short.MAX_VALUE));

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 100, Short.MAX_VALUE));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 100, Short.MAX_VALUE));

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		jPanel4.setBackground(new java.awt.Color(255, 255, 255));

		jLabel2.setIcon(new javax.swing.ImageIcon(
				"C:\\Users\\dipba\\OneDrive\\navigation\\OneDrive\\Desktop\\Images\\student-icon-29.png")); // NOI18N
		jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(204, 204, 204)));

		jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
		jLabel3.setText(" USERNAME");
		jLabel3.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(204, 204, 204)));

		jTextField1
				.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(204, 204, 204)));

		jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
		jLabel4.setText("PASSWORD");
		jLabel4.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(204, 204, 204)));

		jLabel5.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
		jLabel5.setText("Role");
		jLabel5.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(204, 204, 204)));

		jPasswordField1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
		jPasswordField1
				.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(204, 204, 204)));

		jComboBox1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Student", "Faculty" }));
		jComboBox1
				.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(204, 204, 204)));

		jButton1.setBackground(new java.awt.Color(178, 34, 34));
		jButton1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
		jButton1.setForeground(new java.awt.Color(255, 255, 255));
		jButton1.setText("Login");

		jButton2.setBackground(new java.awt.Color(178, 34, 34));
		jButton2.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
		jButton2.setForeground(new java.awt.Color(255, 255, 255));
		jButton2.setText("Cancel");

		jToggleButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jToggleButton1.setText("Show");

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup().addGroup(jPanel4Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel4Layout.createSequentialGroup().addGap(21, 21, 21).addGroup(jPanel4Layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 127,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 127,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 127,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 127,
										javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(76, 76, 76)
								.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel4Layout.createSequentialGroup()
												.addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE,
														147, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE,
														71, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 147,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 147,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 147,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGroup(jPanel4Layout.createSequentialGroup().addGap(140, 140, 140).addComponent(jLabel2)))
						.addContainerGap(12, Short.MAX_VALUE)));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup().addContainerGap()
						.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 154,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel3).addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
										32, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(27, 27, 27)
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jButton2).addComponent(jButton1))
						.addGap(26, 26, 26)));

		jPanel4.setBounds(300, 20, 460, 390);
		getContentPane().setLayout(null);
		getContentPane().add(jPanel4);

		jLabel1.setIcon(new javax.swing.ImageIcon(
				"C:\\Users\\dipba\\OneDrive\\navigation\\OneDrive\\Desktop\\Images\\ready-back-school_1134-12.jpg")); // NOI18N
		jLabel1.setBounds(0, 0, 810, 540);
		getContentPane().setLayout(null);
		getContentPane().add(jLabel1);

		pack();
		jButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = jTextField1.getText();
				String password = String.valueOf(jPasswordField1.getPassword());
				String role = (String) jComboBox1.getSelectedItem();

				// Call your login validation method here
				if (validateLogin(role, username, password)) {
					JOptionPane.showMessageDialog(null, "Login Successful!");
					// Open the next screen based on role
					switch (role.toLowerCase()) {
					case "admin":
						new AdminLogin().setVisible(true);
						break;
                            case "faculty":
                                new FacultyLogin(username).setVisible(true);
                                break;
					case "student":
						new StudentDashboard(username).setVisible(true);
						break;
					}

					((JFrame) SwingUtilities.getWindowAncestor(jButton1)).dispose();

				} else {
					JOptionPane.showMessageDialog(null, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
					jTextField1.setText("");
					jPasswordField1.setText("");
				}
			}
		});
		jToggleButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				isPasswordVisible = !isPasswordVisible;
				jPasswordField1.setEchoChar(isPasswordVisible ? (char) 0 : '•');
			}
		});

		jButton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	private boolean validateLogin(String role, String username, String password) {
		String table;

		switch (role.toLowerCase()) {
		case "admin":
			table = "admins";
			break;
		case "faculty":
			table = "faculty";
			break;
		case "student":
			table = "students";
			break;
		default:
			return false;
		}

		String query = "SELECT * FROM " + table + " WHERE username=? AND password=?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, username);
			stmt.setString(2, password); // Use hashed password in production

			ResultSet rs = stmt.executeQuery();
			return rs.next();

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}

	}// </editor-fold>

	private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new LoginUI().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JComboBox<String> jComboBox1;
	private javax.swing.JDialog jDialog1;
	private javax.swing.JDialog jDialog2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPasswordField jPasswordField1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JToggleButton jToggleButton1;
	// End of variables declaration
}
