/**
 * Created by Antonio Tagliente on 22.01.17.
 */

$(function () {

    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
        var data = returnGraphData("TempGraph");

        var options = {
            title: 'Temperature',
            curveType: 'function',
            legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);

    }

    returnJsonData("EventsGraph");

    function returnGraphData($page) {
        var jsonData = returnJsonData($page);
        var data = new google.visualization.DataTable(jsonData);
        return data;
    }

    function returnJsonData($page) {
        var jsonData = $.ajax({
            url: "../common_assets/php/" + $page + ".php",
            dataType: "json",
            async: false
        }).responseText;
        return jsonData;
    }
})

