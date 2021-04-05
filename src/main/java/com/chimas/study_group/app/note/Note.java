package com.chimas.study_group.app.note;

import com.chimas.study_group.app.student.Student;

public class Note {

    private String title;
    private String description;
    private Student student;


    public Note(String title, String description, Student student) {
        this.title = title;
        this.description = description;
        this.student = student;
    }
}
