function getProjectStats() {

    $.getJSON( "stats", function( data )
    {
        $(".dataCell").html("");

        $(".sourceCompile").append(boolToStringResult(data[0].sourcesCompile));
        $(".testCompile").append(boolToStringResult(data[0].testsCompile));

        if(data[0].totalTestsRun == "0")
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
            var totalTests = data[0].totalTestsRun + data[0].totalTestsIgnored;
            $(".testTotal").append(totalTests);
            $(".testPassed").append(data[0].totalTestsPassed);
            $(".testFailed").append(data[0].totalTestsFailed);
            $(".testIgnored").append(data[0].totalTestsIgnored);
            $(".testPassProgress").css("width", (data[0].totalTestsPassed/totalTests)*100 + "%");
            $(".testFailProgress").css("width", (data[0].totalTestsFailed/totalTests)*100 + "%");
        }

        $(".lineCounts").append(data[0].sourceLineCount+"<br/>");
        $(".lineCounts").append(data[0].testLineCount);

        updateLineCountChart([65,54,30,81,56,55,40], [20,60,42,58,31,21,50]);
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