<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Persona"%>
<%@page import="entidades.Elemento"%>
<%@page import="entidades.TipoElemento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Elementos</title>
<script type="text/javascript">
    	function submitForm(met) {
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
		<form class="form-ABMCElemento" id="myForm" name="myForm" action="" method="post">
        <h2 class="form-ABMCElemento-heading">ABMC de Elementos</h2>
        <label for="inputId" class="sr-only">ID</label>
        <input name="id" id="inputId" class="form-control" placeholder="ID" autofocus="" type=""><br>
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
</html>