package com.insano10.miru;

public class ProjectStatsResponse
{
    private ProjectStatsCsvLine projectStats;
    private long[][] sourceLineCounts;
    private long[][] testLineCounts;

    private ProjectStatsResponse()
    {
    }

    public ProjectStatsResponse(ProjectStatsCsvLine projectStats, long[][] sourceLineCounts, long[][] testLineCounts)
    {
        this.projectStats = projectStats;
        this.sourceLineCounts = sourceLineCounts;
        this.testLineCounts = testLineCounts;
    }
}
