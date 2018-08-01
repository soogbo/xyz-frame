package xyz.frame.configure;


import com.mongodb.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.ArrayList;
import java.util.List;

//@Configuration
public class MongoConfigure {

    @Value("${mongo.config.replica.set.address:127.0.0.1:27017}")
    private String mongoAddress;

    @Value("${mongo.config.username}")
    private String username;

    @Value("${mongo.config.database}")
    private String database;

    @Value("${mongo.config.password}")
    private String password;

    @Bean(name = "mongoClient")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public MongoClient mongoClient() {
        MongoClient mongoClient = null;
        try {
            String[] addresArr = mongoAddress.split(",");
            List<ServerAddress> sends = new ArrayList<>();
            for (String addres : addresArr) {
                String[] hostAndPort = addres.split(":", 2);
                ServerAddress sa = new ServerAddress(hostAndPort[0], Integer.parseInt(hostAndPort[1]));
                sends.add(sa);
            }
            List<MongoCredential> mongoCredentialList = new ArrayList<>();
            mongoCredentialList.add(MongoCredential.createMongoCRCredential(
                    username, database, password.toCharArray()));
            MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
            builder.writeConcern(WriteConcern.ACKNOWLEDGED);
            builder.connectionsPerHost(100);
            builder.threadsAllowedToBlockForConnectionMultiplier(50);
            mongoClient = new MongoClient(sends, mongoCredentialList, builder.build());
        } catch (Exception e) {
            throw new RuntimeException("连接MongoDB数据库错误:" + e.getMessage(), e);
        }
        return mongoClient;
    }

    @Bean(name = "mongoDbFactory")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public MongoDbFactory mongoDbFactory(@Qualifier("mongoClient") MongoClient mongoClient) {
        return new SimpleMongoDbFactory(mongoClient, database);
    }

    @Bean(name = "mongoTemplate")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public MongoTemplate mongoTemplate(@Qualifier("mongoDbFactory") MongoDbFactory mongoDbFactory) {
        return new MongoTemplate(mongoDbFactory);
    }


}
