package com.chimas.study_group.app.group;

import com.chimas.study_group.app.note.Note;

import java.util.HashSet;

public class Group {
    private String name;
    private String subject;
    private int id;
    private String creatorEmail;
    private String whatsAppLink;
    private HashSet<String> studentEmails;
    private HashSet<Integer> noteIds;
    private HashSet<Integer> videoIds;

    public Group(int id, String name, String subject, String creatorEmail, String whatsAppLink, HashSet<String> studentEmails,HashSet<Integer> noteIds, HashSet<Integer> videoIds) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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


    public HashSet<String> getStudentEmails() {
        return studentEmails;
    }

    public void setStudentEmails(HashSet<String> studentEmails) {
        this.studentEmails = studentEmails;
    }

    public HashSet<Integer> getNoteIds() {
        return noteIds;
    }

    public HashSet<Integer> getVideoIds() {
        return videoIds;
    }

    public void setVideoIds(HashSet<Integer> videoIds) {
        this.videoIds = videoIds;
    }

    public void setNoteIds(HashSet<Integer> noteIds) {
        this.noteIds = noteIds;
    }
}
