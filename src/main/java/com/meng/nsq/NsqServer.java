package com.meng.nsq;

import com.github.brainlag.nsq.NSQProducer;
import com.github.brainlag.nsq.exceptions.NSQException;

import java.util.concurrent.TimeoutException;


//https://stackoverflow.com/questions/48536963/nsq-cannot-consume-message-by-connecting-to-nsqlookupd
// ./nsqd --lookupd-tcp-address=192.168.163.128:4160 -broadcast-address=192.168.163.128
public class NsqServer {

    public static void main(String[] args) throws NSQException, TimeoutException {
        NSQProducer producer = new NSQProducer().addAddress("192.168.163.128", 4150).start();
        producer.produce("TestTopic", ("this is a message").getBytes());
    }
}
