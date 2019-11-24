package com.jpa.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class RabbitmqPracticeApplication implements CommandLineRunner{

	  private static final Logger logger = LoggerFactory.getLogger(RabbitmqPracticeApplication.class);

	  
	public static void main(String[] args) {
		SpringApplication.run(RabbitmqPracticeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		name();
	}
	
//	@Scheduled(cron = "0 0/05 * * * *")
//	@Scheduled(fixedDelay=300000) // at evry 5 min
//	@Scheduled(fixedDelay=3000) // at evry 5 min	
	public void name() {
		logger.info("tttt------ at evry 5 min");
//		return "at every five minutes";
	}

}
