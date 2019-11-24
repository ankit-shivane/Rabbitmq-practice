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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test2Controller {

	private static final Logger LOGGER = LoggerFactory.getLogger(Test1Controller.class);
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("${test2.rmq.queue.name}")
	private String routingKey;

	@Value("${test1.rmq.queue.name}")
	private String routingKey1;
	/*
	 * @RequestMapping(value = "/sendDataToQueue1", method = RequestMethod.GET)
	 * // @PostConstruct public String sendWithPriority1() { for(int i
	 * =101;i<200;i++) { int id=new Random().nextInt(200); //
	 * rabbitTemplate.convertAndSend(routingKey1, i); //
	 * rabbitTemplate.convertAndSend(routingKey, id);
	 * 
	 * rabbitTemplate.convertAndSend(routingKey, id, new MessagePostProcessor() {
	 * 
	 * @Override public Message postProcessMessage(Message message) throws
	 * AmqpException { // TODO Auto-generated method stub
	 * message.getMessageProperties().setPriority(9); return message; } }); }
	 * 
	 * LOGGER.info("Priority Queue data sent successfully.....==============");
	 * return "Successfully send..."; }
	 */

	@RequestMapping(value = "/sendDataToQueue2/{id}/{idOfPriority}", method = RequestMethod.GET)
//	@PostConstruct
	public String sendWithPriority1(@PathVariable int id, @PathVariable Integer idOfPriority) {
/*
		rabbitTemplate.convertAndSend(routingKey, id, new MessagePostProcessor() {

			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				// TODO Auto-generated method stub
				message.getMessageProperties().setPriority(idOfPriority);
				return message;
			}
		});*/

		rabbitTemplate.convertAndSend(routingKey, 34, new MessagePostProcessor() {

			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				// TODO Auto-generated method stub
				message.getMessageProperties().setPriority(40);
				return message;
			}
		});
		rabbitTemplate.convertAndSend(routingKey, 22, new MessagePostProcessor() {

			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				// TODO Auto-generated method stub
				message.getMessageProperties().setPriority(100);
				return message;
			}
		});
		rabbitTemplate.convertAndSend(routingKey, 10, new MessagePostProcessor() {

			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				// TODO Auto-generated method stub
				message.getMessageProperties().setPriority(10);
				return message;
			}
		});
		LOGGER.info("Priority Queue data sent successfully.....==============");
		return "Successfully send...";
	}

	
	/////////////////////////////////
	
	
	////   Test 2 
	
	
	
	//////////////////////////////////

	@Scheduled(fixedDelay = 15000)
	public void sendDataToMaxPriQueWithScheduler() {

		/*rabbitTemplate.convertAndSend(routingKey, 34, new MessagePostProcessor() {

			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				// TODO Auto-generated method stub
				message.getMessageProperties().setPriority(8);
				return message;
			}
		});
		rabbitTemplate.convertAndSend(routingKey, 22, new MessagePostProcessor() {

			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				// TODO Auto-generated method stub
				message.getMessageProperties().setPriority(10);
				return message;
			}
		});
		rabbitTemplate.convertAndSend(routingKey, 10, new MessagePostProcessor() {

			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				// TODO Auto-generated method stub
				message.getMessageProperties().setPriority(5);
				return message;
			}
		});*/
		rabbitTemplate.convertAndSend(routingKey, 323);
		rabbitTemplate.convertAndSend(routingKey, 23);
		rabbitTemplate.convertAndSend(routingKey, 23);
	}
}
