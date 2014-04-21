package com.insano10.miru.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.insano10.miru.serialisation.LineCountDataPointSerializer;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class StatsResponseTest
{

    @Test
    public void shouldConvertObjectToJSONUsingCustomLineCounrDataPointSerializer()
    {
        final Gson gson = new GsonBuilder().registerTypeAdapter(LineCountDataPoint.class, new LineCountDataPointSerializer()).create();

        final StatsResponse response = new StatsResponse();

        response.projectName("name");
        response.sourcesCompile(true);
        response.testsCompile(false);
        response.totalTests(10);
        response.totalPassingTests(7);
        response.totalFailingTests(1);
        response.totalIgnoredTests(2);

        response.sourceLineDataPoint(new LineCountDataPoint(123L, 1));
        response.sourceLineDataPoint(new LineCountDataPoint(456L, 2));
        response.sourceLineDataPoint(new LineCountDataPoint(789L, 3));

        response.testLineDataPoint(new LineCountDataPoint(111L, 10));
        response.testLineDataPoint(new LineCountDataPoint(222L, 20));
        response.testLineDataPoint(new LineCountDataPoint(333L, 30));

        response.modifiedFiles(2);
        response.addedFiles(1);
        response.deletedFiles(0);
        response.unversionedFiles(5);

        final String expectedJson = "{" +
                "\"projectName\":\"name\"," +
                "\"sourcesCompile\":true," +
                "\"testsCompile\":false," +
                "\"totalTests\":10," +
                "\"totalPassingTests\":7," +
                "\"totalFailingTests\":1," +
                "\"totalIgnoredTests\":2," +
                "\"sourceLineCounts\":[[123,1],[456,2],[789,3]]," +
                "\"testLineCounts\":[[111,10],[222,20],[333,30]]," +
                "\"modifiedFiles\":2," +
                "\"addedFiles\":1," +
                "\"deletedFiles\":0," +
                "\"unversionedFiles\":5}";

        final String jsonStats = gson.toJson(response);
        assertThat(jsonStats, equalTo(expectedJson));
    }
}
