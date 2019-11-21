<%@page import="controlers.FechaControlers"%>
<%@page import="data.DataReserva"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="JSPFiles/includeimports.jsp" />  
<title>Mis Reservas</title>
</head>
<body>

<%@ page import = "java.text.SimpleDateFormat" %>




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
    
    
    ArrayList<Plan_Reserva> planes_reservas = dres.getReservasxUsuario(usuario);
    Iterator<Plan_Reserva> itr = planes_reservas.iterator();
    Plan_Reserva planReserva = null;
    
    
    FechaControlers fCon = new FechaControlers();
	
 

  
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
			      <th>Origen</th>
			      <th>Destino</th>
			      <th>Tipo de Servicio</th>
			      <th>Cantidad de Pasajeros</th>
			      
			      <th></th>
			    </tr>
			  </thead>
			  <tbody>
			   <tr>
			   <% 
			   
			   while(itr.hasNext()){
				   planReserva = itr.next();
				   // INICIO - RECUPERAR FECHA Y HORA POR SEPARADO
				   
				   String fechaPlanString = fCon.dateToddMMyyyy(planReserva.getPlan().getFechaHora());
				   String horaPlanString = fCon.dateTohhmm(planReserva.getPlan().getFechaHora());
				   String fechaHoraPlanString = fCon.dateToddMMyyyyhhmm(planReserva.getPlan().getFechaHora());		
				   
				   
				   String fechaReservaString = fCon.dateToddMMyyyy(planReserva.getReserva().getFecha_res());
				   String horaReservaString = fCon.dateToHHmmss(planReserva.getReserva().getFecha_res());
				   String fechaHoraReservaString = fCon.dateToddMMyyyyhhmmss(planReserva.getReserva().getFecha_res());		
				   //FIN - RECUPERAR FECHA Y HORA POR SEPARADO
				   
			    %>
			   <td> <%= planReserva.getPlan().getColectivo().getEmpresa().getNombre() %> </td>
			   <td> <%= fechaPlanString %> </td>
			   <td> <%= horaPlanString %> </td>
			   <td> <%= planReserva.getPlan().getOrigen()  %> </td>
			   <td> <%= planReserva.getPlan().getDestino()  %> </td>
			   <td> <%= planReserva.getPlan().getColectivo().getTipo_colectivo() %> </td>
			   <td> <%= planReserva.getReserva().getCant_pas() %> </td>

			   
			   <td> 
			   
			   
				   
				   <form action="RedireccionCancelarReserva" method="post">
						   <input type="hidden" value=<%= fechaReservaString %> name="fechaReserva"/>
						   <input type="hidden" value=<%= horaReservaString %> name="horaReserva"/>
						   
						   <input type="hidden" value=<%= fechaPlanString %> name="fechaViaje"/>
						   <input type="hidden" value=<%= horaPlanString %> name="horaViaje"/>
						   
						   <input type="hidden" value=<%= planReserva.getPlan().getColectivo().getPatente()  %> name="patenteColectivoViaje"/>
						   <input type="hidden" value=<%= planReserva.getPlan().getRuta().getCod_ruta() %> name="codRutaViaje">
						   <input type="hidden" value=<%= planReserva.getReserva().getUsuario().getUsername() %> name="UsernameReserva">
					       
					   <button type="submit" class="btn btn-danger"><i class="fas fa-window-close"></i></button>
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
			
				<% String MensajeCancelarReserva = (String)session.getAttribute("MensajeCancelarReserva");%>
			<% if(session.getAttribute("MensajeCancelarReserva")!=null) { %>
			<br>
			<div class="alert alert-success" role="alert">
			Felicitaciones: <%= MensajeCancelarReserva %>
			</div> 
			<%}%>
           <% session.setAttribute("reservaExitosa",null);
           session.setAttribute("MensajeCancelarReserva",null);%>




<jsp:include page="JSPFiles/includefooter.jsp" />
</body>
</html>