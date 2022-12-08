package com.daymeijroos.iacspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class IacSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(IacSpringApplication.class, args);
	}


}
