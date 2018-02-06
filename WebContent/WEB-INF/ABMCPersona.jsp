<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Persona"%>
<%@page import="entidades.Categoria"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en"><head>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
	<script src="/scripts/jquery.min.js"></script>
	<script src="/bootstrap/js/bootstrap.min.js"></script>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Personas</title>
<script type="text/javascript">
    	function submitForm(met) {
    		if(document.getElementById("box").checked){
    			document.getElementById("boxHidden").disabled=true;
    		}
    		window.history.pushState({}, document.title, "/" + "TP_JAVA_WEB" + "/" );
    		document.myForm.action=met;
        }
    	function submitForm2(){
    		window.history.pushState({}, document.title, "/" + "TP_JAVA_WEB" + "/" );
    	}
    </script>
</head>
<body>
<form class="form-menu" id="myForm" name="myForm" action="" method="post">
	<nav class="navbar navbar-default" role="navigation">
	<a class="navbar-brand">Sistema Reservas</a>
		<%
        	if(((Persona)session.getAttribute("user")).getCategoria().getNombreCat().equals("Administrador")){
   		%>
	<button type="submit" class="btn btn-default navbar-btn" onclick="javascript: submitForm('menu/personas')">Personas</button>
	<button type="submit" class="btn btn-default navbar-btn" onclick="javascript: submitForm('menu/tipos')">Tipos de Elementos</button>
		<%
        	}
    	%>
	<button type="submit" class="btn btn-default navbar-btn" onclick="javascript: submitForm('menu/elementos')">Elementos</button>
	<button type="submit" class="btn btn-default navbar-btn" onclick="javascript: submitForm('menu/reservas')">Reservas</button>
	<button type="submit" class="btn btn-danger navbar-btn" onclick="javascript: submitForm('menu/salir')">Salir</button>
	</nav>	
	
<h1>Bienvenido <%=((Persona)session.getAttribute("user")).getNombre() %></h1>
<table>
		<tr>
   			<th>DNI</th>
  		    <th>Apellido</th>
  		    <th>Nombre</th>
 	    </tr>
		<%
			ArrayList<Persona>listaPers= (ArrayList<Persona>)request.getAttribute("listaPer");
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
	

        <h2 class="form-ABMCPersona-heading">ABMC de Personas</h2>
        <label for="inputUser" class="sr-only">User</label>
        <input name="user" id="inputUser" class="form-control" placeholder="User" autofocus="" type=""><br>
        <label for="inputPass" class="sr-only">Password</label>
        <input name="pass" id="inputPass" class="form-control" placeholder="Password" type="Password"><br>
        <label for="lblDni" class="sr-only">DNI</label>
        <input name="dni" id="txtDni" class="form-control" placeholder="DNI" autofocus="" type=""><br>
        <label for="lblApellido" class="sr-only">Apellido</label>
        <input name="apellido" id="txtApellido" class="form-control" placeholder="Apellido" autofocus="" type=""><br>
        <label for="lblNombre" class="sr-only">Nombre</label>
        <input name="nombre" id="txtNombre" class="form-control" placeholder="Nombre" autofocus="" type=""><br>
        <input type="hidden" value="no" id="boxHidden" name=habilitado>
        <input type="checkbox" id="box" value="si" name="habilitado">Habilitado<br>
        <select name="categoria">
        
        	<%
        		ArrayList<Categoria>listaCat=(ArrayList<Categoria>)request.getAttribute("listaCat");
        		for(Categoria c : listaCat){
        	%>
        	<option value="<%=(Categoria)c%>"><%=c%></option>
        	<%
        		}
        	%>
        </select>
        <button class="btn btn-lg " onclick="javascript: submitForm('personas/buscar')">Buscar</button>
        <button class="btn btn-lg " onclick="javascript: submitForm('personas/insertar')">Insertar</button>
        <button class="btn btn-lg " onclick="javascript: submitForm('personas/eliminar')">Eliminar</button>
        <button class="btn btn-lg " onclick="javascript: submitForm('personas/modificar')">Modificar</button>
      </form>	
</body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</html>