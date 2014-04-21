function getProjectStats() {

    $.getJSON( "stats", function( data )
    {
        var sourcesCompile = boolToStringResult(data.sourcesCompile);
        var testsCompile = boolToStringResult(data.testsCompile);

        $(".dataCell").html("");

        $(".projectName").append(data.projectName);
        $(".sourceCompile").append(sourcesCompile);
        $(".testCompile").append(sourcesCompile);

        if(data.totalTestsRun == "0")
        {
            $(".testTotal").append("0");
            $(".testPassed").append("N/A");
            $(".testFailed").append("N/A");
            $(".testIgnored").append("N/A");
            $(".testPassProgress").css("width", "0%");
            $(".testFailProgress").css("width", "0%");
        }
        else
        {
            $(".testTotal").append(data.totalTests);
            $(".testPassed").append(data.totalPassingTests);
            $(".testFailed").append(data.totalFailingTests);
            $(".testIgnored").append(data.totalIgnoredTests);
            $(".testPassProgress").css("width", (data.totalPassingTests/data.totalTests)*100 + "%");
            $(".testFailProgress").css("width", (data.totalFailingTests/data.totalTests)*100 + "%");
        }

        $(".modifiedFiles").append(data.modifiedFiles);
        $(".addedFiles").append(data.addedFiles);
        $(".deletedFiles").append(data.deletedFiles);
        $(".unversionedFiles").append(data.unversionedFiles);


        var canCommit = (sourcesCompile == "OK" && testsCompile == "OK" && data.totalFailingTests == "0");
        $(".canCommit").append(boolToStringResult(canCommit));

        updateLineCountChart(data.sourceLineCounts, data.testLineCounts);
    });
}

function boolToStringResult(boolResult)
{
    if(boolResult)
    {
        return "OK";
    }
    else
    {
        return "FAIL";
    }
}

setInterval(function () { getProjectStats() }, 1000);