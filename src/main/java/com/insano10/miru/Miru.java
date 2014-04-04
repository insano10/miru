package com.insano10.miru;

import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerServer;
import org.simpleframework.transport.Server;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class Miru
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("Starting Miru server");

        Container container = new MiruServer();
        Server server = new ContainerServer(container);
        Connection connection = new SocketConnection(server);
        SocketAddress address = new InetSocketAddress(8080);

        connection.connect(address);
    }
}
