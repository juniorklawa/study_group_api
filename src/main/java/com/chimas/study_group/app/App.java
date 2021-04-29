package com.chimas.study_group.app;


import static spark.Spark.*;

import com.chimas.study_group.app.group.Group;
import com.chimas.study_group.app.group.GroupService;
import com.chimas.study_group.app.note.Note;
import com.chimas.study_group.app.note.NoteService;
import com.chimas.study_group.app.student.Student;
import com.chimas.study_group.app.student.StudentService;
import com.chimas.study_group.app.video.Video;
import com.chimas.study_group.app.video.VideoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.*;

import java.util.HashSet;
import java.util.List;

public class App {


    public static String mongoUri = "mongodb+srv://<YOUR_NICKNAME>:<YOUR_PASSWORD>@cluster0.jxelo.gcp.mongodb.net/easymeet?retryWrites=true&w=majority";

    private static StudentService studentService = new StudentService();
    private static GroupService groupService = new GroupService();
    private static NoteService noteService = new NoteService();
    private static VideoService videoService = new VideoService();
    private static ObjectMapper om = new ObjectMapper();


    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 8080;
    }

    public static void main(String[] args) {

        port(getHerokuAssignedPort());

        options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));


        get("/", (request, response) -> "API Grupo de Estudos");


        //STUDENT

        // Add an Student
        post("/student/add", (request, response) -> {
            JSONObject responseObject = new JSONObject(request.body());

            String name = responseObject.getString("name");
            String email = responseObject.getString("email");
            String ra = responseObject.getString("ra");

            Student student = studentService.add(name, email, ra);
            response.status(201);
            return om.writeValueAsString(student);
        });

        // Get User by ID
        post("/auth", (request, response) -> {

            JSONObject responseObject = new JSONObject(request.body());

            String email = responseObject.getString("email");

            Student student = studentService.findByEmail(email);
            if (student != null) {
                return om.writeValueAsString(student);
            } else {
                response.status(404); // 404 Not found
                return om.writeValueAsString("student not found");
            }
        });


        //GROUPS

        // Add a group
        post("/group/add", (request, response) -> {
            JSONObject responseObject = new JSONObject(request.body());

            String name = responseObject.getString("name");
            String subject = responseObject.getString("subject");
            String creatorEmail = responseObject.getString("creatorEmail");
            String whatsAppLink = responseObject.getString("whatsAppLink");

            Group group = groupService.add(name, subject, whatsAppLink, creatorEmail);
            response.status(201);
            return om.writeValueAsString(group);
        });


        post("/group/remove", (request, response) -> {
            JSONObject responseObject = new JSONObject(request.body());

            String groupId = responseObject.getString("groupId");

            groupService.delete(groupId);

            response.status(200);
            return om.writeValueAsString("Group removed");
        });

        // Get group by id
        get("/group/:id", (request, response) -> {
            Group group = groupService.findById(request.params(":id"));
            if (group != null) {
                return om.writeValueAsString(group);
            } else {
                return om.writeValueAsString(null);
            }
        });

        //List all groups
        get("/group", (request, response) -> {
            List result = groupService.findAll();
            if (result.isEmpty()) {
                return om.writeValueAsString("groups not found");
            } else {
                return om.writeValueAsString(groupService.findAll());
            }
        });


        // ENTER A GROUP
        post("/group/user/enter", (request, response) -> {

            JSONObject responseObject = new JSONObject(request.body());

            String groupId = responseObject.getString("groupId");
            String studentEmail = responseObject.getString("studentEmail");

            studentService.enterGroup(groupId, studentEmail);

            response.status(201);
            return "201";
        });

        post("/group/user/exit", (request, response) -> {

            JSONObject responseObject = new JSONObject(request.body());

            String groupId = responseObject.getString("groupId");
            String studentEmail = responseObject.getString("studentEmail");

            studentService.exitGroup(groupId, studentEmail);

            response.status(201);
            return om.writeValueAsString("success");
        });


        //NOTES

        // Add a Note
        post("/group/note/add", (request, response) -> {
            JSONObject responseObject = new JSONObject(request.body());

            String title = responseObject.getString("title");
            String description = responseObject.getString("description");
            String creatorEmail = responseObject.getString("creatorEmail");
            String groupId = responseObject.getString("groupId");

            Note note = noteService.addNote(title, description, creatorEmail, groupId);
            response.status(201);
            return om.writeValueAsString(note);
        });


        post("/group/note/remove", (request, response) -> {
            JSONObject responseObject = new JSONObject(request.body());

            String noteId = responseObject.getString("noteId");
            String groupId = responseObject.getString("groupId");
            noteService.delete(noteId, groupId);
            response.status(200);
            return om.writeValueAsString("Note deleted");
        });


        get("/group/notes/list/:groupId", (request, response) -> {


            Group group = groupService.findById(request.params(":groupId"));


            HashSet<Note> notes = new HashSet<Note>();

            for (String noteId : group.getNoteIds()) {
                Note note = noteService.findById(noteId);
                notes.add(note);
            }


            return om.writeValueAsString(notes);
        });


        //NOTES

        // Add a Note
        post("/group/video/add", (request, response) -> {

            JSONObject responseObject = new JSONObject(request.body());
            String title = responseObject.getString("title");
            String url = responseObject.getString("url");
            String creatorEmail = responseObject.getString("creatorEmail");
            String groupId = responseObject.getString("groupId");
            Video video = videoService.addVideo(title, url, creatorEmail, groupId);
            response.status(201);
            return om.writeValueAsString(video);
        });


        post("/group/video/remove", (request, response) -> {
            JSONObject responseObject = new JSONObject(request.body());

            String videoId = responseObject.getString("videoId");
            String groupId = responseObject.getString("groupId");
            videoService.delete(videoId, groupId);
            response.status(200);
            return om.writeValueAsString("Video deleted");
        });


        get("/group/videos/list/:groupId", (request, response) -> {


            Group group = groupService.findById(request.params(":groupId"));


            HashSet<Video> videos = new HashSet<Video>();

            for (String videoId : group.getVideoIds()) {
                Video video = videoService.findById(videoId);
                videos.add(video);
            }


            return om.writeValueAsString(videos);
        });


