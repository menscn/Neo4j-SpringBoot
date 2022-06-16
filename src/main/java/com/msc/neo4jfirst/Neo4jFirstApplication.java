package com.msc.neo4jfirst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class Neo4jFirstApplication {

    public static void main(String[] args) {
        SpringApplication.run(Neo4jFirstApplication.class, args);
    }

}
