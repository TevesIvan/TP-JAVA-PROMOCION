<%@page import="entidades.Persona"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=request.getAttribute("accion")%></title>
<script type="text/javascript">
    	function submitForm() {
    		window.history.pushState({}, document.title, "/" + "TP_JAVA_WEB" + "/" );
        }
    </script>
</head>
<body>
<h1>Bienvenido <%=((Persona)session.getAttribute("user")).getNombre() %></h1>
<%
	if(request.getAttribute("accion").equals("buscar"))
	{
%>
<h2>Persona encontrada <%=((Persona)request.getAttribute("persona")).getNombre()%></h2>
<%
	}
%>
	<form class="form-Error" id="myForm" name="myForm" action="start" method="post">
		<button class="btn btn-lg" onclick="javascript: submitForm()">Aceptar</button>
	</form>
</body>
</html>