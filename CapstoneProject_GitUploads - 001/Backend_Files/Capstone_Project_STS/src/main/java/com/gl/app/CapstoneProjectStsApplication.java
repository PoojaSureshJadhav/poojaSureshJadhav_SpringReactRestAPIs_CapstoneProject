package com.gl.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@ComponentScan(basePackages = {"com.gl.app.controller","com.gl.app.service","com.gl.app.service.impl"})
public class CapstoneProjectStsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapstoneProjectStsApplication.class, args);
	}

}
