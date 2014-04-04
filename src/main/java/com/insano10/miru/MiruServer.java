package com.insano10.miru;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;

import java.io.PrintStream;

public class MiruServer implements Container
{

    @Override
    public void handle(Request request, Response response)
    {
        try
        {
            PrintStream body = response.getPrintStream();
            long time = System.currentTimeMillis();

            response.setValue("Content-Type", "text/plain");
            response.setValue("Server", "HelloWorld/1.0 (Simple 4.0)");
            response.setDate("Date", time);
            response.setDate("Last-Modified", time);

            body.println("Hello World");
            body.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
