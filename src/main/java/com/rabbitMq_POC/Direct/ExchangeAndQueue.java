package com.rabbitMq_POC.Direct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class ExchangeAndQueue {

	public static final String EXCHANGE_NAME = "Direct_Test";
	public static final String ERROR_QUEUE = "Error Queue";
	public static final String WARNING_QUEUE = "Warning Queue";

	public static Logger Logger = LoggerFactory.getLogger(ExchangeAndQueue.class);

	public void createExchangeAndQueue() {
		try {
			Connection connection = RabbitMqConnection.getConnection();
			if (connection != null) {
				Channel channel = connection.createChannel();
				channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

				channel.queueDeclare(ERROR_QUEUE, true, false, false, null);
				channel.queueBind(ERROR_QUEUE, EXCHANGE_NAME, ERROR_QUEUE);

				channel.queueDeclare(WARNING_QUEUE, true, false, false, null);
				channel.queueBind(WARNING_QUEUE, EXCHANGE_NAME, WARNING_QUEUE);

				channel.close();
				connection.close();

			} else {
				Logger.info("Connection is not available");
			}

		} catch (Exception e) {
			Logger.error("Exception ----{}", e);
		}
	}
}
