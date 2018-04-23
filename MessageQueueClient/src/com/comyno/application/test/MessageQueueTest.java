package com.comyno.application.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.comyno.producer.Producer;

public class MessageQueueTest {

	final String queueName = "queue.test";

	@Test
	public void test() throws Exception {
		
		final ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
		final Connection connection = connectionFactory.createConnection();
		connection.start();
		final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		final Destination destination = session.createQueue(queueName);
		
			// MessageProducer producer = session.createProducer(destination);
			
			Producer producer = new Producer(session);
			producer.sendMessage("testing");
		    MessageConsumer consumer = session.createConsumer(destination);
			TextMessage received = (TextMessage) consumer.receive();
			Assert.assertNotNull(received);
			Assert.assertEquals("testing", received.getText());
	}

}
