package com.gorkem.models;

import java.util.ArrayList;
import java.util.List;

public class Lesson {
    
    private String name;
    private int midtermResult;
    private int finalResult;
    private Teacher teacher;
    private List<Student> students = new ArrayList<>();

    public Lesson(String name, int midtermResult, int finalResult, Teacher teacher) {
        this.name = name;
        this.midtermResult = midtermResult;
        this.finalResult = finalResult;
        this.teacher = teacher;
        
    }

    public String getName() {
        return name;
    }

    public int getMidtermResult() {
        return midtermResult;
    }

    public int getFinalResult() {
        return finalResult;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }
}
