package com.rabbitMq_POC.Fanout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class ExchangeAndQueue {

	public static final String EXCHANGE_NAME = "Fanout_Test";
	public static final String QUEUE1 = "Queue1";
	public static final String QUEUE2 = "Queue2";

	public static Logger Logger = LoggerFactory.getLogger(ExchangeAndQueue.class);

	public void createExchangeAndQueue() {
		try {
			Connection connection = RabbitMqConnection.getConnection();
			if (connection != null) {
				Channel channel = connection.createChannel();
				channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

				channel.queueDeclare(QUEUE1, true, false, false, null);
				channel.queueBind(QUEUE1, EXCHANGE_NAME, "");

				channel.queueDeclare(QUEUE2, true, false, false, null);
				channel.queueBind(QUEUE2, EXCHANGE_NAME, "");

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
