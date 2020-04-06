package com.fss.empdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class EmployeeDbApplication  {

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(EmployeeDbApplication.class);
//	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeeDbApplication.class, args);
	}

}