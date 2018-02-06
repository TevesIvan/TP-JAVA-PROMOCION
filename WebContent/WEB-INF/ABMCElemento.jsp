<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Persona"%>
<%@page import="entidades.Elemento"%>
<%@page import="entidades.TipoElemento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en"><head>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
	<script src="/scripts/jquery.min.js"></script>
	<script src="/bootstrap/js/bootstrap.min.js"></script>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Elementos</title>
<script type="text/javascript">
    	function submitForm(met) {
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
   			<th>ID</th>
  		    <th>Nombre</th>
  		    <th>Tipo Elemento</th>
 	    </tr>
		<%
			ArrayList<Elemento>listaEle= (ArrayList<Elemento>)request.getAttribute("listaEle");
			for(Elemento e : listaEle){
		%>
		<tr>
			<td><%=e.getId() %></td>
			<td><%=e.getNombre() %></td>
			<td><%=e.getTipoElemento().getNombre() %></td>
		</tr>
		<%
			}
		%>
		</table>
        <h2 class="form-ABMCElemento-heading">ABMC de Elementos</h2>
        <label for="inputId" class="sr-only">ID</label>
        <select name="id">
        	<%
        		for(Elemento e : listaEle){
        	%>
        	<option value="<%=e.getId()%>"><%=e.getId()%></option>
        	<%
        		}
        	%>
        </select><br>
        <label for="inputNombre" class="sr-only">Nombre</label>
        <input name="nombre" id="inputNombre" class="form-control" placeholder="Nombre" type=""><br>
        <select name="tipo">
        	<%
        		ArrayList<TipoElemento>listaTip=(ArrayList<TipoElemento>)request.getAttribute("listaTip");
        		for(TipoElemento t : listaTip){
        	%>
        	<option value="<%=(TipoElemento)t%>"><%=t%></option>
        	<%
        		}
        	%>
        </select>
        <button class="btn btn-lg " onclick="javascript: submitForm('elementos/buscar')">Buscar</button>
        <button class="btn btn-lg " onclick="javascript: submitForm('elementos/insertar')">Insertar</button>
        <button class="btn btn-lg " onclick="javascript: submitForm('elementos/eliminar')">Eliminar</button>
        <button class="btn btn-lg " onclick="javascript: submitForm('elementos/modificar')">Modificar</button>
      </form>	
</body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</html>