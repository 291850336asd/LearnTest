// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: person.proto

package com.meng.nettynio.nettyproto.bean;

public final class Student {
  private Student() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_meng_nettyproto_bean_StreamResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_meng_nettyproto_bean_StreamResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_meng_nettyproto_bean_StreamRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_meng_nettyproto_bean_StreamRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_meng_nettyproto_bean_StudentRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_meng_nettyproto_bean_StudentRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_meng_nettyproto_bean_StudentResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_meng_nettyproto_bean_StudentResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_meng_nettyproto_bean_StudentResponseList_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_meng_nettyproto_bean_StudentResponseList_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_meng_nettyproto_bean_MyRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_meng_nettyproto_bean_MyRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_meng_nettyproto_bean_MyReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_meng_nettyproto_bean_MyReply_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014person.proto\022\030com.meng.nettyproto.bean" +
      "\"\'\n\016StreamResponse\022\025\n\rresponse_name\030\001 \001(" +
      "\t\"%\n\rStreamRequest\022\024\n\014request_name\030\001 \001(\t" +
      "\"\035\n\016StudentRequest\022\013\n\003age\030\001 \001(\005\":\n\017Stude" +
      "ntResponse\022\014\n\004name\030\001 \001(\t\022\013\n\003age\030\002 \001(\005\022\014\n" +
      "\004city\030\003 \001(\t\"Y\n\023StudentResponseList\022B\n\017st" +
      "udentResponse\030\001 \003(\0132).com.meng.nettyprot" +
      "o.bean.StudentResponse\"\036\n\tMyRequest\022\021\n\tu" +
      "ser_name\030\001 \001(\t\"\034\n\007MyReply\022\021\n\treal_name\030\001" +
      " \001(\t2\273\003\n\016StudentService\022a\n\025GetRealNameBy" +
      "UserName\022#.com.meng.nettyproto.bean.MyRe" +
      "quest\032!.com.meng.nettyproto.bean.MyReply" +
      "\"\000\022k\n\020GetStudentsByAge\022(.com.meng.nettyp" +
      "roto.bean.StudentRequest\032).com.meng.nett" +
      "yproto.bean.StudentResponse\"\0000\001\022v\n\027GetSt" +
      "udentsWrapperByAge\022(.com.meng.nettyproto" +
      ".bean.StudentRequest\032-.com.meng.nettypro" +
      "to.bean.StudentResponseList\"\000(\001\022a\n\006BiTal" +
      "k\022\'.com.meng.nettyproto.bean.StreamReque" +
      "st\032(.com.meng.nettyproto.bean.StreamResp" +
      "onse\"\000(\0010\001B.\n!com.meng.nettynio.nettypro" +
      "to.beanB\007StudentP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_meng_nettyproto_bean_StreamResponse_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_meng_nettyproto_bean_StreamResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_meng_nettyproto_bean_StreamResponse_descriptor,
        new java.lang.String[] { "ResponseName", });
    internal_static_com_meng_nettyproto_bean_StreamRequest_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_meng_nettyproto_bean_StreamRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_meng_nettyproto_bean_StreamRequest_descriptor,
        new java.lang.String[] { "RequestName", });
    internal_static_com_meng_nettyproto_bean_StudentRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_com_meng_nettyproto_bean_StudentRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_meng_nettyproto_bean_StudentRequest_descriptor,
        new java.lang.String[] { "Age", });
    internal_static_com_meng_nettyproto_bean_StudentResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_com_meng_nettyproto_bean_StudentResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_meng_nettyproto_bean_StudentResponse_descriptor,
        new java.lang.String[] { "Name", "Age", "City", });
    internal_static_com_meng_nettyproto_bean_StudentResponseList_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_com_meng_nettyproto_bean_StudentResponseList_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_meng_nettyproto_bean_StudentResponseList_descriptor,
        new java.lang.String[] { "StudentResponse", });
    internal_static_com_meng_nettyproto_bean_MyRequest_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_com_meng_nettyproto_bean_MyRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_meng_nettyproto_bean_MyRequest_descriptor,
        new java.lang.String[] { "UserName", });
    internal_static_com_meng_nettyproto_bean_MyReply_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_com_meng_nettyproto_bean_MyReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_meng_nettyproto_bean_MyReply_descriptor,
        new java.lang.String[] { "RealName", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
