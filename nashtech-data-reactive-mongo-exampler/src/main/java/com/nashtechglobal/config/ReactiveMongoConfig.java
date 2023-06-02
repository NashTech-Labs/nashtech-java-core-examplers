package com.nashtechglobal.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

/**
 * Configuration class for setting up a reactive
 * MongoDB connection and template.
 * <p>
 * The class extends AbstractReactiveMongoConfiguration
 * to provide custom configuration.
 * </p>
 */
@Configuration
public class ReactiveMongoConfig extends AbstractReactiveMongoConfiguration {
    /**
     * The connection string for the MongoDB server.
     */
    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;
    /**
     * The name of the MongoDB database to use.
     */
    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;

    /**
     * Returns the name of the MongoDB database to be used.
     *
     * @return database name - not allowed to be null according to the
     * {@code NonNull} annotation.
     */
    @Override
    protected String getDatabaseName() {
        return mongoDatabase;
    }

    /**
     * Returns a reactive MongoClient instance using the provided mongoUri.
     *
     * @return MongoClient - A non-null reactive MongoClient instance.
     */
    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create(mongoUri);
    }

    /**
     * Creates a new instance of ReactiveMongoTemplate using the configured
     * reactiveMongoClient and database name.
     *
     * @return the created ReactiveMongoTemplate instance.
     */
    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoClient(),
                getDatabaseName());
    }
}
