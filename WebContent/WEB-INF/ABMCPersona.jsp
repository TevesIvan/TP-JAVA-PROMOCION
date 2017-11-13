<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Persona"%>
<%@page import="controlador.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Bienvenido <%=((Persona)session.getAttribute("user")).getNombre() %></h1>
<%
	if((Persona)session.getAttribute("per") != null)
	{
%>
<h2>Persona encontrada <%=((Persona)session.getAttribute("per")).getApellido()%></h2>
<%
	}
%>
<table>
		<%
			CrtlABMCPersona ctrl=new CrtlABMCPersona();
			ArrayList<Persona>listaPers= ctrl.getAll();			
			for(Persona p : listaPers){
		%>
		<tr>
			<td><%=p.getDni() %></td>
			<td><%=p.getApellido() %></td>
			<td><%=p.getNombre() %></td>
		</tr>
		<%
			}
		%>
		</table>		
	
<form class="form-ABMCPersona" name="ABMCPersona" action="ABMCPersona" method="post">
        <h2 class="form-ABMCPersona-heading">ABMC de Personas</h2>
        <label for="inputUser" class="sr-only">User</label>
        <input name="user" id="inputUser" class="form-control" placeholder="User" required="" autofocus="" type=""><br>
        <label for="inputPass" class="sr-only">Password</label>
        <input name="pass" id="inputPass" class="form-control" placeholder="Password" required="" type="Password"><br>
         <label for="lblDni" class="sr-only">DNI</label>
        <input name="dni" id="txtDni" class="form-control" placeholder="DNI" required="" autofocus="" type=""><br>
        <label for="lblApellido" class="sr-only">Apellido</label>
        <input name="apellido" id="txtApellido" class="form-control" placeholder="Apellido" required="" autofocus="" type="" 
<%
	if((Persona)session.getAttribute("per") != null)
	{
%>
        value="<%=((Persona)session.getAttribute("per")).getApellido()%>"><br>
<%
	}
%>
<br>
        <label for="lblNombre" class="sr-only">Nombre</label>
        <input name="nombre" id="txtNombre" class="form-control" placeholder="Nombre" required="" autofocus="" type=""><br>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Buscar</button>
      </form>	
</body>
</html>