//GLOBAL VARS
var lineCountChart;
var options = { animation : false };

function initialiseLineCountChart()
{
//    var emptyData = createLineCountChartDataStructure([0], [0])
//
//    var canvas = document.getElementById("lineCountChart");
//    var ctx = canvas.getContext("2d");
//    lineCountChart = new Chart(ctx);
//    lineCountChart.Line(emptyData, options);
    var sourceSeries = { label: "source lines", data : [], shadowSize: 0};
    var testSeries = { label: "test lines", data : [], shadowSize: 0};
    var data = [sourceSeries, testSeries];
    var options = {
        series: {
            lines: { show: true },
            points: { show: false }
        }
    };

    $("#lineCountChart").plot(data, options);
}

function updateLineCountChart(sourceDataPointArray, testDataPointArray)
{
//    var data = createLineCountChartDataStructure(sourceDataPointArray, testDataPointArray);
//    lineCountChart.Line(data, options);

    var sourceSeries = { label: "source lines", data : [[1,1],[2,2],[3,3]], shadowSize: 0};
    var testSeries = { label: "test lines", data : [[1,3],[2,2],[3,1]], shadowSize: 0};
    var data = [sourceSeries, testSeries];
    var options = {
        series: {
            lines: { show: true },
            points: { show: false }
        }
    };

    $("#lineCountChart").plot(data, options);
}

function createLineCountChartDataStructure(sourceCountDataPoints, testCountDataPoints)
{
    var data = {
        labels : ["","","","","","",""], //currently required :(
        datasets : [
            {
                fillColor : "rgba(220,220,220,0.5)",
                strokeColor : "rgba(220,220,220,1)",
                pointColor : "rgba(220,220,220,1)",
                pointStrokeColor : "#fff",
                data : sourceCountDataPoints
            },
            {
                fillColor : "rgba(151,187,205,0.5)",
                strokeColor : "rgba(151,187,205,1)",
                pointColor : "rgba(151,187,205,1)",
                pointStrokeColor : "#fff",
                data : testCountDataPoints
            }
        ]
    };
    return data;
}

$(document).ready(function(){
    initialiseLineCountChart();
})