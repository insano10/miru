package com.insano10.miru.data;

import com.insano10.miru.ProjectStatsCsvLine;

import java.util.List;

public class ProjectStatsResponse
{
    private String projectName;
    private ProjectStatsCsvLine projectStats;
    private List<LineCountDataPoint> sourceLineCounts;
    private List<LineCountDataPoint> testLineCounts;

    private ProjectStatsResponse()
    {
    }

    public ProjectStatsResponse(String projectName, ProjectStatsCsvLine projectStats, List<LineCountDataPoint> sourceLineCounts, List<LineCountDataPoint> testLineCounts)
    {
        this.projectName = projectName;
        this.projectStats = projectStats;
        this.sourceLineCounts = sourceLineCounts;
        this.testLineCounts = testLineCounts;
    }
}
