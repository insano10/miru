package com.insano10.miru;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class MiruServer
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("Starting Miru server on http://localhost:8080");

        Server server = new org.eclipse.jetty.server.Server(8080);

        WebAppContext webContext = new WebAppContext();
        webContext.setResourceBase("/home/jenny/IdeaProjects/miru/src/web");
        webContext.setContextPath("/");
        webContext.setParentLoaderPriority(true);
        server.setHandler(webContext);

        server.start();
        server.join();
    }
}
