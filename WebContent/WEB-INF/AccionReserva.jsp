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
<%
	if(request.getAttribute("accion").equals("buscar"))
	{
		ArrayList<Elemento>listaResDisp= (ArrayList<Elemento>)request.getAttribute("listaResDisp");
		if(listaResDisp.isEmpty())
		{
		%>
			<h3>Elementos no disponibles.</h3>
				<form class="form-Accion" id="myForm" name="myForm" action="start" method="post">
					<button class="btn btn-primary" onclick="javascript: submitForm2()">Aceptar</button>
				</form>
		<%
		}else
		{
		%>
<h3>Elementos Disponibles para Reserva</h3><br>
<table class="table">
	<thead>
		<tr>
   			<th>Nombre</th>
 	    </tr>
 	</thead>
 	<tbody>
		<%
		for(Elemento e : listaResDisp){
		%>
		<tr>
			<td><%=e.getNombre()%></td>
		</tr>
		<%
			}
		%>
		</tbody>
		</table>
		
	<form class="form-Accion" id="myForm" name="myForm" action="start" method="post">
	<div class="form-group">
		<label for="inputId">Elemento</label>
        <select class="form-control" name="id" id="inputId">
        	<%
        		for(Elemento e : listaResDisp){
        	%>
        	<option value="<%=e.getId()%>"><%=e.getNombre()+", "+e.getTipoElemento().getNombre()%></option>
        	<%
        		}
        	%>
        </select></div><br>
		<button class="btn btn-primary" onclick="javascript: submitForm('reservas/reservar')">Reservar</button>
		<button class="btn btn-danger" onclick="javascript: submitForm2()">Cancelar</button>
	</form>
<%
	}}
	else if(request.getAttribute("accion").equals("reservar"))
	{
%> 
	<h3>Elemento reservado con éxito.</h3>
	<form class="form-Accion" id="myForm" name="myForm" action="start" method="post">
		<button class="btn btn-lg" onclick="javascript: submitForm2()">Aceptar</button>
	</form>
<%
	}
	else
	{
%>
	<h3>Reserva cancelada con éxito.</h3>
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