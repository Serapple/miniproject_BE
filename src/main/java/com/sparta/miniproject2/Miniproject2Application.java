package com.sparta.miniproject2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Miniproject2Application {

	public static void main(String[] args) {
		SpringApplication.run(Miniproject2Application.class, args);
	}

}
