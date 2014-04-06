package com.insano10.miru;

public class ProjectStats implements Comparable<ProjectStats>
{
    private long timestamp;
    private boolean sourcesCompile;
    private boolean testsCompile;
    private boolean testsPass;

    private ProjectStats()
    {
    }

    public ProjectStats(long timestamp, boolean sourcesCompile, boolean testsCompile, boolean testsPass)
    {
        this.timestamp = timestamp;
        this.sourcesCompile = sourcesCompile;
        this.testsCompile = testsCompile;
        this.testsPass = testsPass;
    }

    public static ProjectStats fromCsvString(final String str)
    {
        String[] tokens = str.split(",");
        long timestamp = Long.parseLong(tokens[0].trim());
        boolean sourcesCompile = intToBool(tokens[1].trim());
        boolean testsCompile = intToBool(tokens[2].trim());
        boolean testsPass = intToBool(tokens[3].trim());

        return new ProjectStats(timestamp, sourcesCompile, testsCompile, testsPass);
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
                ", testsPass=" + testsPass +
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
        if (testsPass != that.testsPass) return false;
        if (timestamp != that.timestamp) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (timestamp ^ (timestamp >>> 32));
        result = 31 * result + (sourcesCompile ? 1 : 0);
        result = 31 * result + (testsCompile ? 1 : 0);
        result = 31 * result + (testsPass ? 1 : 0);
        return result;
    }
}
