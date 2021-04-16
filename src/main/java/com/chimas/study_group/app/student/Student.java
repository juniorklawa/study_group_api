package com.chimas.study_group.app.student;

import com.chimas.study_group.app.user.User;

import java.util.HashSet;

public class Student extends User {

    private String ra;
    private HashSet<Integer> groupIds;

    public Student(int id, String name, String email, String ra, HashSet<Integer> groupIds) {
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

    public HashSet<Integer> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(HashSet<Integer> groupIds) {
        this.groupIds = groupIds;
    }
}
