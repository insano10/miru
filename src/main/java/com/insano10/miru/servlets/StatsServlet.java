package com.insano10.miru.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class StatsServlet extends HttpServlet
{
    private final String dataFileFolder = "/home/jenny/IdeaProjects/miru/data";
    private final String dataFile = dataFileFolder + "/romanNumerals.csv";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        final File statsFile = new File(dataFile);
        final Scanner fileReader = new Scanner(statsFile);

        while(fileReader.hasNextLine())
        {
            final String line = fileReader.nextLine();
            response.getWriter().println(line + "</br>");
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}