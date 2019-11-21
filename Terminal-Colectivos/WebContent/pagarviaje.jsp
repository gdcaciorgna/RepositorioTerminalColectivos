<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="JSPFiles/includeimports.jsp" %>

<meta charset="ISO-8859-1">
<title>Pago de Reserva</title>
</head>
<body>
	
<!-- INICIO - REDIRECCION A LOGIN --> 
<%@ page import = "controlers.FechaControlers" %>
<%@ page import = "entities.*" %>
<%@ page import = "java.text.SimpleDateFormat" %>


<%
Usuario usuario;
FechaControlers fCon = new FechaControlers(); 

%>


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

Plan viajeSeleccionado = (Plan) sesion.getAttribute("ViajeSeleccionado");
int cantidadPasajeros = (int) sesion.getAttribute("cantidadPasajeros");

Date diaActual = new Date();

%>


	<%@ page import = "data.DataCompaniaTarjeta" %>
    <%@ page import = "entities.Compania_Tarjeta" %>
    <%@ page import = "java.util.*" %>
    
    
    <% 
    //Inicialización de variables
    DataCompaniaTarjeta dCompaniaTarjeta = new DataCompaniaTarjeta();
    ArrayList<Compania_Tarjeta> companiasTarjetas = dCompaniaTarjeta.getAll();
    Iterator<Compania_Tarjeta> itr = companiasTarjetas.iterator();
    Compania_Tarjeta companiaTarjeta = null;
    
    
	%>





<jsp:include page="JSPFiles/includemenu.jsp" />  

       <div class="login-form-1 center-block">
             <h1>Datos de la tarjeta:</h1>
             
             <hr>
             
     
      
        <form action="RegistrarNuevaReserva" method = "get">
            <div class="form-group titular">
                <label for="owner">Nombre y Apellido del titular:</label>
                <input type="text" class="form-control" name="owner">
            </div>
             <div class="form-group" id="credit" >
                <label for="cardNumber">Numero de la tarjeta:</label>
                <input type="text" class="form-control"name="nro_tarjeta" >
            </div>
            <div class="form-group CVV">
                <label for="cvv">CVV</label>
                <input type="text" class="form-control" name="cvv">
            </div>
            
            <div class="form-group" >
                <label for="compa">Compania de la tarjeta:</label>
                <select name="codCompania">
                
                    <%    
				    while(itr.hasNext()){
				 	companiaTarjeta = itr.next();
					%>
					<option value="<%=companiaTarjeta.getCod_compania()%>"> <%=companiaTarjeta.getNombre() %></option>
					<% } %>
                                   
                </select>
            
            </div>
           
           
            <div class="form-group" id="expiration-date">
                <label>Fecha de Vencimiento:</label>
                <select>
                    <option value="01">Enero</option>
                    <option value="02">Febrero </option>
                    <option value="03">Marzo</option>
                    <option value="04">Abril</option>
                    <option value="05">Mayo</option>
                    <option value="06">Junio</option>
                    <option value="07">Julio</option>
                    <option value="08">Agosto</option>
                    <option value="09">Septiembre</option>
                    <option value="10">Octubre</option>
                    <option value="11">Noviembre</option>
                    <option value="12">Diciembre</option>
                </select>
                <select>
                <% 
                
                Calendar cal= Calendar.getInstance();
				int anioActual= cal.get(Calendar.YEAR);
				
                for(int i=0; i<10; i++) 
                { %>
                
             
                
                    <option value="<%= anioActual+i  %>"> <%= anioActual+i %> </option>
                                       
                <% } %>
                    
                </select>
                
            </div>
            <div class="form-group" id="credit_cards">
                <img src="Imagenes/Tarjetas.png" id="tarjetas">
                
            </div>
            <div class="form-group">
                     <input type="submit" class="btnSubmit" value="Confirmar pago" />
                 </div>
          	<% String errorTarjeta = (String)session.getAttribute("errorTarjeta");%>
			<% if(session.getAttribute("errorTarjeta")!=null) { %>
			<br>
			<div class="alert alert-danger" role="alert">
			Error: <%= errorTarjeta %>
			</div> 
			<%}%>
          
    <% 
session.setAttribute("errorTarjeta",null);%>
 
		 </form>
			</div>
            
            



<jsp:include page="JSPFiles/includefooter.jsp" />  
</body>
</html>
