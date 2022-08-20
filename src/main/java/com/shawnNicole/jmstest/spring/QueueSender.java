package com.shawnNicole.jmstest.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @ProjectName: activemq-study
 * @Package: com.shawnNicole.jmstest.spring
 * @ClassName: QueueSender
 * @Description: []
 * @Author: [Shawn Wu]
 * @Date: 2022/2/11 15:09
 * @Version: V1.0
 * @Copyright: 2021  Inc. All rights reserved.
 * @TODO: 注意, 本文件Shawn Wu 所作,如果转载或使用请标明具体出处!
 **/
@Service
public class QueueSender {
    @Autowired
    private JmsTemplate jmsTemplate = null;

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        QueueSender ct = (QueueSender) ctx.getBean("queueSender");
        ct.jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage msg = session.createTextMessage("Spring msg111====");
                return msg;
            }
        });
    }

}