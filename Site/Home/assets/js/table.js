$(function(){
	
	$.get("/common_assets/php/EventsGraph.php",function(data){
		var json = JSON.parse(data);

		for(var n=0; n < json.length; n++) {
			$("#tab").DataTable().row.add([
				json[n]["typology"],
				json[n]["date"],
				json[n]["note"]	
			]).draw();
		}
	});
});
