package com.insano10.miru.serialisation;

import com.google.gson.*;
import com.insano10.miru.data.LineCountDataPoint;

import java.lang.reflect.Type;

public class LineCountDataPointSerializer implements JsonSerializer<LineCountDataPoint>
{
    @Override
    public JsonElement serialize(LineCountDataPoint lineCountDataPoint, Type type, JsonSerializationContext jsonSerializationContext)
    {
        final JsonArray dataPoint = new JsonArray();

        dataPoint.add(new JsonPrimitive(lineCountDataPoint.getTimestamp()));
        dataPoint.add(new JsonPrimitive(lineCountDataPoint.getLineCount()));

        return dataPoint;
    }
}
