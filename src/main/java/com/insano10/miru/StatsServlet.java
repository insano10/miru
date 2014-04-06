package com.insano10.miru;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class StatsServlet extends HttpServlet
{
    private static final String DATAFILE = "data/romanNumerals.csv";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        final Gson gson = new GsonBuilder().create();
        final List<ProjectStats> stats = new ArrayList<>();
        final File statsFile = new File(DATAFILE);

        try (Scanner fileReader = new Scanner(statsFile))
        {
            while (fileReader.hasNextLine())
            {
                final String line = fileReader.nextLine();

                if (!line.startsWith("#"))
                {
                    stats.add(ProjectStats.fromCsvString(line));
                }
            }
        }

        Collections.sort(stats);
        response.getWriter().println(gson.toJson(stats));
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
}