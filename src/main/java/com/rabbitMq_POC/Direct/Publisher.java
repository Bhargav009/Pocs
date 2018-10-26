package com.rabbitMq_POC.Direct;

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

				channel.basicPublish(ExchangeAndQueue.EXCHANGE_NAME, ExchangeAndQueue.ERROR_QUEUE, null,
						"Message for Error Queue".getBytes());
				Logger.info("Message 1 is published to Error Queue");

				channel.basicPublish(ExchangeAndQueue.EXCHANGE_NAME, ExchangeAndQueue.WARNING_QUEUE, null,
						"Message for Warning Queue".getBytes());
				Logger.info("Message 2 is published to Warning Queue");

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
