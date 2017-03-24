var gMap;
var kmlOptions = {
	    preserveViewport : false, // true 이면 이전의 center, zoom을 변경하지
									// 않는다.
	    suppressInfoWindows : true// true 이면 마커의 기본 도움말을 띄우지 않는다.
	};
var infoWindowStatistics;

function initMap() {
	gMap = new google.maps.Map(document.getElementById('google-map'), {
        center: { lat: 36.5, lng: 127 },
        zoom: 7
    });
     infoWindowStatistics = new google.maps.InfoWindow({
        maxWidth: 150,    
	});
   
 	//kmlFileLoad
 	var kmlFileName = 'main_statistics_map_c2015'+getThisMonth()+'00';
 	addStatisticsKmlLayer('http://14.63.217.11/kml/'+kmlFileName);

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
			
			changeMainWeather2015(data.temperature, data.humidity);
			makeCurCrime(data.cDate.substring(4,6), data.regionCode);
			change2014Data('2014'+data.cDate.substring(4,8), data.regionCode);
			changeRegionName(data.regionName);

				});
			markerKmlLayer.setMap(gMap);
		}
 function changeRegionName(regionName){
	 var $regionName  = $('.area');
	 
	 $regionName.each(function (){
		 $(this).text(regionName);
	 });
 }       
function getOrigin() {
	if (location.origin) {
		return location.origin;
	} else {
		return location.protocol + '//' + location.host;
	}
}

function loadCrimeRateofArea(selMonth,init) {

    var monthClass = document.getElementsByClassName("month");
    for (var i = 0; i < monthClass.length; i++) {
        monthClass[i].innerHTML = selMonth + '월';
    }

    //backGroundColor 변경
	var $mSelbtn = $('#mSelBtn'+selMonth);
	//클릭 버튼 색 변경
		initMonthBtnCss();
		changeMonthBtnCss(selMonth);
	//kmlFileLoad
	if(!init){
		var kmlFileName = 'main_statistics_map_c2015'+selMonth+'00';
		addStatisticsKmlLayer('http://14.63.217.11/kml/'+kmlFileName);
	}
	
    createLineChart(selMonth);
	initSeoul(selMonth);
    createPieChartThisYear(selMonth);
    //changeSelMonthWeather()
}

function thisMonthClick() {
	var month = getThisMonth();
	var init = true;
	loadCrimeRateofArea(month,init);
}

function initMonthBtnCss(){
	var $mSelBtn = $('.mSelBtn');
	$mSelBtn.each(function(){
		$(this).css("background-color","#337ab7");
	})
	//$thisMbtn.css("background-color","#337ab7");
}

function changeMonthBtnCss(selMonth){
	$thisMbtn = $('#mSelBtn'+selMonth);
	$thisMbtn.css("background-color","#286090");
}

function getThisMonth(){
	var date = new Date();
	var month = date.getMonth()+1;
	var thisMonth;
	if(month <10)
		thisMonth = '0'+month;
	else
		thisMonth = month;
	return thisMonth;
}

function createPieChartThisYear(selMonth) {

    //ajax 호출후 데이터가져와서 커스터마이징
    zingchart.render({
        id: 'crimeChart',
        data: {
            graphset: [crimeConfig]
        },
        height: '250',
        width: '100%'
    });
}

function createPieChartPreYear(data) {
	var myConfig = preCrimeConfig;
	myConfig.series = [
	                   {
	                       text: "Docs",
	                       values: [data.crimeRate],
	                       lineColor: "#3468AF",
	                       backgroundColor: "#3468AF",
	                       lineWidth: 1,
	                   },
	                   {
	                       "value-box": {
	                           "visible": false
	                       },
	                       text: "Docs",
	                       values: [(100-data.crimeRate)],
	                       lineColor: "#394264", 
	                       backgroundColor: "#394264",
	                       lineWidth: 1,
	                       marker: {
	                           backgroundColor: '#394264'
	                       }
	                   }
	               ]
    zingchart.render({
        id: 'preCrimeChart',
        data: {
            graphset: [myConfig]
        },
        height: '250',
        width: '100%'
    });
    
}

function createLineChart(selMonth) {
	var param = {
			selMonth : selMonth
		};  
	
		$.ajax({
		    url : '/load5CrimeRateofArea',
		    contentType : 'application/json; charset=utf-8',
		    type : 'GET',
		    dataType : 'json',
		    data : param
		}).done(function(json, status, jqXHR){
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
		        text: '지역별 '+selMonth +'월 범죄 발생예측율',
		        fontColor: "#EBEBEC",
		        adjustLayout: true,
		        marginTop: 15
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
		        id: 'lineChart',
		        data: trendCuvConfig,
		        height: 500,
		        width: '100%'
		    });
		}).fail(function(xhr, status, e){

		});
}

