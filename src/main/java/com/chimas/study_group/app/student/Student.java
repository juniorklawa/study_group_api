package com.chimas.study_group.app.student;

import com.chimas.study_group.app.user.User;

import java.util.HashSet;

public class Student extends User {

    private String ra;
    private HashSet<String> groupIds;

    public Student(String id, String name, String email, String ra, HashSet<String> groupIds) {
        super(email,id, name);
        this.groupIds = groupIds;
        this.ra = ra;
    }


    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }


    public HashSet<String> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(HashSet<String> groupIds) {
        this.groupIds = groupIds;
    }
}
