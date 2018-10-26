package com.rabbitMq_POC.HeaderExchange;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Receiver {

	public final static Logger Logger = LoggerFactory.getLogger(Receiver.class);

	public void consume() {

		try {
			Connection connection = RabbitMqConnection.getConnection();
			if (connection != null) {
				Channel channel = connection.createChannel();
				queueConsume(channel, ExchangeAndQueue.VIP_QUEUE);
				queueConsume(channel, ExchangeAndQueue.NORMAL_QUEUE);
			} else {
				Logger.error("Connection is unavailable");
			}
		} catch (Exception e) {
			Logger.error("Error is: {}", e);
		}
	}

	public void queueConsume(Channel channel, String queueName) throws IOException {
		Consumer consumer = new DefaultConsumer(channel) {
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws java.io.IOException {
				String message = new String(body, "UTF-8");
				Logger.info("message received is -- {}", message);
			}
		};
		channel.basicConsume(queueName, true, consumer);
	}
}
