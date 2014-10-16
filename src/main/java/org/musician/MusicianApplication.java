package org.musician;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableNeo4jRepositories(basePackages = {"org.musician.repository"})
public class MusicianApplication
		extends Neo4jConfiguration {

	public MusicianApplication() {
		setBasePackage("org.musician.domain");
	}

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(MusicianApplication.class);
		application.run(args);
	}

	@Bean
	public GraphDatabaseService graphDatabaseService() {
		return new GraphDatabaseFactory().newEmbeddedDatabase("musician.db");
	}
}