function time() {
    var now = new Date();
    var year = now.getFullYear(); //년
    var month = now.getMonth() + 1; //월
    var day = now.getDate(); //일
    var hour = now.getHours(); //시
    var minute = now.getMinutes(); //분
    var second = now.getSeconds();// 초

    document.getElementById('date').innerHTML = "<strong>" + year + "-" + month + "-" + day + "</strong>";
    document.getElementById('clock').innerHTML = "<strong>" + hour + ":" + minute + ":" + second + "</strong>";
    setTimeout("time()", 1000);
}

function changeMainWeather2015(temperature, humidity) {
	var $container = $('#weatherContainer2015');
	$container.empty();
	var tempc  = Math.round(((temperature -32) / 1.8) *100) / 100;
    $('<strong>'+tempc+'</strong><br>').appendTo($container);
    $('<strong>'+humidity+'%</strong>').appendTo($container);
}

function changeMainWeather2014(temperature, humidity) {
	var $container = $('#weatherContainer2014');
	$container.empty();
	var tempc  = Math.round(((temperature -32) / 1.8) *100) / 100;
    $('<div><strong>'+tempc+'°C</strong></div>').appendTo($container);
    $('<div><strong>'+humidity+'%</strong></div>').appendTo($container)
}

function changeMainGrid2014(data){
	var $preCrimeData = $('.preCrimeData');
	var i =0;
	$preCrimeData.each(function(){
		$(this).text(data[i].crimeCount)
		i++;
	})
}

function initSeoul(selMonth){
	var param = {
			selMonth : selMonth
		};  
	
	$.ajax({
	    url : '/getCrimeRateofArea',
	    contentType : 'application/json; charset=utf-8',
	    type : 'GET',
	    dataType : 'json',
	    data : param
	}).done(function(json, status, jqXHR){
		
		changeMainWeather2015(json.data[0].temperature, json.data[0].humidity);
		makeCurCrime(selMonth, json.data[0].regionCode);
	}).fail(function(xhr, status, e){

	});
	change2014Data('2014'+selMonth+'00', '02');
	
}

function change2014Data(cDate, regionCode) {
	
	var param = {
			cDate : cDate,
			regionCode : regionCode
		};  
	
	//init 2014 Data
	$.ajax({
	    url : '/loadCrimeRate5AndAvg',
	    contentType : 'application/json; charset=utf-8',
	    type : 'GET',
	    dataType : 'json',
	    data : param
	}).done(function(json, status, jqXHR){
		changeMainWeather2014(json.listAvgCrime[0].temperature, json.listAvgCrime[0].humidity);
	    createPieChartPreYear( json.listAvgCrime[0]);
	    changeMainGrid2014(json.list5Crime);
	}).fail(function(xhr, status, e){

	});
}


function makeCurCrime(selMonth, regionCode) {
	createPieChartThisYear(selMonth, regionCode);
    createCurGrid(selMonth, regionCode);
} 

function createPieChartThisYear(selMonth, regionCode){
	   var param = {
	            selMonth : selMonth,
	            regionCode : regionCode
	      };
	   
	   // ajax 호출후 데이터가져와서 커스터마이징
	      $.ajax({
	          url : '/crimePIchart',
	          contentType : 'application/json; charset=utf-8',
	          type : 'GET',
	          data : param,
	          dataType : 'json',
	      }).done(function(json, status, jqXHR){
	    	  var crimePIchartInfo = json.crimePIchart;
	    	  
	    	  crimeConfig.series = [
	{
	    text: "Docs",
	    values: [crimePIchartInfo[0].monthRate],
	    lineColor: "#11A8AB",
	    backgroundColor: "#11A8AB",
	    lineWidth: 1,
	},
	{
	    "value-box": {
	        "visible": false
	    },
	    text: "Docs",
	    values: [100 - crimePIchartInfo[0].monthRate],
	    lineColor: "#394264",
	    backgroundColor: "#394264",
	    lineWidth: 1,
	    marker: {
	        backgroundColor: '#394264'
	    }
	}
	    	                        ];
	    	  
	    	  zingchart.render({
	              id: 'crimeChart',
	              data: {
	                  graphset: [crimeConfig]
	              },
	              height: '250',
	              width: '100%'
	          });
	         
	      }).fail(function(xhr, status, e){
	         
	      });
};

function createCurGrid(selMonth, regionCode) {
	   
	   var param = {
	         selMonth : selMonth,
	         regionCode : regionCode
	   };
	   
	    // ajax 호출후 데이터가져와서 커스터마이징
	   $.ajax({
	       url : '/crimeCount',
	       contentType : 'application/json; charset=utf-8',
	       type : 'GET',
	       data : param,
	       dataType : 'json',
	   }).done(function(json, status, jqXHR){
	      var i = 0;   
	      var $curCrimeDataClass = $(".curCrimeData");
	      $curCrimeDataClass.each(function(){         
	         var curCrime = json.crimeCountList[i++];
	         $(this).text(curCrime.crimeCount);
	      });
	      
	   }).fail(function(xhr, status, e){
	      
	   });
	   
	    
	}
$(document).ready(function () {
    time();
    thisMonthClick();
});