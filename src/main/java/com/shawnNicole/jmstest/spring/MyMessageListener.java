package com.shawnNicole.jmstest.spring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @ProjectName: activemq-study
 * @Package: com.shawnNicole.jmstest.spring
 * @ClassName: MyMessageListener
 * @Description: []
 * @Author: [Shawn Wu]
 * @Date: 2022/2/12 00:43
 * @Version: V1.0
 * @Copyright: 2021  Inc. All rights reserved.
 * @TODO: 注意, 本文件Shawn Wu 所作,如果转载或使用请标明具体出处!
 **/
public class MyMessageListener implements MessageListener {


    @Override
    public void onMessage(Message message) {
        TextMessage msg = (TextMessage) message;
        System.out.println("receive msg:"+msg);
    }
}