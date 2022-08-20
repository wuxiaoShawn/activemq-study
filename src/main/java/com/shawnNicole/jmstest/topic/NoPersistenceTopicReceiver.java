package com.shawnNicole.jmstest.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Enumeration;

/**
 * @ProjectName: activemq-study
 * @Package: com.shawnNicole.jmstest.queue
 * @ClassName: QueueReceiver
 * @Description: []
 * @Author: [Shawn Wu]
 * @Date: 2022/1/4 18:06
 * @Version: V1.0
 * @Copyright: 2021  Inc. All rights reserved.
 * @TODO: 注意, 本文件Shawn Wu 所作,如果转载或使用请标明具体出处!
 **/
public class NoPersistenceTopicReceiver {
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Enumeration names = connection.getMetaData().getJMSXPropertyNames();
        while (names.hasMoreElements()){
            System.out.println(names.nextElement());
        }
        Destination destination = session.createTopic("myTopic");
        MessageConsumer consumer = session.createConsumer(destination);
        Message message = consumer.receive();
        while (message != null){
            TextMessage txtMessage = (TextMessage) message;
            System.out.println("收到的消息:" + txtMessage.getText());
            message = consumer.receive(1000L);
        }
        session.commit();
        session.close();
        connection.close();
    }

}