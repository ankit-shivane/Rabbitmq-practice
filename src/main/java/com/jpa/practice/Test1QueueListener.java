package com.jpa.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component
public class Test1QueueListener {
	static Logger logger = LoggerFactory.getLogger(Test1QueueListener.class);
	
	public void receiveMessage(int data) throws InterruptedException {
	logger.info("consumer called {}",data);
//	Thread.sleep(3000);	
	}
}
