package com.gorkem.models;

import java.util.ArrayList;
import java.util.List;

public class Student {
    
    private String studentName;
    private List<Lesson> lessons = new ArrayList<>();

    public Student(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
    }
}
