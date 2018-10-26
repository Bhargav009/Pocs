package com.rabbitMq_POC.Topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class ExchangeAndQueue {

	public static final String EXCHANGE_NAME = "Topic_Exchange";
	public static final String ERROR_QUEUE = "Error Queue";
	public static final String WARNING_QUEUE = "Warning Queue";
	public static final String LOGS_QUEUE = "Logs Queue";
	public static final String ROUTING_KEY_ERROR = "rabbit.error";
	public static final String ROUTING_KEY_WARNING = "rabbit.warning";
	public static final String ROUTING_KEY_LOGS = "rabbit.*";
 
	public static Logger Logger = LoggerFactory.getLogger(ExchangeAndQueue.class);

	public void createExchangeAndQueue() {
		try {
			Connection connection = RabbitMqConnection.getConnection();
			if (connection != null) {
				Channel channel = connection.createChannel();
				channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

				channel.queueDeclare(ERROR_QUEUE, true, false, false, null);
				channel.queueBind(ERROR_QUEUE, EXCHANGE_NAME, ROUTING_KEY_ERROR);

				channel.queueDeclare(WARNING_QUEUE, true, false, false, null);
				channel.queueBind(WARNING_QUEUE, EXCHANGE_NAME, ROUTING_KEY_WARNING);
				
				channel.queueDeclare(LOGS_QUEUE, true, false, false, null);
				channel.queueBind(LOGS_QUEUE, EXCHANGE_NAME, ROUTING_KEY_LOGS);

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
