<%@page import="entidades.Persona"%>
<%@page import="entidades.Elemento"%>
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
<h2>Elemento encontrado</h2><br>
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
	Elemento Agregado con éxito.
<%
	}
	else if(request.getAttribute("accion").equals("eliminar"))
	{
%>
	Elemento Eliminado con éxito.
<%
	}
	else
	{
%>
	Elemento Modificado con éxito.
<%
	}
%>
	<form class="form-Accion" id="myForm" name="myForm" action="start" method="post">
		<button class="btn btn-lg" onclick="javascript: submitForm()">Aceptar</button>
	</form>
</body>
</html>