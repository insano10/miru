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
        timeformat: "%H:%M",
        minTickSize: [10, "second"]
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
    },
    legend:
    {
        backgroundOpacity: 0
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
    var sourceSeries = { label: "source lines", data : sourceDataPointArray, shadowSize: 0, color: "rgba(151,187,205,1)"};
    var testSeries = { label: "test lines", data : testDataPointArray, shadowSize: 0, color: "rgba(180,180,180,1)"};
    var data = [sourceSeries, testSeries];


    $("#lineCountChart").plot(data, options);
}

$(document).ready(function(){
    initialiseLineCountChart();
})