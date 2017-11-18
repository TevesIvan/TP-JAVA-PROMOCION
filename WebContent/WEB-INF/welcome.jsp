<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Persona"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
        <button class="btn btn-lg " onclick="javascript: submitForm('Menu/personas')">Personas</button>
        <button class="btn btn-lg " onclick="javascript: submitForm('Menu/elementos')">Elementos</button>
        <button class="btn btn-lg " onclick="javascript: submitForm('Menu/tipos')">Tipos de Elementos</button>
        <button class="btn btn-lg " onclick="javascript: submitForm('Menu/reservas')">Reservas</button>
      </form>
</body>
</html>