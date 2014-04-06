function getProjectStats() {

    $.getJSON( "stats", function( data )
    {
        var resultDiv = $(".result");

        resultDiv.html("");
        resultDiv.append("Sources compile: " + boolToStringResult(data[0].sourcesCompile) + "<br/>");
        resultDiv.append("Tests compile: " +  boolToStringResult(data[0].testsCompile) + "<br/>");
        resultDiv.append("Tests pass: " +  boolToStringResult(data[0].testsPass) + "<br/>");

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