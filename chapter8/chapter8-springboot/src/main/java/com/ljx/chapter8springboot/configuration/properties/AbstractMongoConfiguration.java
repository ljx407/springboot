package com.ljx.chapter8springboot.configuration.properties;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractMongoConfiguration {
    String database;
    String host;
    String username;
    String password ;
    int port;

    MongoClient getMongoClient() {

        List<ServerAddress> serverAddresses = new ArrayList<>();
        ServerAddress serverAddress = new ServerAddress(this.host,this.port);
        serverAddresses.add(serverAddress);

        MongoCredential mongoCredential = MongoCredential.createCredential(this.username,this.database,this.password.toCharArray());

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
