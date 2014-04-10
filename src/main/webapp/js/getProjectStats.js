function getProjectStats() {

    $.getJSON( "stats", function( data )
    {
        $(".dataCell").html("");

        $(".projectName").append(data.projectName);
        $(".sourceCompile").append(boolToStringResult(data.projectStats.sourcesCompile));
        $(".testCompile").append(boolToStringResult(data.projectStats.testsCompile));

        if(data.projectStats.totalTestsRun == "0")
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
            var totalTests = data.projectStats.totalTestsRun + data.projectStats.totalTestsIgnored;
            $(".testTotal").append(totalTests);
            $(".testPassed").append(data.projectStats.totalTestsPassed);
            $(".testFailed").append(data.projectStats.totalTestsFailed);
            $(".testIgnored").append(data.projectStats.totalTestsIgnored);
            $(".testPassProgress").css("width", (data.projectStats.totalTestsPassed/totalTests)*100 + "%");
            $(".testFailProgress").css("width", (data.projectStats.totalTestsFailed/totalTests)*100 + "%");
        }

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