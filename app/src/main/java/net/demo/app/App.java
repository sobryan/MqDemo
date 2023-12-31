/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package net.demo.app;

public class App {

    public static void main(String[] args) {
        MQConnectionFactoryProvider provider = new MQConnectionFactoryProvider();
    ConnectionFactory connectionFactory = provider.getPooledConnectionFactory();

    MQSender sender = new MQSender(connectionFactory);
    MQReceiver receiver = new MQReceiver(connectionFactory);

    String correlationId = UUID.randomUUID().toString();  // Generate a unique ID for correlation

    sender.sendMessage("Hello, this is a request!", correlationId);
    String response = receiver.receiveResponse(correlationId);

    System.out.println("Received response: " + response);
    }
}
