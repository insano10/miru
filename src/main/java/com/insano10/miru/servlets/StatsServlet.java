package com.insano10.miru.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.insano10.miru.data.LineCountDataPoint;
import com.insano10.miru.serialisation.LineCountDataPointSerializer;
import com.insano10.miru.ProjectStatsCsvLine;
import com.insano10.miru.data.ProjectStatsResponse;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class StatsServlet extends HttpServlet
{
    private static final String PROJECT_NAME = getProjectName();
    private static final String DATAFILE = "data/"+ PROJECT_NAME +".csv";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        final Gson gson = new GsonBuilder().registerTypeAdapter(LineCountDataPoint.class, new LineCountDataPointSerializer()).create();

        final List<ProjectStatsCsvLine> stats = new ArrayList<>();
        final List<LineCountDataPoint> sourceLineCounts = new ArrayList<>();
        final List<LineCountDataPoint> testLineCounts = new ArrayList<>();

        final File statsFile = new File(DATAFILE);

        try (Scanner fileReader = new Scanner(statsFile))
        {
            while (fileReader.hasNextLine())
            {
                final String line = fileReader.nextLine();

                if (!line.startsWith("#"))
                {
                    ProjectStatsCsvLine statsLine = ProjectStatsCsvLine.fromCsvString(line);
                    stats.add(statsLine);
                    sourceLineCounts.add(new LineCountDataPoint(statsLine.getTimestamp(), statsLine.getSourceLineCount()));
                    testLineCounts.add(new LineCountDataPoint(statsLine.getTimestamp(), statsLine.getTestLineCount()));
                }
            }
        }

        final ProjectStatsResponse response1 = new ProjectStatsResponse(PROJECT_NAME, stats.get(stats.size()-1), sourceLineCounts, testLineCounts);

        response.getWriter().println(gson.toJson(response1));
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
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