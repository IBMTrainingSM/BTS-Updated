package com.ibm.bug;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BugApplication{
	public static void main(String[] args) {
		SpringApplication.run(BugApplication.class, args);
	}
	
	PlatformTransactionManager transactionManager(){
		return new MongoTransactionManager();
	}
}