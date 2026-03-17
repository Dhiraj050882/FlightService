package com.learnspring.flightbookings.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Autowired
    Environment env;

    @SuppressWarnings("NullableProblems")
    @Override
    protected String getDatabaseName() {
        return env.getProperty("spring.data.mongodb.database", "Flights");
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public MongoClient mongoClient() {
        String uri = env.getProperty("spring.data.mongodb.uri", "mongodb+srv://dhiraj2k10_db_user:gO9Ky9TRdPnjO9lK@learnprogramming.7yxb6n7.mongodb.net/Flights?retryWrites=true&w=majority");
        ConnectionString connectionString = new ConnectionString(uri);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory){
        return new MongoTransactionManager(dbFactory);
    }
}
