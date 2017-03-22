

// 지도의 패키지 및 클래스 정의입니다.
(function($){
	$.extend(true, window, {
		'watcher' : {
			'maps' : {
			    'Map' : GoogleMap
			}
		}
	});

	// 지도의 기본 옵션 설정
	var options = {
	    zoom : 3,
	    center: { lat: 36.5, lng: 127 },
	}; 

	// public function GoogleMap(container)
	// ------------------------------------ 
	// 구글 지도의 생성자입니다.
	/**
	 * 구글 지도의 생성자입니다.
	 * 
	 * @param {HTMLElement}
	 *            container 페이지 상에 지도를 보여줄 곳입니다.
	 */
	function GoogleMap(container){
		var map = null;
		var rect = null;
		var markerClusterer = null;
		var clusterer_option = {
		    gridSize : 30,
		    maxZoom : 18
		};

		var kmlOptions = {
		    preserveViewport : false, // true 이면 이전의 center, zoom을 변경하지 않는다.
		    suppressInfoWindows : true// true 이면 마커의 기본 도움말을 띄우지 않는다.
		};

		var btsKmlOptions = {
		    preserveViewport : true,
		    suppressInfoWindows : false
		}

		var btsKmlLayer = null;

		var markerKmlLayers = {};

		// 로딩된 모든 마커들.
		var markers = [];

		// 선택한 파라메터의 마커들..
		var selected_parameters_markers = [];
		
		// 기지국 marker
		var selected_bts_markers = [];
		
		// 기지국 ployGon
		var piePoly_bts_polyGons = [];
		
		// polyLine
		var flightPaths = [];
		
		var infoWindow = new google.maps.InfoWindow({
			content : ''
		});
		
		var infoWindowStatistics = new google.maps.InfoWindow({
		        maxWidth: 550,   
		});
		var id = 'watcher.map.Map_' + (String(Math.random())).replace(/\D/g, '');

		var bounds_changed_timeout = 0;

		var refresh_timeout = 0;
	
		
		function _getCoord(lng, lat){
			return new google.maps.LatLng(lat, lng);
		}

		function resizeEvent(){
			google.maps.event.addListenerOnce(map, 'idle', function() {
			    google.maps.event.trigger(map, 'resize');
			});
		}
		
		(function(){
			map = new google.maps.Map(container, options);
/*			infoWindow.open(map);
			infoWindow.close();*/
		})();

		function refresh(){
			window.clearTimeout(refresh_timeout);
			refresh_timeout = window.setTimeout(function(){
				if(markerClusterer){
					markerClusterer.clearMarkers();
					markerClusterer.addMarkers(selected_parameters_markers);
				}
				else{
					markerClusterer = new window.MarkerClusterer(map, selected_parameters_markers, clusterer_option);
				}
			}, 200);
		}

		function getZoom(){
			return map.getZoom();
		}

		function setZoom(zoom){
			map.setZoom(zoom);
		}

		function setBounds(bounds){
			var sw = _getCoord(bounds.min.lng, bounds.min.lat);
			var ne = _getCoord(bounds.max.lng, bounds.max.lat);

			map.fitBounds(new google.maps.LatLngBounds(sw, ne));
		}

		function setCenter(point){
			map.setCenter(point);
		}

		function resize(){
			google.maps.event.trigger(map, "resize");
		}

		function showInfoOnly(info){
			infoWindow.setPosition(_getCoord(info.lng, info.lat));
			infoWindow.setContent(info.content);
			infoWindow.open(map);
		}

		function showInfo(info){
			infoWindow.setContent(info.content);
			infoWindow.open(map, info.marker);
		}

		function hideInfo(){
			infoWindow.close();
		}
		
		function closeInfoStatistics(){
			infoWindowStatistics.close();
		}
		function showMarker(marker){
			marker.setMap(map);
			selected_parameters_markers.push(marker);
//			refresh();
		}

		function hideMarker(marker){
			// marker.setMap(null);
			var i = selected_parameters_markers.indexOf(marker);
			if(i > -1){
				selected_parameters_markers.splice(i, 1);
			}
//			refresh();
		}

		function createMarker(info){
			if(info.marker){
				return info.marker;
			}
			  var image = {
					    url: info.legendIconPath,
					    anchor: new google.maps.Point(32, 32) //기지국 할때 필요!
					  };
			  
			var marker = new google.maps.Marker({
			    'position' : _getCoord(info.lng, info.lat),
			    'map' : map,
			    'clickable' : true,
			    'cursor' : 'pointer',
			    'animation' : true,
			    'flat' : true,
			    'icon' : image
			});
			marker.setMap(map);
			return marker;
		}

		/*
		 * 초당 측정 데이터 포인터
		 */
		function addMarker(info){
			var marker = createMarker(info);
			google.maps.event.addListener(marker, 'click', function(){
				hideInfo();
				showInfo(info);

			});
			markers.push(info);
			info.marker = marker;
			return marker;
		} 
		
		/*마커 클리어*/
		
		function clearMarker() {
			for (var i = 0; i < selected_parameters_markers.length; i++) {
				selected_parameters_markers[i].setMap(null);
			}
			selected_parameters_markers.length = 0;
		}
		
		function removeMarker(marker){
			marker.setMap(null);
		}
		/*
		 * 기지국
		 * 
		 */
		function showBTS(marker){
			// marker.setMap(map);
			if(selected_bts_markers.indexOf(marker) < 0) selected_bts_markers.push(marker);
			// refresh();
		}

		function hideBTS(marker){
			// marker.setMap(null);
			var i = selected_bts_markers.indexOf(marker);
			if(i > -1){
				selected_bts_markers.splice(i, 1);
			}
			// refresh();
		}

		function addBTS(info){
			var marker = createMarker(info);
			
			google.maps.event.addListener(marker, 'click', function(){
				em.dispatchEvent('DRAW_PLOLY_LINE_SITE', info);
			});

		//	if(map.getZoom() <= clusterer_option.maxZoom) marker.setMap(null);

			selected_bts_markers.push(marker);

			info.marker = marker;
			return marker;
		}

		function highlightMarker(value, highlight){
			// value.marker.setOptions({
			// animation: highlight ? google.maps.Animation.DROP : null
			// });
		}

		function panToMarker(value){
			var latLng = value.marker.getPosition();
			var bounds = map.getBounds();
			if(!bounds.contains(latLng)){
				this.showMarker(value.marker);
				map.panTo(latLng);
			}
		}

		function translates(point){
			return new $.Deferred().resolve();
		}

		function addBtsKmlLayer(url){
			if(!btsKmlLayer){
				btsKmlLayer = new google.maps.KmlLayer(url, btsKmlOptions);
				
				google.maps.event.addListener(btsKmlLayer, 'click', function(e){
					/* TODO 데이터 싱크 처리 필요 */
					if(typeof e.featureData.description === 'undefined'){
						return
					}
					var syncData = JSON.parse(e.featureData.description);
					em.dispatchEvent('DRAW_PLOLY_LINE_SITE', syncData);
				});
				btsKmlLayer.setMap(map);
	
			} else {
				btsKmlLayer.setMap(null);
				btsKmlLayer = null;
				//기지국 폴리라인 제거
				removePolyLine();
			}
			
		}
		
		
		function addStatisticsKmlLayer(id, url) {
			var markerKmlLayer = markerKmlLayers[id];
			if(!markerKmlLayer){
				 // Create the legend and display on the map
				if($('#statisticsMaplegend').length > 0){
					$('#statisticsMaplegend').remove();					
				}
				
		        var legend = document.createElement('div');
		        legend.id = 'statisticsMaplegend';
			    $('#statisticsMaplegend > *').remove();
		        var content = []; 
		        content.push('<h3>Legend</h3>');
		        content.push('<p><div class="statisticsMap legend1"></div>0~10</p>');
		        content.push('<p><div class="statisticsMap legend2"></div>11~20</p>');
		        content.push('<p><div class="statisticsMap legend3"></div>21~30</p>');
		        content.push('<p><div class="statisticsMap legend4"></div>31~40</p>');
		        content.push('<p><div class="statisticsMap legend5"></div>41~50</p>');
		        content.push('<p><div class="statisticsMap legend6"></div>51~60</p>');
		        content.push('<p><div class="statisticsMap legend7"></div>61~70</p>');
		        content.push('<p><div class="statisticsMap legend8"></div>71~80</p>');
		        content.push('<p><div class="statisticsMap legend9"></div>81~90</p>');
		        content.push('<p><div class="statisticsMap legend10"></div>91~100</p>');
		        legend.innerHTML = content.join('');
		        legend.index = 1;
		        map.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(legend);
		        
				markerKmlLayer = new google.maps.KmlLayer(url, kmlOptions);
				google.maps.event.addListener(markerKmlLayer, 'click', function(e){

					/* TODO 데이터 싱크 처리 필요 */
					if(typeof e.featureData.description === 'undefined'){
						return 
					}
					var data = JSON.parse(e.featureData.description);
					//말풍선 구현 
					var $infoContent = $('<div id= "infoContent">');
					$infoContent.append(''+
							data.areaName+'<br>'+'<p>'+'SUCCESS : '+data.value+'%</p>');
					//html5에서 singqutor 처리
					var vJson = encodeURIComponent(JSON.stringify(data)).replace("'","\\'");		
					$infoContent.append('<br>'+'<button onClick="em.dispatchEvent(\'SHOW_STATISTICS_INBUILDING\' ,\''+vJson+'\')">Show Inbuilding</button></li>');
					infoWindowStatistics.close();
					if(!(typeof infoWindowStatistics.content === 'undefined')){
						infoWindowStatistics.content = null;										
					}
					infoWindowStatistics.setPosition(e.latLng);
					infoWindowStatistics.setContent($infoContent[0]);
					infoWindowStatistics.open(map);
					$('.gm-style-iw').children().css('overflow','');
					
					em.dispatchEvent('SHOW_STATISTICSMAP_INFO', data);
 
				});
				markerKmlLayers[id] = markerKmlLayer;
			}
			markerKmlLayer.setMap(map);
		}
		
		function removeMarkerKmlLayer(id, url){
			var markerKmlLayer = markerKmlLayers[id];
			if(markerKmlLayer){
				google.maps.event.clearInstanceListeners(markerKmlLayer);
				markerKmlLayer.setMap(null);
				markerKmlLayers[id] = null;
			}
		}
		
		 
		$.extend(this, {
		    'id' : id,
		    'map' : map,
 
		    'setBounds' : setBounds,
		    'setCenter' : setCenter,

		    'getZoom' : getZoom,
		    'setZoom' : setZoom,

		    'resize' : resize,

		    'showInfoOnly' : showInfoOnly,

		    'showInfo' : showInfo,
		    'hideInfo' : hideInfo,
		    
		    'closeInfoStatistics' : closeInfoStatistics,

		    // 'createMarker': createMarker,
		    'showMarker' : showMarker,
		    'hideMarker' : hideMarker,
		    'addMarker' : addMarker,
		    'clearMarker' : clearMarker,
		    
		    'addStatisticsKmlLayer' : addStatisticsKmlLayer,
		    
		    'resizeEvent' : resizeEvent,
		    
		});
	}
})(jQuery);