package com.rabbitMq_POC.HeaderExchange;

public class App {
	public static void main(String[] args) {
		ExchangeAndQueue exchangeAndQueue = new ExchangeAndQueue();
		exchangeAndQueue.createExchangeAndQueue();

		Publisher publisher = new Publisher();
		publisher.publish();

		Receiver receiver = new Receiver();
		receiver.consume();

	}

}
