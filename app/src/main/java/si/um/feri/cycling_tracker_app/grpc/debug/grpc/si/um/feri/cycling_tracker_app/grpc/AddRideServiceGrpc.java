package si.um.feri.cycling_tracker_app.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: AddRide.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AddRideServiceGrpc {

  private AddRideServiceGrpc() {}

  public static final String SERVICE_NAME = "si.um.feri.cycling_tracker_app.grpc.AddRideService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<si.um.feri.cycling_tracker_app.grpc.AddRideRequest,
      si.um.feri.cycling_tracker_app.grpc.AddRideResponse> getAddRideMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addRide",
      requestType = si.um.feri.cycling_tracker_app.grpc.AddRideRequest.class,
      responseType = si.um.feri.cycling_tracker_app.grpc.AddRideResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<si.um.feri.cycling_tracker_app.grpc.AddRideRequest,
      si.um.feri.cycling_tracker_app.grpc.AddRideResponse> getAddRideMethod() {
    io.grpc.MethodDescriptor<si.um.feri.cycling_tracker_app.grpc.AddRideRequest, si.um.feri.cycling_tracker_app.grpc.AddRideResponse> getAddRideMethod;
    if ((getAddRideMethod = AddRideServiceGrpc.getAddRideMethod) == null) {
      synchronized (AddRideServiceGrpc.class) {
        if ((getAddRideMethod = AddRideServiceGrpc.getAddRideMethod) == null) {
          AddRideServiceGrpc.getAddRideMethod = getAddRideMethod =
              io.grpc.MethodDescriptor.<si.um.feri.cycling_tracker_app.grpc.AddRideRequest, si.um.feri.cycling_tracker_app.grpc.AddRideResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "addRide"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  si.um.feri.cycling_tracker_app.grpc.AddRideRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  si.um.feri.cycling_tracker_app.grpc.AddRideResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AddRideServiceMethodDescriptorSupplier("addRide"))
              .build();
        }
      }
    }
    return getAddRideMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AddRideServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AddRideServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AddRideServiceStub>() {
        @java.lang.Override
        public AddRideServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AddRideServiceStub(channel, callOptions);
        }
      };
    return AddRideServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AddRideServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AddRideServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AddRideServiceBlockingStub>() {
        @java.lang.Override
        public AddRideServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AddRideServiceBlockingStub(channel, callOptions);
        }
      };
    return AddRideServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AddRideServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AddRideServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AddRideServiceFutureStub>() {
        @java.lang.Override
        public AddRideServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AddRideServiceFutureStub(channel, callOptions);
        }
      };
    return AddRideServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class AddRideServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void addRide(si.um.feri.cycling_tracker_app.grpc.AddRideRequest request,
        io.grpc.stub.StreamObserver<si.um.feri.cycling_tracker_app.grpc.AddRideResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddRideMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddRideMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                si.um.feri.cycling_tracker_app.grpc.AddRideRequest,
                si.um.feri.cycling_tracker_app.grpc.AddRideResponse>(
                  this, METHODID_ADD_RIDE)))
          .build();
    }
  }

  /**
   */
  public static final class AddRideServiceStub extends io.grpc.stub.AbstractAsyncStub<AddRideServiceStub> {
    private AddRideServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AddRideServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AddRideServiceStub(channel, callOptions);
    }

    /**
     */
    public void addRide(si.um.feri.cycling_tracker_app.grpc.AddRideRequest request,
        io.grpc.stub.StreamObserver<si.um.feri.cycling_tracker_app.grpc.AddRideResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddRideMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AddRideServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<AddRideServiceBlockingStub> {
    private AddRideServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AddRideServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AddRideServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public si.um.feri.cycling_tracker_app.grpc.AddRideResponse addRide(si.um.feri.cycling_tracker_app.grpc.AddRideRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddRideMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AddRideServiceFutureStub extends io.grpc.stub.AbstractFutureStub<AddRideServiceFutureStub> {
    private AddRideServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AddRideServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AddRideServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<si.um.feri.cycling_tracker_app.grpc.AddRideResponse> addRide(
        si.um.feri.cycling_tracker_app.grpc.AddRideRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddRideMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_RIDE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AddRideServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AddRideServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD_RIDE:
          serviceImpl.addRide((si.um.feri.cycling_tracker_app.grpc.AddRideRequest) request,
              (io.grpc.stub.StreamObserver<si.um.feri.cycling_tracker_app.grpc.AddRideResponse>) responseObserver);
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

  private static abstract class AddRideServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AddRideServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return si.um.feri.cycling_tracker_app.grpc.AddRide.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AddRideService");
    }
  }

  private static final class AddRideServiceFileDescriptorSupplier
      extends AddRideServiceBaseDescriptorSupplier {
    AddRideServiceFileDescriptorSupplier() {}
  }

  private static final class AddRideServiceMethodDescriptorSupplier
      extends AddRideServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AddRideServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (AddRideServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AddRideServiceFileDescriptorSupplier())
              .addMethod(getAddRideMethod())
              .build();
        }
      }
    }
    return result;
  }
}
