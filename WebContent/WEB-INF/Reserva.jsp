<%@page import="java.util.ArrayList"%>
<%@page import="entidades.TipoElemento"%>
<%@page import="entidades.Persona"%>
<%@page import="entidades.Reserva"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reservas</title>
<script type="text/javascript">
    	function submitForm(met) {
    		window.history.pushState({}, document.title, "/" + "TP_JAVA_WEB" + "/" );
    		document.myForm.action=met;
        }
    </script>
</head>
<body>
<h1>Bienvenido <%=((Persona)session.getAttribute("user")).getNombre() %></h1>
<h2>Listado de Reservas</h2>
<table>
		<tr>
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
			<td><%=r.getElemento().getNombre()%></td>
			<td><%=r.getDetalle()%></td>
			<td><%=r.getFechaHoraDesde()%></td>
			<td><%=r.getFechaHoraHasta()%></td>
		</tr>
		<%
			}
		%>
		</table>
<form class="form-Reserva" id="myForm" name="myForm" action="" method="post">
        <h2 class="form-ABMCPersona-heading">Ingresar datos para Reserva</h2>
        <label for="inputFechaHoraDesde" class="sr-only">Fecha Hora Desde</label>
        <input name="fechaHoraDesde" id="inputFechaHoraDesde" class="form-control" placeholder="FechaHoraDesde" autofocus="" type=""><br>
        <label for="inputFechaHoraHasta" class="sr-only">Fecha Hora Hasta</label>
        <input name="fechaHoraHasta" id="inputFechaHoraHasta" class="form-control" placeholder="FechaHoraHasta" autofocus="" type=""><br>
        <label for="inputDetalle" class="sr-only">Detalle</label>
        <input name="detalle" id="inputDetalle" class="form-control" placeholder="Detalle" required="" autofocus="" type=""><br>
        <select name="tipoElemento">
        	<%
        		ArrayList<TipoElemento>listaTip=(ArrayList<TipoElemento>)request.getAttribute("listaTip");
        		for(TipoElemento t : listaTip){
        	%>
        	<option value="<%=(TipoElemento)t%>"><%=t%></option>
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
</html>