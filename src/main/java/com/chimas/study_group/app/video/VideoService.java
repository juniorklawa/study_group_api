package com.chimas.study_group.app.video;

import com.chimas.study_group.app.student.Student;
import com.chimas.study_group.app.student.StudentService;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.chimas.study_group.app.App.*;
import static com.chimas.study_group.app.App.videos;
import static com.mongodb.client.model.Filters.eq;

public class VideoService {


    MongoClientURI mongoClientURI = new MongoClientURI(mongoUri);
    MongoClient mongoClient = new MongoClient(mongoClientURI);
    MongoDatabase database = mongoClient.getDatabase("easymeet");
    MongoCollection<Document> videosCollection = database.getCollection("videos");
    MongoCollection<Document> groupsCollection = database.getCollection("groups");


    public Video findById(String id) {
        return (Video) videos.get(id);
    }

    public Video addVideo(String title, String url, String creatorEmail, String groupId) {

        StudentService studentService = new StudentService();

        ObjectId newObjectId = new ObjectId();

        Document video = new Document("_id", newObjectId);

        Student student = studentService.findByEmail(creatorEmail);



        video.append("title", title)
                .append("url", url)
                .append("creatorEmail", creatorEmail)
                .append("student", student);


        groupsCollection.updateOne(eq("_id", new ObjectId(groupId)), new Document("$push", new Document("noteIds", newObjectId)));


        Video createdVideo = new Video(newObjectId.toString(), title, url, creatorEmail, student);

        videosCollection.insertOne(video);

        return createdVideo;
    }


    public void delete(int id) {
        videos.remove(Integer.toString(id));
    }


    public VideoService() {
    }
}
