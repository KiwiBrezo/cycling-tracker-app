// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AddRide.proto

package si.um.feri.cycling_tracker_app.grpc;

/**
 * Protobuf type {@code si.um.feri.cycling_tracker_app.grpc.AddRideRequest}
 */
public final class AddRideRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:si.um.feri.cycling_tracker_app.grpc.AddRideRequest)
    AddRideRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use AddRideRequest.newBuilder() to construct.
  private AddRideRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AddRideRequest() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new AddRideRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return si.um.feri.cycling_tracker_app.grpc.AddRide.internal_static_si_um_feri_cycling_tracker_app_grpc_AddRideRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return si.um.feri.cycling_tracker_app.grpc.AddRide.internal_static_si_um_feri_cycling_tracker_app_grpc_AddRideRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            si.um.feri.cycling_tracker_app.grpc.AddRideRequest.class, si.um.feri.cycling_tracker_app.grpc.AddRideRequest.Builder.class);
  }

  public static final int RIDE_ID_FIELD_NUMBER = 1;
  private int rideId_ = 0;
  /**
   * <code>int32 ride_id = 1;</code>
   * @return The rideId.
   */
  @java.lang.Override
  public int getRideId() {
    return rideId_;
  }

  public static final int TIMESTART_FIELD_NUMBER = 2;
  private long timeStart_ = 0L;
  /**
   * <code>uint64 timeStart = 2;</code>
   * @return The timeStart.
   */
  @java.lang.Override
  public long getTimeStart() {
    return timeStart_;
  }

  public static final int TIMESTOP_FIELD_NUMBER = 3;
  private long timeStop_ = 0L;
  /**
   * <code>uint64 timeStop = 3;</code>
   * @return The timeStop.
   */
  @java.lang.Override
  public long getTimeStop() {
    return timeStop_;
  }

  public static final int DURATION_FIELD_NUMBER = 4;
  private long duration_ = 0L;
  /**
   * <code>uint64 duration = 4;</code>
   * @return The duration.
   */
  @java.lang.Override
  public long getDuration() {
    return duration_;
  }

  public static final int USER_ID_FIELD_NUMBER = 5;
  private int userId_ = 0;
  /**
   * <code>int32 user_id = 5;</code>
   * @return The userId.
   */
  @java.lang.Override
  public int getUserId() {
    return userId_;
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
    if (rideId_ != 0) {
      output.writeInt32(1, rideId_);
    }
    if (timeStart_ != 0L) {
      output.writeUInt64(2, timeStart_);
    }
    if (timeStop_ != 0L) {
      output.writeUInt64(3, timeStop_);
    }
    if (duration_ != 0L) {
      output.writeUInt64(4, duration_);
    }
    if (userId_ != 0) {
      output.writeInt32(5, userId_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (rideId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, rideId_);
    }
    if (timeStart_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(2, timeStart_);
    }
    if (timeStop_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(3, timeStop_);
    }
    if (duration_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(4, duration_);
    }
    if (userId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, userId_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof si.um.feri.cycling_tracker_app.grpc.AddRideRequest)) {
      return super.equals(obj);
    }
    si.um.feri.cycling_tracker_app.grpc.AddRideRequest other = (si.um.feri.cycling_tracker_app.grpc.AddRideRequest) obj;

    if (getRideId()
        != other.getRideId()) return false;
    if (getTimeStart()
        != other.getTimeStart()) return false;
    if (getTimeStop()
        != other.getTimeStop()) return false;
    if (getDuration()
        != other.getDuration()) return false;
    if (getUserId()
        != other.getUserId()) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + RIDE_ID_FIELD_NUMBER;
    hash = (53 * hash) + getRideId();
    hash = (37 * hash) + TIMESTART_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getTimeStart());
    hash = (37 * hash) + TIMESTOP_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getTimeStop());
    hash = (37 * hash) + DURATION_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getDuration());
    hash = (37 * hash) + USER_ID_FIELD_NUMBER;
    hash = (53 * hash) + getUserId();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static si.um.feri.cycling_tracker_app.grpc.AddRideRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static si.um.feri.cycling_tracker_app.grpc.AddRideRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static si.um.feri.cycling_tracker_app.grpc.AddRideRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static si.um.feri.cycling_tracker_app.grpc.AddRideRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static si.um.feri.cycling_tracker_app.grpc.AddRideRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static si.um.feri.cycling_tracker_app.grpc.AddRideRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static si.um.feri.cycling_tracker_app.grpc.AddRideRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static si.um.feri.cycling_tracker_app.grpc.AddRideRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static si.um.feri.cycling_tracker_app.grpc.AddRideRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static si.um.feri.cycling_tracker_app.grpc.AddRideRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static si.um.feri.cycling_tracker_app.grpc.AddRideRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static si.um.feri.cycling_tracker_app.grpc.AddRideRequest parseFrom(
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
  public static Builder newBuilder(si.um.feri.cycling_tracker_app.grpc.AddRideRequest prototype) {
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
   * Protobuf type {@code si.um.feri.cycling_tracker_app.grpc.AddRideRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:si.um.feri.cycling_tracker_app.grpc.AddRideRequest)
      si.um.feri.cycling_tracker_app.grpc.AddRideRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return si.um.feri.cycling_tracker_app.grpc.AddRide.internal_static_si_um_feri_cycling_tracker_app_grpc_AddRideRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return si.um.feri.cycling_tracker_app.grpc.AddRide.internal_static_si_um_feri_cycling_tracker_app_grpc_AddRideRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              si.um.feri.cycling_tracker_app.grpc.AddRideRequest.class, si.um.feri.cycling_tracker_app.grpc.AddRideRequest.Builder.class);
    }

    // Construct using si.um.feri.cycling_tracker_app.grpc.AddRideRequest.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      rideId_ = 0;
      timeStart_ = 0L;
      timeStop_ = 0L;
      duration_ = 0L;
      userId_ = 0;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return si.um.feri.cycling_tracker_app.grpc.AddRide.internal_static_si_um_feri_cycling_tracker_app_grpc_AddRideRequest_descriptor;
    }

    @java.lang.Override
    public si.um.feri.cycling_tracker_app.grpc.AddRideRequest getDefaultInstanceForType() {
      return si.um.feri.cycling_tracker_app.grpc.AddRideRequest.getDefaultInstance();
    }

    @java.lang.Override
    public si.um.feri.cycling_tracker_app.grpc.AddRideRequest build() {
      si.um.feri.cycling_tracker_app.grpc.AddRideRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public si.um.feri.cycling_tracker_app.grpc.AddRideRequest buildPartial() {
      si.um.feri.cycling_tracker_app.grpc.AddRideRequest result = new si.um.feri.cycling_tracker_app.grpc.AddRideRequest(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(si.um.feri.cycling_tracker_app.grpc.AddRideRequest result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.rideId_ = rideId_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.timeStart_ = timeStart_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.timeStop_ = timeStop_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.duration_ = duration_;
      }
      if (((from_bitField0_ & 0x00000010) != 0)) {
        result.userId_ = userId_;
      }
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
      if (other instanceof si.um.feri.cycling_tracker_app.grpc.AddRideRequest) {
        return mergeFrom((si.um.feri.cycling_tracker_app.grpc.AddRideRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(si.um.feri.cycling_tracker_app.grpc.AddRideRequest other) {
      if (other == si.um.feri.cycling_tracker_app.grpc.AddRideRequest.getDefaultInstance()) return this;
      if (other.getRideId() != 0) {
        setRideId(other.getRideId());
      }
      if (other.getTimeStart() != 0L) {
        setTimeStart(other.getTimeStart());
      }
      if (other.getTimeStop() != 0L) {
        setTimeStop(other.getTimeStop());
      }
      if (other.getDuration() != 0L) {
        setDuration(other.getDuration());
      }
      if (other.getUserId() != 0) {
        setUserId(other.getUserId());
      }
      this.mergeUnknownFields(other.getUnknownFields());
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
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {
              rideId_ = input.readInt32();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            case 16: {
              timeStart_ = input.readUInt64();
              bitField0_ |= 0x00000002;
              break;
            } // case 16
            case 24: {
              timeStop_ = input.readUInt64();
              bitField0_ |= 0x00000004;
              break;
            } // case 24
            case 32: {
              duration_ = input.readUInt64();
              bitField0_ |= 0x00000008;
              break;
            } // case 32
            case 40: {
              userId_ = input.readInt32();
              bitField0_ |= 0x00000010;
              break;
            } // case 40
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private int rideId_ ;
    /**
     * <code>int32 ride_id = 1;</code>
     * @return The rideId.
     */
    @java.lang.Override
    public int getRideId() {
      return rideId_;
    }
    /**
     * <code>int32 ride_id = 1;</code>
     * @param value The rideId to set.
     * @return This builder for chaining.
     */
    public Builder setRideId(int value) {
      
      rideId_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>int32 ride_id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearRideId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      rideId_ = 0;
      onChanged();
      return this;
    }

    private long timeStart_ ;
    /**
     * <code>uint64 timeStart = 2;</code>
     * @return The timeStart.
     */
    @java.lang.Override
    public long getTimeStart() {
      return timeStart_;
    }
    /**
     * <code>uint64 timeStart = 2;</code>
     * @param value The timeStart to set.
     * @return This builder for chaining.
     */
    public Builder setTimeStart(long value) {
      
      timeStart_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>uint64 timeStart = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearTimeStart() {
      bitField0_ = (bitField0_ & ~0x00000002);
      timeStart_ = 0L;
      onChanged();
      return this;
    }

    private long timeStop_ ;
    /**
     * <code>uint64 timeStop = 3;</code>
     * @return The timeStop.
     */
    @java.lang.Override
    public long getTimeStop() {
      return timeStop_;
    }
    /**
     * <code>uint64 timeStop = 3;</code>
     * @param value The timeStop to set.
     * @return This builder for chaining.
     */
    public Builder setTimeStop(long value) {
      
      timeStop_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>uint64 timeStop = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearTimeStop() {
      bitField0_ = (bitField0_ & ~0x00000004);
      timeStop_ = 0L;
      onChanged();
      return this;
    }

    private long duration_ ;
    /**
     * <code>uint64 duration = 4;</code>
     * @return The duration.
     */
    @java.lang.Override
    public long getDuration() {
      return duration_;
    }
    /**
     * <code>uint64 duration = 4;</code>
     * @param value The duration to set.
     * @return This builder for chaining.
     */
    public Builder setDuration(long value) {
      
      duration_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <code>uint64 duration = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearDuration() {
      bitField0_ = (bitField0_ & ~0x00000008);
      duration_ = 0L;
      onChanged();
      return this;
    }

    private int userId_ ;
    /**
     * <code>int32 user_id = 5;</code>
     * @return The userId.
     */
    @java.lang.Override
    public int getUserId() {
      return userId_;
    }
    /**
     * <code>int32 user_id = 5;</code>
     * @param value The userId to set.
     * @return This builder for chaining.
     */
    public Builder setUserId(int value) {
      
      userId_ = value;
      bitField0_ |= 0x00000010;
      onChanged();
      return this;
    }
    /**
     * <code>int32 user_id = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearUserId() {
      bitField0_ = (bitField0_ & ~0x00000010);
      userId_ = 0;
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


    // @@protoc_insertion_point(builder_scope:si.um.feri.cycling_tracker_app.grpc.AddRideRequest)
  }

  // @@protoc_insertion_point(class_scope:si.um.feri.cycling_tracker_app.grpc.AddRideRequest)
  private static final si.um.feri.cycling_tracker_app.grpc.AddRideRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new si.um.feri.cycling_tracker_app.grpc.AddRideRequest();
  }

  public static si.um.feri.cycling_tracker_app.grpc.AddRideRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AddRideRequest>
      PARSER = new com.google.protobuf.AbstractParser<AddRideRequest>() {
    @java.lang.Override
    public AddRideRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<AddRideRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AddRideRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public si.um.feri.cycling_tracker_app.grpc.AddRideRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
