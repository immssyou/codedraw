<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/> 
<title>Insert title here</title>

  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script type="text/javascript">
function gogo(){
	
	$.ajax({ 
	    url: "/getDataDBtest", 
	    type: 'GET', 
	    dataType: 'json', 
	    contentType: 'application/json',
	    success: function(data) { 
	     console.log(data);
	    },
	    error:function(data,status,er) { 
	       console.log("error");
	    }
	});
	
} 

</script>
</head>
<body>
<h2>spring 공부중</h2>
<button onClick="gogo()">크릭해봥</button>
</body>
</html>