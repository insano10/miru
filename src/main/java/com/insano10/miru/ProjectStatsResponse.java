package com.insano10.miru;

public class ProjectStatsResponse
{
    private String projectName;
    private ProjectStatsCsvLine projectStats;
    private long[][] sourceLineCounts;
    private long[][] testLineCounts;

    private ProjectStatsResponse()
    {
    }

    public ProjectStatsResponse(String projectName, ProjectStatsCsvLine projectStats, long[][] sourceLineCounts, long[][] testLineCounts)
    {
        this.projectName = projectName;
        this.projectStats = projectStats;
        this.sourceLineCounts = sourceLineCounts;
        this.testLineCounts = testLineCounts;
    }
}
