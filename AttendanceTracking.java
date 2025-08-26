package com.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class AttendanceTracking extends javax.swing.JFrame {

    private JDatePickerImpl fromDatePicker;
    private JDatePickerImpl toDatePicker;

    public AttendanceTracking() {
        initComponents();
        setSize(810, 560);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        // Initialize JDatePicker date choosers
        UtilDateModel modelFrom = new UtilDateModel();
        UtilDateModel modelTo = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        JDatePanelImpl datePanelFrom = new JDatePanelImpl(modelFrom, p);
        JDatePanelImpl datePanelTo = new JDatePanelImpl(modelTo, p);

        fromDatePicker = new JDatePickerImpl(datePanelFrom, new DateLabelFormatter());
        toDatePicker = new JDatePickerImpl(datePanelTo, new DateLabelFormatter());

        jLabel11.setText("jLabel11");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("From Date");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("To Date");

        jButton1.setBackground(new java.awt.Color(153, 153, 153));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Clear Data");

        jButton2.setBackground(new java.awt.Color(153, 153, 153));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("View");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                        .addComponent(fromDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(67, 67, 67)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                        .addComponent(toDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(34, 34, 34)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel1)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fromDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(toDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel1.setBounds(110, 10, jPanel1.getPreferredSize().width, 120);
        getContentPane().add(jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String[] {
                "Sr. No.", "Date", "Course", "Status"
            }
        ) {
            Class[] types = new Class[] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel2.setBounds(330, 180, jPanel2.getPreferredSize().width, 250);
        getContentPane().add(jPanel2);

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\dipba\\OneDrive\\navigation\\OneDrive\\Desktop\\Images\\ready-back-school_1134-12.jpg"));
        jLabel2.setBounds(0, 0, 800, 540);
        getContentPane().add(jLabel2);
       
        jButton2.addActionListener(evt -> {
            // Fetch selected dates
            java.util.Date fromDate = (java.util.Date) fromDatePicker.getModel().getValue();
            java.util.Date toDate = (java.util.Date) toDatePicker.getModel().getValue();
            
            if (fromDate != null && toDate != null) {
                // Fetch and display attendance data
                loadAttendanceData(fromDate, toDate);
            } else {
                JOptionPane.showMessageDialog(this, "Please select both From and To dates.");
            }
        });


        pack();
    }
    
    private void loadAttendanceData(java.util.Date fromDate, java.util.Date toDate) {
        // Format the dates to match the format stored in the database
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedFromDate = sdf.format(fromDate);
        String formattedToDate = sdf.format(toDate);

        // Assuming you have a method to fetch data from the database
        List<Attendance> attendanceList = fetchAttendanceData(formattedFromDate, formattedToDate);

        // Ensure table updates on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            // Clear the previous data in the table
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            // Populate the table with new data
            for (Attendance attendance : attendanceList) {
                model.addRow(new Object[]{
                    attendance.getAttendanceId(),
                    attendance.getDate(),
                    attendance.getCourseCode(),
                    attendance.getStatus()
                });
            }
        });
    }

    private List<Attendance> fetchAttendanceData(String fromDate, String toDate) {
        List<Attendance> attendanceList = new ArrayList<>();
        // Query now includes the WHERE clause for date filtering
        String query = "SELECT attendance_id, student_id, course_code, date, status FROM attendance WHERE date BETWEEN ? AND ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the parameters for the date range
            stmt.setString(1, fromDate);
            stmt.setString(2, toDate);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int attendanceId = rs.getInt("attendance_id");  // Correct column name
                int studentId = rs.getInt("student_id");        // Correct column name
                String courseCode = rs.getString("course_code"); // Correct column name
                java.sql.Date date = rs.getDate("date");         // Use java.sql.Date for proper date handling
                String status = rs.getString("status");          // Correct column name

                // Convert java.sql.Date to String if you need to display it as a String
                String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);

                // Add the attendance object to the list
                attendanceList.add(new Attendance(attendanceId, studentId, courseCode, formattedDate, status));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Optionally log the error or show a message to the user
        }

        return attendanceList;
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
            java.util.logging.Logger.getLogger(AttendanceTracking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new AttendanceTracking().setVisible(true));
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration
}
