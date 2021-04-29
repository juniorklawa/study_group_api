package com.chimas.study_group.app.student;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

import static com.chimas.study_group.app.App.*;
import static com.mongodb.client.model.Filters.eq;

public class StudentService {


    MongoClientURI mongoClientURI = new MongoClientURI(mongoUri);
    MongoClient mongoClient = new MongoClient(mongoClientURI);
    MongoDatabase database = mongoClient.getDatabase("easymeet");
    MongoCollection<Document> usersCollection = database.getCollection("users");
    MongoCollection<Document> groupsCollection = database.getCollection("groups");


    public Student findByEmail(String email) {

        Document foundStudent = usersCollection.find(eq("email", email)).first();
        JSONObject studentJSON = new JSONObject(foundStudent.toJson());
        String studentId = studentJSON.getJSONObject("_id").getString("$oid");
        String studentName = studentJSON.getString("name");
        String studentEmail = studentJSON.getString("email");
        String studentRa = studentJSON.getString("ra");
        JSONArray studentGroupIds = studentJSON.getJSONArray("groupIds");

        HashSet<String> convertedList = new HashSet<>();

        for (int i = 0, l = studentGroupIds.length(); i < l; i++) {
            convertedList.add(studentGroupIds.getString(i));
        }

        Student student = new Student(studentId, studentName, studentEmail, studentRa, convertedList);

        return student;
    }

    public Student add(String name, String email, String ra) {
        HashSet<String> groupIds = new HashSet<>();

        ObjectId newObjectId = new ObjectId();

        Document student = new Document("_id", newObjectId);

        student.append("name", name)
                .append("email", email)
                .append("ra", ra)
                .append("groupIds", groupIds);

        usersCollection.insertOne(student);


        Student createdStudent = new Student(newObjectId.toString(), name, email, ra, groupIds);

        return createdStudent;
    }

    public void enterGroup(String groupId, String studentEmail) {

        usersCollection.updateOne(eq("email", studentEmail),
                new Document("$push",
                        new Document("groupIds", groupId)
                )
        );
        groupsCollection.updateOne(eq("_id", new ObjectId(groupId)), new Document("$push", new Document("studentEmails", studentEmail)));
    }


    public void exitGroup(String groupId, String studentEmail) {
        try {

            usersCollection.updateOne(eq("email", studentEmail),
                    new Document("$pull",
                            new Document("groupIds", groupId)
                    )
            );

            groupsCollection.updateOne(eq("_id", new ObjectId(groupId)), new Document("$pull", new Document("studentEmails", studentEmail)));


        } catch (Exception e) {
            new Error(e);
        }
    }


    public StudentService() {
    }
}
