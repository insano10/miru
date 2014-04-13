package com.insano10.miru;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ProjectStatsCsvLineTest
{

    @Test
    public void shouldConvertObjectToJSON()
    {
        final Gson gson = new GsonBuilder().create();
        final ProjectStatsCsvLine stats = new ProjectStatsCsvLine(0L, true, true, 9, 7, 2, 4, 100, 85);

        final String expectedJson = "{\"timestamp\":0,\"sourcesCompile\":true,\"testsCompile\":true,\"totalTests\":9," +
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
}
