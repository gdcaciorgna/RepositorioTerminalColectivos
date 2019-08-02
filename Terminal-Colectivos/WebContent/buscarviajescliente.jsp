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


<% 
String origenViaje = (String) session.getAttribute("origenViaje");
String destinoViaje = (String) session.getAttribute("destinoViaje");
String fechaViaje = (String) session.getAttribute("fechaViaje");

if(origenViaje==null){origenViaje="Origen";}
if(destinoViaje==null){destinoViaje="Destino";}

%>


<%@ page import = "data.DataPlan" %>
    <%@ page import = "java.util.*" %>
    <%@ page import = "entities.Plan" %>
    <% 
    //Inicialización de variables
    DataPlan dplan = new DataPlan();
    ArrayList<Plan> planes = dplan.getViajesDia(origenViaje, destinoViaje, fechaViaje);
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
			                    <span class="float-left"><h4>Listado de <b>Viajes</b></h2></span>
			                    <span class="float-right"><h4><%=origenViaje %> <i class="fas fa-chevron-circle-right"></i> <%= destinoViaje %> </h5></span>
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
			    %>
			   <td> <%= plan.getColectivo().getEmpresa().getNombre() %> </td>
			   <td> <%= plan.getFecha() %> </td>
			   <td> <%= plan.getHora() %> </td>
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

<div class="login-form-1">
<!-- Default form contact -->
<form action="BuscarViajesCliente" method="post" class="text-center border border-light p-5">
    <p class="h4 mb-4"><i class="fas fa-bus"></i> Buscador de Viajes</p>

 <!-- Origen -->
    <div class="form-group">

      <select class="custom-select" id="origenViaje" name="origenViaje">
      <option <% if(origenViaje==null) {%> selected <% } %>>Origen</option>
      <%@ page import="data.DataLocalidad" %>
      <%@ page import="entities.Localidad" %>
       <%
       	//Inicialización de variables
           DataLocalidad dloc = new DataLocalidad();
           ArrayList<Localidad> localidades = dloc.getAll();
           Iterator<Localidad> itr1 = localidades.iterator();
           Localidad loc = null;
       %>
    
    <%    
    while(itr1.hasNext()){
 	loc = itr1.next();
	%>
	<option <% if(loc.getNombre().equals(destinoViaje)) {%> selected <%}%>> <%=loc.getNombre() %></option>
	<% } %>

      
      </select>
      
   
      </div>
    
    <!-- Destino -->
    <div class="form-group">
      
      <select class="custom-select" id="destinoViaje"  name="destinoViaje">
        <option <% if(origenViaje==null) {%> selected <% } %>>Destino</option>
    <%    
    Iterator<Localidad> itr2 = localidades.iterator();
    while(itr2.hasNext()){
 	loc = itr2.next();
	%>
	<option <% if(loc.getNombre().equals(origenViaje)) {%> selected <%}%>> <%=loc.getNombre() %></option>
	<% } %>
     </select>

     </div>
  	 
    
   
   
   <!-- Fecha -->
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat"%>  
<%@ page import = "java.time.format.DateTimeFormatter" %>
<%@ page import = "java.time.LocalDate" %>
<%
   Date dNow = new Date();
   SimpleDateFormat ft = 
   new SimpleDateFormat ("dd/MM/yyyy");
   String currentDate = ft.format(dNow);
   
   
   if(fechaViaje != null)
   {
	  currentDate = fechaViaje;
	}
%>
 

  <div class="form-group">
  
                <div class="input-group date form_date " data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                   <div class="input-group-prepend">
				   <div class="input-group-text"><i class="fas fa-calendar"></i></div>
				   </div>
                   <input class="form-control" type="text" value="" readonly>
                   <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span> <!-- No tengo idea para que es el span pero es inevitable  -->
                   <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>              
                <input type="hidden" name="fechaViaje"  id="dtp_input2" /> 
                </div>	
  </div>
            
	
	
	

<!-- Send button -->
<div class="form-group">
<button class="btn btn-info btn-block" type="submit"><i class="fas fa-search"></i> Buscar</button>
</div>


</form>
<!-- Default form contact -->
</div>


<!-- Este script se debería realizar en un archivo js diferente para poder reutilizarlo en otras ocasiones -->


<script type="text/javascript">
$('.form_date').datetimepicker({
    language:  'es',
    weekStart: 1,
    todayBtn:  1,
	autoclose: 1,
	todayHighlight: 1,
	startView: 2,
	minView: 2,
	forceParse: 0,
	format: 'dd/mm/yyyy'

});

</script>
</div>

</div>


<jsp:include page="JSPFiles/includefooter.jsp" />
</body>
</html>