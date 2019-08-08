

<% 

String origenViaje = (String) session.getAttribute("origenViaje");
String destinoViaje = (String) session.getAttribute("destinoViaje");
%>


<div class="login-form-1">
<!-- Default form contact -->

    <p class="h4 mb-4"><i class="fas fa-bus"></i> Buscador de Viajes</p>

   
   
   
   <!-- Fecha -->
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat"%>  
<%@ page import = "java.time.format.DateTimeFormatter" %>
<%@ page import = "java.time.LocalDate" %>


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
          <input type="hidden" name="fechaString"  id="dtp_input2"/> 
	</div>
	</div>	
	</div>

	
	

<!-- Send button -->
<div class="form-group">
<button class="btn btn-info btn-block" type="submit"><i class="fas fa-search"></i> Buscar</button>
</div>



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