<%@page import="controlers.FechaControlers"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="JSPFiles/includeimports.jsp" />  
<title>Buscar Viaje</title>
</head>
<body>


<!-- INICIO - REDIRECCION A LOGIN --> 
 <%@ page import = "entities.Usuario" %>
<% Usuario usuario;%>
 
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




 <%@ page import = "java.util.*" %>
 
 <%@ page import = "java.text.SimpleDateFormat" %>
 
<% 
HttpSession sesion = request.getSession();
  
sesion.setAttribute("mensajeRegistro", null);
   
String origenViaje = (String) sesion.getAttribute("origenViaje");
String destinoViaje = (String) sesion.getAttribute("destinoViaje");
java.util.Date fechaViajeDate = (Date) sesion.getAttribute("fechaViaje");
String fechaViajeString = "-";
FechaControlers fCon = new FechaControlers();



if(origenViaje==null){origenViaje = "Cualquiera";}
if(destinoViaje==null){destinoViaje = "Cualquiera";}


if(fechaViajeDate != null)
{
	fechaViajeString = fCon.dateToddMMyyyyhhmm(fechaViajeDate);
}
else
{
	fechaViajeDate = new Date(); //En el caso que el viaje sea nulo, se le asigna el día actual
}

%>




	<%@ page import = "data.DataPlan" %>
    <%@ page import = "entities.Plan" %>
    
    <% 
    //Inicialización de variables
    DataPlan dplan = new DataPlan();
    ArrayList<Plan> planes = dplan.getViajesDia(origenViaje, destinoViaje, fechaViajeDate);
    Iterator<Plan> itr = planes.iterator();
    Plan plan = null;
    
    
	%>


  




<jsp:include page="JSPFiles/includemenu.jsp" />  

<div class="row">

<!--Grid column-->
        <div class="col-sm-7 mb-4">
            <div class="container" style=" margin-top: 2%; margin-bottom: 2%;  ">
			 <div class="container">
			        <div class="table-wrapper">
			                    <span class="float-left"><h4>Listado de <b>Viajes</b></h4></span>
 								<span class="float-right">
 								<a href="registrarNuevoPlan.jsp"> <button type="button" class="btn btn-info add-new">  <i class="fa fa-plus"></i> Nuevo Plan de Viaje</button></a>
 								</span>			                    
			        </div> 
			        <br>
			        <br>
		     </div>
			           
			<table class="table table-striped">
			  <thead class="thead-dark">
			    <tr>
			      <th>Empresa</th>
			      <th>Origen</th>
			      <th>Destino</th>
			      <th>Fecha de Salida</th>
			      <th>Hora de Salida</th>
			      <th>Tipo de Servicio</th>
			      <th>Precio</th>
			      <th></th>
			    </tr>
			  </thead>
			  <tbody>
			   <tr>
			   <% 
			   
			   while(itr.hasNext())
			   {
				   plan = itr.next();
				   
				   // INICIO - RECUPERAR FECHA Y HORA POR SEPARADO
				 
				   String fechaString = fCon.dateToddMMyyyy(plan.getFechaHora());
				   String horaString = fCon.dateTohhmm(plan.getFechaHora());
				   String fechaHoraString = fCon.dateToddMMyyyyhhmm(plan.getFechaHora());
				   				   
				   //FIN - RECUPERAR FECHA Y HORA POR SEPARADO
			    %>
			    
			   <td> <%= plan.getColectivo().getEmpresa().getNombre() %> </td>
			   <td><%= plan.getOrigen() %></td>
			   <td> <%= plan.getDestino() %>  </td>
			   <td> <%= fechaString %> </td>
			   <td> <%= horaString %> </td>
			   <td> <%= plan.getColectivo().getTipo_colectivo() %> </td>
			   <td> <%= plan.getPrecio() %> </td>
			   <td>   
			   <div class="row">
			   		<div class="col">
					   <form action="RedireccionEditarPlanServlet" method="get">
					  
					   <input type="hidden" value=<%= fechaString %> name="fechaViajeString"/>
					   <input type="hidden" value=<%= horaString %> name="horaViajeString"/>
					   <input type="hidden" value=<%= plan.getRuta().getCod_ruta() %> name="codRutaViajeString">
					   <input type="hidden" value=<%= plan.getColectivo().getPatente()  %> name="patenteColectivoViajeString"/>

					   <button type="submit" class="btn btn-warning"><i class="fas fa-edit"></i></button>
			  		   </form> 
			   		</div>
			   		<div class="col">
					   <form action="EliminarPlanServlet" method="get">
					   
					   <input type="hidden" value=<%= fechaString %> name="fechaViajeString"/>
					   <input type="hidden" value=<%= horaString %> name="horaViajeString"/>
					   <input type="hidden" value=<%= plan.getRuta().getCod_ruta() %> name="codRutaViajeString">
					   <input type="hidden" value=<%= plan.getColectivo().getPatente()  %> name="patenteColectivoViajeString"/>
					  			    
		   			   
					   <button type="submit" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
					   </form>
			   		</div>
			   </div>
			   </td>   	   
			   </tr>
		
			 <%  } %> 
			    
			  </tbody>
			</table>
			 
			</div>
			<% Integer planesEditados = 0;%> 
			<% planesEditados = (Integer) session.getAttribute("planesEditados");%>
			<% if(session.getAttribute("planesEditados")!=null) { %>
			<br>
			<div class="alert alert-warning" role="alert">
			Viajes editados: <%= planesEditados %>
			</div> 
			<%}%>
			
			
			
			<% Integer planesEliminados = 0;%> 
			<% planesEliminados = (Integer) session.getAttribute("planesEliminados");%>
			<% if(session.getAttribute("planesEliminados")!=null) { %>
			<br>
			<div class="alert alert-danger" role="alert">
			Viajes eliminados: <%= planesEliminados %>
			</div> 
			<%}%>
        </div>
        <!--Grid column-->




<div class="col-sm-5">
<form action="BuscarViajesAdmin" method="post" class="text-center border border-light p-5">
<jsp:include page="JSPFiles/includebuscadorviajes.jsp" />  
</form>








</div>

</div>


<jsp:include page="JSPFiles/includefooter.jsp" />
</body>
</html>