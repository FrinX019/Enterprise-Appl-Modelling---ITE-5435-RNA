package com.fp.w10;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	public static final String NAME_QUEUE = "nameQueue";
	public static final String AGE_QUEUE  = "ageQueue";

	@Bean
	public Queue nameQueue() {
		return new Queue(NAME_QUEUE, false);
	}

	@Bean
	public Queue ageQueue() {
		return new Queue(AGE_QUEUE, false);
	}

	@RabbitListener(queues = NAME_QUEUE)
	public void listenForName(String name) {
		System.out.println("YourName: " + name);
	}

	@RabbitListener(queues = AGE_QUEUE)
	public void listenForAge(String age) {
		System.out.println("YourAge: " + age);
	}
}
