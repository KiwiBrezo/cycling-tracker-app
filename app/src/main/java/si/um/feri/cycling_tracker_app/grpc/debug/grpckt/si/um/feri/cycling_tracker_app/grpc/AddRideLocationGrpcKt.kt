package si.um.feri.cycling_tracker_app.grpc

import io.grpc.CallOptions
import io.grpc.CallOptions.DEFAULT
import io.grpc.Channel
import io.grpc.Metadata
import io.grpc.MethodDescriptor
import io.grpc.ServerServiceDefinition
import io.grpc.ServerServiceDefinition.builder
import io.grpc.ServiceDescriptor
import io.grpc.Status.UNIMPLEMENTED
import io.grpc.StatusException
import io.grpc.kotlin.AbstractCoroutineServerImpl
import io.grpc.kotlin.AbstractCoroutineStub
import io.grpc.kotlin.ClientCalls.unaryRpc
import io.grpc.kotlin.ServerCalls.unaryServerMethodDefinition
import io.grpc.kotlin.StubFor
import kotlin.String
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import si.um.feri.cycling_tracker_app.grpc.AddRideLocationServiceGrpc.getServiceDescriptor

/**
 * Holder for Kotlin coroutine-based client and server APIs for
 * si.um.feri.cycling_tracker_app.grpc.AddRideLocationService.
 */
public object AddRideLocationServiceGrpcKt {
  public const val SERVICE_NAME: String = AddRideLocationServiceGrpc.SERVICE_NAME

  @JvmStatic
  public val serviceDescriptor: ServiceDescriptor
    get() = AddRideLocationServiceGrpc.getServiceDescriptor()

  public val addRideLocationMethod: MethodDescriptor<AddLocationRequest, AddLocationResponse>
    @JvmStatic
    get() = AddRideLocationServiceGrpc.getAddRideLocationMethod()

  /**
   * A stub for issuing RPCs to a(n) si.um.feri.cycling_tracker_app.grpc.AddRideLocationService
   * service as suspending coroutines.
   */
  @StubFor(AddRideLocationServiceGrpc::class)
  public class AddRideLocationServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT,
  ) : AbstractCoroutineStub<AddRideLocationServiceCoroutineStub>(channel, callOptions) {
    public override fun build(channel: Channel, callOptions: CallOptions):
        AddRideLocationServiceCoroutineStub = AddRideLocationServiceCoroutineStub(channel,
        callOptions)

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun addRideLocation(request: AddLocationRequest, headers: Metadata = Metadata()):
        AddLocationResponse = unaryRpc(
      channel,
      AddRideLocationServiceGrpc.getAddRideLocationMethod(),
      request,
      callOptions,
      headers
    )
  }

  /**
   * Skeletal implementation of the si.um.feri.cycling_tracker_app.grpc.AddRideLocationService
   * service based on Kotlin coroutines.
   */
  public abstract class AddRideLocationServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns the response to an RPC for
     * si.um.feri.cycling_tracker_app.grpc.AddRideLocationService.addRideLocation.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun addRideLocation(request: AddLocationRequest): AddLocationResponse =
        throw
        StatusException(UNIMPLEMENTED.withDescription("Method si.um.feri.cycling_tracker_app.grpc.AddRideLocationService.addRideLocation is unimplemented"))

    public final override fun bindService(): ServerServiceDefinition =
        builder(getServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = AddRideLocationServiceGrpc.getAddRideLocationMethod(),
      implementation = ::addRideLocation
    )).build()
  }
}
