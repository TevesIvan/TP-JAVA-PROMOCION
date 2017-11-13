<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Persona"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
  	<h1>Bienvenido <%=((Persona)session.getAttribute("user")).getNombre() %></h1> 
	<form class="form-menu" name="menu" action="Menu" method="post">
        <h2 class="form-menu-heading">Menu Principal</h2>
        <button class="btn btn-lg btn-primary btn-block" type="submit">ABMC Personas</button>
      </form>
	<!--
  	<h1>Bienvenido <%=((Persona)session.getAttribute("user")).getNombre() %></h1> 
        <h2 class="form-menu-heading">Menu Principal</h2>
        <form class="form-ABMCPersona-Menu" name="ABMCPersona-Menu" action="Menu" method="post">
        	<p><a type="submit">ABMC De Personas</a></p>
        </form>-->
</body>
</html>