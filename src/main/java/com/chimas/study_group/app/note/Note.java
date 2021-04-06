package com.chimas.study_group.app.note;

import com.chimas.study_group.app.student.Student;

public class Note {

    private int id;
    private String title;
    private String description;
    private int creatorId;


    public Note(int id, String title, String description, int creatorId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creatorId = creatorId;
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

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }
}
