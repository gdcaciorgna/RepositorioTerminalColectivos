<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<%@include file="JSPFiles/includeimports.jsp" %>


<title>Reservar Viaje</title>
</head>
<body>

<!-- INICIO - REDIRECCION A LOGIN --> 
<%@ page import = "controlers.FechaControlers" %>
<%@ page import = "entities.*" %>
<%@ page import = "java.text.SimpleDateFormat" %>


<%
Usuario usuario;
FechaControlers fCon = new FechaControlers(); 

%>


<%
String username="s/usuario", estado="s/estado"; 
usuario = (Usuario) session.getAttribute("usuarioActual");  
if(usuario!=null) 
{
	username = usuario.getUsername(); 
	estado = usuario.getEstado(); 
}

 if(username.equals("s/usuario") || estado.equals("s/estado") || !estado.equals("activo")) 
	{   
 
	String sitioweb = "http://localhost:8080/Terminal-Colectivos/"; 
	response.sendRedirect(sitioweb+"login.jsp"); 
	} 
%>
<!-- FIN - REDIRECCION A LOGIN -->

<% 
HttpSession sesion = request.getSession();

Plan viajeSeleccionado = (Plan) sesion.getAttribute("ViajeSeleccionado");

// INICIO - RECUPERAR FECHA Y HORA POR SEPARADO

String fechaString = fCon.dateToddMMyyyy(viajeSeleccionado.getFechaHora());
String horaString = fCon.dateTohhmm(viajeSeleccionado.getFechaHora());
String fechaHoraString = fCon.dateToddMMyyyyhhmm(viajeSeleccionado.getFechaHora());
				   
//FIN - RECUPERAR FECHA Y HORA POR SEPARADO

%>



<%@ page import = "entities.Plan" %>


<jsp:include page="JSPFiles/includemenu.jsp" />  

<form action="ReservarViaje" method = "post">
<div class="container login-container">
<div class="row">
    
    <div class="col-sm">
       <div class="login-form-1 center-block">
             <h1>Reservar Viaje</h1>
             
             <hr>
             
             <div class="alert alert-light" role="alert">
			  <div class="text-center text-justify"><b>---------- Datos del viaje ----------</b></div> <br>
			 
			 
			 
			  Fecha y hora del viaje: <b> <%= fechaHoraString %> </b> <br>
  			  Origen: <b><%= viajeSeleccionado.getOrigen() %></b> <br>
  			  Destino: <b><%= viajeSeleccionado.getDestino() %></b> <br>
 			  Empresa: <b><%= viajeSeleccionado.getColectivo().getEmpresa().getNombre() %></b> <br>
			  Precio: <b>$ <%= viajeSeleccionado.getPrecio() %></b> <br>
			  Asientos Disponibles: <b><%= viajeSeleccionado.getColectivo().getCapacidad() %></b> <br> <!-- SE DEBERÁ UTILIZAR UNA FUNCION PARA RESTAR CAPACIDAD TOTAL CON VIAJES VENDIDOS  -->
			  
			  
			 
			  </div>
             
              <div class="form-group row">
              	<label for="cantPas" class="col-sm-4 col-form-label">Cantidad de pasajeros: </label>
             		<div class="col-sm-8">
			    		<select name="cantPas" id="cantPas" class="form-control">
					     
					     <%
					     for(int i=1; (i<= viajeSeleccionado.getColectivo().getCapacidad()) && (i<=10); i++) // SE DEBERÁ UTILIZAR UNA FUNCION PARA RESTAR CAPACIDAD TOTAL CON VIAJES VENDIDOS
					     {
					     
					     %>
					     <option value="<%= i %>"><%= i %></option> 
					     <% 
					     } 
					     %>
					    </select>
					 </div>
		 	</div>
		 	<div class="row justify-content-center">
			 <button type="submit" class="btn btn-primary">Avanzar</button> </div>
		    <div>
		 

		 </div>
	 </div>
          
</div> 
</div>
</div>
</form>
<jsp:include page="JSPFiles/includefooter.jsp" />  



</body>
</html>