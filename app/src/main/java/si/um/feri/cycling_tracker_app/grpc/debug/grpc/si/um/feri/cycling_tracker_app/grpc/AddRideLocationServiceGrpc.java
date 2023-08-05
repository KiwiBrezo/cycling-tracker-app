package si.um.feri.cycling_tracker_app.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: AddRideLocation.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AddRideLocationServiceGrpc {

  private AddRideLocationServiceGrpc() {}

  public static final String SERVICE_NAME = "si.um.feri.cycling_tracker_app.grpc.AddRideLocationService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<si.um.feri.cycling_tracker_app.grpc.AddLocationRequest,
      si.um.feri.cycling_tracker_app.grpc.AddLocationResponse> getAddRideLocationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addRideLocation",
      requestType = si.um.feri.cycling_tracker_app.grpc.AddLocationRequest.class,
      responseType = si.um.feri.cycling_tracker_app.grpc.AddLocationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<si.um.feri.cycling_tracker_app.grpc.AddLocationRequest,
      si.um.feri.cycling_tracker_app.grpc.AddLocationResponse> getAddRideLocationMethod() {
    io.grpc.MethodDescriptor<si.um.feri.cycling_tracker_app.grpc.AddLocationRequest, si.um.feri.cycling_tracker_app.grpc.AddLocationResponse> getAddRideLocationMethod;
    if ((getAddRideLocationMethod = AddRideLocationServiceGrpc.getAddRideLocationMethod) == null) {
      synchronized (AddRideLocationServiceGrpc.class) {
        if ((getAddRideLocationMethod = AddRideLocationServiceGrpc.getAddRideLocationMethod) == null) {
          AddRideLocationServiceGrpc.getAddRideLocationMethod = getAddRideLocationMethod =
              io.grpc.MethodDescriptor.<si.um.feri.cycling_tracker_app.grpc.AddLocationRequest, si.um.feri.cycling_tracker_app.grpc.AddLocationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "addRideLocation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  si.um.feri.cycling_tracker_app.grpc.AddLocationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  si.um.feri.cycling_tracker_app.grpc.AddLocationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AddRideLocationServiceMethodDescriptorSupplier("addRideLocation"))
              .build();
        }
      }
    }
    return getAddRideLocationMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AddRideLocationServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AddRideLocationServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AddRideLocationServiceStub>() {
        @java.lang.Override
        public AddRideLocationServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AddRideLocationServiceStub(channel, callOptions);
        }
      };
    return AddRideLocationServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AddRideLocationServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AddRideLocationServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AddRideLocationServiceBlockingStub>() {
        @java.lang.Override
        public AddRideLocationServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AddRideLocationServiceBlockingStub(channel, callOptions);
        }
      };
    return AddRideLocationServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AddRideLocationServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AddRideLocationServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AddRideLocationServiceFutureStub>() {
        @java.lang.Override
        public AddRideLocationServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AddRideLocationServiceFutureStub(channel, callOptions);
        }
      };
    return AddRideLocationServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class AddRideLocationServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void addRideLocation(si.um.feri.cycling_tracker_app.grpc.AddLocationRequest request,
        io.grpc.stub.StreamObserver<si.um.feri.cycling_tracker_app.grpc.AddLocationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddRideLocationMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddRideLocationMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                si.um.feri.cycling_tracker_app.grpc.AddLocationRequest,
                si.um.feri.cycling_tracker_app.grpc.AddLocationResponse>(
                  this, METHODID_ADD_RIDE_LOCATION)))
          .build();
    }
  }

  /**
   */
  public static final class AddRideLocationServiceStub extends io.grpc.stub.AbstractAsyncStub<AddRideLocationServiceStub> {
    private AddRideLocationServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AddRideLocationServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AddRideLocationServiceStub(channel, callOptions);
    }

    /**
     */
    public void addRideLocation(si.um.feri.cycling_tracker_app.grpc.AddLocationRequest request,
        io.grpc.stub.StreamObserver<si.um.feri.cycling_tracker_app.grpc.AddLocationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddRideLocationMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AddRideLocationServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<AddRideLocationServiceBlockingStub> {
    private AddRideLocationServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AddRideLocationServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AddRideLocationServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public si.um.feri.cycling_tracker_app.grpc.AddLocationResponse addRideLocation(si.um.feri.cycling_tracker_app.grpc.AddLocationRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddRideLocationMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AddRideLocationServiceFutureStub extends io.grpc.stub.AbstractFutureStub<AddRideLocationServiceFutureStub> {
    private AddRideLocationServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AddRideLocationServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AddRideLocationServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<si.um.feri.cycling_tracker_app.grpc.AddLocationResponse> addRideLocation(
        si.um.feri.cycling_tracker_app.grpc.AddLocationRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddRideLocationMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_RIDE_LOCATION = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AddRideLocationServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AddRideLocationServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD_RIDE_LOCATION:
          serviceImpl.addRideLocation((si.um.feri.cycling_tracker_app.grpc.AddLocationRequest) request,
              (io.grpc.stub.StreamObserver<si.um.feri.cycling_tracker_app.grpc.AddLocationResponse>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class AddRideLocationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AddRideLocationServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return si.um.feri.cycling_tracker_app.grpc.AddRideLocation.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AddRideLocationService");
    }
  }

  private static final class AddRideLocationServiceFileDescriptorSupplier
      extends AddRideLocationServiceBaseDescriptorSupplier {
    AddRideLocationServiceFileDescriptorSupplier() {}
  }

  private static final class AddRideLocationServiceMethodDescriptorSupplier
      extends AddRideLocationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AddRideLocationServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (AddRideLocationServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AddRideLocationServiceFileDescriptorSupplier())
              .addMethod(getAddRideLocationMethod())
              .build();
        }
      }
    }
    return result;
  }
}
