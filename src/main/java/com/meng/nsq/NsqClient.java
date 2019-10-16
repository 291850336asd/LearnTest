package com.meng.nsq;

import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;

public class NsqClient {
    public static void main(String[] args) {
        NSQLookup lookup = new DefaultNSQLookup();
        lookup.addLookupAddress("192.168.163.128", 4161);
        NSQConsumer consumer = new NSQConsumer(lookup, "test" ,"nsq_to_file", (message) -> {
            System.out.println("received: " + new String(message.getMessage()));
            //now mark the message as finished.
            message.finished();

            //or you could requeue it, which indicates a failure and puts it back on the queue.
            //message.requeue();
        });

//        By default Backoff does not kick in and a consumer will eat all your memory and CPU. To enable Backoff you have to set your own thread pool executer with:
//
//        consumer.setExecutor(...);
        consumer.start();
    }
}
