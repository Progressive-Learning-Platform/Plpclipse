/**
    Copyright 2016 PLP Contributors

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */
package plptool.visualizer.communication;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import plptool.visualizer.event.SnapshotListener;

/**
 * This thread will keep listening on the message transfer from back end.
 * And notify the front end through registered listeners(call backs).
 */
public class FrontendConsumer implements Runnable, ExceptionListener {
	/**
	 * Listeners(Call backs) list.
	 */
	private List<SnapshotListener> listeners = new ArrayList<SnapshotListener>();

	/**
	 * Register a listener(call back)
	 * @param toAdd The call back need to execute after received the message.
	 */
	public void addListener(SnapshotListener toAdd) {
		listeners.add(toAdd);
	}

	/**
	 * Keep listening on the message event
	 * When received the message trigger call backs one by one.
	 */
	public void run() {
		try {
			// Create a ConnectionFactory
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");

			// Create a Connection
			Connection connection = connectionFactory.createConnection();
			connection.start();

			connection.setExceptionListener(this);

			// Create a Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			Destination destination = session.createQueue("SNAPSHOT");

			// Create a MessageConsumer from the Session to the Topic or Queue
			MessageConsumer consumer = session.createConsumer(destination);

			while (true) {
				// Wait for a message
				Message message = consumer.receive(1000);
	
				if (message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;
					String text = textMessage.getText();
					// Received valid message, invoke the call backs.
					for (SnapshotListener hl : listeners)
						hl.receiveSnapshot(text);
				} else {
					// Received invalid message, do nothing.
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized void onException(JMSException ex) {
		System.out.println("JMS Exception occured.  Shutting down client.");
	}
}
