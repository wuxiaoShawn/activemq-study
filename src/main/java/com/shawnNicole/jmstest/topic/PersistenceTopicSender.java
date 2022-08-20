package com.shawnNicole.jmstest.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @ProjectName: activemq-study
 * @Package: com.shawnNicole.jmstest.queue
 * @ClassName: QueueSender
 * @Description: []
 * @Author: [Shawn Wu]
 * @Date: 2022/1/2 22:59
 * @Version: V1.0
 * @Copyright: 2021  Inc. All rights reserved.
 * @TODO: 注意, 本文件Shawn Wu 所作,如果转载或使用请标明具体出处!
 **/
public class PersistenceTopicSender {
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = activeMQConnectionFactory.createConnection();
        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic("myPersistenceTopic");
        TemporaryQueue tq = session.createTemporaryQueue();

        MessageProducer producer = session.createProducer(destination);
        //设置持久化模式
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        //创建连接
        connection.start();

        for (int i = 0; i < 10; i++) {
            TextMessage message = session.createTextMessage();
            message.setText("message777"+i);
            message.setJMSReplyTo(tq);
            producer.send(message);
        }
        session.commit();
        session.close();
        connection.close();
    }


}