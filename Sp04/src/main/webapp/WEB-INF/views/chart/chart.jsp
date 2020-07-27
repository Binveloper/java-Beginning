<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Google Chart API</title>

<script src="https://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>


</head>

<script type="text/javascript">
	function ajaxData(){
		/* console.log("dd");
		alert("dd"); */
		$.ajax({
			/* url: "./chartData",
			dataType: "json", */
			url: "./chartDataRan.json",
			type: "post",
			async: true,
			success: function(list) {
				google.charts.load('current', {'packages':['corechart']}); //api 로딩
				google.charts.setOnLoadCallback(drawChart); //콜백 메소드 등록
				
				function drawChart() {
					var dataChart = [['글로벌 회사', '주가총액']];
					if(list.length != 0){
						$.each(list, function(i, item){
							dataChart.push([item.item, item.number]);
						});
					}else{
						dataChart.push(['넘어온 데이터가 없음', 1]);
					}
					
					var data = google.visualization.arrayToDataTable(dataChart);
					var view = new google.visualization.DataView(data);
					var options = {
							title: "글로벌 IT회사 주가 총액 비교",
							width: 350,
							height: 200
					};
					var chart1 = new google.visualization.PieChart(document.getElementById('piechart'));
					chart1.draw(view, options);
					
					var chart2 = new google.visualization.LineChart(document.getElementById('linechart'));
					chart2.draw(view, options);
					
					var chart3 = new google.visualization.BarChart(document.getElementById('barchart'));
					chart3.draw(view, options);
					
					var chart4 = new google.visualization.ColumnChart(document.getElementById('columnchart'));
					chart4.draw(view, options);
				}
			}
		});
	}
	$(document).ready(function(){
		ajaxData();
	});
	
	setInterval(function(){ajaxData()}, 1000);
</script>

<body>
    <center>
        <h1>Google Chart</h1> 
        <input type="button" value="데이터호출" onclick="ajaxData()"/>
        &nbsp;&nbsp;
	    <a href="../">인덱스</a>
	    <br/><br/>
	   
		<div id="piechart"></div>
		<div id="linechart"></div>
		<div id="barchart"></div>
		<div id="columnchart"></div>
		
	</center>
</body>
</html>
