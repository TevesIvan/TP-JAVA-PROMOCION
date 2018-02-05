<%@page import="entidades.Persona"%>
<%@page import="entidades.TipoElemento"%>
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
<h2>Tipo de Elemento encontrado</h2><br>
<form class="form-Buscar" action="">
	<label>ID: <%=((TipoElemento)request.getAttribute("tipoElemento")).getId()%></label><br>
	<label>Nombre: <%=((TipoElemento)request.getAttribute("tipoElemento")).getNombre()%></label><br>
	<label>Cantidad Máxima: <%=((TipoElemento)request.getAttribute("tipoElemento")).getCantMax() %></label><br>
	<label>Encargado: <%=((TipoElemento)request.getAttribute("tipoElemento")).isEncargado()%></label><br>
</form>
<%
	}
	else if(request.getAttribute("accion").equals("insertar"))
	{
%>
	Tipo de Elemento Agregado con éxito.
<%
	}
	else if(request.getAttribute("accion").equals("eliminar"))
	{
%>
	Tipo de Elemento Eliminado con éxito.
<%
	}
	else
	{
%>
	Tipo de Elemento Modificado con éxito.
<%
	}
%>
	<form class="form-Accion" id="myForm" name="myForm" action="start" method="post">
		<button class="btn btn-lg" onclick="javascript: submitForm()">Aceptar</button>
	</form>
</body>
</html>