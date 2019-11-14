<%@page import="controlers.FechaControlers"%>
<%@page import="data.DataReserva"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="JSPFiles/includeimports.jsp" />  
<title>Reservar Viaje</title>
</head>
<body>

 <%@ page import = "java.text.SimpleDateFormat" %>

<% 

String fechaViajeString = "-";
FechaControlers fCon = new FechaControlers();




%>
 <%@ page import = "entities.Usuario" %>
<% Usuario usuario= new Usuario();%>
 
<%

usuario = (Usuario) session.getAttribute("usuarioActual");  

%>

<%@ page import = "data.DataReservaPlan" %>
    <%@ page import = "java.util.*" %>
    <%@ page import = "entities.Plan_Reserva" %>
     <%@ page import = "entities.Plan" %>
      <%@ page import = "entities.Reserva" %>
    <% 
    //Inicialización de variables
    
    DataReservaPlan dres = new DataReservaPlan();
    
    
    ArrayList<Plan_Reserva> reservas = dres.getReservasxUsuario(usuario);
    Iterator<Plan_Reserva> itr = reservas.iterator();
    Plan_Reserva reserva = null;
  
    %>

<jsp:include page="JSPFiles/includemenu.jsp" />  



<!--Grid column-->
        <div class="col-sm-7 mb-4">
            <div class="container" style=" margin-top: 2%; margin-bottom: 2%;  ">
			 <div class="container">
			        <div class="table-wrapper">
			                    <span class="float-left"><h4> Mis <b>Reservas</b></h4></span>
			                  
			        </div> 
		     </div>
			           
			<table class="table table-striped">
			  <thead class="thead-dark">
			    <tr>
			      <th>Empresa</th>
			      <th>Fecha de Salida</th>
			      <th>Hora de Salida</th>
			      <th>Tipo de Servicio</th>
			      <th>Cantidad de Pasajeros</th>
			      
			      <th></th>
			    </tr>
			  </thead>
			  <tbody>
			   <tr>
			   <% 
			   
			   while(itr.hasNext()){
				   reserva = itr.next();
				   Plan plan=new Plan();
				    plan= reserva.getPlan();
				    Reserva res= new Reserva();
				    res= reserva.getReserva();
				   
				// INICIO - RECUPERAR FECHA Y HORA POR SEPARADO
					  String fechaString = fCon.dateToddMMyyyy(plan.getFechaHora());
				   String horaString = fCon.dateTohhmm(plan.getFechaHora());
				   String fechaHoraString = fCon.dateToddMMyyyyhhmm(plan.getFechaHora());		
				   
				   //FIN - RECUPERAR FECHA Y HORA POR SEPARADO
				   
			    %>
			   <td> <%= plan.getColectivo().getEmpresa().getNombre() %> </td>
			   <td> <%= fechaString %> </td>
			   <td> <%= horaString %> </td>
			   <td> <%= plan.getColectivo().getTipo_colectivo() %> </td>
			   <td> <%= res.getCant_pas() %> </td>
			   <td> 
			   
			   <form action="RedireccionReservarViaje" method="post">
					   <input type="hidden" value=<%= res.getFecha_res() %> name="fechaHoraReserva"/>
					   <input type="hidden" value=<%= plan.getFechaHora() %> name="fechaHoraViaje"/>
					   <input type="hidden" value=<%= plan.getRuta().getCod_ruta() %> name="codRutaViaje">
					   <input type="hidden" value=<%= plan.getColectivo().getPatente()  %> name="patenteColectivoViajeString"/>
				       
				   <button type="submit" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
			   </form> 
				
			    </td>
			   	   
			   </tr>
		
			 <% 
			 } %> 
			    
			  </tbody>
			</table>
			 
			</div>
        </div>
        <!--Grid column-->
			<% String reservaExitosa = (String)session.getAttribute("reservaExitosa");%>
			<% if(session.getAttribute("reservaExitosa")!=null) { %>
			<br>
			<div class="alert alert-success" role="alert">
			Felicitaciones: <%= reservaExitosa %>
			</div> 
			<%}%>
			
		






<jsp:include page="JSPFiles/includefooter.jsp" />
</body>
</html>