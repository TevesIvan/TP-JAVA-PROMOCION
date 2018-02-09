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
	<button type="submit" class="btn btn-info navbar-btn" onclick="javascript: submitForm('menu/elementos')">Elementos</button>
	<button type="submit" class="btn btn-default navbar-btn" onclick="javascript: submitForm('menu/reservas')">Reservas</button>
	<button type="submit" class="btn btn-danger navbar-btn navbar-right" onclick="javascript: submitForm('menu/salir')">Salir</button>
	</nav>	
<table class="table" style="margin-top:50px">
	<thead>
		<tr>
  		    <th>Nombre</th>
  		    <th>Tipo Elemento</th>
 	    </tr>
 	 </thead>
 	 <tbody>
		<%
			ArrayList<Elemento>listaEle= (ArrayList<Elemento>)request.getAttribute("listaEle");
			for(Elemento e : listaEle){
		%>
		<tr>
			<td><%=e.getNombre() %></td>
			<td><%=e.getTipoElemento().getNombre() %></td>
		</tr>
		<%
			}
		%>
	</tbody>
		</table>
        <h2 class="form-ABMCElemento-heading">ABMC de Elementos</h2>
        <div class="form-group">
        	<label for="sel1">Elemento</label>
        	<select class="form-control" name="id" id="sel1">
        	<%
        		for(Elemento e : listaEle){
        	%>
        	<option value="<%=e.getId()%>"><%=e.getNombre()+", "+e.getTipoElemento().getNombre()%></option>
        	<%
        		}
        	%>
        	</select></div><br>
        <button class="btn btn-primary " onclick="javascript: submitForm('elementos/buscar')">Buscar</button>
        <button class="btn btn-primary " onclick="javascript: submitForm('elementos/eliminar')">Eliminar</button><br><br>
        <div class="form-group">
        	<label for="inputNombre">Nombre de Elemento</label>
        	<input type="text" name="nombre" id="inputNombre" class="form-control" placeholder="Nombre">
        </div><br>
        <div class="form-group">
        	<label for="sel2">Tipo de Elemento</label>
        	<select class="form-control" name="tipo" id="sel2">
        	<%
        		ArrayList<TipoElemento>listaTip=(ArrayList<TipoElemento>)request.getAttribute("listaTip");
        		for(TipoElemento t : listaTip){
        	%>
        	<option value="<%=(TipoElemento)t%>"><%=t%></option>
        	<%
        		}
        	%>
        	</select></div><br>
        <button class="btn btn-primary " onclick="javascript: submitForm('elementos/insertar')">Insertar</button>
        <button class="btn btn-primary " onclick="javascript: submitForm('elementos/modificar')">Modificar</button>
      </form>	
</body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</html>