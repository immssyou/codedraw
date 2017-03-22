// JavaScript source code

$('#vdo-wrapper').tubular({
    videoId: 'GEJLyW72bXU?rel=0&amp;controls=0&amp;showinfo=0',
    width: $(window).width(),
    wrapperZIndex: -100,
    repeat: false,
    start: 0,
});

function skip(){
	location.href("/mainPage"); 
}
$('document').ready(function () {
    $('#btn-wrapper').hide();

    setTimeout(function () {
        $('#pause').trigger('click');
        $('#btn-wrapper').fadeIn(1000);
        checkVersion ();
    }, 28000);
   
    
});

function get_version_of_IE () { 

    var word; 
    var version = "N/A"; 

    var agent = navigator.userAgent.toLowerCase(); 
    var name = navigator.appName; 

    // IE old version ( IE 10 or Lower ) 
    if ( name == "Microsoft Internet Explorer" ) word = "msie "; 

    else { 
       // IE 11 
       if ( agent.search("trident") > -1 ) word = "trident/.*rv:"; 

       // Microsoft Edge  
       else if ( agent.search("edge/") > -1 ) word = "edge/"; 
    } 

    var reg = new RegExp( word + "([0-9]{1,})(\\.{0,}[0-9]{0,1})" ); 

    if (  reg.exec( agent ) != null  ) version = RegExp.$1 + RegExp.$2; 

    return version; 
} 

function checkVersion () { 

    var verString = get_version_of_IE(); 
    var verNumber = parseInt ( get_version_of_IE() , 10 ); 

    if ( verString != "N/A" ){
    	location.href = "/mainPage";
    }
} 

