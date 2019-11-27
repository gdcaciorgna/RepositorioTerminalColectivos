<%@page import="controlers.FechaControlers"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="/JSPFiles/includeimports.jsp" />  
<title>Reservar Viaje</title>
</head>
<body>

 <%@ page import = "java.text.SimpleDateFormat" %>

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

%>


<%@ page import = "data.DataPlan" %>
    <%@ page import = "java.util.*" %>
    <%@ page import = "entities.Plan" %>
    <% 
    //Inicialización de variables
    DataPlan dplan = new DataPlan();
    ArrayList<Plan> planes = dplan.getViajesDia(origenViaje, destinoViaje, fechaViajeDate);
    Iterator<Plan> itr = planes.iterator();
    Plan plan = null;
    
    %>

<jsp:include page="/JSPFiles/includemenu.jsp" />  

<div class="row">

<!--Grid column-->
        <div class="col-sm-7 mb-4">
            <div class="container" style=" margin-top: 2%; margin-bottom: 2%;  ">
			 <div class="container">
			        <div class="table-wrapper">
			                    <span class="float-left"><h4>Listado de <b>Viajes</b></h4></span>
			                    <span class="float-right"><h5> <%=fechaViajeString %> | <%=origenViaje %> <i class="fas fa-chevron-circle-right"></i> <%= destinoViaje %> </h5></span>
			        </div> 
		     </div>
			           
			<table class="table table-striped">
			  <thead class="thead-dark">
			    <tr>
			      <th>Empresa</th>
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
			   
			   while(itr.hasNext()){
				   plan = itr.next();
				   
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
			   <td> <%= plan.getPrecio() %> </td>
			   <td> 
			   
			   <form action="RedireccionReservarViaje" method="post">
					   <input type="hidden" value=<%= fechaString %> name="fechaViajeString"/>
					   <input type="hidden" value=<%= horaString %> name="horaViajeString"/>
					   <input type="hidden" value=<%= plan.getRuta().getCod_ruta() %> name="codRutaViajeString">
					   <input type="hidden" value=<%= plan.getColectivo().getPatente()  %> name="patenteColectivoViajeString"/>
				   
				   <button type="submit" class="btn btn-success"><i class="fas fa-shopping-cart"></i></button>
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




<div class="col-sm-5">

<form action="BuscarViajesCliente" method="post" class="text-center border border-light p-5">
<jsp:include page="/JSPFiles/includebuscadorviajes.jsp" />  
</form>

</div>

</div>


<jsp:include page="/JSPFiles/includefooter.jsp" />
</body>
</html>