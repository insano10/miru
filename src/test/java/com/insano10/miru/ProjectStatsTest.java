package com.insano10.miru;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ProjectStatsTest
{

    @Test
    public void shouldConvertObjectToJSON()
    {
        final Gson gson = new GsonBuilder().create();
        final ProjectStats stats = new ProjectStats(0L, true, true, 9, 7, 2);

        final String expectedJson = "{\"timestamp\":0,\"sourcesCompile\":true,\"testsCompile\":true,\"totalTestsRun\":9,\"totalTestsPassed\":7,\"totalTestsFailed\":2}";

        final String jsonStats = gson.toJson(stats);
        assertThat(jsonStats, equalTo(expectedJson));
    }

    @Test
    public void shouldCreateFromCorrectlyFormattedString()
    {
        final String csvString = "12345, 1, 1, 5, 5, 0";

        final ProjectStats expectedStats = new ProjectStats(12345L, true, true, 5, 5, 0);
        final ProjectStats stats = ProjectStats.fromCsvString(csvString);

        assertThat(stats, equalTo(expectedStats));
    }

    @Test public void shouldSortStatsInDescendingOrder()
    {
        final List<ProjectStats> stats = new ArrayList<>();
        ProjectStats stats1 = new ProjectStats(1L, true, true, 0, 0, 0);
        ProjectStats stats2 = new ProjectStats(3L, true, true, 0, 0, 0);
        ProjectStats stats3 = new ProjectStats(2L, true, true, 0, 0, 0);

        stats.add(stats1);
        stats.add(stats2);
        stats.add(stats3);

        assertThat(stats.get(0), equalTo(stats1));
        assertThat(stats.get(1), equalTo(stats2));
        assertThat(stats.get(2), equalTo(stats3));

        Collections.sort(stats);

        assertThat(stats.get(0), equalTo(stats2));
        assertThat(stats.get(1), equalTo(stats3));
        assertThat(stats.get(2), equalTo(stats1));
    }
}
