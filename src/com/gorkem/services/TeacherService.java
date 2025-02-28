package com.gorkem.services;

import com.gorkem.main.Main;
import com.gorkem.models.Teacher;
import com.gorkem.models.Lesson;

public class TeacherService {
    
    public boolean addTeacher(Teacher teacher) {
        return Main.teacherList.add(teacher);
    }

    public void assignLessonToTeacher(Teacher teacher, Lesson lesson) {
        teacher.addLesson(lesson);
    }
}
