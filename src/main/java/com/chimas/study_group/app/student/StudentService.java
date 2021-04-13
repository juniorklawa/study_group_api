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

        System.out.println(id);

        return (Student) students.get(id);
    }

    public Student findByEmail(String email) {


        return (Student) students.get(email);
    }

    public Student add(String name, String email, String nickname, String ra) {
        int currentId = count.incrementAndGet();

        HashSet<Integer> groupIds = new HashSet<Integer>();
        System.out.println();
        Student student = new Student(currentId, name, email, nickname, ra,groupIds);
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
        Group group = (Group) groups.get(Integer.toString(groupId));

        try {
            HashSet<Integer> groupList = new HashSet<Integer>(student.getGroupIds());
            HashSet<Integer> studentsList = new HashSet<Integer>(group.getStudentIds());


            groupList.add(groupId);
            studentsList.add(studentId);

            student.setGroupIds(groupList);
            group.setStudentIds(studentsList);
        } catch (Exception e){
            new Error(e);
        }



        students.put(studentId, student);
        groups.put(groupId, group);

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
