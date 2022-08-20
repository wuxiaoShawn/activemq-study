package com.shawnNicole.jmstest.kahaDB;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.store.kahadb.KahaDBStore;

import java.io.File;

/**
 * @ProjectName: activemq-study
 * @Package: com.shawnNicole.jmstest.kahaDB
 * @ClassName: EmbededBrokerUsingKahaDBStoreExample
 * @Description: []
 * @Author: [Shawn Wu]
 * @Date: 2022/2/12 16:20
 * @Version: V1.0
 * @Copyright: 2021  Inc. All rights reserved.
 * @TODO: 注意, 本文件Shawn Wu 所作,如果转载或使用请标明具体出处!
 **/
public class EmbededBrokerUsingKahaDBStoreExample {
    //创建java内置broker时定义及配置kahadb
    BrokerService createEmbededBroker() throws Exception{
        BrokerService broker = new BrokerService();
        File dateFileDir = new File("target/amq-in-action/kahadb");
        KahaDBStore kaha = new KahaDBStore();
        kaha.setDirectory(dateFileDir);
        kaha.setJournalMaxFileLength(1024*100);
        kaha.setIndexWriteBatchSize(100);
        kaha.setEnableIndexWriteAsync(true);
        broker.setPersistenceAdapter(kaha);
        broker.addConnector("tcp://127.0.0.1:61616");
        broker.start();
        return broker;
    }

}