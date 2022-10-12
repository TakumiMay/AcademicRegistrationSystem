package com.university.academicRegistrationSystem;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "University Registration API", version = "1.0", description = "Registration Operations"))
public class AcademicRegistrationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademicRegistrationSystemApplication.class, args);
	}

}
