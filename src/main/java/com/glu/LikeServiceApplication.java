package com.glu;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.glu")
@EnableCassandraRepositories(basePackages = "com.glu.repository")
public class LikeServiceApplication {
}
