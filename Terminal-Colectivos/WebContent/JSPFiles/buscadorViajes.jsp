<div class="login-form-1">
<!-- Default form contact -->
<form action="BuscarViajesOrigenDestinoFecha" method="post" class="text-center border border-light p-5">
    <p class="h4 mb-4"><i class="fas fa-bus"></i> Buscador de Viajes</p>

    <!-- Origen -->
    <div class="form-group">
      <select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="origenViaje">
      <option selected>Origen...</option>
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
	<option><%=loc.getNombre() %></option>
	<% } %>

      
      </select>
      </div>
    
    <!-- Destino -->
    <div class="form-group">
      <select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="destinoViaje">
        <option selected>Destino...</option>
    <%    
    Iterator<Localidad> itr2 = localidades.iterator();
    while(itr2.hasNext()){
 	loc = itr2.next();
	%>
	<option><%=loc.getNombre() %></option>
	<% } %>
      </select>
  	 </div>
   
   
   <!-- Fecha -->
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat"%>  
<%
   Date dNow = new Date();
   SimpleDateFormat ft = 
   new SimpleDateFormat ("dd/MM/yyyy");
   String currentDate = ft.format(dNow);
   
  
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