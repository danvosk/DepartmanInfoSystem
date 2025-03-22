package com.gorkem.services;

import com.gorkem.database.DatabaseConnection;

import java.sql.*;

public class StudentCourseService {

    // ✅ Öğrenciyi derse kaydetme
    public boolean enrollStudentInCourse(int studentId, int courseId) {
        String query = "INSERT INTO student_courses (student_id, course_id, midterm, final_exam) VALUES (?, ?, 0, 0)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Check if student exists
            PreparedStatement checkStudent = conn.prepareStatement("SELECT COUNT(*) FROM students WHERE id = ?");
            checkStudent.setInt(1, studentId);
            ResultSet rsStudent = checkStudent.executeQuery();
            rsStudent.next();
            if (rsStudent.getInt(1) == 0) {
                System.out.println("Student with ID " + studentId + " does not exist.");
                return false;
            }

            // Check if course exists
            PreparedStatement checkCourse = conn.prepareStatement("SELECT COUNT(*) FROM courses WHERE id = ?");
            checkCourse.setInt(1, courseId);
            ResultSet rsCourse = checkCourse.executeQuery();
            rsCourse.next();
            if (rsCourse.getInt(1) == 0) {
                System.out.println("Course with ID " + courseId + " does not exist.");
                return false;
            }

            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}