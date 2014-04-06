package com.insano10.miru;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ProjectStatsTest
{

    @Test
    public void shouldConvertObjectToJSON()
    {
        final Gson gson = new GsonBuilder().create();
        final ProjectStats stats = new ProjectStats(true, true, false);

        final String expectedJson = "{\"sourcesCompile\":true,\"testsCompile\":true,\"testsPass\":false}";

        final String jsonStats = gson.toJson(stats);
        assertThat(jsonStats, equalTo(expectedJson));
    }

    @Test
    public void shouldCreateFromCorrectlyFormattedString()
    {
        final String csvString = "1, 1, 0";

        final ProjectStats expectedStats = new ProjectStats(true, true, false);
        final ProjectStats stats = ProjectStats.fromCsvString(csvString);

        assertThat(stats, equalTo(expectedStats));
    }
}
