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
public class PersistenceTopicReceiver {
    public static void main(String[] args) throws JMSException {
        //远程服务器地址20.27.239.94
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = activeMQConnectionFactory.createConnection();
        //创建唯一ID，识别消费者
        connection.setClientID("textID997");
        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        //Enumeration names = connection.getMetaData().getJMSXPropertyNames();
        //while (names.hasMoreElements()){
        //    System.out.println(names.nextElement());
        //}
        Topic destination = session.createTopic("myPersistenceTopic");
        //需要创建TopicSubscriber来订阅
        TopicSubscriber ts = session.createDurableSubscriber(destination,"T1");
        //都设置好了才能创建连接
        connection.start();
        Message message = ts.receive();
        int i=0;
        while (message != null){
            i++;
            TextMessage txtMessage = (TextMessage) message;
            System.out.println(i+"、收到的消息:" + txtMessage.getText());
            message = ts.receive(1000L);

        }

        session.commit();
        session.close();
        connection.close();
    }

}