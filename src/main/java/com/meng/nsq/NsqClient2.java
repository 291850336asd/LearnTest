package com.meng.nsq;

import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;

public class NsqClient2 {
    public static void main(String[] args) {
        NSQLookup lookup = new DefaultNSQLookup();
        lookup.addLookupAddress("192.168.163.128", 4161);
        NSQConsumer consumer = new NSQConsumer(lookup, "TestTopic" ,"hha", (message) -> {
            System.out.println("received: " + new String(message.getMessage()));
            //now mark the message as finished.
            message.finished();

            //or you could requeue it, which indicates a failure and puts it back on the queue.
            //message.requeue();
        });

        consumer.start();
    }
}
