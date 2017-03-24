var gMap;
var kmlOptions = {
	    preserveViewport : false, // true 이면 이전의 center, zoom을 변경하지
									// 않는다.
	    suppressInfoWindows : true// true 이면 마커의 기본 도움말을 띄우지 않는다.
	};
var infoWindowStatistics;
/*simulation chart*/
zingchart.THEME = "classic";

function loadSimul() {
	
	var $progress = $('<div class="progress-circle"></div>').appendTo($('#google-map'));
	$progress.show();
	var param = {
			 temperature : $('#tempSlider').val(),
			 humidity : $('#humiditySlider').val()
		};  
	
		$.ajax({
		    url : '/loadSimul',
		    contentType : 'application/json; charset=utf-8',
		    type : 'GET',
		    dataType : 'json',
		    data : param
		}).done(function(json, status, jqXHR){
		
			var url = 'http://14.63.217.11/kml/'+json.kmlFileName;
			addStatisticsKmlLayer(url);
			
			var myConfig = trendCuvConfig;
			var crimeRateArray = new Array();
			
			for(var i=0; i< 5; i++) {
				var arry = new Array();
				for(var j=0; j<json.data[i].length; j++){
					arry[j] = json.data[i][j].crimeRate;
				}
				crimeRateArray[i] = arry;
			}
			
			myConfig.title = {
		        text: '지역별 범죄 발생예측율',
		        fontColor: "#EBEBEC",
		        adjustLayout: true,
		        marginTop: 15
		    }
			
			myConfig.subtitle = {
			        text: "시뮬레이션",
			        fontColor: "#EBEBEC",
			        adjustLayout: true,
			        marginTop: 45
			  }
			
			myConfig.series = [
				           		{
				        		    values: crimeRateArray[0],
				        		    text: "절도",
				        		    backgroundColor: "#46C28C",
				        		    lineColor: "#46C28C",
				        		    marker: {
				        		        backgroundColor: "#46C28C",
				        		        borderColor: "#46C28C"

				        		    }
				        		},
				        		{
				        		    values: crimeRateArray[1],
				        		    text: "강간",
				        		    backgroundColor: "#D47362",
				        		    lineColor: "#D47362",
				        		    marker: {
				        		        backgroundColor: "#D47362",
				        		        borderColor: "#D47362"

				        		    }
				        		},
				        		{
				        		    values: crimeRateArray[2],
				        		    text: "강도",
				        		    backgroundColor: "#0CBADB",
				        		    lineColor: "#0CBADB",
				        		    marker: {
				        		        backgroundColor: "#0CBADB",
				        		        borderColor: "#0CBADB"

				        		    }
				        		},
				                {
				                    values: crimeRateArray[3],
				                    text: "살인",
				                    backgroundColor: "#F5D0A9",
				                    lineColor: "#F7BE81",
				                    marker: {
				                        backgroundColor: "#F5D0A9",
				                        borderColor: "#F7BE81"

				                    }
				                },
				                {
				                    values: crimeRateArray[4],
				                    text: "폭력",
				                    backgroundColor: "#D358F7",
				                    lineColor: "#CC2EFA",
				                    marker: {
				                        backgroundColor: "#D358F7",
				                        borderColor: "#CC2EFA"

				                    }
				                }
				            ]
			
		    zingchart.render({
			    id: 'simulation-chart',
		        data: myConfig,
		        height: 500,
		        width: '100%'
		    });
			$progress.hide();
		}).fail(function(xhr, status, e){
			$progress.hide();
		});
}

function addStatisticsKmlLayer(url) {
	//init 후
	gMap = new google.maps.Map(document.getElementById('google-map'), {
        center: { lat: 36.5, lng: 127 },
        zoom: 7
    });
     infoWindowStatistics = new google.maps.InfoWindow({
        maxWidth: 150,    
	});

	 // Create the legend and display on the map
	if($('#statisticsMaplegend').length > 0){
		$('#statisticsMaplegend').remove();					
	}
	
    var legend = document.createElement('div');
    legend.id = 'statisticsMaplegend';
    $('#statisticsMaplegend > *').remove();
    var content = []; 
    content.push('<p><div class="statisticsMap legend1"></div>0~3</p>');
    content.push('<p><div class="statisticsMap legend2"></div>4~7</p>');
    content.push('<p><div class="statisticsMap legend3"></div>8~11</p>');
    content.push('<p><div class="statisticsMap legend4"></div>12~15</p>');
    content.push('<p><div class="statisticsMap legend5"></div>16~19</p>');
    content.push('<p><div class="statisticsMap legend6"></div>20~23</p>');
    content.push('<p><div class="statisticsMap legend7"></div>24~27</p>');
    content.push('<p><div class="statisticsMap legend8"></div>28~31</p>');
    content.push('<p><div class="statisticsMap legend9"></div>32~35</p>');
    content.push('<p><div class="statisticsMap legend10"></div>36~</p>');
    legend.innerHTML = content.join('');
    legend.index = 1;
    gMap.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(legend);
    
	markerKmlLayer = new google.maps.KmlLayer(url, kmlOptions);
	google.maps.event.addListener(markerKmlLayer, 'click', function(e){

		/* TODO 데이터 싱크 처리 필요 */
		if(typeof e.featureData.description === 'undefined'){
			return 
		}
		var data = JSON.parse(e.featureData.description);
		// 말풍선 구현
		var $infoContent = $('<div id= "infoContent">');
		$infoContent.append(''+
				data.regionName+'<br>'+'<p>'+'범죄율 : '+data.value+'%</p>');
		// html5에서 singqutor 처리
		infoWindowStatistics.close();
		/*
		 * if(!(typeof infoWindowStatistics.content === 'undefined')){
		 * infoWindowStatistics.content = null; }
		 */
				infoWindowStatistics.setPosition(e.latLng);
				infoWindowStatistics.setContent($infoContent[0]);
				infoWindowStatistics.open(gMap);

			});
		markerKmlLayer.setMap(gMap);
}


$(document).ready(function () {
	zingchart.render({
	    id: 'simulation-chart',
	    data: trendCuvConfig,
	    height: 663,
	    width: '100%'
	});
});