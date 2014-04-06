package com.insano10.miru;

public class ProjectStats
{
    private boolean sourcesCompile;
    private boolean testsCompile;
    private boolean testsPass;

    private ProjectStats()
    {
    }

    public ProjectStats(boolean sourcesCompile, boolean testsCompile, boolean testsPass)
    {
        this.sourcesCompile = sourcesCompile;
        this.testsCompile = testsCompile;
        this.testsPass = testsPass;
    }

    public static ProjectStats fromCsvString(final String str)
    {
        String[] tokens = str.split(",");
        boolean sourcesCompile = intToBool(tokens[0].trim());
        boolean testsCompile = intToBool(tokens[1].trim());
        boolean testsPass = intToBool(tokens[2].trim());

        return new ProjectStats(sourcesCompile, testsCompile, testsPass);
    }

    private static boolean intToBool(final String integerString)
    {
        return !integerString.equals("0");
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

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (sourcesCompile ? 1 : 0);
        result = 31 * result + (testsCompile ? 1 : 0);
        result = 31 * result + (testsPass ? 1 : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "ProjectStats{" +
                "sourcesCompile=" + sourcesCompile +
                ", testsCompile=" + testsCompile +
                ", testsPass=" + testsPass +
                '}';
    }
}
