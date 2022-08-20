package com.shawnNicole.jmstest.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Enumeration;
import java.util.Map;

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
public class QueueReceiver {
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61716");
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Enumeration names = connection.getMetaData().getJMSXPropertyNames();
        while (names.hasMoreElements()){
            System.out.println(names.nextElement());
        }

        Destination destination = session.createQueue("test-queue");

        MessageConsumer consumer = session.createConsumer(destination);

        for (int i = 0; i < 10; i++) {
            MapMessage message = (MapMessage) consumer.receive();
            message.getJMSReplyTo();
            session.commit();
            //if (i==9){
            //    //在中间签收试试
            //    message.acknowledge();
            //}
            System.out.println("收到的消息:" + message.getString("message"+i)+",name : "+message.getStringProperty("testName"));

        }
        session.close();
        connection.close();
    }

}