package com.chimas.study_group.app.video;

import com.chimas.study_group.app.group.Group;
import com.chimas.study_group.app.note.Note;
import com.chimas.study_group.app.student.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.chimas.study_group.app.App.*;
import static com.chimas.study_group.app.App.videos;

public class VideoService {

    private static final AtomicInteger count = new AtomicInteger(0);

    public Video findById(String id) {
        return (Video) videos.get(id);
    }

    public Video addVideo(String title, String url, String creatorEmail, int groupId) {

        int currentId = count.incrementAndGet();

        Group group = (Group) groups.get(Integer.toString(groupId));

        Student student = (Student) students.get(creatorEmail);

        try {
            HashSet<Integer> noteList = new HashSet<Integer>(group.getNoteIds());
            noteList.add(currentId);
            group.setNoteIds(noteList);


            HashSet<Integer> videoList = new HashSet<Integer>(group.getVideoIds());
            videoList.add(currentId);
            group.setVideoIds(noteList);

        } catch (Exception e) {
            new Error(e);
        }

        Video video = new Video(currentId, title, url, creatorEmail, student);
        videos.put(String.valueOf(currentId), video);

        return video;
    }


    public void delete(int id) {
        videos.remove(String.valueOf(id));
    }

    public List findAll() {
        return new ArrayList<>(videos.values());
    }

    public VideoService() {
    }
}
