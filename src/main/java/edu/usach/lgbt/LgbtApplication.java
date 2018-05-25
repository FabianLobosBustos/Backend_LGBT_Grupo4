package edu.usach.lgbt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication 
@ComponentScan({"edu.usach.lgbt.application", "edu.usach.lgbt.tweet", "edu.usach.lgbt.rest"})
@EntityScan("edu.usach.lgbt.entities")
@EnableJpaRepositories("edu.usach.lgbt.repository")
@EnableScheduling
public class LgbtApplication {

	public static void main(String[] args) {
		SpringApplication.run(LgbtApplication.class, args);
	}
}
