package com.insano10.miru;

public class ProjectStats implements Comparable<ProjectStats>
{
    private long timestamp;
    private boolean sourcesCompile;
    private boolean testsCompile;
    private int totalTestsRun;
    private int totalTestsPassed;
    private int totalTestsFailed;
    private int totalTestsIgnored;

    private ProjectStats()
    {
    }

    public ProjectStats(long timestamp, boolean sourcesCompile, boolean testsCompile, int totalTestsRun, int totalTestsPassed, int totalTestsFailed, int totalTestsIgnored)
    {
        this.timestamp = timestamp;
        this.sourcesCompile = sourcesCompile;
        this.testsCompile = testsCompile;
        this.totalTestsRun = totalTestsRun;
        this.totalTestsPassed = totalTestsPassed;
        this.totalTestsFailed = totalTestsFailed;
        this.totalTestsIgnored = totalTestsIgnored;
    }

    public static ProjectStats fromCsvString(final String str)
    {
        String[] tokens = str.split(",");
        long timestamp = Long.parseLong(tokens[0].trim());
        boolean sourcesCompile = intToBool(tokens[1].trim());
        boolean testsCompile = intToBool(tokens[2].trim());
        int totalTestsRun = Integer.valueOf(tokens[3].trim());
        int totalTestsPassed = Integer.valueOf(tokens[4].trim());
        int totalTestsFailed = Integer.valueOf(tokens[5].trim());
        int totalTestsIgnored = Integer.valueOf(tokens[6].trim());

        return new ProjectStats(timestamp, sourcesCompile, testsCompile, totalTestsRun, totalTestsPassed, totalTestsFailed, totalTestsIgnored);
    }

    private static boolean intToBool(final String integerString)
    {
        return !integerString.equals("0");
    }

    @Override
    public int compareTo(final ProjectStats o)
    {
        if(this.timestamp > o.timestamp)
        {
            return -1;
        }
        else if(this.timestamp == o.timestamp)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }

    @Override
    public String toString()
    {
        return "ProjectStats{" +
                "timestamp=" + timestamp +
                ", sourcesCompile=" + sourcesCompile +
                ", testsCompile=" + testsCompile +
                ", totalTestsRun=" + totalTestsRun +
                ", totalTestsPassed=" + totalTestsPassed +
                ", totalTestsFailed=" + totalTestsFailed +
                ", totalTestsIgnored=" + totalTestsIgnored +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectStats that = (ProjectStats) o;

        if (sourcesCompile != that.sourcesCompile) return false;
        if (testsCompile != that.testsCompile) return false;
        if (timestamp != that.timestamp) return false;
        if (totalTestsFailed != that.totalTestsFailed) return false;
        if (totalTestsIgnored != that.totalTestsIgnored) return false;
        if (totalTestsPassed != that.totalTestsPassed) return false;
        if (totalTestsRun != that.totalTestsRun) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (timestamp ^ (timestamp >>> 32));
        result = 31 * result + (sourcesCompile ? 1 : 0);
        result = 31 * result + (testsCompile ? 1 : 0);
        result = 31 * result + totalTestsRun;
        result = 31 * result + totalTestsPassed;
        result = 31 * result + totalTestsFailed;
        result = 31 * result + totalTestsIgnored;
        return result;
    }
}
