# Lab 10 - RabbitMQ Messaging with Spring Boot

This lab demonstrates message queuing using **RabbitMQ** with two Spring Boot microservices.

## Projects

### ProducerService
- Sends messages (`YourName` and `YourAge`) to a RabbitMQ queue via a REST endpoint.

### ConsumerService
- Listens to the RabbitMQ queue and logs received messages to the console.

## Technologies
- Java / Spring Boot
- Spring AMQP (RabbitMQ)
- Maven
