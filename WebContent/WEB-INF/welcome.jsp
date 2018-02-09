<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Persona"%>
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
<title>Menu</title>
<script type="text/javascript">
    	function submitForm(met) {
    		window.history.pushState({}, document.title, "/" + "TP_JAVA_WEB" + "/" );
    		document.menu.action=met;
        }
    	function submitForm2(){
    		window.history.pushState({}, document.title, "/" + "TP_JAVA_WEB" + "/" );
    	}
    </script>
</head>
<body>
	<form class="form-menu" id="menu" name="menu" action="" method="post">
	<nav class="navbar navbar-default container-fluid navbar-fixed-top" role="navigation">
	<a class="navbar-brand">Sistema Reservas</a>
	<p class="navbar-text"><%=((Persona)session.getAttribute("user")).getApellido()+" "+ ((Persona)session.getAttribute("user")).getNombre()%> </p>
		<%
        	if(((Persona)session.getAttribute("user")).getCategoria().getNombreCat().equals("Administrador")){
   		%>
   	<p class="navbar-text" style="color:blue">Administrador</p>
	<button type="submit" class="btn btn-default navbar-btn" onclick="javascript: submitForm('menu/personas')">Personas</button>
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
	</form>
	<!--	<ul class="nav navbar-nav">
 	<li class="active"><a href="#">Menú Principal</a></li>
	<li><a href="#" onclick="javascript: submitForm('menu/personas')">Personas</a></li>
	<li><a href="#">Tipos</a></li>
	<li><a href="#">Elementos</a></li>
	<li><a href="#">Reservas</a></li> 
	
	</ul> -->

  <!--  	<h1>Bienvenido <%=((Persona)session.getAttribute("user")).getNombre() %></h1> 
	<form class="form-menu" id="menu" name="menu" action="" method="post">
        <h2 class="form-menu-heading">Menu Principal</h2>
        <%
        	if(((Persona)session.getAttribute("user")).getCategoria().getNombreCat().equals("Administrador")){
        %>
        <button class="btn btn-lg " onclick="javascript: submitForm('menu/personas')">Personas</button>
        <button class="btn btn-lg " onclick="javascript: submitForm('menu/tipos')">Tipos de Elementos</button>
        <%
        	}
        %>
        <button class="btn btn-lg " onclick="javascript: submitForm('menu/elementos')">Elementos</button>
        <button class="btn btn-lg " onclick="javascript: submitForm('menu/reservas')">Reservas</button>
      </form> -->
      
       <script src="style/ie10-viewport-bug-workaround.js"></script>
</body>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</html>