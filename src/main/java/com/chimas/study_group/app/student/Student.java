package com.chimas.study_group.app.student;

import com.chimas.study_group.app.user.User;

import java.util.HashSet;
import java.util.List;

public class Student extends User {

    private String ra;
    private List<String> groupIds;

    public Student(String id, String name, String email, String ra, List<String> groupIds) {
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


    public List<String> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<String> groupIds) {
        this.groupIds = groupIds;
    }
}
