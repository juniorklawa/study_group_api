package com.chimas.study_group.app.group;
import java.util.HashSet;

public class Group {
    private String name;
    private String subject;
    private String id;
    private String creatorEmail;
    private String whatsAppLink;
    private HashSet<String> studentEmails;
    private HashSet<String> noteIds;
    private HashSet<String> videoIds;

    public Group(String id, String name, String subject, String creatorEmail, String whatsAppLink, HashSet<String> studentEmails,HashSet<String> noteIds, HashSet<String> videoIds) {
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


    public HashSet<String> getStudentEmails() {
        return studentEmails;
    }

    public void setStudentEmails(HashSet<String> studentEmails) {
        this.studentEmails = studentEmails;
    }

    public HashSet<String> getNoteIds() {
        return noteIds;
    }

    public void setNoteIds(HashSet<String> noteIds) {
        this.noteIds = noteIds;
    }

    public HashSet<String> getVideoIds() {
        return videoIds;
    }

    public void setVideoIds(HashSet<String> videoIds) {
        this.videoIds = videoIds;
    }
}
