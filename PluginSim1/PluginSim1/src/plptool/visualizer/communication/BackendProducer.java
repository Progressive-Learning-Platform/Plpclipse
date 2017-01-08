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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Mocking test, backend simulator.
 * This class support to test the front end separately.
 * 1.Read the fake*.json files under test_data folder.
 * 2.Send these data to front end.
 */
public class BackendProducer implements Runnable {
	public void run() {

		int test_number = 0;
		while (true) {
			try {
				Thread.sleep(1000);
				// Create a ConnectionFactory
				ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");

				// Create a Connection
				Connection connection = connectionFactory.createConnection();
				connection.start();

				// Create a Session
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

				// Create the destination (Topic or Queue)
				Destination destination = session.createQueue("SNAPSHOT");

				// Create a MessageProducer from the Session to the Topic or Queue
				MessageProducer producer = session.createProducer(destination);
				producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
				producer.setTimeToLive(10);

				// Create a messages
				String conf_file = "test_data/fake" + String.valueOf(test_number) + ".json";
				test_number++;
				if (test_number > 4)
					test_number = 0;
				BufferedReader reader;
				String line = null;
				String text = "";
				try {
					reader = new BufferedReader(new FileReader (conf_file));
					while((line = reader.readLine()) != null) {
						text += line;
					}
					reader.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				TextMessage message = session.createTextMessage(text);

				// Tell the producer to send the message
				producer.send(message);

				// Clean up
				session.close();
				connection.close();
			}
			catch (Exception e) {
				System.out.println("Caught: " + e);
				e.printStackTrace();
			}
		}
	}
}
