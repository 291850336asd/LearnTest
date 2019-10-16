package com.meng.nettynio.nettyproto.bean;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * The greeting service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.21.0)",
    comments = "Source: person.proto")
public final class StudentServiceGrpc {

  private StudentServiceGrpc() {}

  public static final String SERVICE_NAME = "com.meng.nettyproto.bean.StudentService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.meng.nettynio.nettyproto.bean.MyRequest,
      com.meng.nettynio.nettyproto.bean.MyReply> getGetRealNameByUserNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetRealNameByUserName",
      requestType = com.meng.nettynio.nettyproto.bean.MyRequest.class,
      responseType = com.meng.nettynio.nettyproto.bean.MyReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.meng.nettynio.nettyproto.bean.MyRequest,
      com.meng.nettynio.nettyproto.bean.MyReply> getGetRealNameByUserNameMethod() {
    io.grpc.MethodDescriptor<com.meng.nettynio.nettyproto.bean.MyRequest, com.meng.nettynio.nettyproto.bean.MyReply> getGetRealNameByUserNameMethod;
    if ((getGetRealNameByUserNameMethod = StudentServiceGrpc.getGetRealNameByUserNameMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetRealNameByUserNameMethod = StudentServiceGrpc.getGetRealNameByUserNameMethod) == null) {
          StudentServiceGrpc.getGetRealNameByUserNameMethod = getGetRealNameByUserNameMethod = 
              io.grpc.MethodDescriptor.<com.meng.nettynio.nettyproto.bean.MyRequest, com.meng.nettynio.nettyproto.bean.MyReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.meng.nettyproto.bean.StudentService", "GetRealNameByUserName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.meng.nettynio.nettyproto.bean.MyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.meng.nettynio.nettyproto.bean.MyReply.getDefaultInstance()))
                  .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetRealNameByUserName"))
                  .build();
          }
        }
     }
     return getGetRealNameByUserNameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.meng.nettynio.nettyproto.bean.StudentRequest,
      com.meng.nettynio.nettyproto.bean.StudentResponse> getGetStudentsByAgeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStudentsByAge",
      requestType = com.meng.nettynio.nettyproto.bean.StudentRequest.class,
      responseType = com.meng.nettynio.nettyproto.bean.StudentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.meng.nettynio.nettyproto.bean.StudentRequest,
      com.meng.nettynio.nettyproto.bean.StudentResponse> getGetStudentsByAgeMethod() {
    io.grpc.MethodDescriptor<com.meng.nettynio.nettyproto.bean.StudentRequest, com.meng.nettynio.nettyproto.bean.StudentResponse> getGetStudentsByAgeMethod;
    if ((getGetStudentsByAgeMethod = StudentServiceGrpc.getGetStudentsByAgeMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetStudentsByAgeMethod = StudentServiceGrpc.getGetStudentsByAgeMethod) == null) {
          StudentServiceGrpc.getGetStudentsByAgeMethod = getGetStudentsByAgeMethod = 
              io.grpc.MethodDescriptor.<com.meng.nettynio.nettyproto.bean.StudentRequest, com.meng.nettynio.nettyproto.bean.StudentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.meng.nettyproto.bean.StudentService", "GetStudentsByAge"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.meng.nettynio.nettyproto.bean.StudentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.meng.nettynio.nettyproto.bean.StudentResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetStudentsByAge"))
                  .build();
          }
        }
     }
     return getGetStudentsByAgeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.meng.nettynio.nettyproto.bean.StudentRequest,
      com.meng.nettynio.nettyproto.bean.StudentResponseList> getGetStudentsWrapperByAgeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStudentsWrapperByAge",
      requestType = com.meng.nettynio.nettyproto.bean.StudentRequest.class,
      responseType = com.meng.nettynio.nettyproto.bean.StudentResponseList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.meng.nettynio.nettyproto.bean.StudentRequest,
      com.meng.nettynio.nettyproto.bean.StudentResponseList> getGetStudentsWrapperByAgeMethod() {
    io.grpc.MethodDescriptor<com.meng.nettynio.nettyproto.bean.StudentRequest, com.meng.nettynio.nettyproto.bean.StudentResponseList> getGetStudentsWrapperByAgeMethod;
    if ((getGetStudentsWrapperByAgeMethod = StudentServiceGrpc.getGetStudentsWrapperByAgeMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetStudentsWrapperByAgeMethod = StudentServiceGrpc.getGetStudentsWrapperByAgeMethod) == null) {
          StudentServiceGrpc.getGetStudentsWrapperByAgeMethod = getGetStudentsWrapperByAgeMethod = 
              io.grpc.MethodDescriptor.<com.meng.nettynio.nettyproto.bean.StudentRequest, com.meng.nettynio.nettyproto.bean.StudentResponseList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.meng.nettyproto.bean.StudentService", "GetStudentsWrapperByAge"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.meng.nettynio.nettyproto.bean.StudentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.meng.nettynio.nettyproto.bean.StudentResponseList.getDefaultInstance()))
                  .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetStudentsWrapperByAge"))
                  .build();
          }
        }
     }
     return getGetStudentsWrapperByAgeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.meng.nettynio.nettyproto.bean.StreamRequest,
      com.meng.nettynio.nettyproto.bean.StreamResponse> getBiTalkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BiTalk",
      requestType = com.meng.nettynio.nettyproto.bean.StreamRequest.class,
      responseType = com.meng.nettynio.nettyproto.bean.StreamResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.meng.nettynio.nettyproto.bean.StreamRequest,
      com.meng.nettynio.nettyproto.bean.StreamResponse> getBiTalkMethod() {
    io.grpc.MethodDescriptor<com.meng.nettynio.nettyproto.bean.StreamRequest, com.meng.nettynio.nettyproto.bean.StreamResponse> getBiTalkMethod;
    if ((getBiTalkMethod = StudentServiceGrpc.getBiTalkMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getBiTalkMethod = StudentServiceGrpc.getBiTalkMethod) == null) {
          StudentServiceGrpc.getBiTalkMethod = getBiTalkMethod = 
              io.grpc.MethodDescriptor.<com.meng.nettynio.nettyproto.bean.StreamRequest, com.meng.nettynio.nettyproto.bean.StreamResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.meng.nettyproto.bean.StudentService", "BiTalk"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.meng.nettynio.nettyproto.bean.StreamRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.meng.nettynio.nettyproto.bean.StreamResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("BiTalk"))
                  .build();
          }
        }
     }
     return getBiTalkMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StudentServiceStub newStub(io.grpc.Channel channel) {
    return new StudentServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StudentServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new StudentServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StudentServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new StudentServiceFutureStub(channel);
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static abstract class StudentServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getRealNameByUserName(com.meng.nettynio.nettyproto.bean.MyRequest request,
        io.grpc.stub.StreamObserver<com.meng.nettynio.nettyproto.bean.MyReply> responseObserver) {
      asyncUnimplementedUnaryCall(getGetRealNameByUserNameMethod(), responseObserver);
    }

    /**
     */
    public void getStudentsByAge(com.meng.nettynio.nettyproto.bean.StudentRequest request,
        io.grpc.stub.StreamObserver<com.meng.nettynio.nettyproto.bean.StudentResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetStudentsByAgeMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.meng.nettynio.nettyproto.bean.StudentRequest> getStudentsWrapperByAge(
        io.grpc.stub.StreamObserver<com.meng.nettynio.nettyproto.bean.StudentResponseList> responseObserver) {
      return asyncUnimplementedStreamingCall(getGetStudentsWrapperByAgeMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.meng.nettynio.nettyproto.bean.StreamRequest> biTalk(
        io.grpc.stub.StreamObserver<com.meng.nettynio.nettyproto.bean.StreamResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getBiTalkMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetRealNameByUserNameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.meng.nettynio.nettyproto.bean.MyRequest,
                com.meng.nettynio.nettyproto.bean.MyReply>(
                  this, METHODID_GET_REAL_NAME_BY_USER_NAME)))
          .addMethod(
            getGetStudentsByAgeMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.meng.nettynio.nettyproto.bean.StudentRequest,
                com.meng.nettynio.nettyproto.bean.StudentResponse>(
                  this, METHODID_GET_STUDENTS_BY_AGE)))
          .addMethod(
            getGetStudentsWrapperByAgeMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.meng.nettynio.nettyproto.bean.StudentRequest,
                com.meng.nettynio.nettyproto.bean.StudentResponseList>(
                  this, METHODID_GET_STUDENTS_WRAPPER_BY_AGE)))
          .addMethod(
            getBiTalkMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.meng.nettynio.nettyproto.bean.StreamRequest,
                com.meng.nettynio.nettyproto.bean.StreamResponse>(
                  this, METHODID_BI_TALK)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class StudentServiceStub extends io.grpc.stub.AbstractStub<StudentServiceStub> {
    private StudentServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceStub(channel, callOptions);
    }

    /**
     */
    public void getRealNameByUserName(com.meng.nettynio.nettyproto.bean.MyRequest request,
        io.grpc.stub.StreamObserver<com.meng.nettynio.nettyproto.bean.MyReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetRealNameByUserNameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getStudentsByAge(com.meng.nettynio.nettyproto.bean.StudentRequest request,
        io.grpc.stub.StreamObserver<com.meng.nettynio.nettyproto.bean.StudentResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetStudentsByAgeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.meng.nettynio.nettyproto.bean.StudentRequest> getStudentsWrapperByAge(
        io.grpc.stub.StreamObserver<com.meng.nettynio.nettyproto.bean.StudentResponseList> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getGetStudentsWrapperByAgeMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.meng.nettynio.nettyproto.bean.StreamRequest> biTalk(
        io.grpc.stub.StreamObserver<com.meng.nettynio.nettyproto.bean.StreamResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getBiTalkMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class StudentServiceBlockingStub extends io.grpc.stub.AbstractStub<StudentServiceBlockingStub> {
    private StudentServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.meng.nettynio.nettyproto.bean.MyReply getRealNameByUserName(com.meng.nettynio.nettyproto.bean.MyRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetRealNameByUserNameMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.meng.nettynio.nettyproto.bean.StudentResponse> getStudentsByAge(
        com.meng.nettynio.nettyproto.bean.StudentRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getGetStudentsByAgeMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class StudentServiceFutureStub extends io.grpc.stub.AbstractStub<StudentServiceFutureStub> {
    private StudentServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.meng.nettynio.nettyproto.bean.MyReply> getRealNameByUserName(
        com.meng.nettynio.nettyproto.bean.MyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetRealNameByUserNameMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_REAL_NAME_BY_USER_NAME = 0;
  private static final int METHODID_GET_STUDENTS_BY_AGE = 1;
  private static final int METHODID_GET_STUDENTS_WRAPPER_BY_AGE = 2;
  private static final int METHODID_BI_TALK = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final StudentServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(StudentServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_REAL_NAME_BY_USER_NAME:
          serviceImpl.getRealNameByUserName((com.meng.nettynio.nettyproto.bean.MyRequest) request,
              (io.grpc.stub.StreamObserver<com.meng.nettynio.nettyproto.bean.MyReply>) responseObserver);
          break;
        case METHODID_GET_STUDENTS_BY_AGE:
          serviceImpl.getStudentsByAge((com.meng.nettynio.nettyproto.bean.StudentRequest) request,
              (io.grpc.stub.StreamObserver<com.meng.nettynio.nettyproto.bean.StudentResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_STUDENTS_WRAPPER_BY_AGE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getStudentsWrapperByAge(
              (io.grpc.stub.StreamObserver<com.meng.nettynio.nettyproto.bean.StudentResponseList>) responseObserver);
        case METHODID_BI_TALK:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.biTalk(
              (io.grpc.stub.StreamObserver<com.meng.nettynio.nettyproto.bean.StreamResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class StudentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StudentServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.meng.nettynio.nettyproto.bean.Student.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StudentService");
    }
  }

  private static final class StudentServiceFileDescriptorSupplier
      extends StudentServiceBaseDescriptorSupplier {
    StudentServiceFileDescriptorSupplier() {}
  }

  private static final class StudentServiceMethodDescriptorSupplier
      extends StudentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    StudentServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (StudentServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StudentServiceFileDescriptorSupplier())
              .addMethod(getGetRealNameByUserNameMethod())
              .addMethod(getGetStudentsByAgeMethod())
              .addMethod(getGetStudentsWrapperByAgeMethod())
              .addMethod(getBiTalkMethod())
              .build();
        }
      }
    }
    return result;
  }
}
