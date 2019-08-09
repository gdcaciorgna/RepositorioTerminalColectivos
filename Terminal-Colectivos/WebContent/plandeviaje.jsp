<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="JSPFiles/includeimports.jsp" />
<title>Modificar Plan</title>
</head>
<body>
<% 


String origenPlanViejo = (String) session.getAttribute("origenPlanViejo");
String destinoPlanViejo = (String) session.getAttribute("destinoPlanViejo");
String patenteColectivoPlanViejo = (String) session.getAttribute("patenteColectivoPlanViejo");
String usuarioChoferPlanViejo = (String) session.getAttribute("usuarioChoferPlanViejo");
String precioStringPlanViejo = (String) session.getAttribute("precioStringPlanViejo");
String fechaHoraStringPlanViejo =  (String) session.getAttribute("fechaHoraStringPlanViejo");




if(origenPlanViejo==null){origenPlanViejo="";}
if(destinoPlanViejo==null){destinoPlanViejo="";}
if(patenteColectivoPlanViejo==null){patenteColectivoPlanViejo="";}
if(usuarioChoferPlanViejo==null){usuarioChoferPlanViejo="";}
if(precioStringPlanViejo==null){precioStringPlanViejo="";}
if(fechaHoraStringPlanViejo==null){fechaHoraStringPlanViejo="";}



%>


<%@ page import = "data.DataPlan" %>
<%@ page import = "java.util.*" %>
<%@ page import = "entities.Plan" %>
<%@ page import = "entities.Colectivo" %>


<jsp:include page="JSPFiles/includemenu.jsp" />  

<form action="EditarPlanServlet" method = "get">
<div class="container login-container">
<div class="row">
    
    <div class="col-sm">
       <div class="login-form-1 center-block">
             <h3>Ingrese los nuevos datos del plan a editar</h3>
             <div class="alert alert-light" role="alert">
			  <div class="text-center"><b>---------- Datos Actuales ----------</b></div> <br>
			  <div class="text-justify">
			  Fecha/Hora del plan: <b> <%= fechaHoraStringPlanViejo %> </b> <br>
  			  Origen: <b><%= origenPlanViejo %></b> <br>
  			  Destino: <b><%= destinoPlanViejo %></b> <br>
 			  Colectivo: <b><%= patenteColectivoPlanViejo %></b> <br>
			  Chofer: <b><%= usuarioChoferPlanViejo %></b> <br>
			  Precio: <b><%= precioStringPlanViejo %></b>
			  </div>
			  </div>
             
               
            <div class="form-group">
                <div class="input-group date form_date " data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy/mm/dd">
                   <div class="input-group-prepend">
				   <div class="input-group-text"><i class="fas fa-calendar"></i></div>
				   </div>
                   <input class="form-control" type="text" value="" readonly>
                   <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span> <!-- No tengo idea para que es el span pero es inevitable  -->
                   <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>              
                <input type="hidden" name= fechaStringPlanNuevo id="dtp_input2" value="" /> 
                </div>	
            </div>
      
      
			<div class="form-group">
                <div class="input-group date form_time" data-date="" data-date-format="hh:ii" data-link-field="dtp_input3" data-link-format="hh:ii">
                   <div class="input-group-prepend">
				   <div class="input-group-text"><i class="fas fa-clock"></i></div>
				   </div>
                    <input class="form-control" type="text" value="" readonly>
					<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                
                <input type="hidden" name= horaStringPlanNuevo id="dtp_input3" value="" />
                
                </div>
				
            </div>
               
               <div class="form-group">
      <select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="usuarioChoferPlanNuevo">
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
		
	<option <% if(chofer.getUsuario().equals(usuarioChoferPlanViejo)) { %> selected <% } %>> <%=chofer.getUsuario() %></option>
	<% } %>
      </select>
  	 </div> 
  	 
  	 
  	 
  	  <div class="form-group">
      <select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="patenteColectivoPlanNuevo">
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
		
	<option <% if(patenteColectivoPlanViejo.equals(colectivo.getPatente())) {%> selected <% } %>> <%=colectivo.getPatente() %></option>
	<% } %>
    </select>
  	 </div> 
  	 
  	 
                 <!-- Origen -->
    <div class="form-group">

      <select class="custom-select" id="origenViaje" name="origenPlanNuevo">
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
	<option <% if(loc.getNombre().equals(origenPlanViejo)) { %> selected <% } %> > <%=loc.getNombre() %></option>
	<% } %>

      
      </select>
      
   
      </div>
    
    <!-- Destino -->
    <div class="form-group">
      
      <select class="custom-select" id="destinoViaje"  name="destinoPlanNuevo">
        <option>Destino</option>
    <%    
    Iterator<Localidad> itr2 = localidades.iterator();
    while(itr2.hasNext()){
 	loc = itr2.next();
	%>
	<option  <% if(loc.getNombre().equals(destinoPlanViejo)) {%> selected <%}%>><%=loc.getNombre() %></option>
	<% } %>
     </select>

     </div>
           		 <div class="form-group input-group">
	           		 <div class="input-group-prepend">
					 <div class="input-group-text"><i class="fas fa-dollar-sign"></i></div>
					 </div>
                     <input  type="text" class="form-control" name="precioStringPlanNuevo" placeholder="Precio" <%if (!precioStringPlanViejo.equals("")) { %>  value= <%= precioStringPlanViejo %> <% } %>  required/>
                 </div>
                
               
                
               <div class="form-group">
                     <input type="submit" class="btnSubmit" value="Registrar nuevo plan" />
                 </div>
                 
     
            <% String mensajeRegistro = (String) session.getAttribute("mensajeRegistro");%>
			<% if(mensajeRegistro != null) 
			{
				if(mensajeRegistro.equals("OK"))
				{ %>
				<div class="alert alert-success text-center" role="alert">Felicitaciones: El Plan se ha modificado exitosamente </div>

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
				else if(mensajeRegistro.equals("Error6")) 
				{ %>
				<div class="alert alert-danger text-center" role="alert">Error: Ya existe un plan para la fecha, hora, colectivo y chofer designado</div>
				<% 
				}
			} 	
			%>
			
			
			
           </div>
                 
                 
         </div>
    </div>
    	 
</div>
 
 <!-- INICIO - SEPARAR FECHA Y HORA | NO SE PQ NO ME DEJA PASAR EL STRING DE FECHAHORA COMPLETO --> 
<% 
 String[] separador = fechaHoraStringPlanViejo.split(" ");
 String fechaStringPlanViejo = separador[0];
 String horaStringPlanViejo = separador[1];
 %>
 <!-- FIN - SEPARAR FECHA Y HORA --> 
 
<!--  INICIO - DATOS DEL PLAN A MODIFICAR -->
<input type="hidden" name="fechaStringPlanViejo" value= <%= fechaStringPlanViejo %> >
<input type="hidden" name="horaStringPlanViejo" value= <%= horaStringPlanViejo %> >
<input type="hidden" name="origenPlanViejo" value= <%= origenPlanViejo %> >
<input type="hidden" name="destinoPlanViejo" value= <%= destinoPlanViejo %> >
<input type="hidden" name="patenteColectivoPlanViejo" value= <%= patenteColectivoPlanViejo %> >



<!--  FIN - DATOS DEL PLAN A MODIFICAR -->


 </form>
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
</body>
</html>