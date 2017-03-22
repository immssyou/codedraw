$(document).ready(function () {
	init()
});

function init() {
	   $.ajax({
	       url : '/keyWordList',
	       contentType : 'application/json; charset=utf-8',
	       type : 'GET',
	       dataType : 'json',
	   }).done(function(json, status, jqXHR){
		   
	      var i = 0;   
	      var $koreaData = $(".koreaData");
	      var $germanyData = $(".germanyData");
	      var $usa = $(".usa");
	      var $japan= $(".japan");
	      var $france = $(".france");
	      var $russia = $(".russia");
	      
	      $koreaData.each(function(){
	 	         $(this).text(json.wordList[i].word);
	 	         i++;
	      });
	      
	      $germanyData.each(function(){
	 	         $(this).text(json.wordList[i].word);
	 	         i++;
	      });
	      
	      $usa.each(function(){
	 	         $(this).text(json.wordList[i].word);
	 	         i++;
	      });
	      
	      $japan.each(function(){
	 	         $(this).text(json.wordList[i].word);
	 	         i++;
	      });
	      
	      $france.each(function(){
	 	         $(this).text(json.wordList[i].word);
	 	         i++;
	      });
	      
	      $russia.each(function(){
	 	         $(this).text(json.wordList[i].word);
	 	         i++;
	      });
	      
	   }).fail(function(xhr, status, e){
	      
	   });
}