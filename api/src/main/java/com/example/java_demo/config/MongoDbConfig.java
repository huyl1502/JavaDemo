package com.example.java_demo.config;

import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.codecs.configuration.*;
import org.bson.codecs.pojo.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import static org.bson.codecs.configuration.CodecRegistries.*;

@Configuration
public class MongoDbConfig {
    @Value("${spring.mongodb.uri}")
    private String uri;

    @Bean
    public MongoClient mongoClient() {
        CodecRegistry pojoCodecRegistry =
                fromRegistries(
                        MongoClientSettings.getDefaultCodecRegistry(),
                        fromProviders(
                                PojoCodecProvider.builder()
                                        .automatic(true)
                                        .build()
                        )
                );

        MongoClientSettings settings =
                MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(uri))
                        .codecRegistry(pojoCodecRegistry)
                        .build();
        return MongoClients.create(settings);
    }

    @Bean
    public MongoDatabase mongoDatabase(MongoClient mongoClient) {
        ConnectionString connectionString = new ConnectionString(uri);

        String dbName = connectionString.getDatabase();

        if (dbName == null || dbName.isBlank()) {
            throw new IllegalStateException(
                    "Database name is missing in mongodb.uri");
        }

        return mongoClient.getDatabase(dbName);
    }
}
