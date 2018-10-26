package com.rabbitMq_POC.HeaderExchange;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class ExchangeAndQueue {
	public static final String EXCHANGE_NAME = "Traffic Exchange";
	public static final String VIP_QUEUE = "Vip Visitors";
	public static final String NORMAL_QUEUE = "Normal Visitors";

	public static Logger Logger = LoggerFactory.getLogger(ExchangeAndQueue.class);

	public void createExchangeAndQueue() {
		try {
			Connection connection = RabbitMqConnection.getConnection();
			if (connection != null) {
				Channel channel = connection.createChannel();
				channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.HEADERS);

				Map<String, Object> args1 = new HashMap<String, Object>();
				args1.put("x-match", "all");
				args1.put("visitType", "Vip");
				args1.put("token", "Vip");
				channel.queueDeclare(VIP_QUEUE, true, false, false, null);
				channel.queueBind(VIP_QUEUE, EXCHANGE_NAME, "", args1);

				Map<String, Object> args2 = new HashMap<String, Object>();
				args2.put("x-match", "any");
				args2.put("visitType", "Vip");
				args2.put("token", "Vip");
				channel.queueDeclare(NORMAL_QUEUE, true, false, false, null);
				channel.queueBind(NORMAL_QUEUE, EXCHANGE_NAME, "", args2);

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
