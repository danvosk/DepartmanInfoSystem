package com.gorkem.services;

import com.gorkem.main.Main;
import com.gorkem.models.Student;
import com.gorkem.models.Lesson;

public class StudentService {
    
    public boolean addStudent(Student student) {
        return Main.studentList.add(student);
    }

    public void enrollStudentInLesson(Student student, Lesson lesson) {
        student.addLesson(lesson);
        lesson.addStudent(student);
    }
}
