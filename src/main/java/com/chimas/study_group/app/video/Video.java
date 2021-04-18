package com.chimas.study_group.app.video;

import com.chimas.study_group.app.student.Student;

public class Video {

    private int id;
    private String title;
    private String url;
    private String creatorEmail;
    private Student creator;


    public Video(int id, String title, String url, String creatorEmail, Student creator) {
        this.id = id;
        this.title = title;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
