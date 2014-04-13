package com.insano10.miru;

public class ProjectStatsCsvLine
{
    private long timestamp;
    private boolean sourcesCompile;
    private boolean testsCompile;
    private int totalTests;
    private int totalTestsPassed;
    private int totalTestsFailed;
    private int totalTestsIgnored;
    private int sourceLineCount;
    private int testLineCount;

    public ProjectStatsCsvLine(long timestamp, boolean sourcesCompile, boolean testsCompile, int totalTests, int totalTestsPassed, int totalTestsFailed, int totalTestsIgnored,
                               int sourceLineCount, int testLineCount)
    {
        this.timestamp = timestamp;
        this.sourcesCompile = sourcesCompile;
        this.testsCompile = testsCompile;
        this.totalTests = totalTests;
        this.totalTestsPassed = totalTestsPassed;
        this.totalTestsFailed = totalTestsFailed;
        this.totalTestsIgnored = totalTestsIgnored;
        this.sourceLineCount = sourceLineCount;
        this.testLineCount = testLineCount;
    }

    public static ProjectStatsCsvLine fromCsvString(final String str)
    {
        String[] tokens = str.split(",");
        long timestamp = Long.parseLong(tokens[0].trim());
        boolean sourcesCompile = intToBool(tokens[1].trim());
        boolean testsCompile = intToBool(tokens[2].trim());
        int totalTests = Integer.valueOf(tokens[3].trim());
        int totalTestsPassed = Integer.valueOf(tokens[4].trim());
        int totalTestsFailed = Integer.valueOf(tokens[5].trim());
        int totalTestsIgnored = Integer.valueOf(tokens[6].trim());
        int sourceLineCount = Integer.valueOf(tokens[7].trim());
        int testLineCount = Integer.valueOf(tokens[8].trim());

        return new ProjectStatsCsvLine(timestamp, sourcesCompile, testsCompile, totalTests, totalTestsPassed, totalTestsFailed, totalTestsIgnored, sourceLineCount, testLineCount);
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public int getSourceLineCount()
    {
        return sourceLineCount;
    }

    public int getTestLineCount()
    {
        return testLineCount;
    }

    private static boolean intToBool(final String integerString)
    {
        return !integerString.equals("0");
    }

    @Override
    public String toString()
    {
        return "ProjectStatsCsvLine{" +
                "timestamp=" + timestamp +
                ", sourcesCompile=" + sourcesCompile +
                ", testsCompile=" + testsCompile +
                ", totalTests=" + totalTests +
                ", totalTestsPassed=" + totalTestsPassed +
                ", totalTestsFailed=" + totalTestsFailed +
                ", totalTestsIgnored=" + totalTestsIgnored +
                ", sourceLineCount=" + sourceLineCount +
                ", testLineCount=" + testLineCount +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectStatsCsvLine that = (ProjectStatsCsvLine) o;

        if (sourceLineCount != that.sourceLineCount) return false;
        if (sourcesCompile != that.sourcesCompile) return false;
        if (testLineCount != that.testLineCount) return false;
        if (testsCompile != that.testsCompile) return false;
        if (timestamp != that.timestamp) return false;
        if (totalTestsFailed != that.totalTestsFailed) return false;
        if (totalTestsIgnored != that.totalTestsIgnored) return false;
        if (totalTestsPassed != that.totalTestsPassed) return false;
        if (totalTests != that.totalTests) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (timestamp ^ (timestamp >>> 32));
        result = 31 * result + (sourcesCompile ? 1 : 0);
        result = 31 * result + (testsCompile ? 1 : 0);
        result = 31 * result + totalTests;
        result = 31 * result + totalTestsPassed;
        result = 31 * result + totalTestsFailed;
        result = 31 * result + totalTestsIgnored;
        result = 31 * result + sourceLineCount;
        result = 31 * result + testLineCount;
        return result;
    }
}
