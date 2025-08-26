package com.UI;

public class Attendance {
    private int attendanceId;  // Changed from serialNo to attendanceId
    private int studentId;     // Added studentId field
    private String courseCode; // Changed from course to courseCode
    private String date;       // Date will remain as String
    private String status;

    // Updated constructor to match the new parameters
    public Attendance(int attendanceId, int studentId, String courseCode, String date, String status) {
        this.attendanceId = attendanceId;
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.date = date;
        this.status = status;
    }

    // Getters for all fields
    public int getAttendanceId() {
        return attendanceId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }
}
