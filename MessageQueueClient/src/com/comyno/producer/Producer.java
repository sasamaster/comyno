package com.comyno.producer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Producer {
	
	private Session session;
	private MessageProducer producer;
	private Destination destination;
    private Properties prop;
    
    public Producer(Session session) throws IOException, JMSException {
    	
    	this.loadProperties();
    	this.setSession(session);
    	destination = session.createQueue(prop.getProperty("activemq.queue.name"));
    	producer = session.createProducer(destination);
    	
    }
    
	public void sendMessage(String text) throws JMSException {
		
		//Create anonymous message
		TextMessage message = session.createTextMessage("testing");
		producer.send(destination, message);
	}
	
	public void loadProperties() throws IOException {
		
		prop = new Properties();          
		prop.load(new FileInputStream("config.properties"));
	}

	public Destination getDestination() {
		// TODO Auto-generated method stub
		return destination;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
}
