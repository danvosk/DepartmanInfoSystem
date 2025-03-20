package com.gorkem.models;

public class Course {
    private int id;
    private String courseName;
    private int teacherId;

    public Course(int id, String courseName, int teacherId) {
        this.id = id;
        this.courseName = courseName;
        this.teacherId = teacherId;
    }

    public int getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getTeacherId() {
        return teacherId;
    }

    @Override
    public String toString() {
        return courseName;
    }
}
