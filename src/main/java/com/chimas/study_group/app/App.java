package com.chimas.study_group.app;


import static spark.Spark.*;

import com.chimas.study_group.app.group.Group;
import com.chimas.study_group.app.group.GroupService;
import com.chimas.study_group.app.note.Note;
import com.chimas.study_group.app.note.NoteService;
import com.chimas.study_group.app.student.Student;
import com.chimas.study_group.app.student.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {


    public static Map students = new HashMap<>();
    public static Map groups = new HashMap<>();
    public static Map notes = new HashMap<>();

    private static StudentService studentService = new StudentService();
    private static GroupService groupService = new GroupService();
    private static NoteService noteService = new NoteService();
    private static ObjectMapper om = new ObjectMapper();

    public static void main(String[] args) {

        port(8080);

        get("/", (request, response) -> "API Grupo de Estudos");


        //STUDENT

        // Add an Student
        post("/student/add", (request, response) -> {
            JSONObject responseObject = new JSONObject(request.body());

            String name = responseObject.getString("name");
            String email = responseObject.getString("email");
            String nickname = responseObject.getString("nickname");
            String ra = responseObject.getString("ra");

            Student student = studentService.add(name, email,nickname,ra);
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

                studentService.update(id, name, email,nickname,ra);
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
            String whatsAppLink = responseObject.getString("whatsAppLink");

            Group group = groupService.add(name, subject,whatsAppLink,1);
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
            System.out.println("ENTREI");

            JSONObject responseObject = new JSONObject(request.body());

            int groupId = responseObject.getInt("groupId");
            int studentId = responseObject.getInt("studentId");


            Student student = studentService.enterGroup(groupId,studentId);

            response.status(201);
            return om.writeValueAsString(student);
        });





        //NOTES

        // Add a Note
        post("/group/note/add", (request, response) -> {
            JSONObject responseObject = new JSONObject(request.body());

            String title = responseObject.getString("title");
            String description = responseObject.getString("description");
            int creatorId = responseObject.getInt("creatorId");
            int groupId = responseObject.getInt("groupId");

            Note note = noteService.addNote(title,description, creatorId,groupId);
            response.status(201);
            return om.writeValueAsString(note);
        });


        studentService.add("Everaldo Jr", "everaldo@email.com","everaldojunior","1798200");
        groupService.add("ED2 - BSI", "Estrutura de Dados 2", "www.zapzap.com/chimas", 1);


        System.out.println("Running server on port 8080");

    }

}
