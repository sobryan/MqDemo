import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.apache.activemq.jms.pool.PooledConnectionFactory;
import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;

public class MQConnectionFactoryProvider {

    private static final String HOST = "YOUR_MQ_HOST";
    private static final int PORT = 1414;
    private static final String CHANNEL = "YOUR_MQ_CHANNEL";
    private static final String QMGR = "YOUR_QMGR";

    public ConnectionFactory getPooledConnectionFactory() throws JMSException {
        MQConnectionFactory mqFactory = new MQConnectionFactory();
        mqFactory.setHostName(HOST);
        mqFactory.setPort(PORT);
        mqFactory.setQueueManager(QMGR);
        mqFactory.setChannel(CHANNEL);
        mqFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);

        PooledConnectionFactory pooledFactory = new PooledConnectionFactory();
        pooledFactory.setConnectionFactory(mqFactory);
        pooledFactory.setMaxConnections(100);
        pooledFactory.setBlockIfSessionPoolIsFull(true);

        return pooledFactory;
    }
}
