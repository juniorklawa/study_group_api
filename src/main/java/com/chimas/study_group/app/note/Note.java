package com.chimas.study_group.app.note;

import com.chimas.study_group.app.student.Student;

public class Note {

    private int id;
    private String title;
    private String description;
    private String creatorEmail;
    private Student creator;


    public Note(int id, String title, String description, String creatorEmail, Student creator) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creatorEmail = creatorEmail;
        this.creator = creator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatorEmail() {
        return creatorEmail;
    }

    public void setCreatorEmail(String creatorEmail) {
        this.creatorEmail = creatorEmail;
    }

    public Student getCreator() {
        return creator;
    }

    public void setCreator(Student creator) {
        this.creator = creator;
    }
}
