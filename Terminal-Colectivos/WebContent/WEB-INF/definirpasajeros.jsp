<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/JSPFiles/includeimports.jsp" %>

<meta charset="ISO-8859-1">
<title>Definir pasajeros para el viaje</title>
</head>
<body>
	

<%@ page import = "controlers.FechaControlers" %>


<%
FechaControlers fCon = new FechaControlers(); 

%>



<% 
HttpSession sesion = request.getSession();

Plan viajeSeleccionado = (Plan) sesion.getAttribute("ViajeSeleccionado");
int cantidadPasajeros = (int) sesion.getAttribute("cantidadPasajeros");

// INICIO - RECUPERAR FECHA Y HORA POR SEPARADO

String fechaString = fCon.dateToddMMyyyy(viajeSeleccionado.getFechaHora());
String horaString = fCon.dateTohhmm(viajeSeleccionado.getFechaHora());
String fechaHoraString = fCon.dateToddMMyyyyhhmm(viajeSeleccionado.getFechaHora());
				   
//FIN - RECUPERAR FECHA Y HORA POR SEPARADO

%>



<%@ page import = "entities.Plan" %>


<jsp:include page="/JSPFiles/includemenu.jsp" />  
<div class="container login-container">
<div class="row">
    
    <div class="col-sm">
       <div class="login-form-1 center-block">
             <h1>Definir pasajeros</h1>
             
             <hr>
             
            <div class="alert alert-light" role="alert">
			<div class="text-center text-justify"><b>---------- Datos del viaje ----------</b></div> <br>
			 
			 
			 
			  Fecha y hora del viaje: <b> <%= fechaHoraString %> </b> <br>
  			  Origen: <b><%= viajeSeleccionado.getOrigen() %></b> <br>
  			  Destino: <b><%= viajeSeleccionado.getDestino() %></b> <br>
 			  Empresa: <b><%= viajeSeleccionado.getColectivo().getEmpresa().getNombre() %></b> 
 			  <hr>
			  Precio por Boleto: <b>$ <%= viajeSeleccionado.getPrecio() %></b> <br>
			  Cantidad de pasajeros: <b><%= cantidadPasajeros %></b> <br> <!-- SE DEBERÁ UTILIZAR UNA FUNCION PARA RESTAR CAPACIDAD TOTAL CON VIAJES VENDIDOS  -->
			  Total: <b>$ <%= cantidadPasajeros*viajeSeleccionado.getPrecio() %></b> 
			  <hr>
			  
			</div>
            <form action="RedireccionPagoViajes" method = "post">
            
            <% for(int i=1; i<=cantidadPasajeros; i++) { %>
            
            <div class="card bg-light" style="max-width: 24rem;">
  			<div class="card-header">Pasajero n° <%=i %> </div>
  			<div class="card-body">
  			
  			<div class="form-group row">
              	<label for="cantidadPasajeros" class="col-sm-4 col-form-label">DNI </label>
             		<div class="col-sm-8">
			    		<input type="number" class="form-control" name="dni<%=i %>" id="dni<%=i %>" >
					 </div>
		 	</div>
		 	
  			<div class="form-group row">
              	<label for="cantidadPasajeros" class="col-sm-4 col-form-label">Nombre </label>
             		<div class="col-sm-8">
			    		<input type="text" class="form-control"  name="nombre<%=i %>" id="nombre<%=i %>" >
					 </div>
		 	</div>
		 	
		 	<div class="form-group row">
              	<label for="cantidadPasajeros" class="col-sm-4 col-form-label">Apellido </label>
             		<div class="col-sm-8">
			    		<input type="text" class="form-control" name="apellido<%=i %>" id="apellido<%=i %>" >
					</div>
		 	</div>
		 	


  			</div>
			</div>
			
			<br>
            
            <% } %>
		    
		    <div class="row justify-content-center">
			
			<button type="submit" class="btn btn-primary">Avanzar</button>
			</div>
			<div>
			
			<% 
    		String mensajeError = (String) session.getAttribute("mensajeError");
    		String mensajeExito = (String) session.getAttribute("mensajeExito");
    		
    		if(mensajeExito!=null) { %>
			<br>
			<div class="alert alert-success" role="alert">
			<%= mensajeExito %>
			</div> 
			<%}%>
			
			
			<% if(mensajeError!=null) { %>
			<br>
			<div class="alert alert-danger" role="alert">
			Error: <%= mensajeError %>
			</div> 
			<%}%>
			
		<% 
	        session.setAttribute("mensajeError", null);
	        session.setAttribute("mensajeExito", null);
        %> 	 


			</div>
		    
		    </form>
		 

		 </div>
	 </div>
          
</div> 
</div>

<jsp:include page="/JSPFiles/includefooter.jsp" />  
</body>
</html>