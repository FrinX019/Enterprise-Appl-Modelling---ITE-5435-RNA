package com.fp.w10;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {
	private final RabbitTemplate rabbitTemplate;
	
	public MessageProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}


	public void sendName(String name) {
		rabbitTemplate.convertAndSend(RabbitMQConfig.NAME_QUEUE, name);
	}

	public void sendAge(int age) {
		rabbitTemplate.convertAndSend(RabbitMQConfig.AGE_QUEUE, String.valueOf(age));
	}
}