<%@page import="entidades.Persona"%>
<%@page import="entidades.Elemento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en"><head>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
	<script src="/scripts/jquery.min.js"></script>
	<script src="/bootstrap/js/bootstrap.min.js"></script>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title><%=request.getAttribute("accion")%></title>
<script type="text/javascript">
    	function submitForm() {
    		window.history.pushState({}, document.title, "/" + "TP_JAVA_WEB" + "/" );
        }
    </script>
</head>
<body>
<%
	if(request.getAttribute("accion").equals("buscar"))
	{
%>
<h3>Elemento encontrado</h3><br>
<form class="form-Buscar" action="">
	<label>ID: <%=((Elemento)request.getAttribute("elemento")).getId()%></label><br>
	<label>Nombre: <%=((Elemento)request.getAttribute("elemento")).getNombre()%></label><br>
	<label>Tipo Elemento: <%=((Elemento)request.getAttribute("elemento")).getTipoElemento().getNombre() %></label><br>
</form>
<%
	}
	else if(request.getAttribute("accion").equals("insertar"))
	{
%>
	<h3>Elemento Agregado con éxito.</h3>
<%
	}
	else if(request.getAttribute("accion").equals("eliminar"))
	{
%>
	<h3>Elemento Eliminado con éxito.</h3>
<%
	}
	else
	{
%>
	<h3>Elemento Modificado con éxito.</h3>
<%
	}
%>
	<form class="form-Accion" id="myForm" name="myForm" action="start" method="post">
		<button class="btn btn-primary" onclick="javascript: submitForm()">Aceptar</button>
	</form>
</body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</html>