package edu.usach.lgbt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication (scanBasePackages = {"edu.usach.lgbt.graphrepository", "edu.usach.lgbt.graph"})
@ComponentScan({"edu.usach.lgbt.application", "edu.usach.lgbt.tweet", "edu.usach.lgbt.rest"})
@EntityScan({"edu.usach.lgbt.entities", "edu.usach.lgbt.graph"})
@EnableNeo4jRepositories("edu.usach.lgbt.graphrepository")
@EnableJpaRepositories("edu.usach.lgbt.repository")
@EnableScheduling
@EnableTransactionManagement
public class LgbtApplication {

	public static void main(String[] args) {
		SpringApplication.run(LgbtApplication.class, args);
	}
}
