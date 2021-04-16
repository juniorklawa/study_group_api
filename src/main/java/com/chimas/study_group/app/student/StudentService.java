package com.chimas.study_group.app.student;

import com.chimas.study_group.app.group.Group;
import com.chimas.study_group.app.note.Note;
import com.chimas.study_group.app.user.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.chimas.study_group.app.App.students;
import static com.chimas.study_group.app.App.groups;

public class StudentService {


    private static final AtomicInteger count = new AtomicInteger(0);

    public Student findById(String id) {
        return (Student) students.get(id);
    }

    public Student findByEmail(String email) {


        return (Student) students.get(email);
    }

    public Student add(String name, String email, String ra) {
        int currentId = count.incrementAndGet();

        HashSet<Integer> groupIds = new HashSet<Integer>();
        Student student = new Student(currentId, name, email, ra,groupIds);
        students.put(email, student);
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


        students.put(id, student);

        return student;

    }



    public Student enterGroup(int groupId, String studentEmail) {

        Student student = (Student) students.get(studentEmail);
        Group group = (Group) groups.get(Integer.toString(groupId));

        try {
            HashSet<Integer> groupList = new HashSet<Integer>(student.getGroupIds());
            HashSet<String> studentsList = new HashSet<String>(group.getStudentEmails());


            groupList.add(groupId);
            studentsList.add(studentEmail);

            student.setGroupIds(groupList);
            group.setStudentEmails(studentsList);
        } catch (Exception e){
            new Error(e);
        }
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
