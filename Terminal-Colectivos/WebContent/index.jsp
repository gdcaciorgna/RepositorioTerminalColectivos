<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="JSPFiles/imports.jsp" />  
<meta charset="charset=UTF-8">
<title>Inicio</title>
</head>

<body>
<jsp:include page="JSPFiles/header.jsp" />  
<script type="text/javascript" src="JavascriptFiles/datepickerEs.js"></script>

<div>

 Usuario: <%= session.getAttribute("usuario") %> - Rol: <%= session.getAttribute("rol") %>
 <%--Para recuperar el nombre de la sesión --%>

</div>


<br>


<div class="container">
<div class="col-md-6 login-form-1">
<!-- Default form contact -->
<form class="text-center border border-light p-5">

    <p class="h4 mb-4"><i class="fas fa-bus"></i> Buscador de Viajes</p>

    <!-- Origen -->
    <div class="form-group">
      <select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="origenViaje">
        <option selected>Origen...</option>
        <option value="1">One</option>
        <option value="2">Two</option>
        <option value="3">Three</option>
      </select>
      </div>
    
    <!-- Destino -->
    <div class="form-group">
      <select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="destinoViaje">
        <option selected>Destino...</option>
        <option value="1">One</option>
        <option value="2">Two</option>
        <option value="3">Three</option>
      </select>
  	 </div>
   
   <!-- Fecha -->
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat"%>  
<%
   Date dNow = new Date();
   SimpleDateFormat ft = 
   new SimpleDateFormat ("MM/dd/yyyy");
   String currentDate = ft.format(dNow);
%>
 

   <div class="form-group">
  <div class="input-group mb-2 mr-sm-2">
    <div class="input-group-prepend">
      <div class="input-group-text"><i class="fas fa-calendar"></i></div>
    </div>
    <input class="form-control" id="date" name="fechaViaje" placeholder="MM/DD/YYY" type="text" value=<%=currentDate%> />
  </div>
  </div>
	
	
	

<!-- Send button -->
<div class="form-group">
<button class="btn btn-info btn-block" type="submit"><i class="fas fa-search"></i> Buscar</button>
</div>


</form>
<!-- Default form contact -->
</div></div>




<jsp:include page="JSPFiles/footer.jsp" />  

</body>
</html>