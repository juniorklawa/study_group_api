package com.chimas.study_group.app.video;

import com.chimas.study_group.app.student.Student;

public class Video {

    private int id;
    private String title;
    private String url;
    private int creatorId;
    private Student creator;


    public Video(int id, String title, String url, int creatorId, Student creator) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.creatorId = creatorId;
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

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public Student getCreator() {
        return creator;
    }

    public void setCreator(Student creator) {
        this.creator = creator;
    }
}
