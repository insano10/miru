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
        }
        else
        {
            $(".testTotal").append(data[0].totalTestsRun);
            $(".testPassed").append(data[0].totalTestsPassed);
            $(".testFailed").append(data[0].totalTestsFailed);
            $(".testIgnored").append(data[0].totalTestsIgnored);
        }

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