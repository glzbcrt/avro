package protocol;

import org.apache.avro.AvroRemoteException;
import org.apache.avro.ipc.NettyServer;
import org.apache.avro.ipc.NettyTransceiver;
import org.apache.avro.ipc.specific.SpecificRequestor;
import org.apache.avro.ipc.specific.SpecificResponder;
import protocol.dto.HelloWorld;
import protocol.dto.Request;
import protocol.dto.Response;
import protocol.dto.Status;

import java.net.InetSocketAddress;

public class ProtocolServer01 {

    public static class HelloWorldImpl implements HelloWorld {

        @Override
        public Response send(Request request, long age, Status status) throws AvroRemoteException {
            return Response.newBuilder().setNameToReturn(request.getName().toString().toUpperCase()).build();
        }

    }

    public static void main(String[] args) throws Exception {

        if (args[0].equals("-server")) {
            NettyServer server = new NettyServer(
                    new SpecificResponder(HelloWorld.class, new HelloWorldImpl()),
                    new InetSocketAddress(7000)
            );
        } else {
            NettyTransceiver client = new NettyTransceiver(
                    new InetSocketAddress(args[1], 7000)
            );

            HelloWorld hello = SpecificRequestor.getClient(HelloWorld.class, client);

            System.out.println(
                    hello.send(
                            Request.newBuilder().setName(args[2]).build(),
                            20,
                            Status.SYM1
                    ).getNameToReturn()
            );

            client.close();
        }

    }

}
