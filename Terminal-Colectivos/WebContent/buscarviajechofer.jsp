<%@page import="data.DataReservaPlan"%>
<%@page import="entities.Plan_Reserva"%>
<%@page import="logic.PlanLogic"%>
<%@page import="controlers.FechaControlers"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="JSPFiles/includeimports.jsp" />  

<!-- INICIO - REDIRECCION A LOGIN --> 
<%@ page import = "entities.Usuario" %>
<%@ page import = "java.text.SimpleDateFormat" %>


<% Usuario usuarioActual;%>
 
<% 
HttpSession sesion = request.getSession();

String origenViaje = (String) sesion.getAttribute("origenViaje");
String destinoViaje = (String) sesion.getAttribute("destinoViaje");
java.util.Date fechaViajeDate = (Date) sesion.getAttribute("fechaViaje");
String fechaViajeString = "-";
FechaControlers fCon = new FechaControlers();


if(origenViaje==null){origenViaje = "Cualquiera";}
if(destinoViaje==null){destinoViaje = "Cualquiera";}

if(fechaViajeDate !=null)
{
	fechaViajeString = fCon.dateToddMMyyyy(fechaViajeDate);
}
else {fechaViajeDate = new Date();}



String username="s/usuario", estado="s/estado"; 
usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");  
if(usuarioActual!=null) 
{
	username = usuarioActual.getUsername(); 
	estado = usuarioActual.getEstado(); 
}

 if(username.equals("s/usuario") || estado.equals("s/estado") || !estado.equals("activo")) 
	{   
 
	String sitioweb = "http://localhost:8080/Terminal-Colectivos/"; 
	response.sendRedirect(sitioweb+"login.jsp"); 
	} 
%>
<!-- FIN - REDIRECCION A LOGIN -->

<% String nombreApellidoChofer = usuarioActual.getNombre() + " " + usuarioActual.getApellido(); %>

<title>Próximos viajes para el chofer <%= nombreApellidoChofer %></title>
</head>
<body>


<%@ page import = "data.DataPlan" %>
    <%@ page import = "java.util.*" %>
    <%@ page import = "entities.Plan" %>
    <% 
    //Inicialización de variables
    DataReservaPlan dplan = new DataReservaPlan();
    ArrayList<Plan_Reserva> planes_reservas = dplan.getViajesxChofer(usuarioActual);
    Iterator<Plan_Reserva> itr = planes_reservas.iterator();
    Plan_Reserva plan_reserva = null;
    
    %>
    
    <% 
    PlanLogic plogic = new PlanLogic();
    
    %>

<jsp:include page="JSPFiles/includemenu.jsp" />  

<div class="row">


<!--Grid column-->
            <div class="container" style=" margin-top: 2%; margin-bottom: 2%;  ">
			 <div class="container">
			        <div class="table-wrapper">
			        <span class="float-left"><h4> Viajes para el chofer <b><%= nombreApellidoChofer %></b></h4></span>
			        </div> 
		     </div>
			           
			<table class="table table-striped">
			  <thead class="thead-dark">
			    <tr>
			      <th>Empresa</th>
			      <th>Fecha de Salida</th>
			      <th>Hora de Salida</th>
			      <th>Origen</th>
			      <th>Destino</th>
			      <th>Asientos reservados</th>
			      <th>Capacidad colectivo</th>
			      <th></th>
			    </tr>
			  </thead>
			  <tbody>
			  <tr>
			   <%  
			   while(itr.hasNext()){
				   plan_reserva = itr.next();
				   // INICIO - RECUPERAR FECHA Y HORA POR SEPARADO
				   
				   String fechaPlanString = fCon.dateToddMMyyyy(plan_reserva.getPlan().getFechaHora());
				   String horaPlanString = fCon.dateTohhmm(plan_reserva.getPlan().getFechaHora());
				   String fechaHoraPlanString = fCon.dateToddMMyyyyhhmm(plan_reserva.getPlan().getFechaHora());		
				   
				   //FIN - RECUPERAR FECHA Y HORA POR SEPARADO
				   

				   
			    %>
			   <td> <%= plan_reserva.getPlan().getColectivo().getEmpresa().getNombre() %> </td>
			   <td> <%= fechaPlanString %> </td>
			   <td> <%= horaPlanString %> </td>
			   <td> <%= plan_reserva.getPlan().getOrigen()  %> </td>
			   <td> <%= plan_reserva.getPlan().getDestino()  %> </td>
			   <td> <%= plogic.calcularAsientosReservados(plan_reserva.getPlan(), planes_reservas) %> </td>
			   <td> <%= plan_reserva.getPlan().getColectivo().getCapacidad()%> </td>

			   
			   <td> 
			   
			   
				   
				   <form action="VerInformacionViaje" method="post">
						   
						   <input type="hidden" value=<%= fechaPlanString %> name="fechaViaje"/>
						   <input type="hidden" value=<%= horaPlanString %> name="horaViaje"/>
						   
						   <input type="hidden" value=<%= plan_reserva.getPlan().getColectivo().getPatente()  %> name="patenteColectivoViaje"/>
						   <input type="hidden" value=<%= plan_reserva.getPlan().getRuta().getCod_ruta() %> name="codRutaViaje">
						   <input type="hidden" value=<%= plan_reserva.getPlan().getChofer().getUsername() %> name="choferViaje">
					       
					   <button type="submit" class="btn btn-info"><i class="fa fa-bars"></i></button>
				   </form> 
				   
				   
				   
				  
				
			    </td>
			   	   
			   </tr>
		
			 <% 
			 } %> 
			    
			  </tbody>
			</table>
			 
			</div>
    



</div>


<jsp:include page="JSPFiles/includefooter.jsp" />
</body>
</html>