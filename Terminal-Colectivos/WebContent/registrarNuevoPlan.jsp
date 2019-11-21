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

<% 
HttpSession sesion = request.getSession();

String origenViaje = (String) sesion.getAttribute("origenViaje");
String destinoViaje = (String) sesion.getAttribute("destinoViaje");
String patenteColectivoViaje = (String) sesion.getAttribute("patenteColectivoViaje");
String usuarioChoferViaje = (String) sesion.getAttribute("usuarioChoferViaje");
String precioString = (String) sesion.getAttribute("precioString");


if(origenViaje==null){origenViaje="";}
if(destinoViaje==null){destinoViaje="";}
if(patenteColectivoViaje==null){patenteColectivoViaje="";}
if(usuarioChoferViaje==null){usuarioChoferViaje="";}
if(precioString==null){precioString="";}

%>


<%@ page import = "data.DataPlan" %>
<%@ page import = "java.util.*" %>
<%@ page import = "entities.Plan" %>
<%@ page import = "entities.Colectivo" %>



<jsp:include page="JSPFiles/includemenu.jsp" />  

<form action="RegistrarNuevoPlan" method = "post">
<div class="container login-container">
<div class="row">
    
    <div class="col-sm">
       <div class="login-form-1 center-block">
             <h3>Ingrese datos del nuevo plan</h3>
             
               
            <div class="form-group">
                <div class="input-group date form_date " data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy/mm/dd">
                   <div class="input-group-prepend">
				   <div class="input-group-text"><i class="fas fa-calendar"></i></div>
				   </div>
                   <input class="form-control" type="text" value="" readonly>
                   <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span> <!-- No tengo idea para que es el span pero es inevitable  -->
                   <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>              
                <input type="hidden" name= fechaString id="dtp_input2" value="" /> 
                </div>	
            </div>
      
      
			<div class="form-group">
                <div class="input-group date form_time" data-date="" data-date-format="hh:ii" data-link-field="dtp_input3" data-link-format="hh:ii">
                   <div class="input-group-prepend">
				   <div class="input-group-text"><i class="fas fa-clock"></i></div>
				   </div>
                    <input class="form-control" type="text" value="" readonly>
					<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                
                <input type="hidden" name= horaString id="dtp_input3" value="" />
                
                </div>
				
            </div>
               
               <div class="form-group">
      <select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="usuarioChoferViaje">
      <option>Chofer</option>
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
		
	<option <% if(chofer.getUsername().equals(usuarioChoferViaje)) { %> selected <% } %>> <%=chofer.getUsername() %></option>
	<% } %>
      </select>
  	 </div> 
  	 
  	 
  	 
  	  <div class="form-group">
      <select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="patenteColectivoViaje">
      <option>Patente</option>
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
		
	<option <% if(patenteColectivoViaje.equals(colectivo.getPatente())) {%> selected <% } %>> <%=colectivo.getPatente() %></option>
	<% } %>
    </select>
  	 </div> 
  	 
  	 
                 <!-- Origen -->
    <div class="form-group">

      <select class="custom-select" id="origenViaje" name="origenViaje">
      <option>Origen</option>
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
	<option <% if(loc.getNombre().equals(origenViaje)) { %> selected <% } %> > <%=loc.getNombre() %></option>
	<% } %>

      
      </select>
      
   
      </div>
    
    <!-- Destino -->
    <div class="form-group">
      
      <select class="custom-select" id="destinoViaje"  name="destinoViaje">
        <option>Destino</option>
    <%    
    Iterator<Localidad> itr2 = localidades.iterator();
    while(itr2.hasNext()){
 	loc = itr2.next();
	%>
	<option  <% if(loc.getNombre().equals(destinoViaje)) {%> selected <%}%>><%=loc.getNombre() %></option>
	<% } %>
     </select>

     </div>
           		 <div class="form-group input-group">
	           		 <div class="input-group-prepend">
					 <div class="input-group-text"><i class="fas fa-dollar-sign"></i></div>
					 </div>
                     <input  type="text" class="form-control" name="precioString" placeholder="Precio" <%if (!precioString.equals("")) { %>  value= <%= precioString %> <% } %>  required/>
                 </div>
                
               
                
               <div class="form-group">
                     <input type="submit" class="btnSubmit" value="Registrar nuevo plan" />
                 </div>
                 
     
            <% String mensajeRegistro = (String) session.getAttribute("mensajeRegistro");%>
			<% if(mensajeRegistro != null) 
			{
				if(mensajeRegistro.equals("OK"))
				{ %>
				<div class="alert alert-success text-center" role="alert">Felicitaciones: El Plan se ha registrado exitosamente </div>

				<% 
				} 
				else if(mensajeRegistro.equals("Error1")) 
				{ %>
				<div class="alert alert-danger text-center" role="alert">Error: Hay campos vacíos</div>
				<% 
				} 
				else if(mensajeRegistro.equals("Error2")) 
				{ %>
				<div class="alert alert-danger text-center" role="alert">Error: El origen o el destino debe ser Rosario</div>
				<%
				} 
				else if(mensajeRegistro.equals("Error3")) 
				{ %>
				<div class="alert alert-danger text-center" role="alert">Error: El origen y el destino no </div>
				<%
				} 
				else if(mensajeRegistro.equals("Error4")) 
				{ %>
				<div class="alert alert-danger text-center" role="alert">Error: No se encontraron rutas existentes para origen y destino indicados</div>
				<% 
				}
				else if(mensajeRegistro.equals("Error5")) 
				{ %>
				<div class="alert alert-danger text-center" role="alert">Error: El campo <b>"precio"</b> no es un valor numérico</div>
				<% 
				}
			} 	
			%>
			<% 
              session.setAttribute("OK",null);
              session.setAttribute("Error2",null);
              session.setAttribute("Error3",null);
              session.setAttribute("Error4",null);
              session.setAttribute("Error5",null);
              session.setAttribute("Error1",null);%>
			
				<div class="text-center"><a href="buscarviajesadmin.jsp">Volver al buscador de viajes</a></div>
			
           </div>
                		
                 
                 
         </div>
    </div>
    	 
</div>
 
 </form>
<jsp:include page="JSPFiles/includefooter.jsp" />  




</body>
</html>