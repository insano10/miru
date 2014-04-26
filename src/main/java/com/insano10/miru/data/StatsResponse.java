package com.insano10.miru.data;

import java.util.ArrayList;
import java.util.List;

public class StatsResponse
{
    //serialised by magic gson
    private String projectName;

    private boolean sourcesCompile;
    private boolean testsCompile;

    private int totalTests;
    private int totalPassingTests;
    private int totalFailingTests;
    private int totalIgnoredTests;

    private List<LineCountDataPoint> sourceLineCounts = new ArrayList<>();
    private List<LineCountDataPoint> testLineCounts = new ArrayList<>();

    private int modifiedFiles;
    private int addedFiles;
    private int deletedFiles;
    private int unversionedFiles;

    public StatsResponse projectName(String projectName)
    {
        this.projectName = projectName;
        return this;
    }

    public StatsResponse sourcesCompile(boolean sourcesCompile)
    {
        this.sourcesCompile = sourcesCompile;
        return this;
    }

    public StatsResponse testsCompile(boolean testsCompile)
    {
        this.testsCompile = testsCompile;
        return this;
    }

    public StatsResponse totalTests(int totalTests)
    {
        this.totalTests = totalTests;
        return this;
    }

    public StatsResponse totalPassingTests(int totalPassingTests)
    {
        this.totalPassingTests = totalPassingTests;
        return this;
    }

    public StatsResponse totalFailingTests(int totalFailingTests)
    {
        this.totalFailingTests = totalFailingTests;
        return this;
    }

    public StatsResponse totalIgnoredTests(int totalIgnoredTests)
    {
        this.totalIgnoredTests = totalIgnoredTests;
        return this;
    }

    public StatsResponse sourceLineDataPoint(LineCountDataPoint dataPoint)
    {
        this.sourceLineCounts.add(dataPoint);
        return this;
    }

    public StatsResponse testLineDataPoint(LineCountDataPoint dataPoint)
    {
        this.testLineCounts.add(dataPoint);
        return this;
    }

    public StatsResponse modifiedFiles(int modifiedFiles)
    {
        this.modifiedFiles = modifiedFiles;
        return this;
    }

    public StatsResponse addedFiles(int addedFiles)
    {
        this.addedFiles = addedFiles;
        return this;
    }

    public StatsResponse deletedFiles(int deletedFiles)
    {
        this.deletedFiles = deletedFiles;
        return this;
    }

    public StatsResponse unversionedFiles(int unversionedFiles)
    {
        this.unversionedFiles = unversionedFiles;
        return this;
    }

    public void trimDataPoints(int maxPoints)
    {
        int maxSource = sourceLineCounts.size() - 1;
        sourceLineCounts = sourceLineCounts.subList(Math.max(0, maxSource - maxPoints), maxSource);

        int maxTest = testLineCounts.size() - 1;
        testLineCounts = testLineCounts.subList(Math.max(0, maxTest - maxPoints), maxTest);
    }
}
