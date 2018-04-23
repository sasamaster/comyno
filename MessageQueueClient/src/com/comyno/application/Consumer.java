package com.comyno.application;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.comyno.producer.Producer;

public class Consumer {

	// default broker URL set to : tcp://localhost:61616"
	private static String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

	public static void main(String[] args) throws JMSException, IOException {

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
		Connection connection = connectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// Create producer instance
		Producer producer = new Producer(session);

		// Getting the destination queue
		Destination destination = producer.getDestination();

		// Send text message to the queue
		producer.sendMessage("testing");

		// Create JMS consumer
		MessageConsumer consumer = (MessageConsumer) session.createConsumer(destination);

		// Receiving the message from the queue.
		Message message = consumer.receive();

		if (message instanceof TextMessage) {

			TextMessage textMessage = (TextMessage) message;
			System.out.println("Received '" + textMessage.getText() + "'");

		}

		connection.close();

	}

}
