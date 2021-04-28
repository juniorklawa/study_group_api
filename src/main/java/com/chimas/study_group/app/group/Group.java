package com.chimas.study_group.app.group;

import com.chimas.study_group.app.note.Note;

import java.util.HashSet;
import java.util.List;

public class Group {
    private String name;
    private String subject;
    private String id;
    private String creatorEmail;
    private String whatsAppLink;
    private List<String> studentEmails;
    private List<String> noteIds;
    private List<String> videoIds;

    public Group(String id, String name, String subject, String creatorEmail, String whatsAppLink, List<String> studentEmails,List<String> noteIds, List<String> videoIds) {
        this.name = name;
        this.subject = subject;
        this.id = id;
        this.creatorEmail = creatorEmail;
        this.whatsAppLink = whatsAppLink;
        this.studentEmails = studentEmails;
        this.noteIds = noteIds;
        this.videoIds = videoIds;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatorEmail() {
        return creatorEmail;
    }

    public void setCreatorEmail(String creatorEmail) {
        this.creatorEmail = creatorEmail;
    }

    public String getWhatsAppLink() {
        return whatsAppLink;
    }

    public void setWhatsAppLink(String whatsAppLink) {
        this.whatsAppLink = whatsAppLink;
    }


    public List<String> getStudentEmails() {
        return studentEmails;
    }

    public void setStudentEmails(List<String> studentEmails) {
        this.studentEmails = studentEmails;
    }

    public List<String> getNoteIds() {
        return noteIds;
    }

    public void setNoteIds(List<String> noteIds) {
        this.noteIds = noteIds;
    }

    public List<String> getVideoIds() {
        return videoIds;
    }

    public void setVideoIds(List<String> videoIds) {
        this.videoIds = videoIds;
    }
}
