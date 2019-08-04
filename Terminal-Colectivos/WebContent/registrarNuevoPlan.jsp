<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<%@include file="JSPFiles/includeimports.jsp" %>
<meta charset="ISO-8859-1">
<title>Registrar nuevo plan de viaje</title>



</head>
<body>
<% 
	String patente="";
	String chof= "";
	String origenViaje = (String) session.getAttribute("origenViaje");
	String destinoViaje = (String) session.getAttribute("destinoViaje");
	String fechaViaje = (String) session.getAttribute("fechaViaje");

	if(origenViaje==null){origenViaje="Origen";}
	if(destinoViaje==null){destinoViaje="Destino";}

%>


<%@ page import = "data.DataPlan" %>
    <%@ page import = "java.util.*" %>
    <%@ page import = "entities.Plan" %>
    <%@ page import = "entities.Colectivo" %>
    <% 
    //Inicialización de variables
    DataPlan dplan = new DataPlan();
    ArrayList<Plan> planes = dplan.getViajesDia(origenViaje, destinoViaje, fechaViaje);
    Iterator<Plan> itr = planes.iterator();
    Plan plan = null;
    
    %>


	<jsp:include page="JSPFiles/includemenu.jsp" />  

<div class="container login-container">
<div class="row">
    
    <div class="col-sm">
       <div class="login-form-1 center-block">
             <h3>Ingrese datos del nuevo plan</h3>
             <form action="RegistrarNuevoPlan" method = "post">
               
            <div class="form-group">
                <div class="input-group date form_date " data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                   <div class="input-group-prepend">
				   <div class="input-group-text"><i class="fas fa-calendar"></i></div>
				   </div>
                   <input class="form-control" type="text" value="" readonly>
                   <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span> <!-- No tengo idea para que es el span pero es inevitable  -->
                   <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>              
                <input type="hidden" name= fecha id="dtp_input2" value="" /> 
                </div>	
            </div>
      
      
			<div class="form-group">
                <div class="input-group date form_time" data-date="" data-date-format="hh:ii" data-link-field="dtp_input3" data-link-format="hh:ii">
                   <div class="input-group-prepend">
				   <div class="input-group-text"><i class="fas fa-clock"></i></div>
				   </div>
                    <input class="form-control" type="text" value="" readonly>
					<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                
                <input type="hidden" name= hora id="dtp_input3" value="" />
                
                </div>
				
            </div>
               
               <div class="form-group">
      <select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="choferes">
      <option <% if(chof==null) {%> selected <% } %>>Choferes...</option>
      <%@ page import="data.DataUsuario" %>
      <%@ page import="entities.Usuario" %>
       <%
       	//Inicialización de variables
           DataUsuario dusu = new DataUsuario();
           ArrayList<Usuario> choferes = dusu.getAall();
           Iterator<Usuario> itr3 = choferes.iterator();
           Usuario chofer = null;
       %>
    
    <%    
    while(itr3.hasNext()){
 	chofer= itr3.next();
	%>
		
	<option <% if(chofer.getUsuario().equals(chof)) {%> selected <%}%>> <%=chofer.getUsuario() %></option>
	<% } %>
      </select>
  	 </div> 
  	 
  	 
  	 
  	     <div class="form-group">
      <select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="patente">
      <option <% if(patente==null) {%> selected <% } %>>Patentes...</option>
      <%@ page import="data.DataColectivo" %>
      <%@ page import="entities.Colectivo" %>
       <%
       	//Inicialización de variables
           DataColectivo dcole= new DataColectivo();
           ArrayList<Colectivo> colectivos = dcole.getAll();
           Iterator<Colectivo> itr4 = colectivos.iterator();
           Colectivo colectivo = null;
       %>
    
    <%    
    while(itr4.hasNext()){
 	colectivo= itr4.next();
	%>
		
	<option <% if(colectivo.getPatente().equals(patente)) {%> selected <%}%>> <%=colectivo.getPatente() %></option>
	<% } %>
      </select>
  	 </div> 
  	 
  	 
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
           		 <div class="form-group">
                     <input type="text" class="form-control" name="precio" placeholder="Precio" value=""  required/>
                 </div>
                
               
                
               <div class="form-group">
                     <input type="submit" class="btnSubmit" value="Registrar nuevo plan" />
                 </div>
              		<% String registroExitoso = (String)session.getAttribute("registroExitoso");%>
			<% if(session.getAttribute("registroExitoso")!=null) { %>
			<br>
			<div class="alert alert-success" role="alert">
			Felicitaciones: <%= registroExitoso %>
			</div> 
			<%}%>
			
                 </div>
                 
              		<% String errorOrigenDestino = (String)session.getAttribute("errorOrigenDestino");%>
			<% if(session.getAttribute("errorOrigenDestino")!=null) { %>
			<br>
			<div class="alert alert-danger" role="alert">
			Error: <%= errorOrigenDestino %>
			</div> 
			<%}%>
                 
                 
              		
			
              		
             </form>
         </div>
    </div>
    	 
</div>
 
</div>
<jsp:include page="JSPFiles/includefooter.jsp" />  


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
$('.form_time').datetimepicker({
    language:  'es',
    weekStart: 1,
    todayBtn:  1,
	autoclose: 1,
	todayHighlight: 1,
	startView: 1,
	minView: 0,
	maxView: 1,
	forceParse: 0
});
</script>


</body>
</html>