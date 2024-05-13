package com.api.test.user.h2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan(basePackages = "com.api.test.user.h2")
public class TestRGaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestRGaApplication.class, args);
	}

}
