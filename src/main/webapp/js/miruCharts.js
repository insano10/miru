//GLOBAL VARS
var options = {
    series:
    {
        lines: { show: true, fill: 0.5 },
        points: { show: false }
    },
    xaxis:
    {
        tickLength: 0,
        mode: "time",
        timeformat: "%H:%M:%S",
        minTickSize: [5, "minute"]
    },
    grid:
    {
        borderColor: "rgba(220,220,220,1)"
    },
    yaxis:
    {
        tickLength: 0,
        minTickSize: 1,
        tickDecimals: 0,
        tickFormatter: function(val, axis) { return val < axis.max ? val.toFixed(0) : "Line count";}
    }
};

function initialiseLineCountChart()
{
    var sourceSeries = { label: "source lines", data : [], color: "rgba(151,187,205,1)"};
    var testSeries = { label: "test lines", data : [], color: "rgba(220,220,220,1)"};
    var data = [sourceSeries, testSeries];
    $("#lineCountChart").plot(data, options);
}

function updateLineCountChart(sourceDataPointArray, testDataPointArray)
{
    var sourceSeries = { label: "source lines", data : [[1397069754000,1],[1397069755000,2],[1397069756000,3]], shadowSize: 0, color: "rgba(151,187,205,1)"};
    var testSeries = { label: "test lines", data : [[1397069754000,3],[1397069755000,2],[1397069756000,1]], shadowSize: 0, color: "rgba(220,220,220,1)"};
    var data = [sourceSeries, testSeries];


    $("#lineCountChart").plot(data, options);
}

$(document).ready(function(){
    initialiseLineCountChart();
})