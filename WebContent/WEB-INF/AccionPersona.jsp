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
<h2>Persona encontrada</h2><br>
<form class="form-Buscar" action="">
	<label>ID: <%=((Persona)request.getAttribute("persona")).getId()%></label><br>
	<label>Nombre: <%=((Persona)request.getAttribute("persona")).getNombre()%></label><br>
	<label>Apellido: <%=((Persona)request.getAttribute("persona")).getApellido()%></label><br>
	<label>DNI: <%=((Persona)request.getAttribute("persona")).getDni()%></label><br>
	<label>Usuario: <%=((Persona)request.getAttribute("persona")).getUsuario()%></label><br>
	<label>Contraseña: <%=((Persona)request.getAttribute("persona")).getContraseña()%></label><br>
	<label>Categoria: <%=((Persona)request.getAttribute("persona")).getCategoria().getNombreCat()%></label><br>
	<label>Habilitado: <%=((Persona)request.getAttribute("persona")).isHabilitado()%></label><br>
</form>
<%
	}
	else if(request.getAttribute("accion").equals("insertar"))
	{
%>
	Persona Agregada con éxito.
<%
	}
	else if(request.getAttribute("accion").equals("eliminar"))
	{
%>
	Persona Eliminada con éxito.
<%
	}
	else
	{
%>
	Persona Modificada con éxito.
<%
	}
%>
	<form class="form-Accion" id="myForm" name="myForm" action="start" method="post">
		<button class="btn btn-lg" onclick="javascript: submitForm()">Aceptar</button>
	</form>
</body>
</html>