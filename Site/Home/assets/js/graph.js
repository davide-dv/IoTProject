/**
 * Created by Antonio Tagliente on 22.01.17.
 */

$(function () {
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Year', 'Sales', 'Expenses'],
            ['2004',  1000,      400],
            ['2005',  1170,      460],
            ['2006',  660,       1120],
            ['2007',  1030,      540]
        ]);

        var data2 = google.visualization.arrayToDataTable([
            ['Director (Year)',  'Rotten Tomatoes', 'IMDB'],
            ['Alfred Hitchcock (1935)', 8.4,         7.9],
            ['Ralph Thomas (1959)',     6.9,         6.5],
            ['Don Sharp (1978)',        6.5,         6.4],
            ['James Hawes (2008)',      4.4,         6.2]
        ]);

        var options = {
            title: 'Company Performance',
            curveType: 'function',
            legend: { position: 'bottom' }
        };

        var options2 = {
            title: 'The decline of \'The 39 Steps\'',
            vAxis: {title: 'Accumulated Rating'},
            isStacked: true
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);

        var chart2 = new google.visualization.SteppedAreaChart(document.getElementById('chart_div'));

        chart2.draw(data2, options2);
    }
})