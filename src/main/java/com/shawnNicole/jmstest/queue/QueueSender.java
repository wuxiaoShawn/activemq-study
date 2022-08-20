package com.shawnNicole.jmstest.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Enumeration;

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
public class QueueSender {
    public static void main(String[] args) throws JMSException, InterruptedException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //创建session时要确定事务类型与签收方式(延迟提交)
        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        //Enumeration names = connection.getMetaData().getJMSXPropertyNames();
        //while (names.hasMoreElements()){
        //    System.out.println(names.nextElement());
        //}

        Destination destination = session.createQueue("test-queue");
        TemporaryQueue tq = session.createTemporaryQueue();

        MessageProducer producer = session.createProducer(destination);

        for (int i = 0; i < 10; i++) {
            //TextMessage textMessage = session.createTextMessage("message_" + i);
            MapMessage message = session.createMapMessage();
            message.setStringProperty("testName","shawn");
            message.setString("message"+i,"my message 889 ---"+i);
            message.setJMSReplyTo(tq);
            //message.setJMSExpiration(10000);

            //Thread.sleep(1000);

            producer.send(message);
        }
        session.commit();
        session.close();
        connection.close();
    }


}