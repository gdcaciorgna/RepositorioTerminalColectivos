<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<jsp:include page="imports.jsp" /> 

<title>Iniciar Sesión</title>

</head>
<body>

<jsp:include page="header.jsp" />  

<div class="container login-container">
       <div class="col-md-6 login-form-1">
             <h3>Iniciar Sesión</h3>
             <form action="LoginServlet" method = "post">
                 <div class="form-group">
                     <input type="text" class="form-control" name="txtusu" placeholder="Ingresar usuario..." value="" />
                 </div>
                 <div class="form-group">
                     <input type="password" class="form-control" name="txtpass" placeholder="Ingresar contraseña..." value="" />
                 </div>
           
                 <div class="form-group">
                     <input type="submit" class="btnSubmit" value="Ingresar" />
                 </div>
                  <div align="center"> <a href="#">Crear una nueva cuenta</a> </div>
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