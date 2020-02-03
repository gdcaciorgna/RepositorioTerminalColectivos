<%@page import="controlers.BuscarViajes"%>
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


<%
	Usuario usuarioActual;
%>
 
<%
 	HttpSession sesion = request.getSession();

 usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");  

 String mensajeError = (String) session.getAttribute("mensajeError");
 String mensajeExito = (String) session.getAttribute("mensajeExito");


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
 %>

<%
	String nombreApellidoChofer = usuarioActual.getNombre() + " " + usuarioActual.getApellido();
%>

<title>Próximos viajes para el chofer <%=nombreApellidoChofer%></title>
</head>
<body>


<%@ page import = "data.DataPlan" %>
    <%@ page import = "java.util.*" %>
    <%@ page import = "entities.Plan" %>
    <%
    	//Inicialización de variables
        BuscarViajes buscador = new BuscarViajes();  
        ArrayList<Plan> planes = new ArrayList<Plan>();
        
        
        
        
        String manejoDeError = null;

        
        try
        {
        	planes = buscador.getViajesxChofer(usuarioActual);
        
        	
        }
        catch(Exception e)
        {
        	manejoDeError = e.getMessage();
        }
        
        Iterator<Plan> itr = planes.iterator();
        Plan plan = null;
    %>
    
    <% 
    PlanLogic plogic = new PlanLogic();
    
    %>

<jsp:include page="/JSPFiles/includemenu.jsp" />  

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
			      <th> </th>
			
			    </tr>
			  </thead>
			  <tbody>
			  <tr>
			   <%  
			   while(itr.hasNext()){
				   plan = itr.next();
				   // INICIO - RECUPERAR FECHA Y HORA POR SEPARADO
				   
				   String fechaPlanString = fCon.dateToddMMyyyy(plan.getFechaHora());
				   String horaPlanString = fCon.dateTohhmm(plan.getFechaHora());
				   String fechaHoraPlanString = fCon.dateToddMMyyyyhhmm(plan.getFechaHora());		
				   
				   //FIN - RECUPERAR FECHA Y HORA POR SEPARADO
				   

				   
			    %>
			   <td> <%= plan.getColectivo().getEmpresa().getNombre() %> </td>
			   <td> <%= fechaPlanString %> </td>
			   <td> <%= horaPlanString %> </td>
			   <td> <%= plan.getOrigen()  %> </td>
			   <td> <%= plan.getDestino()  %> </td>
			   <td>
			   
					   
				   	   <form action="RedireccionInfoViaje" method="post">
					   
					   <input type="hidden" value=<%= fechaPlanString %> name="fechaViaje"/>
					   <input type="hidden" value=<%= horaPlanString %> name="horaViaje"/>
					   
					   <input type="hidden" value=<%= plan.getColectivo().getPatente()  %> name="patenteColectivoViaje"/>
					   <input type="hidden" value=<%= plan.getRuta().getCod_ruta() %> name="codRutaViaje">
					   <input type="hidden" value=<%= plan.getChofer().getUsername() %> name="choferViaje">
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

<div class="row">
			<% if(mensajeExito!=null) { %>
			<br>
			<div class="alert alert-success" role="alert">
			<%= mensajeExito %>
			</div> 
			<%}%>
			
			
			<% if(mensajeError!=null) { %>
			<br>
			<div class="alert alert-danger" role="alert">
			Error: <%= mensajeError %>
			</div> 
			<%}%>
			
        </div>
        <% 
	        sesion.setAttribute("mensajeError", null);
	        sesion.setAttribute("mensajeExito", null);
        %>

<jsp:include page="/JSPFiles/includefooter.jsp" />
</body>
</html>