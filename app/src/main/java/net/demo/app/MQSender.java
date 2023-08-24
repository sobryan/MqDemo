import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class MQSender {

    private ConnectionFactory connectionFactory;

    public MQSender(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void sendMessage(String messageText, String correlationId) {
        try (Connection connection = connectionFactory.createConnection();
             Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
             MessageProducer producer = session.createProducer(null)) {

            Queue sendQueue = session.createQueue("YOUR_SEND_QUEUE_NAME");
            TextMessage message = session.createTextMessage(messageText);
            message.setJMSCorrelationID(correlationId);

            producer.send(sendQueue, message);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
