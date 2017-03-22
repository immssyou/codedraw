// Menu toggle
$("#menu-toggle").click(function (e) {
    e.preventDefault();
    $("#wrapper").toggleClass("toggled");
});

var myColors = [
                ["#F5AAAA", "#F5A0A0", "#F06E6E", "#F06464", "#EB3232", "#EB0000", "#D25A5A", "#CD4646", "#CD0000"],
                ["#FFC300", "#FFC800", "#FFCD00", "#FFD200", "#FFD700", "#FFAA28", "#FFA01E", "#FF9614", "#FF8200"],
                ["#74D19D", "#6AC793", "#60BD89", "#6DD66D", "#63CC63", "#54BD54", "#4AB34A", "#369F36", "#228B22"],
                ["#E0E6F8", "#A9BCF5", "#819FF7", "#5882FA", "#2E64FE", "#0040FF", "#013ADF", "#0431B4", "#08298A"],
                ["#A9F5F2", "#81F7F3", "#58FAF4", "#2EFEF7", "#00FFFF", "#01DFD7", "#04B4AE", "#088A85", "#0B615E"],
                ["#D358F7", "#CC2EFA", "#BF00FF", "#A901DB", "#8904B1", "#6A0888", "#4C0B5F", "#6A0888", "#2F0B3A"]
                ];

function createWordcloud() {
      $.ajax({
          url : '/wordList',
          contentType : 'application/json; charset=utf-8',
          type : 'GET',
          dataType : 'json',
          
      }).done(function(json, status, jqXHR){
    	  
    	 var cloudChartClass = document.getElementsByClassName('chart-cloud');

         
         for( var i = 0; i < 6; i++){
            var wordList = json.wordList[i];
            var list = new Array();
            for (var j = 0; j < wordList.length; j++){
            	if( j >100){
            		break;
            	}
               var key = wordList[j].word;
               list[j] = {
                     text : key,
                     weight : wordList[j].frequency
               }
            }
            $(cloudChartClass[i]).jQCloud('destroy');
             $(cloudChartClass[i]).jQCloud(list, {
                 autoResize: true,
                 fontSize: {
                     from: 0.1,
                     to: 0.03
                 },
                 shape: 'elliptic',
                 colors : myColors[i]
             });
         }
         
      }).fail(function(xhr, status, e){
         console.log("데이터 오류");
      });
}

$(document).ready(function () {
   createWordcloud();
});