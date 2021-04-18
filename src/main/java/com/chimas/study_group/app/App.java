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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class App {


    public static Map students = new HashMap<>();
    public static Map groups = new HashMap<>();
    public static Map notes = new HashMap<>();
    public static Map videos = new HashMap<>();

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
        return 8080; //return default port if heroku-port isn't set (i.e. on localhost)
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
        get("/student/:id", (request, response) -> {
            Student student = studentService.findById(request.params(":id"));
            if (student != null) {
                return om.writeValueAsString(student);
            } else {
                response.status(404); // 404 Not found
                return om.writeValueAsString("student not found");
            }
        });


        post("/auth", (request, response) -> {

            JSONObject responseObject = new JSONObject(request.body());

            String email = responseObject.getString("email");

            System.out.println(email);

            Student student = studentService.findByEmail(email);
            if (student != null) {
                return om.writeValueAsString(student);
            } else {
                response.status(404); // 404 Not found
                return om.writeValueAsString("student not found");
            }
        });

        // Lists All Users
        get("/student", (request, response) -> {
            List result = studentService.findAll();
            if (result.isEmpty()) {
                return om.writeValueAsString("student not found");
            } else {
                return om.writeValueAsString(studentService.findAll());
            }
        });

        // Update User
        put("/student/:id", (request, response) -> {
            String id = request.params(":id");
            Student student = studentService.findById(id);
            if (student != null) {
                JSONObject responseObject = new JSONObject(request.body());

                String name = responseObject.getString("name");
                String email = responseObject.getString("email");
                String nickname = responseObject.getString("nickname");
                String ra = responseObject.getString("ra");

                studentService.update(id, name, email, nickname, ra);
                return om.writeValueAsString("student with id " + id + " is updated!");
            } else {
                response.status(404);
                return om.writeValueAsString("student not found");
            }
        });

        // Delete User
        delete("/student/:id", (request, response) -> {
            String id = request.params(":id");
            Student student = studentService.findById(id);
            if (student != null) {
                studentService.delete(id);
                return om.writeValueAsString("student with id " + id + " is deleted!");
            } else {
                response.status(404);
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


            studentService.enterGroup(group.getId(), creatorEmail);
            response.status(201);
            return om.writeValueAsString(group);
        });

        // Get group by id
        get("/group/:id", (request, response) -> {
            Group group = groupService.findById(request.params(":id"));
            if (group != null) {
                return om.writeValueAsString(group);
            } else {
                response.status(404); // 404 Not found
                return om.writeValueAsString("student not found");
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

            int groupId = responseObject.getInt("groupId");
            String studentEmail = responseObject.getString("studentEmail");

            Student student = studentService.enterGroup(groupId, studentEmail);

            response.status(201);
            return om.writeValueAsString(student);
        });

        post("/group/user/exit", (request, response) -> {

            JSONObject responseObject = new JSONObject(request.body());

            int groupId = responseObject.getInt("groupId");
            String studentEmail = responseObject.getString("studentEmail");

            Student student = studentService.exitGroup(groupId, studentEmail);

            response.status(201);
            return om.writeValueAsString(student);
        });


        //NOTES

        // Add a Note
        post("/group/note/add", (request, response) -> {
            JSONObject responseObject = new JSONObject(request.body());

            String title = responseObject.getString("title");
            String description = responseObject.getString("description");
            String creatorEmail = responseObject.getString("creatorEmail");
            int groupId = responseObject.getInt("groupId");

            Note note = noteService.addNote(title, description, creatorEmail, groupId);
            response.status(201);
            return om.writeValueAsString(note);
        });


        post("/group/note/remove", (request, response) -> {
            JSONObject responseObject = new JSONObject(request.body());

            int noteId = responseObject.getInt("noteId");
            noteService.delete(noteId);
            response.status(200);
            return om.writeValueAsString("Note deleted");
        });


        get("/group/notes/list/:groupId", (request, response) -> {


            Group group = groupService.findById(request.params(":groupId"));


            HashSet<Note> notes = new HashSet<Note>();


            for (int noteId : group.getNoteIds()) {
                Note note = noteService.findById(Integer.toString(noteId));
                notes.add(note);
            }


            return notes.isEmpty() ? om.writeValueAsString("") : om.writeValueAsString(notes);
        });


        //NOTES

        // Add a Note
        post("/group/video/add", (request, response) -> {




            JSONObject responseObject = new JSONObject(request.body());

            String title = responseObject.getString("title");
            String url = responseObject.getString("url");
            String creatorEmail = responseObject.getString("creatorEmail");
            int groupId = responseObject.getInt("groupId");


            Video video = videoService.addVideo(title, url, creatorEmail, groupId);
            response.status(201);
            return om.writeValueAsString(video);
        });


        get("/group/videos/list/:groupId", (request, response) -> {


            Group group = groupService.findById(request.params(":groupId"));


            HashSet<Video> videos = new HashSet<Video>();


            for (int videoId : group.getVideoIds()) {
                Video video = videoService.findById(Integer.toString(videoId));
                videos.add(video);
            }


            return om.writeValueAsString(videos);
        });


        get("/group/students/list/:groupId", (request, response) -> {


            Group group = groupService.findById(request.params(":groupId"));


            HashSet<Student> students = new HashSet<Student>();


            for (String studentEmail : group.getStudentEmails()) {
                Student student = studentService.findById(studentEmail);
                students.add(student);
            }


            return om.writeValueAsString(students);
        });


        studentService.add("Everaldo Jr", "everaldo@email.com", "1798200");
        studentService.add("André A", "andre@email.com", "17948200");
        studentService.add("Erick B", "erick@email.com", "17982200");
        studentService.add("Christian C", "christian@email.com", "17984200");
        studentService.add("Seeder", "abc@abc.com", "1798201");
        groupService.add("ED2 - BSI", "Estrutura de Dados 2", "https://chat.whatsapp.com/KntO7lh5A6wHq7YKlRkPPv", "abc@abc.com");
        groupService.add("Psicologia - S75", "Psicologia aplicada ao Trabalho", "https://chat.whatsapp.com/KntO7lh5A6wHq7YKlRkPPv", "abc@abc.com");
        groupService.add("Economia - Professora Maria", "Economia", "https://chat.whatsapp.com/KntO7lh5A6wHq7YKlRkPPv", "abc@abc.com");
        groupService.add("Sociologia - S71", "Sociologia", "https://chat.whatsapp.com/KntO7lh5A6wHq7YKlRkPPv", "abc@abc.com");
        groupService.add("Cálculo 3 - S93", "Cálculo", "https://chat.whatsapp.com/KntO7lh5A6wHq7YKlRkPPv", "abc@abc.com");
        groupService.add("Matemática Discreta - S73", "Matemática", "https://chat.whatsapp.com/KntO7lh5A6wHq7YKlRkPPv", "abc@abc.com");


        System.out.println("Running server on port 8080");

    }

}
