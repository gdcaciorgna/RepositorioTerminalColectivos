<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="imports.jsp" />  

<meta charset="ISO-8859-1">
<title>Baja Satisfactoria</title>
</head>
<body>
<jsp:include page="header.jsp" />  
<br>
<div class="card text-white bg-success mb-3" style="max-width: 18rem;">
  <div class="card-header">�Adi�s!</div>
  <div class="card-body">
    <h5 class="card-title">Baja Satisfactoria</h5>
    <p class="card-text">Se ha logrado dar de baja al usuario <span class="font-weight-bold"><%= session.getAttribute("usuario") %></span> de manera satisfactoria  .</p>
    <hr>
  <p class="mb-0"><a href="index.jsp" class="text-white font-weight-bold"><i class="fas fa-link"></i>  Volver a la p�gina principal</a>.</p>
  </div>
 </div>

</body>
</html>