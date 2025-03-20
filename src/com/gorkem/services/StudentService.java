package com.gorkem.services;

import com.gorkem.database.DatabaseConnection;
import com.gorkem.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    // ✅ Öğrenci ekleme (Başarı mesajı eklendi)
    public boolean addStudent(Student student) {
        String query = "INSERT INTO students (first_name, last_name) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Student successfully added: " + student.getFirstName() + " " + student.getLastName());
                return true;
            } else {
                System.out.println("Failed to add student.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Öğrencileri dersleriyle birlikte listeleme
    public void getAllStudentsWithCourses() {
        String query = "SELECT s.id, s.first_name, s.last_name, c.course_name, sc.midterm, sc.final_exam " +
                       "FROM students s " +
                       "LEFT JOIN student_courses sc ON s.id = sc.student_id " +
                       "LEFT JOIN courses c ON sc.course_id = c.id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String courseName = rs.getString("course_name");
                int midterm = rs.getInt("midterm");
                int finalExam = rs.getInt("final_exam");

                System.out.println(firstName + " " + lastName + " enrolled in:");
                if (courseName != null) {
                    System.out.println("- " + courseName + " (Midterm: " + midterm + ", Final: " + finalExam + ")");
                } else {
                    System.out.println("- No enrolled courses.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
