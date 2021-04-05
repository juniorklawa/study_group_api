package com.chimas.study_group.app.group;
import com.chimas.study_group.app.note.Note;
import com.chimas.study_group.app.student.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class GroupService {


    public static Map groups = new HashMap<>();
    private static final AtomicInteger count = new AtomicInteger(0);

    public Group findById(String id) {
        return (Group) groups.get(id);
    }

    public Group add(String name, String subject, String whatsAppLink, int creatorId) {
        int currentId = count.incrementAndGet();
        Note[] notes = {};
        Group group = new Group(currentId, name, subject, creatorId, whatsAppLink,notes);
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
