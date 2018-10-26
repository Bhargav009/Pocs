package com.rabbitMq_POC.Topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Publisher {

	public static Logger Logger = LoggerFactory.getLogger(Publisher.class);

	public void publish() {

		try {
			Connection connection = RabbitMqConnection.getConnection();
			if (connection != null) {
				Channel channel = connection.createChannel();

				channel.basicPublish(ExchangeAndQueue.EXCHANGE_NAME, ExchangeAndQueue.ROUTING_KEY_ERROR, null, "Error Message".getBytes());
				Logger.info("Message1 is published to exchange {}", ExchangeAndQueue.EXCHANGE_NAME);
				
				channel.basicPublish(ExchangeAndQueue.EXCHANGE_NAME, ExchangeAndQueue.ROUTING_KEY_WARNING, null, "Warning Message".getBytes());
				Logger.info("Message2 is published to exchange {}", ExchangeAndQueue.EXCHANGE_NAME);

				channel.close();
				connection.close();

			} else {
				Logger.info("Connection is not available");
			}
		} catch (Exception e) {
			Logger.error("Exception ---{}", e);
		}

	}
}
