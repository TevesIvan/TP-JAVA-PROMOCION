<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Persona"%>
<%@page import="entidades.TipoElemento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tipos Elementos</title>
<script type="text/javascript">
    	function submitForm(met) {
    		if(document.getElementById("box").checked){
    			document.getElementById("boxHidden").disabled=true;
    		}
    		window.history.pushState({}, document.title, "/" + "TP_JAVA_WEB" + "/" );
    		document.myForm.action=met;
        }
    </script>
</head>
<body>
<h1>Bienvenido <%=((Persona)session.getAttribute("user")).getNombre() %></h1>
<table>
		<tr>
   			<th>ID</th>
  		    <th>Nombre</th>
  		    <th>Cantidad Maxima</th>
  		    <th>Tiempo M�ximo Reserva [hs]</th>
  		    <th>Tiempo M�ximo de Anticipaci�n [d�as]</th>
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
			<td>S�</td>
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
		<form class="form-ABMCTipoElemento" id="myForm" name="myForm" action="" method="post">
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
        <label for="inputCantMax" class="sr-only">Cantidad M�xima</label>
        <input name="cantMax" id="inputCantMax" class="form-control" placeholder="Cantidad Max" type=""><br>
        <label for="inputMaxTiempo" class="sr-only">Tiempo M�ximo Reserva [hs]</label>
        <input name="maxTiempo" id="inputMaxTiempo" class="form-control" placeholder="Tiempo M�x" type=""><br>
        <label for="inputDiasAntMax" class="sr-only">Tiempo M�ximo de Anticipaci�n [d�as]</label>
        <input name="diasAntMax" id="inputdiasAntMax" class="form-control" placeholder="Anticipaci�n Max" type=""><br>
        <input type="hidden" value="no" id="boxHidden" name=encargado>
        <input type="checkbox" id="box" value="si" name="encargado">Encargado<br>
        
        <button class="btn btn-lg " onclick="javascript: submitForm('tiposElementos/buscar')">Buscar</button>
        <button class="btn btn-lg " onclick="javascript: submitForm('tiposElementos/insertar')">Insertar</button>
        <button class="btn btn-lg " onclick="javascript: submitForm('tiposElementos/eliminar')">Eliminar</button>
        <button class="btn btn-lg " onclick="javascript: submitForm('tiposElementos/modificar')">Modificar</button>
      </form>	
</body>
</html>