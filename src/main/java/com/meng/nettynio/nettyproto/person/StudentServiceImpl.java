package com.meng.nettynio.nettyproto.person;

import com.meng.nettynio.nettyproto.bean.*;
import io.grpc.stub.StreamObserver;

public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {

    @Override
    public void getRealNameByUserName(MyRequest request, StreamObserver<MyReply> responseObserver) {
        System.out.println("接收到客户端信息 ：" + request.getUserName());
        responseObserver.onNext(MyReply.newBuilder().setRealName("real" + request.getUserName()).build());
        responseObserver.onCompleted();
        System.out.println("----------------------");
    }

    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        System.out.println("接收到客户端信息 ：" + request.getAge());
        responseObserver.onNext(StudentResponse.newBuilder().setAge(20).setCity("bj").setName("ss1").build());
        responseObserver.onNext(StudentResponse.newBuilder().setAge(21).setCity("bj").setName("ss2").build());
        responseObserver.onNext(StudentResponse.newBuilder().setAge(22).setCity("bj").setName("ss3").build());
        responseObserver.onCompleted();
        System.out.println("----------------------");
    }

    @Override
    public StreamObserver<StudentRequest> getStudentsWrapperByAge(StreamObserver<StudentResponseList> responseObserver) {
        return new StreamObserver<StudentRequest>() {
            @Override
            public void onNext(StudentRequest studentRequest) {
                System.out.println("onNext :" + studentRequest.getAge());
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                StudentResponse response = StudentResponse.newBuilder().setAge(21).setCity("bj").setName("ss1").build();
                StudentResponse response2 = StudentResponse.newBuilder().setAge(22).setCity("bj").setName("ss2").build();
                StudentResponseList studentResponseList = StudentResponseList.newBuilder()
                        .addStudentResponse(response).addStudentResponse(response2).build();
                responseObserver.onNext(studentResponseList);
                responseObserver.onCompleted();
                System.out.println("onCompleted");
                System.out.println("---------------------");
            }
        };
    }

    @Override
    public StreamObserver<StreamRequest> biTalk(StreamObserver<StreamResponse> responseObserver) {
        return new StreamObserver<StreamRequest>() {
            @Override
            public void onNext(StreamRequest value) {
                System.out.println("onNext :" + value.getRequestName());
                responseObserver.onNext(StreamResponse.newBuilder().setResponseName("stream" + value.getRequestName()).build());
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
                System.out.println("onCompleted");
                System.out.println("------------------------");
            }
        };
    }
}
