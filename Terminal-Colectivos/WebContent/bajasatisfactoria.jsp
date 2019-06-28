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
<div class="alert alert-success" role="alert">
  <h4 class="alert-heading">¡Adiós !</h4>
  <p>Se ha logrado dar de baja la cuenta <div class="font-weight-bold"><%= session.getAttribute("usuario") %> de manera satisfactoria</div>  .</p>
  <hr>
  <p class="mb-0"><a href="index.jsp"> Volver a la página principal</a>.</p>
</div>

</body>
</html>