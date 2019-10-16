// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: person.proto

package com.meng.nettynio.nettyproto.bean;

/**
 * Protobuf type {@code com.meng.nettyproto.bean.StreamRequest}
 */
public  final class StreamRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.meng.nettyproto.bean.StreamRequest)
    StreamRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use StreamRequest.newBuilder() to construct.
  private StreamRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private StreamRequest() {
    requestName_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private StreamRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            requestName_ = s;
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.meng.nettynio.nettyproto.bean.Student.internal_static_com_meng_nettyproto_bean_StreamRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.meng.nettynio.nettyproto.bean.Student.internal_static_com_meng_nettyproto_bean_StreamRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.meng.nettynio.nettyproto.bean.StreamRequest.class, com.meng.nettynio.nettyproto.bean.StreamRequest.Builder.class);
  }

  public static final int REQUEST_NAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object requestName_;
  /**
   * <code>string request_name = 1;</code>
   */
  public java.lang.String getRequestName() {
    java.lang.Object ref = requestName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      requestName_ = s;
      return s;
    }
  }
  /**
   * <code>string request_name = 1;</code>
   */
  public com.google.protobuf.ByteString
      getRequestNameBytes() {
    java.lang.Object ref = requestName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      requestName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getRequestNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, requestName_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getRequestNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, requestName_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.meng.nettynio.nettyproto.bean.StreamRequest)) {
      return super.equals(obj);
    }
    com.meng.nettynio.nettyproto.bean.StreamRequest other = (com.meng.nettynio.nettyproto.bean.StreamRequest) obj;

    if (!getRequestName()
        .equals(other.getRequestName())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + REQUEST_NAME_FIELD_NUMBER;
    hash = (53 * hash) + getRequestName().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.meng.nettynio.nettyproto.bean.StreamRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.meng.nettynio.nettyproto.bean.StreamRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.meng.nettynio.nettyproto.bean.StreamRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.meng.nettynio.nettyproto.bean.StreamRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.meng.nettynio.nettyproto.bean.StreamRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.meng.nettynio.nettyproto.bean.StreamRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.meng.nettynio.nettyproto.bean.StreamRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.meng.nettynio.nettyproto.bean.StreamRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.meng.nettynio.nettyproto.bean.StreamRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.meng.nettynio.nettyproto.bean.StreamRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.meng.nettynio.nettyproto.bean.StreamRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.meng.nettynio.nettyproto.bean.StreamRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.meng.nettynio.nettyproto.bean.StreamRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.meng.nettyproto.bean.StreamRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.meng.nettyproto.bean.StreamRequest)
      com.meng.nettynio.nettyproto.bean.StreamRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.meng.nettynio.nettyproto.bean.Student.internal_static_com_meng_nettyproto_bean_StreamRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.meng.nettynio.nettyproto.bean.Student.internal_static_com_meng_nettyproto_bean_StreamRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.meng.nettynio.nettyproto.bean.StreamRequest.class, com.meng.nettynio.nettyproto.bean.StreamRequest.Builder.class);
    }

    // Construct using com.meng.nettynio.nettyproto.bean.StreamRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      requestName_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.meng.nettynio.nettyproto.bean.Student.internal_static_com_meng_nettyproto_bean_StreamRequest_descriptor;
    }

    @java.lang.Override
    public com.meng.nettynio.nettyproto.bean.StreamRequest getDefaultInstanceForType() {
      return com.meng.nettynio.nettyproto.bean.StreamRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.meng.nettynio.nettyproto.bean.StreamRequest build() {
      com.meng.nettynio.nettyproto.bean.StreamRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.meng.nettynio.nettyproto.bean.StreamRequest buildPartial() {
      com.meng.nettynio.nettyproto.bean.StreamRequest result = new com.meng.nettynio.nettyproto.bean.StreamRequest(this);
      result.requestName_ = requestName_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.meng.nettynio.nettyproto.bean.StreamRequest) {
        return mergeFrom((com.meng.nettynio.nettyproto.bean.StreamRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.meng.nettynio.nettyproto.bean.StreamRequest other) {
      if (other == com.meng.nettynio.nettyproto.bean.StreamRequest.getDefaultInstance()) return this;
      if (!other.getRequestName().isEmpty()) {
        requestName_ = other.requestName_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.meng.nettynio.nettyproto.bean.StreamRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.meng.nettynio.nettyproto.bean.StreamRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object requestName_ = "";
    /**
     * <code>string request_name = 1;</code>
     */
    public java.lang.String getRequestName() {
      java.lang.Object ref = requestName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        requestName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string request_name = 1;</code>
     */
    public com.google.protobuf.ByteString
        getRequestNameBytes() {
      java.lang.Object ref = requestName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        requestName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string request_name = 1;</code>
     */
    public Builder setRequestName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      requestName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string request_name = 1;</code>
     */
    public Builder clearRequestName() {
      
      requestName_ = getDefaultInstance().getRequestName();
      onChanged();
      return this;
    }
    /**
     * <code>string request_name = 1;</code>
     */
    public Builder setRequestNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      requestName_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.meng.nettyproto.bean.StreamRequest)
  }

  // @@protoc_insertion_point(class_scope:com.meng.nettyproto.bean.StreamRequest)
  private static final com.meng.nettynio.nettyproto.bean.StreamRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.meng.nettynio.nettyproto.bean.StreamRequest();
  }

  public static com.meng.nettynio.nettyproto.bean.StreamRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<StreamRequest>
      PARSER = new com.google.protobuf.AbstractParser<StreamRequest>() {
    @java.lang.Override
    public StreamRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new StreamRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<StreamRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<StreamRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.meng.nettynio.nettyproto.bean.StreamRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

