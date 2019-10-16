package com.meng.nettynio.nettyproto.person;

import com.meng.nettynio.nettyproto.bean.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class PersonClient {
    private final ManagedChannel channel;
    //同步
    private final StudentServiceGrpc.StudentServiceBlockingStub blockingStub;
    //异步请求
    private final StudentServiceGrpc.StudentServiceStub studentServiceStub;
    /** Construct client connecting to HelloWorld server at {@code host:port}. */
    public PersonClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                // Channels are secure by default (via SSL/TLS). For the nettyexample we disable TLS to avoid
                // needing certificates.
                .usePlaintext()
                .build());
    }
    /** Construct client for accessing HelloWorld server using the existing channel. */
    PersonClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = StudentServiceGrpc.newBlockingStub(channel);
        studentServiceStub = StudentServiceGrpc.newStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /** Say hello to server. */
    public void getRealName() {
        MyRequest request = MyRequest.newBuilder().setUserName("meng").build();
        MyReply response;
        try {
            response = blockingStub.getRealNameByUserName(request);
        } catch (StatusRuntimeException e) {
            return;
        }
        System.out.println("reanName : " + response.getRealName());
        System.out.println("--------------------------");

        Iterator<StudentResponse> iterator = blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(19).build());
        while (iterator.hasNext()){
            StudentResponse studentResponse = iterator.next();
            System.out.println(studentResponse);
        }
        System.out.println("--------------------------");

        StreamObserver<StudentResponseList> streamObserverResponse = new StreamObserver<StudentResponseList>() {
            @Override
            public void onNext(StudentResponseList studentResponseList) {
                studentResponseList.getStudentResponseList().forEach(studentResponse -> {
                    System.out.println(studentResponse);
                });
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("stream onCompleted");
            }
        };
        StreamObserver<StudentRequest> streamObserverRequest = studentServiceStub.getStudentsWrapperByAge(streamObserverResponse);
        streamObserverRequest.onNext(StudentRequest.newBuilder().setAge(30).build());
        streamObserverRequest.onNext(StudentRequest.newBuilder().setAge(31).build());
        streamObserverRequest.onNext(StudentRequest.newBuilder().setAge(32).build());
        streamObserverRequest.onNext(StudentRequest.newBuilder().setAge(33).build());
        streamObserverRequest.onCompleted();
        System.out.println("--------------------------");

        StreamObserver<StreamResponse> streamResponseStreamObserver = new StreamObserver<StreamResponse>() {
            @Override
            public void onNext(StreamResponse value) {
                System.out.println("receive :" + value);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("stream onCompleted");
            }
        };
        StreamObserver<StreamRequest> streamRequestStreamObserver = studentServiceStub.biTalk(streamResponseStreamObserver);
        for (int i = 0; i < 10; i++) {
            streamRequestStreamObserver.onNext(StreamRequest.newBuilder().setRequestName("sss" + i).build());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        streamRequestStreamObserver.onCompleted();
        System.out.println("onCompleted");
    }


    /**
     * Greet server. If provided, the first element of {@code args} is the name to use in the
     * greeting.
     */
    public static void main(String[] args) throws Exception {
        PersonClient client = new PersonClient("localhost", 50051);
        try {
            client.getRealName();
        } finally {
            client.shutdown();
        }
    }
}