//        studentService.add("Everaldo Jr", "everaldo@email.com", "1798200");
//        studentService.add("André A", "andre@email.com", "17948200");
//        studentService.add("Erick B", "erick@email.com", "17982200");
//        studentService.add("Christian C", "christian@email.com", "17984200");
//        studentService.add("Seeder", "abc@abc.com", "1798201");
//        groupService.add("ED2 - BSI", "Estrutura de Dados 2", "https://chat.whatsapp.com/KntO7lh5A6wHq7YKlRkPPv", "abc@abc.com");
//        groupService.add("Psicologia - S75", "Psicologia aplicada ao Trabalho", "https://chat.whatsapp.com/KntO7lh5A6wHq7YKlRkPPv", "abc@abc.com");
//        groupService.add("Economia - Professora Maria", "Economia", "https://chat.whatsapp.com/KntO7lh5A6wHq7YKlRkPPv", "abc@abc.com");
//        groupService.add("Sociologia - S71", "Sociologia", "https://chat.whatsapp.com/KntO7lh5A6wHq7YKlRkPPv", "abc@abc.com");
//        groupService.add("Cálculo 3 - S93", "Cálculo", "https://chat.whatsapp.com/KntO7lh5A6wHq7YKlRkPPv", "abc@abc.com");
//        groupService.add("Matemática Discreta - S73", "Matemática", "https://chat.whatsapp.com/KntO7lh5A6wHq7YKlRkPPv", "abc@abc.com");

        System.out.println("Running server on port 8080");

    }

}
