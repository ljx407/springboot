package com.ljx.chapter8springboot.configuration.properties;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class AnotherAbstractMongoConfiguration extends MongoProperties {

    MongoClient getMongoClient() {

        List<ServerAddress> serverAddresses = new ArrayList<>();
        ServerAddress serverAddress = new ServerAddress(getHost(),getPort());
        serverAddresses.add(serverAddress);

        MongoCredential mongoCredential = MongoCredential.createCredential(getUsername(),getDatabase(),getPassword());

        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyToConnectionPoolSettings(
                        builder -> builder.maxConnectionIdleTime(5, TimeUnit.SECONDS).maxWaitTime(1, TimeUnit.MINUTES))
                .applyToClusterSettings(builder -> builder.hosts(serverAddresses))
                .credential(mongoCredential)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    public abstract MongoTemplate initMongoTemplate() ;

}
