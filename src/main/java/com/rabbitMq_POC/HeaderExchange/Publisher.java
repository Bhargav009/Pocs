package com.rabbitMq_POC.HeaderExchange;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Publisher {
	public static Logger Logger = LoggerFactory.getLogger(Publisher.class);

	public void publish() {

		try {
			Connection connection = RabbitMqConnection.getConnection();
			if (connection != null) {
				Channel channel = connection.createChannel();
				Map<String, Object> header1 = new HashMap<String, Object>();
				header1.put("visitType", "Vip");
				header1.put("token", "Vip");
				sendToQueue(channel, header1, "Visitor is Vip");

				Map<String, Object> header2 = new HashMap<String, Object>();
				header2.put("token", "Vip");
				sendToQueue(channel, header2, "Normal Visitor");

				channel.close();
				connection.close();

			} else {
				Logger.info("Connection is not available");
			}
		} catch (Exception e) {
			Logger.error("Exception ---{}", e);
		}

	}

	public void sendToQueue(Channel channel, Map<String, Object> header, String message) throws IOException {
		BasicProperties props = new BasicProperties();
		channel.basicPublish(ExchangeAndQueue.EXCHANGE_NAME, "", props.builder().headers(header).build(),
				message.getBytes());
		Logger.info("Message published is -- {}", message);

	}

}
