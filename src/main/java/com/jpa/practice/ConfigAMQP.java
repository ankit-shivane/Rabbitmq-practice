package com.jpa.practice;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConfigAMQP {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigAMQP.class);
	@Value("${test1.rmq.queue.name}")
	private String queueName;

	@Value("${test2.rmq.queue.name}")
	private String queueName2;

	@Value("${test1.rmq.exchange.name}")
	private String exchangeName;

	/* Queue declaration */
	@Primary
	@Bean
	Queue test1Queue() {
		/*Map<String, Object> hasmap = new HashMap<>();
		hasmap.put("x-max-priority", 10);*/
		return new Queue(queueName, true);
//		return new Queue(queueName, true, false, false, hasmap);
	}

	@Bean
	Queue test2Queue() {
		Map<String, Object> hasmap = new HashMap<>();
		hasmap.put("x-max-priority",10);
//		return new Queue(queueName2, true);
		
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("x-max-priority", 10);
		return new Queue(queueName2, true, false, false, args);
	}

	/* exchange declaration */
	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchangeName);
	}

	/* Queue and exchange binding */
	@Bean
	Binding test2QueueBinding(Queue que, DirectExchange dx) {
		// here in .with() is routing key
		return BindingBuilder.bind(que).to(dx).with(queueName2);
	}

	@Bean
	Binding test1QueueBinding(Queue que, DirectExchange dx) {
		// here in .with() is routing key
		return BindingBuilder.bind(que).to(dx).with(queueName);
	}

	/* listener container configuration */
	@Bean
	@Qualifier("test1QueueListenerContainer")
	SimpleMessageListenerContainer test1QueueListenerContainer(ConnectionFactory connectionFactory,
			@Qualifier("test1QueueListenerAdapter") MessageListenerAdapter msgListAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		LOGGER.info("Insdie listener adapter", queueName);
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(msgListAdapter);
		return container;
	}

	@Bean
	@Qualifier("test2QueueListenerContainer")
	SimpleMessageListenerContainer test2QueueListenerContainer(ConnectionFactory connectionFactory,
			@Qualifier("test2QueueListenerAdapter") MessageListenerAdapter msgListAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		LOGGER.info("Insdie listener adapter", queueName2);
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName2);
		container.setMessageListener(msgListAdapter);
		return container;
	}

	
	/* Listener adapter configuration */
	@Primary
	@Bean
	MessageListenerAdapter test1QueueListenerAdapter(Test1QueueListener test1QueueListener) {
		return new MessageListenerAdapter(test1QueueListener, "receiveMessage");
	}
	
	@Bean
	MessageListenerAdapter test2QueueListenerAdapter(Test2QueueListener test2QueueListener) {
		return new MessageListenerAdapter(test2QueueListener, "receiveMessage");
	}

	/*  Bean for listener */
	
	@Bean
	Test1QueueListener test1QueueListener() {
		return new Test1QueueListener();
	}
	@Bean
	Test2QueueListener test2QueueListener() {
		return new Test2QueueListener();
	}
	
}
