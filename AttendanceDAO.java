package com.UI;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {

    private static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/StudentManagementSystem"; // Replace with your DB URL
        String user = "root"; // Replace with your DB user
        String password = "dip220804"; // Replace with your DB password
        return DriverManager.getConnection(url, user, password);
    }

    public static List<String> getStudentCourses(int studentId) {
        List<String> courses = new ArrayList<>();
        String query = "SELECT course_code FROM student_courses WHERE student_id = ?";

        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, studentId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                courses.add(rs.getString("course_code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    public static List<String[]> getStudentAttendance(int studentId, String courseCode, String startDate, String endDate) {
        List<String[]> data = new ArrayList<>();
        String query = "SELECT date, course_code, student_id, status FROM attendance " +
                       "WHERE student_id = ? AND course_code = ? AND date BETWEEN ? AND ? ORDER BY date";

        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, studentId);
            pst.setString(2, courseCode);
            pst.setString(3, startDate);
            pst.setString(4, endDate);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String[] row = new String[4];
                row[0] = rs.getString("date");
                row[1] = rs.getString("course_code");
                row[2] = rs.getString("student_id");
                row[3] = rs.getString("status");
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
} 
