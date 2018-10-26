package com.rabbitMq_POC.Fanout;

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

				channel.basicPublish(ExchangeAndQueue.EXCHANGE_NAME, "", null, "Fanout Message".getBytes());
				Logger.info("Message is published to {}", ExchangeAndQueue.EXCHANGE_NAME);

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
