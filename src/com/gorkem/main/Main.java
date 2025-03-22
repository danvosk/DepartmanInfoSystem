package com.gorkem.main;

import com.gorkem.models.Course;
import com.gorkem.models.Student;
import com.gorkem.models.Teacher;
import com.gorkem.services.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentService studentService = new StudentService();
        TeacherService teacherService = new TeacherService();
        CourseService courseService = new CourseService();
        StudentCourseService studentCourseService = new StudentCourseService();

        while (true) {
            System.out.println("\n--- Department Information System ---");
            System.out.println("1. Student Operations");
            System.out.println("2. Teacher Operations");
            System.out.println("3. Course Operations");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    studentOperations(studentService, studentCourseService, scanner);
                    break;
                case 2:
                    teacherOperations(teacherService, scanner);
                    break;
                case 3:
                    courseOperations(courseService, teacherService, studentCourseService, scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void studentOperations(StudentService studentService, StudentCourseService studentCourseService, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Student Operations ---");
            System.out.println("1. Add Student");
            System.out.println("2. List Students and Their Courses");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. Go Back");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter first name: ");
                String firstName = scanner.nextLine();
                System.out.print("Enter last name: ");
                String lastName = scanner.nextLine();
                studentService.addStudent(new Student(0, firstName, lastName));
                System.out.println("Student added successfully!");
            } else if (choice == 2) {
                studentService.getAllStudentsWithCourses();
            } else if (choice == 3) {
                System.out.print("Enter Student ID: ");
                int studentId = scanner.nextInt();
                System.out.print("Enter Course ID: ");
                int courseId = scanner.nextInt();
                studentCourseService.enrollStudentInCourse(studentId, courseId);
                System.out.println("Student enrolled in course!");
            } else if (choice == 4) {
                return;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private static void teacherOperations(TeacherService teacherService, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Teacher Operations ---");
            System.out.println("1. Add Teacher");
            System.out.println("2. List Teachers and Their Courses");
            System.out.println("3. Update Student Grades (Midterm & Final)");
            System.out.println("4. Go Back");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter first name: ");
                String firstName = scanner.nextLine();
                System.out.print("Enter last name: ");
                String lastName = scanner.nextLine();
                teacherService.addTeacher(new Teacher(0, firstName, lastName));
                System.out.println("Teacher added successfully!");
            } else if (choice == 2) {
                teacherService.getAllTeachersWithCourses();
            } else if (choice == 3) {
                System.out.print("Enter Student ID: ");
                int studentId = scanner.nextInt();
                System.out.print("Enter Course ID: ");
                int courseId = scanner.nextInt();
                System.out.print("Enter Midterm Grade: ");
                int midterm = scanner.nextInt();
                System.out.print("Enter Final Exam Grade: ");
                int finalExam = scanner.nextInt();
                boolean isUpdated = teacherService.updateStudentGrades(studentId, courseId, midterm, finalExam);
                if (isUpdated) {
                    System.out.println("Student grades updated successfully!");
                } else {
                    System.out.println("Failed to update grades.");
                }
            } else if (choice == 4) {
                return;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private static void courseOperations(CourseService courseService, TeacherService teacherService, StudentCourseService studentCourseService, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Course Operations ---");
            System.out.println("1. List Courses");
            System.out.println("2. Add New Course");
            System.out.println("3. Go Back");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                List<Course> courses = courseService.getAllCourses();
                for (Course course : courses) {
                    System.out.println(course);
                }
            } else if (choice == 2) {
                System.out.print("Enter Course Name: ");
                String courseName = scanner.nextLine();

                System.out.println("Available Teachers:");
                teacherService.getAllTeachersWithCourses();
                System.out.print("Select Teacher ID: ");
                int teacherId = scanner.nextInt();

                courseService.addCourse(courseName, teacherId);
                System.out.println("Course added successfully!");
            } else if (choice == 3) {
                return;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}
