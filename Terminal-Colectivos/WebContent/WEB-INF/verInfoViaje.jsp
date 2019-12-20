<%@page import="controlers.PlanReservaControlers"%>
<%@page import="entities.Pasajero_Reserva"%>
<%@page import="data.DataPasajeroReserva"%>
<%@page import="data.DataReservaPlan"%>
<%@page import="entities.Plan_Reserva"%>
<%@page import="logic.PlanLogic"%>
<%@page import="controlers.FechaControlers"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="/JSPFiles/includeimports.jsp" />  

<%@ page import = "entities.Usuario" %>


<% Usuario usuarioActual;%>
 
<% 
HttpSession sesion = request.getSession();

Plan planSeleccionado = (Plan) sesion.getAttribute("planSeleccionado");

usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");  

String nombreApellidoChofer = usuarioActual.getNombre() + " " + usuarioActual.getApellido(); 



%>

<title>Información del viaje seleccionado</title>
</head>
<body>


<%@ page import = "data.DataPlan" %>
    <%@ page import = "java.util.*" %>
    <%@ page import = "entities.Plan" %>
    <%@ page import = "logic.PlanLogic" %>
    
    <% 
    //Inicialización de variables
    DataPasajeroReserva dPasRes = new DataPasajeroReserva();
    ArrayList<Pasajero_Reserva> pasajeros_reservas = dPasRes.getPasajerosxPlan(planSeleccionado);
    Iterator<Pasajero_Reserva> itr = pasajeros_reservas.iterator();
    Pasajero_Reserva pasajero_reserva = null;
    
    
	 // INICIO - RECUPERAR FECHA Y HORA POR SEPARADO
	 FechaControlers fCon = new FechaControlers();

	   String fechaString = fCon.dateToddMMyyyy(planSeleccionado.getFechaHora());
	   String horaString = fCon.dateTohhmm(planSeleccionado.getFechaHora());
	   String fechaHoraString = fCon.dateToddMMyyyyhhmm(planSeleccionado.getFechaHora());
	   				   
	   //FIN - RECUPERAR FECHA Y HORA POR SEPARADO
    
	   
	   
	   PlanLogic pLog = new PlanLogic();
	   PlanReservaControlers planResCon = new PlanReservaControlers();
	   
	   ArrayList<Plan_Reserva> plan_reservas = new ArrayList<Plan_Reserva>();
	   plan_reservas = planResCon.getReservasxPlan(planSeleccionado);
	   
    %>
    

<jsp:include page="/JSPFiles/includemenu.jsp" />  
	<br>
	<br>
	<div class="container">
  	<div class="row">

    <div class="col-sm">
    <div class="alert alert-info" role="alert">
		
	<div class="text-center"><b>---------- Datos del viaje ----------</b></div> 
	
	<div class="text-justify">
	Fecha/Hora del viaje: <b> <%= fechaHoraString %> </b> <br>
	Origen: <b><%= planSeleccionado.getOrigen() %></b> <br>
	Destino: <b><%= planSeleccionado.getDestino() %></b> <br>
	Colectivo: <b><%= planSeleccionado.getColectivo().getPatente() %></b> <br>
	Capacidad: <b><%= planSeleccionado.getColectivo().getCapacidad() %></b> | <b> <%= pLog.calcularAsientosReservados(planSeleccionado, plan_reservas) %> </b> reservados - <b><%= pLog.calcularAsientosDisponibles(planSeleccionado, plan_reservas) %></b> disponibles <br>
	Chofer: <b><%= planSeleccionado.getChofer().getUsername() %></b> <br>
	Precio: <b><%= planSeleccionado.getPrecio() %></b>
	</div>
	
	</div>
    </div>
   
  	</div>
	</div>
	<br>
	<hr>
	

<div class="row">

<!--Grid column-->
            <div class="container" style=" margin-top: 2%; margin-bottom: 2%;  ">
			 <div class="container">
	         <div class="table-wrapper">
	         <span class="float-left"><h4> Informacion de viaje seleccionado</h4></span>
	         </div> 
		     </div>
			<br>
			<hr>
			<br>           
			<table class="table table-striped">
			  <thead class="thead-dark">
			    <tr>
			     
			      <th>Nombre</th>
			      <th>Apellido</th>
			      <th>DNI</th>
			      <th>Asiento</th>
			      <th>Usuario que reservó</th>
			      
			    </tr>
			  </thead>
			  <tbody>
			  <tr>
			   <%  
			   while(itr.hasNext()){
				   pasajero_reserva = itr.next();
							   

			    %>
			   <td> <%= pasajero_reserva.getPasajero().getNombre() %> </td>
			   <td> <%= pasajero_reserva.getPasajero().getApellido() %> </td>
			   <td> <%= pasajero_reserva.getPasajero().getDni() %> </td>
			   <td> <%= pasajero_reserva.getAsiento()  %> </td>
			   <td> <%= pasajero_reserva.getReserva().getUsuario().getUsername() %> </td>
					   	   
			   </tr>
		
			 <% 
			 } %> 
			    
			  </tbody>
			</table>
			 
			</div>
    



</div>


<jsp:include page="/JSPFiles/includefooter.jsp" />
</body>
</html>