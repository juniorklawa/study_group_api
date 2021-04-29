package com.chimas.study_group.app.video;

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

public class VideoService {


    MongoClientURI mongoClientURI = new MongoClientURI(mongoUri);
    MongoClient mongoClient = new MongoClient(mongoClientURI);
    MongoDatabase database = mongoClient.getDatabase("easymeet");
    MongoCollection<Document> videosCollection = database.getCollection("videos");
    MongoCollection<Document> groupsCollection = database.getCollection("groups");


    public Video findById(String id) {
        Document foundVideo = videosCollection.find(eq("_id", new ObjectId(id))).first();
        StudentService studentService = new StudentService();

        JSONObject videoJSON = new JSONObject(foundVideo.toJson());

        String videoId = videoJSON.getJSONObject("_id").getString("$oid");
        String videoTitle = videoJSON.getString("title");
        String videoUrl = videoJSON.getString("url");
        String videoCreatorEmail = videoJSON.getString("creatorEmail");
        Student creator = studentService.findByEmail(videoCreatorEmail);

        Video video = new Video(videoId, videoTitle, videoUrl, videoCreatorEmail, creator);

        return video;
    }

    public Video addVideo(String title, String url, String creatorEmail, String groupId) throws JsonProcessingException {

        StudentService studentService = new StudentService();
        Student student = studentService.findByEmail(creatorEmail);
        ObjectMapper om = new ObjectMapper();

        ObjectId newObjectId = new ObjectId();

        Document video = new Document("_id", newObjectId);

        video.append("title", title)
                .append("url", url)
                .append("creatorEmail", creatorEmail)
                .append("student", om.writeValueAsString(student));


        groupsCollection.updateOne(eq("_id", new ObjectId(groupId)), new Document("$push", new Document("videoIds", newObjectId.toString())));

        Video createdVideo = new Video(newObjectId.toString(), title, url, creatorEmail, student);
        videosCollection.insertOne(video);

        return createdVideo;
    }

    public void delete(String id, String groupId) {

        videosCollection.deleteOne(eq("_id", new ObjectId(id)));
        groupsCollection.updateOne(eq("_id", new ObjectId(groupId)),
                new Document("$pull",
                        new Document("videoIds", id)
                )
        );
    }

    public VideoService() {
    }
}
