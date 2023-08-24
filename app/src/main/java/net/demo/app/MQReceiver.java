import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class MQReceiver {

    private ConnectionFactory connectionFactory;

    public MQReceiver(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public String receiveMessage(String correlationId) {
        try (Connection connection = connectionFactory.createConnection();
             Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE)) {

            Queue queue = session.createQueue("YOUR_QUEUE_NAME");
            String selector = "JMSCorrelationID = '" + correlationId + "'";
            try (MessageConsumer consumer = session.createConsumer(queue, selector)) {
                Message message = consumer.receive(15000);
                if (message != null && message instanceof TextMessage) {
                    return ((TextMessage) message).getText();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
