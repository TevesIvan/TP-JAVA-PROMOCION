<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Persona"%>
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
<title>Tipos Elementos</title>
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
   			<th>ID</th>
  		    <th>Nombre</th>
  		    <th>Cantidad Maxima</th>
  		    <th>Tiempo Máximo Reserva [hs]</th>
  		    <th>Tiempo Máximo de Anticipación [días]</th>
  		    <th>Encargado</th>
 	    </tr>
		<%
			ArrayList<TipoElemento>listaTip= (ArrayList<TipoElemento>)request.getAttribute("listaTip");
			for(TipoElemento t : listaTip){
		%>
		<tr>
			<td><%=t.getId() %></td>
			<td><%=t.getNombre() %></td>
			<td><%=t.getCantMax() %></td>
			<td><%=t.getMaxTiempo() %></td>
			<td><%=t.getDiasAntMax() %></td>
			<%
				if(t.isEncargado())
				{
			%>
			<td>Sí</td>
			<%
				}
				else
				{
			%>
			<td>No</td>
		</tr>
		<%
			}
			}
		%>
		</table>
        <h2 class="form-ABMCTipoElemento-heading">ABMC de Tipos de Elementos</h2>
        <label for="inputId" class="sr-only">ID</label>
        <select name="id">
        	<%
        		for(TipoElemento t : listaTip){
        	%>
        	<option value="<%=t.getId()%>"><%=t.getId()%></option>
        	<%
        		}
        	%>
        </select><br>
        <label for="inputNombre" class="sr-only">Nombre</label>
        <input name="nombre" id="inputNombre" class="form-control" placeholder="Nombre" type=""><br>
        <label for="inputCantMax" class="sr-only">Cantidad Máxima</label>
        <input name="cantMax" id="inputCantMax" class="form-control" placeholder="Cantidad Max" type=""><br>
        <label for="inputMaxTiempo" class="sr-only">Tiempo Máximo Reserva [hs]</label>
        <input name="maxTiempo" id="inputMaxTiempo" class="form-control" placeholder="Tiempo Máx" type=""><br>
        <label for="inputDiasAntMax" class="sr-only">Tiempo Máximo de Anticipación [días]</label>
        <input name="diasAntMax" id="inputdiasAntMax" class="form-control" placeholder="Anticipación Max" type=""><br>
        <input type="hidden" value="no" id="boxHidden" name=encargado>
        <input type="checkbox" id="box" value="si" name="encargado">Encargado<br>
        
        <button class="btn btn-lg " onclick="javascript: submitForm('tiposElementos/buscar')">Buscar</button>
        <button class="btn btn-lg " onclick="javascript: submitForm('tiposElementos/insertar')">Insertar</button>
        <button class="btn btn-lg " onclick="javascript: submitForm('tiposElementos/eliminar')">Eliminar</button>
        <button class="btn btn-lg " onclick="javascript: submitForm('tiposElementos/modificar')">Modificar</button>
      </form>	
</body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</html>