package com.insano10.miru.data;

public class LineCountDataPoint
{
    private final long timestamp;
    private final int lineCount;

    public LineCountDataPoint(long timestamp, int lineCount)
    {
        this.timestamp = timestamp;
        this.lineCount = lineCount;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public int getLineCount()
    {
        return lineCount;
    }
}
