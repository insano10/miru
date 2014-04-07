function getProjectStats() {

    $.getJSON( "stats", function( data )
    {
        var resultDiv = $(".result");

        resultDiv.html("");
        resultDiv.append("Sources compile: " + boolToStringResult(data[0].sourcesCompile) + "<br/>");
        resultDiv.append("Tests compile: " +  boolToStringResult(data[0].testsCompile) + "<br/>");
        resultDiv.append("Tests total: " +  data[0].totalTestsRun + "<br/>");
        resultDiv.append("Tests passed: " +  data[0].totalTestsPassed + "<br/>");
        resultDiv.append("Tests failed: " +  data[0].totalTestsFailed + "<br/>");

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