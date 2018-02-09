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
	<button type="submit" class="btn btn-info navbar-btn" onclick="javascript: submitForm('menu/reservas')">Reservas</button>
	<button type="submit" class="btn btn-danger navbar-btn navbar-right" onclick="javascript: submitForm('menu/salir')">Salir</button>
	</nav>	
<h2 style="margin-top:50px">Listado de Reservas</h2>
<table class="table">
	<thead>
		<tr>
			<%
        	if(((Persona)session.getAttribute("user")).getCategoria().getNombreCat().equals("Administrador")){
   			%>
			<th>Persona</th>
			<%
        	}
   			%>
   			<th>Elemento</th>
  		    <th>Detalle</th>
  		    <th>Fecha Hora Desde</th>
  		    <th>Fecha Hora Hasta</th>
 	    </tr>
 	 </thead>
 	 <tbody>
		<%
			ArrayList<Reserva>listaRes= (ArrayList<Reserva>)request.getAttribute("listaRes");
			for(Reserva r : listaRes){
		%>
		<tr>
			<%
        	if(((Persona)session.getAttribute("user")).getCategoria().getNombreCat().equals("Administrador")){
   			%>
   			<td><%=r.getPersona().getApellido()+" "+r.getPersona().getNombre()+", "+r.getPersona().getDni()%></td>
   			<%
        	}
   			%>
			<td><%=r.getElemento().getNombre()%></td>
			<td><%=r.getDetalle()%></td>
			<td><%=r.getFechaHoraDesde()%></td>
			<td><%=r.getFechaHoraHasta()%></td>
		</tr>
		<%
			}
		%>
		</tbody>
		</table>
        <h2 class="form-ABMCPersona-heading">Ingresar datos para Reserva Nueva</h2>
        <div class="form-group">
        	<label for="inputFechaHoraDesde">Fecha Hora Desde</label>
        	<input type="datetime" name="fechaHoraDesde" id="inputFechaHoraDesde" class="form-control" placeholder="DD/MM/AAAA HH:mm">
        </div><br>
        <div class="form-group">
        	<label for="inputFechaHoraHasta">Fecha Hora Hasta</label>
        	<input type="datetime" name="fechaHoraHasta" id="inputFechaHoraHasta" class="form-control" placeholder="DD/MM/AAAA HH:mm">
        </div><br>
        <div class="form-group">
        	<label for="inputDetalle">Detalle</label>
        	<textarea name="detalle" id="inputDetalle" class="form-control" rows="3" placeholder="Detalle"></textarea>
        </div><br>
        <div class="form-group">
        	<label for="tip">Tipo de Elemento</label>
        	<select class="form-control" name="tipoElemento" id="tip">
        	<%
        		ArrayList<TipoElemento>listaTip=(ArrayList<TipoElemento>)request.getAttribute("listaTip");
        		for(TipoElemento t : listaTip){
        	%>
        	<option value="<%=(TipoElemento)t%>"><%=t%></option>
        	<%
        		}
        	%>
        </select></div><br>
        <button class="btn btn-primary " onclick="javascript: submitForm('reservas/buscar')">Buscar</button><br><br>
        
        <h2 class="form-ABMCPersona-heading">Cancelar Reserva</h2>
        <div class="form-group">
        	<label for="res">ID</label>
        	<select class="form-control" name="id" id="res">
        	<%
        		for(Reserva r : listaRes){
        	%>
        	<option value="<%=r.getId()%>"><%=r.getElemento().getNombre()+", "+r.getElemento().getTipoElemento().getNombre()+", Fecha Desde: "+r.getFechaHoraDesde()%></option>
        	<%
        		}
        	%>
        </select></div><br>
        <button class="btn btn-primary " onclick="javascript: submitForm('reservas/cancelar')">Cancelar</button>
</form>		
</body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</html>