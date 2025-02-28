package com.gorkem.main;

import com.gorkem.models.Teacher;
import com.gorkem.models.Student;
import com.gorkem.models.Lesson;
import com.gorkem.services.StudentService;
import com.gorkem.services.TeacherService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    private static Scanner scanner = new Scanner(System.in); 
        
    public static List<Student> studentList = new ArrayList<>();
    public static List<Teacher> teacherList = new ArrayList<>();
    public static List<Lesson> lessonList = new ArrayList<>();

    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        TeacherService teacherService = new TeacherService();

        // ✅ Öğrencileri ekle
        Student student1 = new Student("Gorkem Karagoz");
        Student student2 = new Student("Ramazan Demir");
        Student student3 = new Student("Bugra Toklu");

        studentService.addStudent(student1);
        studentService.addStudent(student2);
        studentService.addStudent(student3);

        // ✅ Öğretmenleri ekle
        Teacher teacher1 = new Teacher("Yusuf Ozcevik");
        Teacher teacher2 = new Teacher("Osman Altay");

        teacherService.addTeacher(teacher1);
        teacherService.addTeacher(teacher2);

        // ✅ Dersleri oluştur ve öğretmenlere ata
        Lesson lesson1 = new Lesson("Veri Yapilari", 80, 90, teacher1);
        Lesson lesson2 = new Lesson("Veri Madenciligi", 75, 85, teacher2);

        lessonList.add(lesson1);
        lessonList.add(lesson2);

        teacherService.assignLessonToTeacher(teacher1, lesson1);
        teacherService.assignLessonToTeacher(teacher2, lesson2);

        // ✅ Dersleri öğrencilere ekle
        studentService.enrollStudentInLesson(student1, lesson1);
        studentService.enrollStudentInLesson(student1, lesson2);
        studentService.enrollStudentInLesson(student2, lesson1);
        studentService.enrollStudentInLesson(student3, lesson2);

        selectionScreen(); 
    }

    public static void selectionScreen() { 
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n--- Bolum Bilgi Sistemi ---");
            System.out.println("1. Ogrenci islemleri");
            System.out.println("2. Ogretmen islemleri");
            System.out.println("3. Ders islemleri");
            System.out.println("4. Cikis");
            System.out.print("Bir secim yapin: ");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    studentOperations();
                    break;
                case 2:
                    teacherOperations();
                    break;
                case 3:
                    lessonOperations();
                    break;
                case 4:
                    isRunning = false;
                    System.out.println("Sistemden çıkılıyor...");
                    break;
                default:
                    System.out.println("Lütfen doğru işlemi seçiniz...");
            }
        }
    }

    public static void studentOperations() {
        while (true) {
            System.out.println("\n--- Ogrenci Islemleri ---");
            System.out.println("1. Ogrencileri Listele");
            System.out.println("2. Geri Git");
            System.out.print("Bir secim yapin: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                listAllStudentsWithLessons();
            } else if (choice == 2) {
                break;
            } else {
                System.out.println("Gecersiz secim!");
            }
        }
    }

    public static void teacherOperations() {
        while (true) {
            System.out.println("\n--- Ogretmen Islemleri ---");
            System.out.println("1. Ogretmenleri Listele");
            System.out.println("2. Geri Git");
            System.out.print("Bir secim yapin: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                listAllTeachersWithLessons();
            } else if (choice == 2) {
                break;
            } else {
                System.out.println("Gecersiz secim!");
            }
        }
    }

    public static void lessonOperations() {
        while (true) {
            System.out.println("\n--- Ders Islemleri ---");
            System.out.println("1. Dersleri Listele");
            System.out.println("2. Geri Git");
            System.out.print("Bir secim yapin: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                listAllLessonsWithStudents();
            } else if (choice == 2) {
                break;
            } else {
                System.out.println("Gecersiz secim!");
            }
        }
    }

    public static void listAllStudentsWithLessons() {
        System.out.println("\n--- Ogrenciler ve Aldiklari Dersler ---");
        for (Student student : studentList) {
            System.out.println(student.getStudentName() + " aldigi dersler:");
            for (Lesson lesson : student.getLessons()) {
                System.out.println(" - " + lesson.getName() + 
                    " (Vize: " + lesson.getMidtermResult() + 
                    ", Final: " + lesson.getFinalResult() + ")");
            }
        }
    }

    public static void listAllTeachersWithLessons() {
        System.out.println("\n--- Ogretmenler ve Verdikleri Dersler ---");
        for (Teacher teacher : teacherList) {
            System.out.println(teacher.getTeacherName() + " verdigi dersler:");
            for (Lesson lesson : teacher.getLessons()) {
                System.out.println(" - " + lesson.getName());
            }
        }
    }

    public static void listAllLessonsWithStudents() {
        System.out.println("\n--- Dersler ve Kayitli Ogrenciler ---");
        for (Lesson lesson : lessonList) {
            System.out.println(lesson.getName() + " dersine kayitli ogrenciler:");
            for (Student student : lesson.getStudents()) {
                System.out.println(" - " + student.getStudentName());
            }
        }
    }
}
