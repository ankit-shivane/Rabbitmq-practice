package com.jpa.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Component
public class Test2QueueListener {

	static Logger logger = LoggerFactory.getLogger(Test2QueueListener.class);

//	@Scheduled(fixedDelay=300000)
	public void receiveMessage(int data) throws InterruptedException {
		
		logger.info("Priority Queue listener called {}", data);
//		Thread.sleep(5000);
	}

	
	
	
	
/*	public void abc() {
		Timer time = new Timer();
		time.schedule(new TimerTask() {

			@Override
			public void run() {

				logger.info("HAHAHAHH====");
			}
		}, 1000);
	}*/
}
