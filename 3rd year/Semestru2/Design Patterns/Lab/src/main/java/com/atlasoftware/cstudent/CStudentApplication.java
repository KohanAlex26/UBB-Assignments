package com.atlasoftware.cstudent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.security.SecureRandom;


@SpringBootApplication
@EnableSwagger2
public class CStudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CStudentApplication.class, args);
	}
}
