package com.chimas.study_group.app.note;


import com.chimas.study_group.app.student.Student;
import com.chimas.study_group.app.student.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONObject;

import static com.chimas.study_group.app.App.*;
import static com.mongodb.client.model.Filters.eq;

public class NoteService {


    MongoClientURI mongoClientURI = new MongoClientURI(mongoUri);
    MongoClient mongoClient = new MongoClient(mongoClientURI);
    MongoDatabase database = mongoClient.getDatabase("easymeet");
    MongoCollection<Document> notesCollection = database.getCollection("notes");
    MongoCollection<Document> groupsCollection = database.getCollection("groups");
    MongoCollection<Document> usersCollection = database.getCollection("users");


    public Note findById(String id) {
        Document foundNote = notesCollection.find(eq("_id", new ObjectId(id))).first();
        StudentService studentService = new StudentService();

        JSONObject noteJSON = new JSONObject(foundNote.toJson());

        String noteId = noteJSON.getJSONObject("_id").getString("$oid");
        String noteTitle = noteJSON.getString("title");
        String noteDescription = noteJSON.getString("description");
        String noteCreatorEmail = noteJSON.getString("creatorEmail");
        Student creator = studentService.findByEmail(noteCreatorEmail);

        Note note = new Note(noteId, noteTitle, noteDescription, noteCreatorEmail, creator);

        return note;
    }

    public Note addNote(String title, String description, String creatorEmail, String groupId) throws JsonProcessingException {


        StudentService studentService = new StudentService();
        Student student = studentService.findByEmail(creatorEmail);
        ObjectMapper om = new ObjectMapper();

        ObjectId newObjectId = new ObjectId();

        Document note = new Document("_id", newObjectId);

        note.append("title", title)
                .append("description", description)
                .append("creatorEmail", creatorEmail)
                .append("student", om.writeValueAsString(student));


        groupsCollection.updateOne(eq("_id", new ObjectId(groupId)), new Document("$push", new Document("noteIds", newObjectId.toString())));

        Note createdNote = new Note(newObjectId.toString(), title, description, creatorEmail, student);
        notesCollection.insertOne(note);

        return createdNote;
    }


    public void delete(String id, String groupId) {

        notesCollection.deleteOne(eq("_id", new ObjectId(id)));


        groupsCollection.updateOne(eq("_id", new ObjectId(groupId)),
                new Document("$pull",
                        new Document("noteIds", id)
                )
        );

    }

    public NoteService() {
    }
}
