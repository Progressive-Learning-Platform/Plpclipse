package plptool.mips.communication;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
/**
 * This class in charge of sending message from back end to front end.
 * It's under a Singleton model.
 * @author tobielf
 */
public class Messenger {
    /**
     * Singleton instance
     */
    private static Messenger instance = null;
    
    private ActiveMQConnectionFactory connectionFactory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageProducer producer = null;

    /**
     * Constructor
     * Create one connection and reuse it to send message
     */
    protected Messenger() {
        try {
            connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //SAME QUEUE NAME IN FRONTEND CONSUMER AND THIS BACKEND PRODUCER
            destination = session.createQueue("SNAPSHOT");
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        }
        catch (Exception e) 
        {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }

    /**
     * Get one instance.
     * @return instance of the messenger
     */
    public static Messenger getInstance() {
        if (instance == null)
            instance = new Messenger();
        return instance;
    }

    /**
     * Send a message to front end.
     * @param message the message will send
     * @return true, successfully sent the message, otherwise failed.
     */
    public boolean sentMessage(String message) {
        TextMessage text_message;
        try {
            text_message = session.createTextMessage(message.toString());
            producer.send(text_message);
        } catch (JMSException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
