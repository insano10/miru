package com.insano10.miru.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.insano10.miru.data.LineCountDataPoint;
import com.insano10.miru.data.StatsResponse;
import com.insano10.miru.serialisation.LineCountDataPointSerializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class StatsServlet extends HttpServlet
{
    private static final String PROJECT_NAME = getProjectName();
    private static final String DATAFILE = "data/"+ PROJECT_NAME +".csv";
    private static final Gson GSON = new GsonBuilder().registerTypeAdapter(LineCountDataPoint.class, new LineCountDataPointSerializer()).create();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        final File statsFile = new File(DATAFILE);
        final StatsResponse statsResponse = buildResponse(statsFile);

        response.getWriter().println(GSON.toJson(statsResponse));
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private StatsResponse buildResponse(File statsFile) throws FileNotFoundException
    {
        final StatsResponse statsResponse = new StatsResponse();

        try (Scanner fileReader = new Scanner(statsFile))
        {
            String line = "";

            while (fileReader.hasNextLine())
            {
                line = fileReader.nextLine();

                if (!line.startsWith("#"))
                {
                    String[] tokens = line.split(",");
                    long timestamp = Long.parseLong(tokens[0].trim());
                    int sourceLineCount = Integer.parseInt(tokens[7].trim());
                    int testLineCount = Integer.parseInt(tokens[8].trim());


                    statsResponse.sourceLineDataPoint(new LineCountDataPoint(timestamp, sourceLineCount));
                    statsResponse.testLineDataPoint(new LineCountDataPoint(timestamp, testLineCount));
                }
            }

            //last line in file
            String[] tokens = line.split(",");
            statsResponse.sourcesCompile(integerToBoolean(tokens[1].trim()));
            statsResponse.testsCompile(integerToBoolean(tokens[2].trim()));
            statsResponse.totalTests(Integer.valueOf(tokens[3].trim()));
            statsResponse.totalPassingTests(Integer.valueOf(tokens[4].trim()));
            statsResponse.totalFailingTests(Integer.valueOf(tokens[5].trim()));
            statsResponse.totalIgnoredTests(Integer.valueOf(tokens[6].trim()));
            statsResponse.modifiedFiles(Integer.valueOf(tokens[9].trim()));
            statsResponse.addedFiles(Integer.valueOf(tokens[10].trim()));
            statsResponse.deletedFiles(Integer.valueOf(tokens[11].trim()));
            statsResponse.unversionedFiles(Integer.valueOf(tokens[12].trim()));
            statsResponse.projectName(PROJECT_NAME);

            //only display the most recent 500 data points
            statsResponse.trimDataPoints(500);
        }
        return statsResponse;
    }

    private boolean integerToBoolean(final String integerString)
    {
        return !integerString.equals("0");
    }

    private static String getProjectName()
    {
        try
        {
            final String propertyFile = System.getProperty("miruProperties");
            final Properties properties = new Properties();
            properties.load(new FileInputStream(propertyFile));

            final String projectName = properties.getProperty("projectName");

            if(projectName == null || projectName.isEmpty())
            {
                throw new RuntimeException("projectName not found in miru property file: " + propertyFile);
            }
            return projectName;

        } catch (Exception e)
        {
            throw new RuntimeException("Could not load miruProperties from system property.");
        }
    }
}