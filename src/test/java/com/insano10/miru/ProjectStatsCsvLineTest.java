package com.insano10.miru;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ProjectStatsCsvLineTest
{

    @Test
    public void shouldConvertObjectToJSON()
    {
        final Gson gson = new GsonBuilder().create();
        final ProjectStatsCsvLine stats = new ProjectStatsCsvLine(0L, true, true, 9, 7, 2, 4, 100, 85);

        final String expectedJson = "{\"timestamp\":0,\"sourcesCompile\":true,\"testsCompile\":true,\"totalTestsRun\":9," +
                                     "\"totalTestsPassed\":7,\"totalTestsFailed\":2,\"totalTestsIgnored\":4," +
                                     "\"sourceLineCount\":100,\"testLineCount\":85}";

        final String jsonStats = gson.toJson(stats);
        assertThat(jsonStats, equalTo(expectedJson));
    }

    @Test
    public void shouldCreateFromCorrectlyFormattedString()
    {
        final String csvString = "12345, 1, 1, 5, 5, 0, 10, 99, 10";

        final ProjectStatsCsvLine expectedStats = new ProjectStatsCsvLine(12345L, true, true, 5, 5, 0, 10, 99, 10);
        final ProjectStatsCsvLine stats = ProjectStatsCsvLine.fromCsvString(csvString);

        assertThat(stats, equalTo(expectedStats));
    }

    @Test public void shouldSortStatsInAscendingOrder()
    {
        final List<ProjectStatsCsvLine> stats = new ArrayList<>();
        ProjectStatsCsvLine stats1 = new ProjectStatsCsvLine(1L, true, true, 0, 0, 0, 0, 0, 0);
        ProjectStatsCsvLine stats2 = new ProjectStatsCsvLine(3L, true, true, 0, 0, 0, 0, 0, 0);
        ProjectStatsCsvLine stats3 = new ProjectStatsCsvLine(2L, true, true, 0, 0, 0, 0, 0, 0);

        stats.add(stats1);
        stats.add(stats2);
        stats.add(stats3);

        assertThat(stats.get(0), equalTo(stats1));
        assertThat(stats.get(1), equalTo(stats2));
        assertThat(stats.get(2), equalTo(stats3));

        Collections.sort(stats);

        assertThat(stats.get(0), equalTo(stats1));
        assertThat(stats.get(1), equalTo(stats3));
        assertThat(stats.get(2), equalTo(stats2));
    }
}
