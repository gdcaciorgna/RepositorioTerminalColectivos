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

if(origenViaje==null){origenViaje="Cualquiera";}
if(destinoViaje==null){destinoViaje="Cualquiera";}
if(fechaViaje==null){fechaViaje="-";}


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


<!-- INICIO - CAMBIO DE FORMATO DE FECHA -->
<% 
if(!fechaViaje.equals("-"))
{
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
fechaViaje = sdf2.format(sdf.parse(fechaViaje)); 
}
%>
  
<!-- FIN - CAMBIO DE FORMATO DE FECHA -->




<jsp:include page="JSPFiles/includemenu.jsp" />  

<div class="row">

<!--Grid column-->
        <div class="col-sm-7 mb-4">
            <div class="container" style=" margin-top: 2%; margin-bottom: 2%;  ">
			 <div class="container">
			        <div class="table-wrapper">
			                    <span class="float-left"><h4>Listado de <b>Viajes</b></h2></span>
			                    <span class="float-right"><h4><i class="fa fa-calendar" aria-hidden="true"></i> Día: <%= fechaViaje %>  </h5></span>
			                    
			        </div> 
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
			    </tr>
			  </thead>
			  <tbody>
			   <tr>
			   <% 
			   
			   while(itr.hasNext()){
				   plan = itr.next();
			    %>
			   <td> <%= plan.getColectivo().getEmpresa().getNombre() %> </td>
			   <td><%= plan.getOrigen() %></td>
			   <td> <%= plan.getDestino() %>  </td>
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
<form action="BuscarViajesAdmin" method="post" class="text-center border border-light p-5">
    <p class="h4 mb-4"><i class="fas fa-bus"></i> Buscador de Viajes</p>

   
   
   
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
 

 <!-- Origen -->
    <div class="form-group row">

	  <label for="origenViaje" class="col-sm-2 col-form-label">Origen</label>
	  <div class="col-sm-10">
      <select class="custom-select" id="origenViaje" name="origenViaje">
      <option <% if(origenViaje==null) {%> selected <% } %>>Cualquiera</option>
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
      </div>
    
    <!-- Destino -->
    <div class="form-group row">
    <label for="destinoViaje" class="col-sm-2 col-form-label">Destino</label>
    <div class="col-sm-10">
    <select class="custom-select" id="destinoViaje"  name="destinoViaje">
        <option <% if(origenViaje==null) {%> selected <% } %>>Cualquiera</option>
    <%    
    Iterator<Localidad> itr2 = localidades.iterator();
    while(itr2.hasNext()){
 	loc = itr2.next();
	%>
	<option <% if(loc.getNombre().equals(origenViaje)) {%> selected <%}%>> <%=loc.getNombre() %></option>
	<% } %>
     </select>
     
     </div>
     </div>
  	 
    
   
       <!-- Fecha -->
	<div class="form-group row">
	<label for="dtp_input2" class="col-sm-2 col-form-label">Fecha</label>
	<div class="col-sm-10">
  
  
          <div class="input-group date form_date " data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
          <div class="input-group-prepend">
 		  <div class="input-group-text"><i class="fas fa-calendar"></i></div>
          </div>
          <input class="form-control" type="text" readonly>
          <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span> <!-- No tengo idea para que es el span pero es inevitable  -->
          <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>              
          <input type="hidden" name="fechaViaje"  id="dtp_input2"/> 
	</div>
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