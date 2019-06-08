package com.ljx.configuration;

import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableMongoRepositories(basePackages = "com.ljx.mongoDao")
public class MongoDBConfiguration {


    @Bean
    public MongoClientFactoryBean mongoClient() {
        MongoClientFactoryBean mongoClientFactoryBean = new MongoClientFactoryBean();
        mongoClientFactoryBean.setHost("localhost");
        mongoClientFactoryBean.setPort(27017);

        MongoCredential credential = MongoCredential.createCredential("root", "chapter8java", "root".toCharArray());
        List<MongoCredential> credentialList = new ArrayList<>();
        credentialList.add(credential);

//        MongoCredential[] credentials = new MongoCredential[credentialList.size()];

        mongoClientFactoryBean.setCredentials(credentialList.toArray(new MongoCredential[credentialList.size()]));

        MongoClientOptions mongoClientOptions = MongoClientOptions.builder()
                .maxConnectionIdleTime(5000)
                .maxWaitTime(1000 * 60 * 2)
                .build();
        mongoClientFactoryBean.setMongoClientOptions(mongoClientOptions);
        return mongoClientFactoryBean;
    }

    @Bean
    public MongoDbFactory mongoDbFactory() {
        SimpleMongoDbFactory simpleMongoDbFactory = null ;
        try {
            simpleMongoDbFactory = new SimpleMongoDbFactory(mongoClient().getObject(),"chapter8java");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return simpleMongoDbFactory ;
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }
}
