<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Persona"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu</title>
<!-- Bootstrap core CSS -->
    <link href="style/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="style/start.css" rel="stylesheet">
<script type="text/javascript">
    	function submitForm(met) {
    		document.menu.action=met;
        }
    </script>
</head>
<body>
	
  	<h1>Bienvenido <%=((Persona)session.getAttribute("user")).getNombre() %></h1> 
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
      </form>
</body>
</html>