package com.gorkem.models;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    
    private String teacherName;
    private List<Lesson> lessons = new ArrayList<>();

    public Teacher(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
    }
}
