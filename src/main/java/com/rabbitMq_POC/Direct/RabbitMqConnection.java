package com.rabbitMq_POC.Direct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMqConnection {
	public static Logger Logger = LoggerFactory.getLogger(RabbitMqConnection.class);

	public static Connection getConnection() throws Exception {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setUsername("sekhar.palacharla");
			factory.setPassword("Welcome@1234");
			factory.setHost("172.16.0.87");
			factory.setPort(5672);

			Connection connection = factory.newConnection();
			return connection;
		} catch (Exception e) {
			Logger.error("Error ---{}", e);
			return null;
		}
	}

}
