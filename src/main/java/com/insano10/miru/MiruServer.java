package com.insano10.miru;

import org.eclipse.jetty.server.Server;

public class MiruServer
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("Starting Miru server on http://localhost:8080");

        Server server = new org.eclipse.jetty.server.Server(8080);
        server.start();
        server.join();
    }
}
