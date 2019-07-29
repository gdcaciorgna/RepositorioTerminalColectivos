

<% 

String origenViaje = (String) session.getAttribute("origenViaje");
String destinoViaje = (String) session.getAttribute("destinoViaje");
String fechaViaje = (String) session.getAttribute("fechaViaje");



%>

<div class="login-form-1">
<!-- Default form contact -->
<form action="BuscarViajesOrigenDestinoFecha" method="post" class="text-center border border-light p-5">
    <p class="h4 mb-4"><i class="fas fa-bus"></i> Buscador de Viajes</p>

    <!-- Origen -->
    <div class="form-group">
      <select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="origenViaje">
      <option <% if(origenViaje==null) {%> selected <% } %>>Origen...</option>
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
      <select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="destinoViaje">
        <option <% if(origenViaje==null) {%> selected <% } %>>Destino...</option>
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
	  //INICIO - CAMBIAR EL FORMATO DE DD/MM/YYYY -> YYYYY/MM/DD
	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
      DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      fechaViaje = LocalDate.parse(fechaViaje, formatter).format(formatter2);
      
	  currentDate = fechaViaje;
	}
%>
 

   <div class="form-group">
  <div class="input-group mb-2 mr-sm-2">
    <div class="input-group-prepend">
      <div class="input-group-text"><i class="fas fa-calendar"></i></div>
    </div>
    <input class="form-control" id="date" name="date" placeholder="DD/MM/YYYY" type="text" value=<%= currentDate %>  />
  </div>
  </div>
	
	
	

<!-- Send button -->
<div class="form-group">
<button class="btn btn-info btn-block" type="submit"><i class="fas fa-search"></i> Buscar</button>
</div>


</form>
<!-- Default form contact -->
</div>