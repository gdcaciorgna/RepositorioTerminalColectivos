<%@page import="controlers.FechaControlers"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="/JSPFiles/includeimports.jsp" />  

<title>Cancelar Reserva</title>
</head>
<body>
 
 
	<%@ page import = "java.text.SimpleDateFormat" %>
	<%@ page import = "java.util.*" %>

	<%@ page import = "entities.*" %>
	<%@ page import = "data.DataPasajeroReserva" %>
	
	<% Usuario usuario= new Usuario();%>
	<% Reserva reservaACancelar= new Reserva();%>
	<% DataPasajeroReserva dPasRes = new DataPasajeroReserva(); %>
	
 
	<%
	
	usuario = (Usuario) session.getAttribute("usuarioActual");
	reservaACancelar = (Reserva) session.getAttribute("reservaACancelar"); 
	double importeADevolver = (double) session.getAttribute("importeADevolver");
	
	ArrayList<Pasajero> pasajeros = dPasRes.getPasajerosxReserva(reservaACancelar);
	
	
	
	// INICIO - RECUPERAR FECHA Y HORA POR SEPARADO
	
	FechaControlers fCon = new FechaControlers();
	
	String fechaReservaString = fCon.dateToddMMyyyy(reservaACancelar.getFecha_res());
	String horaReservaString = fCon.dateTohhmm(reservaACancelar.getFecha_res());
	String fechaHoraReservaString = fCon.dateToddMMyyyyhhmm(reservaACancelar.getFecha_res());
   				   
	//FIN - RECUPERAR FECHA Y HORA POR SEPARADO
	
	%>

<jsp:include page="/JSPFiles/includemenu.jsp" />  


<form action="CancelarReserva" method = "post">
<div class="container login-container">
<div class="row">
    
    <div class="col-sm">
       <div class="login-form-1 center-block">
             
             <h3>�Desea confirmar la cancelaci�n del viaje?</h3>
             
             <hr>
             
             Usuario: <%= reservaACancelar.getUsuario().getUsername() %> <br>
             Fecha de reserva: <%= fechaHoraReservaString %> <br>
             Banco a devolver el dinero: <%= reservaACancelar.getCompania_tarjeta().getNombre() %> <br>
             Cantidad de Pasajeros: <%= reservaACancelar.getCant_pas() %><br>
             
             <hr>
             
             <% for(Pasajero pasajero : pasajeros ) { %>
             <br>
             Dni: <%= pasajero.getDni() %> <br>
             Nombre: <%= pasajero.getNombre()%> <br>
             Apellido: <%= pasajero.getApellido() %> <br>
             <br>
             
             <hr>
             
             <% } %>
             
             
             
             
             Importe a Devolver: <%= importeADevolver %>
             
              
             <hr>
             <br>  
            
			<div class="row">
				<div class="col">
				<div class="text-center"><a href="MisReservas">Volver a mis reservas</a></div>
				</div>
				
				<div class="col">
				<button type="submit" class="btn btn-danger">Cancelar Reserva</button>
				</div>
			
			</div>
			
			
			
           </div>
                		
                 
                 
         </div>
    </div>
    	 
</div>
 
 </form>


</body>
</html>