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
	<nav class="navbar navbar-default container-fluid navbar-fixed-top" role="navigation">
	<a class="navbar-brand">Sistema Reservas</a>
	<p class="navbar-text"><%=((Persona)session.getAttribute("user")).getApellido()+" "+ ((Persona)session.getAttribute("user")).getNombre()%> </p>
		<%
        	if(((Persona)session.getAttribute("user")).getCategoria().getNombreCat().equals("Administrador")){
   		%>
   	<p class="navbar-text" style="color:blue">Administrador</p>
	<button type="submit" class="btn btn-info navbar-btn" onclick="javascript: submitForm('menu/personas')">Personas</button>
	<button type="submit" class="btn btn-default navbar-btn" onclick="javascript: submitForm('menu/tipos')">Tipos de Elementos</button>
		<%
        	}
        	else if(((Persona)session.getAttribute("user")).getCategoria().getNombreCat().equals("Encargado")){
    	%>
    <p class="navbar-text" style="color:blue">Encargado</p>
    	<%
        	}else{
    	%>
    <p class="navbar-text" style="color:blue">Usuario</p>
    	<%
        	}
    	%>
	<button type="submit" class="btn btn-default navbar-btn" onclick="javascript: submitForm('menu/elementos')">Elementos</button>
	<button type="submit" class="btn btn-default navbar-btn" onclick="javascript: submitForm('menu/reservas')">Reservas</button>
	<button type="submit" class="btn btn-danger navbar-btn navbar-right" onclick="javascript: submitForm('menu/salir')">Salir</button>
	</nav>	
	
<table class="table" style="margin-top:50px">
	<thead>
		<tr>
   			<th>DNI</th>
  		    <th>Apellido</th>
  		    <th>Nombre</th>
 	    </tr>
 	</thead>
 	<tbody>
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
	</tbody>
		</table>		
	

        <h2 class="form-ABMCPersona-heading">ABMC de Personas</h2>
        <div class="form-group">
        	<label for="lblDni">DNI</label>
        	<input type="number" name="dni" id="txtDni" class="form-control" placeholder="DNI">
        </div><br>
        <button class="btn btn-primary " onclick="javascript: submitForm('personas/buscar')">Buscar</button>
        <button class="btn btn-primary " onclick="javascript: submitForm('personas/eliminar')">Eliminar</button><br><br>
        <div class="form-group">
      	  	<label for="inputUser">Nombre de Usuario:</label>
        	<input type="text" name="user" id="inputUser" class="form-control" placeholder="Usuario">
        </div><br>
        <div class="form-group">
        	<label for="inputPass">Contraseña</label>
        	<input type="Password" name="pass" id="inputPass" class="form-control" placeholder="Contraseña">
        </div><br>
        <div class="form-group">
        	<label for="lblApellido">Apellido</label>
        	<input type="text" name="apellido" id="txtApellido" class="form-control" placeholder="Apellido">
        </div><br>
        <div class="form-group">
        	<label for="lblNombre">Nombre</label>
        	<input type="text" name="nombre" id="txtNombre" class="form-control" placeholder="Nombre">
        </div><br>
        <input type="hidden" value="no" id="boxHidden" name=habilitado>
        <div class="checkbox">
        	<label><input type="checkbox" id="box" value="si" name="habilitado">Habilitado</label>
        </div><br>
        <div class="form-group">
        	<label for="cat">Categoría</label>
        	<select class="form-control" name="categoria" id="cat">
        
        	<%
        		ArrayList<Categoria>listaCat=(ArrayList<Categoria>)request.getAttribute("listaCat");
        		for(Categoria c : listaCat){
        	%>
        	<option value="<%=(Categoria)c%>"><%=c%></option>
        	<%
        		}
        	%>
        </select></div><br>

        <button class="btn btn-primary " onclick="javascript: submitForm('personas/insertar')">Insertar</button>
        <button class="btn btn-primary " onclick="javascript: submitForm('personas/modificar')">Modificar</button>
      </form>	
</body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</html>