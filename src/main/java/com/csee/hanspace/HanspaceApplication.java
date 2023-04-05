package com.csee.hanspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HanspaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HanspaceApplication.class, args);
	}

}
