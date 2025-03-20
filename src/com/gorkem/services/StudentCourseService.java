package com.gorkem.services;

import com.gorkem.database.DatabaseConnection;

import java.sql.*;

public class StudentCourseService {

    // ✅ Öğrenciyi derse kaydetme
    public boolean enrollStudentInCourse(int studentId, int courseId) {
        String query = "INSERT INTO student_courses (student_id, course_id, midterm, final_exam) VALUES (?, ?, 0, 0)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
