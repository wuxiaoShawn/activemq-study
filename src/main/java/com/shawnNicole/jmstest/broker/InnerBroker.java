package com.shawnNicole.jmstest.broker;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.context.ApplicationContext;
import org.xbean.spring.context.ClassPathXmlApplicationContext;

import java.net.URI;

/**
 * @ProjectName: activemq-study
 * @Package: com.shawnNicole.jmstest.broker
 * @ClassName: InnerBroker
 * @Description: []
 * @Author: [Shawn Wu]
 * @Date: 2022/2/3 02:22
 * @Version: V1.0
 * @Copyright: 2021  Inc. All rights reserved.
 * @TODO: 注意, 本文件Shawn Wu 所作,如果转载或使用请标明具体出处!
 **/
public class InnerBroker {
    public static void main(String[] args) throws Exception {
        //BrokerService broker = new BrokerService();
        //broker.setUseJmx(true);
        //broker.addConnector("tcp://localhost:61616");
        //broker.start();

        //String Uri = "properties:broker.properties";
        //BrokerService broker = BrokerFactory.createBroker(new URI(Uri));
        //broker.addConnector("tcp://localhost:61616");
        //broker.start();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");


    }

}