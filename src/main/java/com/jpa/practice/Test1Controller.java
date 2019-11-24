package com.jpa.practice;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test1Controller {

	private static final Logger LOGGER=LoggerFactory.getLogger(Test1Controller.class);
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("${test1.rmq.queue.name}")
	private String routingKey;
	
	@RequestMapping(value = "/sendDataToQueue", method = RequestMethod.GET)
	public String send() {
		rabbitTemplate.convertAndSend(routingKey, "Hello Ankit SHivane");
		LOGGER.info("Data send successfully");
		return "Successfully send...";
	}
	
	@RequestMapping(value = "/testapi", method = RequestMethod.GET)
	public String testapi() {
		return "Running fine...";
	}
//	@RequestMapping(value = "/sendDataToQueue", method = RequestMethod.GET)
	/*@PostConstruct
	public String sendWithPriority() {
		for(int i =0;i<100;i++) {
			int id=new Random().nextInt(100);
			rabbitTemplate.convertAndSend(routingKey, i);	
		}
		
		LOGGER.info("Normal Queue data sent successfully.....-----------------");
		return "Successfully send...";
	}
*/
	
	

	/////////////////////////////////
	
	
	////   Test 2 
	
	
	
	//////////////////////////////////

	@Scheduled(fixedDelay = 15000)
	public void sendDataToNormalQueWithScheduler() {
		rabbitTemplate.convertAndSend(routingKey, 50);
		rabbitTemplate.convertAndSend(routingKey, 545);
		rabbitTemplate.convertAndSend(routingKey, 102);
	}
}