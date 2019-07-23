<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="JSPFiles/imports.jsp" %>
<meta charset="ISO-8859-1">
<title>Registrar nueva cuenta</title>
</head>
<body>
<div class="container login-container">
<div class="row">
    
    <div class="col-sm">
       <div class="login-form-1 center-block">
             <h3>Ingrese sus datos</h3>
             <form action="RegistrarNuevaCuenta" method = "post">
                 <div class="form-group">
                     <input type="text" class="form-control" name="nombre" placeholder="Nombre" value="" required />
                 </div>
                 <div class="form-group">
                     <input type="text" class="form-control" name="apellido" placeholder="Apellido" value=""  required/>
                 </div>
                 <div class="form-group">
                     <input type="text" class="form-control" name="usuario" placeholder="Usuario" value=""  required/>
                 </div>
                 <div class="form-group">
                     <input type="password" class="form-control" name="password" placeholder="Contraseña" value=""  required/>
                 </div>
           		 <div class="form-group">
                     <input type="password" class="form-control" name="passwordrep" placeholder="Confirmar Contraseña" value=""  required/>
                 </div>
                  <div class="form-group">
                     <input type="text" class="form-control" name="email" placeholder="E-mail" value=""  required/>
                 </div>
                  <div class="form-group">
                     <input type="text" class="form-control" name="cuil" placeholder="Cuil" value=""  required/>
                 </div>
               
               <div class="form-group">
			 
			    <select name="rol" class="form-control">
			      <option value="cliente"> Rol...</option>
			      <option value="admin"> Administrador</option>
			      <option value="chofer">Chofer</option>
			      <option value="cliente">Cliente</option>
			    </select>
			  </div>
                
               <div class="form-group">
                     <input type="submit" class="btnSubmit" value="Registrarse" />
                 </div>
              		<% String error1 = (String)session.getAttribute("error1");%>
			<% if(session.getAttribute("error1")!=null) { %>
			<br>
			<div class="alert alert-danger" role="alert">
			Error: <%= error1 %>
			</div> 
			<%}%>
			  	<% String error2 = (String)session.getAttribute("error2");%>
			<% if(session.getAttribute("error2")!=null) { %>
			<br>
			<div class="alert alert-danger" role="alert">
			Error: <%= error2 %>
			</div> 
			<%}%>
             </form>
         </div>
       
    </div>
    
 
  </div>
      
</div>
</body>
</html>