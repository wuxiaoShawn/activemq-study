package com.shawnNicole.jmstest.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.activation.MailcapCommandMap;

/**
 * @ProjectName: activemq-study
 * @Package: com.shawnNicole.jmstest.spring
 * @ClassName: QueueReceiver
 * @Description: []
 * @Author: [Shawn Wu]
 * @Date: 2022/2/11 18:11
 * @Version: V1.0
 * @Copyright: 2021  Inc. All rights reserved.
 * @TODO: 注意, 本文件Shawn Wu 所作,如果转载或使用请标明具体出处!
 **/
@Service
public class QueueReceiver {
    @Autowired
    private JmsTemplate jmsTemplate = null;

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        QueueReceiver ct = (QueueReceiver)ctx.getBean("queueReceiver");
        String msg = (String) ct.jmsTemplate.receiveAndConvert();
        System.out.println("msg:"+msg);
    }
}