<%@page import="controlers.ABMPasajero"%>
<%@page import="data.DataReservaPlan"%>
<%@page import="data.DataPlan"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<%@include file="/JSPFiles/includeimports.jsp" %>


<title>Reservar Viaje</title>
</head>
<body>

<!-- INICIO - REDIRECCION A LOGIN --> 
<%@ page import = "controlers.FechaControlers" %>
<%@ page import = "entities.*" %>
<%@ page import = "logic.PlanLogic" %>
<%@ page import = "java.text.SimpleDateFormat" %>


<%
Usuario usuario;
FechaControlers fCon = new FechaControlers(); 
PlanLogic planl = new PlanLogic();
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



ArrayList<Plan_Reserva> planes_reservas = new ArrayList<Plan_Reserva>();

DataReservaPlan dresplan = new DataReservaPlan();

planes_reservas = dresplan.getReservasPlan(viajeSeleccionado);

int cantAsientosDisponibles = planl.calcularAsientosDisponibles(viajeSeleccionado, planes_reservas );


// INICIO - RECUPERAR FECHA Y HORA POR SEPARADO

String fechaString = fCon.dateToddMMyyyy(viajeSeleccionado.getFechaHora());
String horaString = fCon.dateTohhmm(viajeSeleccionado.getFechaHora());
String fechaHoraString = fCon.dateToddMMyyyyhhmm(viajeSeleccionado.getFechaHora());
				   
//FIN - RECUPERAR FECHA Y HORA POR SEPARADO

%>




<jsp:include page="/JSPFiles/includemenu.jsp" />  


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
			  Asientos Disponibles: <b><%= cantAsientosDisponibles  %></b> <br> <!-- SE DEBERÁ UTILIZAR UNA FUNCION PARA RESTAR CAPACIDAD TOTAL CON VIAJES VENDIDOS  -->
			  
			  
			 
			  </div>
             <form action="RedireccionDefinirPasajeros" method = "post">
            
              <div class="form-group row">
              	<label for="cantidadPasajeros" class="col-sm-4 col-form-label">Cantidad de pasajeros: </label>
             		<div class="col-sm-8">
			    		<select name="cantidadPasajeros" id="cantidadPasajeros" class="form-control">
					     
					     <%
					     for(int i=1; (i<= cantAsientosDisponibles) && (i<=10); i++) // SE DEBERÁ UTILIZAR UNA FUNCION PARA RESTAR CAPACIDAD TOTAL CON VIAJES VENDIDOS
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
			
			<button type="submit" class="btn btn-primary">Avanzar</button>
			</div>
					  
		    </form>
		 

		 </div>
	 </div>
          
</div> 
</div>

<jsp:include page="/JSPFiles/includefooter.jsp" />  



</body>
</html>