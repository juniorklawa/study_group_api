package com.chimas.study_group.app.group;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


import static com.chimas.study_group.app.App.groups;

public class GroupService {


    private static final AtomicInteger count = new AtomicInteger(0);

    public Group findById(String id) {
        return (Group) groups.get(id);
    }

    public Group add(String name, String subject, String whatsAppLink, int creatorId) {
        int currentId = count.incrementAndGet();

        HashSet<Integer> studentIds = new HashSet<Integer>();
        HashSet<Integer> noteIds = new HashSet<Integer>();

        Group group = new Group(currentId, name, subject, creatorId, whatsAppLink,studentIds,noteIds);
        groups.put(String.valueOf(currentId), group);
        return group;
    }

    public Group update(String id, String name, String email, String nickname, String ra) {

        Group group = (Group) groups.get(id);

        return group;
    }
    public void delete(String id) {
        groups.remove(id);
    }

    public List findAll() {
        return new ArrayList<>(groups.values());
    }

    public GroupService() {
    }

}
