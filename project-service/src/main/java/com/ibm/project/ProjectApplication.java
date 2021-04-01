package com.ibm.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableTransactionManagement
public class ProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	
//	PlatformTransactionManager transactionManager(){
//		return new MongoTransactionManager();
//	}
}
