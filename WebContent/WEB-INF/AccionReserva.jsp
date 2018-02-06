<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Persona"%>
<%@page import="entidades.Elemento"%>
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
<title><%=request.getAttribute("accion")%></title>
<script type="text/javascript">
    	function submitForm2() {
    		window.history.pushState({}, document.title, "/" + "TP_JAVA_WEB" + "/" );
        }
    	
    	function submitForm(met) {
    		window.history.pushState({}, document.title, "/" + "TP_JAVA_WEB" + "/" );
    		document.myForm.action=met;
        }
    </script>
</head>
<body>
<h1>Bienvenido <%=((Persona)session.getAttribute("user")).getNombre() %></h1>
<%
	if(request.getAttribute("accion").equals("buscar"))
	{
		ArrayList<Elemento>listaResDisp= (ArrayList<Elemento>)request.getAttribute("listaResDisp");
		if(listaResDisp.isEmpty())
		{
		%>
			Elementos no disponibles.
				<form class="form-Accion" id="myForm" name="myForm" action="start" method="post">
					<button class="btn btn-lg" onclick="javascript: submitForm2()">Aceptar</button>
				</form>
		<%
		}else
		{
		%>
<h2>Elementos Disponibles para Reserva</h2><br>
<table>
		<tr>
			<th>ID</th>
   			<th>Nombre</th>
 	    </tr>
		<%
		for(Elemento e : listaResDisp){
		%>
		<tr>
			<td><%=e.getId()%></td>
			<td><%=e.getNombre()%></td>
		</tr>
		<%
			}
		%>
		</table>
		
	<form class="form-Accion" id="myForm" name="myForm" action="start" method="post">
		<label for="inputId" class="sr-only">ID Elemento</label>
        <select name="id">
        	<%
        		for(Elemento e : listaResDisp){
        	%>
        	<option value="<%=e.getId()%>"><%=e.getId()%></option>
        	<%
        		}
        	%>
        </select><br>
		<button class="btn btn-lg" onclick="javascript: submitForm('reservas/reservar')">Reservar</button>
		<button class="btn btn-lg" onclick="javascript: submitForm2()">Cancelar</button>
	</form>
<%
	}}
	else if(request.getAttribute("accion").equals("reservar"))
	{
%> 
	Elemento reservado con éxito.
	<form class="form-Accion" id="myForm" name="myForm" action="start" method="post">
		<button class="btn btn-lg" onclick="javascript: submitForm2()">Aceptar</button>
	</form>
<%
	}
	else
	{
%>
	Reserva cancelada con éxito.
	<form class="form-Accion" id="myForm" name="myForm" action="start" method="post">
		<button class="btn btn-lg" onclick="javascript: submitForm2()">Aceptar</button>
	</form>
<%
	}
%>
</body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</html>