package com.ljx;


import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Updates.inc;
import static com.mongodb.client.model.Updates.set;


@RunWith(JUnit4.class)
public class MongoDBTest {

    private MongoCollection<Document> user ;

    /**
     * mongoclient 的三种初始化方式
     * 1. 直接指定host，port
     * 2. 通过定义mongoClientURI
     * 3. 通过定义serverAddress，MongoClientOptions
     */
    @Before
    public void beforeMethod() {
//        MongoClient mongoClient = new MongoClient("localhost", 27017);

//        MongoClientURI mongoClientURI = new MongoClientURI("mongodb://localshot:27017/chapter8java", MongoClientOptions.builder()
//                .connectTimeout(600000)
//                .maxWaitTime(1000 * 60 * 2));
//        MongoClient mongoClient = new MongoClient(mongoClientURI);


//        ServerAddress serverAddress = new ServerAddress("localhost", 27017);
//
//        MongoClientOptions mongoClientOptions = MongoClientOptions.builder()
//                .connectTimeout(600000)
//                .maxWaitTime(1000 * 60 * 2)
//                .build();
//
//        MongoClient mongoClient = new MongoClient(serverAddress,mongoClientOptions);
//


        MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .applyToClusterSettings(builder ->
                                builder.hosts(Arrays.asList(new ServerAddress("host1", 27017))))
                        .credential(MongoCredential.createCredential("root", "chapter8java", "root".toCharArray()))
                        .applyToConnectionPoolSettings(builder -> builder.maxWaitTime(2, TimeUnit.MINUTES).maxConnectionIdleTime(10, TimeUnit.MINUTES))
                        .build());


        MongoDatabase chapter8java = mongoClient.getDatabase("chapter8java");
        user = chapter8java.getCollection("user");
    }

    @Test
    public void testInsert() {
        Document document = new Document();
        document.append("name" , "test02");
        document.append("age" , 4);
        user.insertOne(document);
    }

    @Test
    public void testInsertMany() {
        List<Document> documents = new ArrayList<Document>();

        Document document1 = new Document()
                .append("name" , "test02")
                .append("age" , 4);

        Document document2 = new Document()
                .append("name" , "test03")
                .append("age" , 5);

        documents.add(document1);
        documents.add(document2);

        user.insertMany(documents);
    }

    @Test
    public void testDeleteOne() {
        Document query = new Document();
        query.append("name","test01");

//        Document filter = new Document();
//        filter.append("$set", query);

        DeleteResult deleteResult = user.deleteOne(query);
        System.out.println(deleteResult.getDeletedCount());

    }

    @Test
    public void testDeleteOne2() {

        DeleteResult deleteResult = user.deleteOne(Filters.eq("name","test01"));
        System.out.println(deleteResult.getDeletedCount());

    }

    @Test
    public void testDeleteMany() {

        DeleteResult deleteResult = user.deleteMany(Filters.regex("name","test"));
        System.out.println(deleteResult.getDeletedCount());

    }

    @Test
    public void testUpdateOne() {
        user.updateOne(Filters.eq("name","test02"),set("name","test022"));
    }

    @Test
    public void testUpdateMany() {
        user.updateMany(Filters.gt("age",10),inc("age",-1));
    }

    @Test
    public void testFindAll() {
        MongoCursor<Document> iterator = user.find().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toJson());
        }

    }

    @Test
    public void testFind() {

        MongoCursor<Document> iterator = user.find(Filters.gte("age",5))
                .sort(descending("age"))
                .skip(2)
                .limit(1)
                .iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next().toJson());
        }



    }
}
