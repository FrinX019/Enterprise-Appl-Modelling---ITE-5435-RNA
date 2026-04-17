package com.fp.w10;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
	private final MessageProducer messageProducer;
	public MessageController(MessageProducer messageProducer) {
		this.messageProducer = messageProducer;
	}
	

	@GetMapping("/sendName")
	public String sendName(@RequestParam String name) {
		messageProducer.sendName(name);
		return "Name sent: " + name;
	}

	@GetMapping("/sendAge")
	public String sendAge(@RequestParam int age) {
		messageProducer.sendAge(age);
		return "Age sent: " + age;
	}
}
