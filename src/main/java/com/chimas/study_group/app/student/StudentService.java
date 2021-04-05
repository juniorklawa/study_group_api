package com.chimas.study_group.app.student;

import com.chimas.study_group.app.group.Group;
import com.chimas.study_group.app.note.Note;
import com.chimas.study_group.app.user.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class StudentService {

    public static Map students = new HashMap<>();
    public static Map groups = new HashMap<>();
    private static final AtomicInteger count = new AtomicInteger(0);

    public Student findById(String id) {
        return (Student) students.get(id);
    }

    public Student add(String name, String email, String nickname, String ra) {
        int currentId = count.incrementAndGet();

        HashSet<Integer> groupIds = new HashSet<Integer>();

        Student student = new Student(currentId, name, email, nickname, ra,groupIds);
        students.put(String.valueOf(currentId), student);
        return student;
    }

    public Student update(String id, String name, String email, String nickname, String ra) {

        Student student = (Student) students.get(id);
        if (name != null) {
            student.setName(name);
        }

        if (email != null) {
            student.setEmail(email);
        }

        if (nickname != null) {
            student.setNickname(nickname);
        }

        if (ra != null) {
            student.setNickname(ra);
        }

        students.put(id, student);

        return student;

    }



    public Student enterGroup(int groupId, int studentId) {
        Student student = (Student) students.get(Integer.toString(studentId));

        HashSet<Integer> groupList = new HashSet<Integer>(student.getGroupIds());
        groupList.add(groupId);
        ArrayList<Integer> convertedGroupList = new ArrayList<Integer>(groupList);

        student.setGroupIds(groupList);
        students.put(studentId, student);

        return student;

    }


    public void delete(String id) {
        students.remove(id);
    }

    public List findAll() {
        return new ArrayList<>(students.values());
    }

    public StudentService() {
    }
}
