package com.chimas.study_group.app.group;

import com.chimas.study_group.app.note.Note;

public class Group {
    private String name;
    private String subject;
    private int id;
    private int creatorId;
    private String whatsAppLink;
    private Note[] notes;

    public Group(int id, String name, String subject, int creatorId, String whatsAppLink, Note[] notes) {
        this.name = name;
        this.subject = subject;
        this.id = id;
        this.creatorId = creatorId;
        this.whatsAppLink = whatsAppLink;
        this.notes = notes;
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

    public Note[] getNotes() {
        return notes;
    }

    public void setNotes(Note[] notes) {
        this.notes = notes;
    }
}
