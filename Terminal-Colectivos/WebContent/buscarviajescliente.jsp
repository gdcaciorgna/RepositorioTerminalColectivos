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
String origenViaje = (String) session.getAttribute("origenViaje");
String destinoViaje = (String) session.getAttribute("destinoViaje");
java.util.Date fechaViajeDate = (Date) session.getAttribute("fechaViaje");
String fechaViajeString = "-";


if(origenViaje==null){origenViaje = "Cualquiera";}
if(destinoViaje==null){destinoViaje = "Cualquiera";}

if(fechaViajeDate !=null)
{
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
	fechaViajeString = sdf1.format(fechaViajeDate);
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

<jsp:include page="JSPFiles/includemenu.jsp" />  

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
			    </tr>
			  </thead>
			  <tbody>
			   <tr>
			   <% 
			   
			   while(itr.hasNext()){
				   plan = itr.next();
				   
				   // INICIO - RECUPERAR FECHA Y HORA POR SEPARADO
					 
				   SimpleDateFormat sdfFechaSeparado = new SimpleDateFormat("dd/MM/yyyy");
				   SimpleDateFormat sdfHoraSeparado = new SimpleDateFormat("HH:mm");

				   String fecha = sdfFechaSeparado.format(plan.getFechaHora());
				   String hora = sdfHoraSeparado.format(plan.getFechaHora());
				   //FIN - RECUPERAR FECHA Y HORA POR SEPARADO
				   
			    %>
			   <td> <%= plan.getColectivo().getEmpresa().getNombre() %> </td>
			   <td> <%= fecha %> </td>
			   <td> <%= hora %> </td>
			   <td> <%= plan.getColectivo().getTipo_colectivo() %> </td>
			   <td> <%= plan.getPrecio() %> </td>
			   	   
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
<jsp:include page="JSPFiles/includebuscadorviajes.jsp" />  
</form>

</div>

</div>


<jsp:include page="JSPFiles/includefooter.jsp" />
</body>
</html>