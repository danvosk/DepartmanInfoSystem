package com.gorkem.services;

import com.gorkem.database.DatabaseConnection;
import com.gorkem.models.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherService {

    // ✅ Öğretmen ekleme (Başarı mesajı eklendi)
    public boolean addTeacher(Teacher teacher) {
        String query = "INSERT INTO teachers (first_name, last_name) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, teacher.getFirstName());
            stmt.setString(2, teacher.getLastName());
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Teacher successfully added: " + teacher.getFirstName() + " " + teacher.getLastName());
                return true;
            } else {
                System.out.println("Failed to add teacher.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Öğretmenleri ve verdikleri dersleri listeleme
    public void getAllTeachersWithCourses() {
        String query = "SELECT t.first_name, t.last_name, c.course_name " +
                       "FROM teachers t " +
                       "LEFT JOIN courses c ON t.id = c.teacher_id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String courseName = rs.getString("course_name");

                System.out.println(firstName + " " + lastName + " teaches:");
                if (courseName != null) {
                    System.out.println("- " + courseName);
                } else {
                    System.out.println("- No assigned courses.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Öğretmenin öğrencinin notlarını güncellemesi
    public boolean updateStudentGrades(int studentId, int courseId, int midterm, int finalExam) {
        String query = "UPDATE student_courses SET midterm = ?, final_exam = ? WHERE student_id = ? AND course_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, midterm);
            stmt.setInt(2, finalExam);
            stmt.setInt(3, studentId);
            stmt.setInt(4, courseId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
