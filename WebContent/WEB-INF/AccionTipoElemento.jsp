<%@page import="entidades.Persona"%>
<%@page import="entidades.TipoElemento"%>
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
<h3>Tipo de Elemento encontrado</h3><br>
<form class="form-Buscar" action="">
	<label>ID: <%=((TipoElemento)request.getAttribute("tipoElemento")).getId()%></label><br>
	<label>Nombre: <%=((TipoElemento)request.getAttribute("tipoElemento")).getNombre()%></label><br>
	<label>Cantidad Máxima: <%=((TipoElemento)request.getAttribute("tipoElemento")).getCantMax() %></label><br>
	<label>Tiempo Máximo de Reserva [hs]: <%=((TipoElemento)request.getAttribute("tipoElemento")).getMaxTiempo() %></label><br>
	<label>Máxima Anticipación de Reserva [Días]: <%=((TipoElemento)request.getAttribute("tipoElemento")).getDiasAntMax() %></label><br>
	<label>Encargado: <%=((TipoElemento)request.getAttribute("tipoElemento")).isEncargado()%></label><br>
</form>
<%
	}
	else if(request.getAttribute("accion").equals("insertar"))
	{
%>
	<h3>ipo de Elemento Agregado con éxito.</h3>
<%
	}
	else if(request.getAttribute("accion").equals("eliminar"))
	{
%>
	<h3>Tipo de Elemento Eliminado con éxito.</h3>
<%
	}
	else
	{
%>
	<h3>Tipo de Elemento Modificado con éxito.</h3>
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