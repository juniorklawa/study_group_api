package com.chimas.study_group.app.group;

import com.chimas.study_group.app.note.Note;

import java.util.HashSet;

public class Group {
    private String name;
    private String subject;
    private int id;
    private int creatorId;
    private String whatsAppLink;
    private HashSet<Integer> studentIds;
    private HashSet<Integer> noteIds;
    private HashSet<Integer> videoIds;

    public Group(int id, String name, String subject, int creatorId, String whatsAppLink, HashSet<Integer> studentIds,HashSet<Integer> noteIds, HashSet<Integer> videoIds) {
        this.name = name;
        this.subject = subject;
        this.id = id;
        this.creatorId = creatorId;
        this.whatsAppLink = whatsAppLink;
        this.studentIds = studentIds;
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

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public String getWhatsAppLink() {
        return whatsAppLink;
    }

    public void setWhatsAppLink(String whatsAppLink) {
        this.whatsAppLink = whatsAppLink;
    }


    public HashSet<Integer> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(HashSet<Integer> studentIds) {
        this.studentIds = studentIds;
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
