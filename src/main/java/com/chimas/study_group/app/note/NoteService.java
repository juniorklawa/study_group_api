package com.chimas.study_group.app.note;


import com.chimas.study_group.app.group.Group;
import com.chimas.study_group.app.student.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.chimas.study_group.app.App.*;

public class NoteService {

    private static final AtomicInteger count = new AtomicInteger(0);

    public Note findById(String id) {
        return (Note) notes.get(id);
    }

    public Note addNote(String title, String description, int creatorId, int groupId) {


        int currentId = count.incrementAndGet();

        Group group = (Group) groups.get(Integer.toString(groupId));

        try {
            HashSet<Integer> noteList = new HashSet<Integer>(group.getNoteIds());
            noteList.add(currentId);
            group.setNoteIds(noteList);
        } catch (Exception e){
            new Error(e);
        }



        Note note = new Note(currentId,title,description,creatorId);
        notes.put(String.valueOf(currentId), note);





        return note;
    }






    public void delete(String id) {
        notes.remove(id);
    }

    public List findAll() {
        return new ArrayList<>(notes.values());
    }

    public NoteService() {
    }
}
