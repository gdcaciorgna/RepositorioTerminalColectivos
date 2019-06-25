<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Iniciar Sesión</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
 <link rel="stylesheet" href="estilos.css"> 


</head>
<body>



<div class="container login-container">
       <div class="col-md-6 login-form-1">
             <h3>Iniciar Sesión</h3>
             <form action="ServletLogin" method = "post">
                 <div class="form-group">
                     <input type="text" class="form-control" name="txtusu" placeholder="Ingresar usuario..." value="" />
                 </div>
                 <div class="form-group">
                     <input type="password" class="form-control" name="txtpass" placeholder="Ingresar contraseña..." value="" />
                 </div>
           
                 <div class="form-group">
                     <input type="submit" class="btnSubmit" value="Ingresar" />
                 </div>
                  <div align="center"> <a href="url">Crear una nueva cuenta</a> </div>
              	<% String error = (String)session.getAttribute("error");%>
			<% if(session.getAttribute("error")!=null) { %>
			<br>
			<div class="alert alert-danger" role="alert">
			Error: <%= error %>
			</div> 
			<%}%>
             </form>
         </div>
</div>

</body>
</html>