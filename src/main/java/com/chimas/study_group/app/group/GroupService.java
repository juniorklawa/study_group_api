package com.chimas.study_group.app.group;

import com.chimas.study_group.app.student.StudentService;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;


import static com.chimas.study_group.app.App.mongoUri;
import static com.mongodb.client.model.Filters.eq;

public class GroupService {

    MongoClientURI mongoClientURI = new MongoClientURI(mongoUri);
    MongoClient mongoClient = new MongoClient(mongoClientURI);
    MongoDatabase database = mongoClient.getDatabase("easymeet");
    MongoCollection<Document> collection = database.getCollection("groups");

    public Group findById(String id) {
        Document foundGroup = collection.find(eq("_id", new ObjectId(id))).first();

        if (foundGroup != null) {

            JSONObject groupJSON = new JSONObject(foundGroup.toJson());

            String groupId = groupJSON.getJSONObject("_id").getString("$oid");
            String groupName = groupJSON.getString("name");
            String groupSubject = groupJSON.getString("subject");
            String groupEmail = groupJSON.getString("creatorEmail");
            String groupWhatsAppLink = groupJSON.getString("whatsAppLink");
            JSONArray groupStudentEmails = groupJSON.getJSONArray("studentEmails");
            JSONArray groupNoteIds = groupJSON.getJSONArray("noteIds");
            JSONArray groupVideoIds = groupJSON.getJSONArray("videoIds");

            HashSet<String> convertedStudentEmails = new HashSet<>();
            HashSet<String> convertedNoteIds = new HashSet<>();
            HashSet<String> convertedVideoIds = new HashSet<>();

            for (int i = 0, l = groupStudentEmails.length(); i < l; i++) {
                convertedStudentEmails.add(groupStudentEmails.getString(i));
            }

            for (int i = 0, l = groupNoteIds.length(); i < l; i++) {
                convertedNoteIds.add(groupNoteIds.getString(i));
            }

            for (int i = 0, l = groupVideoIds.length(); i < l; i++) {
                convertedVideoIds.add(groupVideoIds.getString(i));
            }

            Group group = new Group(groupId, groupName, groupSubject, groupEmail, groupWhatsAppLink, convertedStudentEmails, convertedNoteIds, convertedVideoIds);


            return group;

        }

        Group group = new Group("null", "", "", "", "", new HashSet<>(), new HashSet<>(), new HashSet<>());

        return group;

    }

    public Group add(String name, String subject, String whatsAppLink, String creatorEmail) {


        HashSet<String> studentEmails = new HashSet<>();
        HashSet<String> emptyList = new HashSet<>();
        studentEmails.add(creatorEmail);
        ObjectId newObjectId = new ObjectId();

        Document group = new Document("_id", newObjectId);

        group.append("name", name)
                .append("subject", subject)
                .append("creatorEmail", creatorEmail)
                .append("whatsAppLink", whatsAppLink)
                .append("studentEmails", studentEmails)
                .append("noteIds", emptyList)
                .append("videoIds", emptyList);


        Group createdGroup = new Group(newObjectId.toString(), name, subject, creatorEmail, whatsAppLink, studentEmails, emptyList, emptyList);


        StudentService studentService = new StudentService();

        studentService.enterGroup(createdGroup.getId(), creatorEmail);

        collection.insertOne(group);

        return createdGroup;
    }

    public void delete(String id) {
        collection.deleteOne(eq("_id", new ObjectId(id)));
    }

    public List findAll() {

        List<Document> groupList = collection.find().into(new ArrayList<>());
        List<Group> formattedGroupList = new ArrayList<>();
        for (Document group : groupList) {

            JSONObject groupJSON = new JSONObject(group.toJson());

            String groupId = groupJSON.getJSONObject("_id").getString("$oid");
            String groupName = groupJSON.getString("name");
            String groupSubject = groupJSON.getString("subject");
            String groupEmail = groupJSON.getString("creatorEmail");
            String groupWhatsAppLink = groupJSON.getString("whatsAppLink");
            JSONArray groupStudentEmails = groupJSON.getJSONArray("studentEmails");
            JSONArray groupNoteIds = groupJSON.getJSONArray("noteIds");
            JSONArray groupVideoIds = groupJSON.getJSONArray("videoIds");

            HashSet<String> convertedStudentEmails = new HashSet<>();
            HashSet<String> convertedNoteIds = new HashSet<>();
            HashSet<String> convertedVideoIds = new HashSet<>();

            for (int i = 0, l = groupStudentEmails.length(); i < l; i++) {
                convertedStudentEmails.add(groupStudentEmails.getString(i));
            }

            for (int i = 0, l = groupNoteIds.length(); i < l; i++) {
                convertedNoteIds.add(groupNoteIds.getString(i));
            }

            for (int i = 0, l = groupVideoIds.length(); i < l; i++) {
                convertedVideoIds.add(groupVideoIds.getString(i));
            }

            Group createdGroup = new Group(groupId, groupName, groupSubject, groupEmail, groupWhatsAppLink, convertedStudentEmails, convertedNoteIds, convertedVideoIds);
            formattedGroupList.add(createdGroup);

        }

        return new ArrayList<>(formattedGroupList);
    }

    public GroupService() {
    }

}
