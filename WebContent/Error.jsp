<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
<script type="text/javascript">
    	function submitForm() {
    		window.history.pushState({}, document.title, "/" + "TP_JAVA_WEB" + "/" );
        }
    </script>
</head>
<body>
	<form class="form-Error" id="myForm" name="myForm" action="<%=(String)request.getAttribute("url")%>" method="post">
		<label class="sr-only"><%=(String)request.getAttribute("error")%></label>
		<button class="btn btn-lg" onclick="javascript: submitForm()">Aceptar</button>
	</form> 
</body>
</html>