<%@page import="java.util.ArrayList"%>
<%@page import="entidades.TipoElemento"%>
<%@page import="entidades.Persona"%>
<%@page import="entidades.Reserva"%>
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
<title>Reservas</title>
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
<h2>Listado de Reservas</h2>
<table>
		<tr>
			<th>ID</th>
   			<th>Elemento</th>
  		    <th>Detalle</th>
  		    <th>Fecha Hora Desde</th>
  		    <th>Fecha Hora Hasta</th>
 	    </tr>
		<%
			ArrayList<Reserva>listaRes= (ArrayList<Reserva>)request.getAttribute("listaRes");
			for(Reserva r : listaRes){
		%>
		<tr>
			<td><%=r.getId()%></td>
			<td><%=r.getElemento().getNombre()%></td>
			<td><%=r.getDetalle()%></td>
			<td><%=r.getFechaHoraDesde()%></td>
			<td><%=r.getFechaHoraHasta()%></td>
		</tr>
		<%
			}
		%>
		</table>
        <h2 class="form-ABMCPersona-heading">Ingresar datos para Reserva Nueva</h2>
        <label for="inputFechaHoraDesde" class="sr-only">Fecha Hora Desde</label>
        <input name="fechaHoraDesde" id="inputFechaHoraDesde" class="form-control" placeholder="DD/MM/AAAA HH:mm" autofocus="" type=""><br>
        <label for="inputFechaHoraHasta" class="sr-only">Fecha Hora Hasta</label>
        <input name="fechaHoraHasta" id="inputFechaHoraHasta" class="form-control" placeholder="DD/MM/AAAA HH:mm" autofocus="" type=""><br>
        <label for="inputDetalle" class="sr-only">Detalle</label>
        <input name="detalle" id="inputDetalle" class="form-control" placeholder="Detalle" autofocus="" type=""><br>
        <label for="tipoElemento" class="sr-only">Tipo de Elemento</label>
        <select name="tipoElemento">
        	<%
        		ArrayList<TipoElemento>listaTip=(ArrayList<TipoElemento>)request.getAttribute("listaTip");
        		for(TipoElemento t : listaTip){
        	%>
        	<option value="<%=(TipoElemento)t%>"><%=t%></option>
        	<%
        		}
        	%>
        </select><br>
        <button class="btn btn-lg " onclick="javascript: submitForm('reservas/buscar')">Buscar</button><br><br>
        
        <h2 class="form-ABMCPersona-heading">Cancelar Reserva</h2>
        <label for="inputId" class="sr-only">ID</label>
        <select name="id">
        	<%
        		for(Reserva r : listaRes){
        	%>
        	<option value="<%=r.getId()%>"><%=r.getId()%></option>
        	<%
        		}
        	%>
        </select><br>
        <button class="btn btn-lg " onclick="javascript: submitForm('reservas/cancelar')">Cancelar</button>
</form>		
</body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</html>