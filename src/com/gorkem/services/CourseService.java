package com.gorkem.services;

import com.gorkem.database.DatabaseConnection;
import com.gorkem.models.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseService {

    // ✅ Ders ekleme
    public boolean addCourse(String courseName, int teacherId) {
        String query = "INSERT INTO courses (course_name, teacher_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, courseName);
            stmt.setInt(2, teacherId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Dersleri listeleme
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM courses";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String courseName = rs.getString("course_name");
                int teacherId = rs.getInt("teacher_id");
                courses.add(new Course(id, courseName, teacherId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
}
