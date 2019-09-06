<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="JSPFiles/includeimports.jsp" %>
<meta charset="ISO-8859-1">
<title>Registrar nueva cuenta</title>
</head>
<body>
 <%@ page import = "entities.Usuario" %>
 <% Usuario usuario = (Usuario) session.getAttribute("usuarioActual"); 
 String rol  = "cliente";
if(usuario!=null)
	{ 
	rol = usuario.getRol();
	}
 %>


<jsp:include page="JSPFiles/includemenu.jsp" />  

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
                     <input type="email" class="form-control" name="email" placeholder="E-mail" value=""  required/>
                 </div>
               <% if(rol.equals("admin")) { %>
                  <div class="form-group">
                     <input type="text" class="form-control" name="cuil" placeholder="Cuil" value="" />
                 </div>
               
               <div class="form-group">
			 
			    <select name="rol" class="form-control">
			      <option value="cliente"> Rol...</option>
			      <option value="admin"> Administrador</option>
			      <option value="chofer">Chofer</option>
			      <option value="cliente">Cliente</option>
			    </select>
			  </div>
			   <%}
			   %>
                
               <div class="form-group">
                     <input type="submit" class="btnSubmit" value="Registrarse" />
                 </div>
              		<% String mensajeRegistro = (String)session.getAttribute("mensajeRegistro");%>
			<% if(session.getAttribute("mensajeRegistro")!=null) { %>
			<br>
			<div class="alert alert-danger" role="alert">
			Error: <%= mensajeRegistro %>
			</div> 
			<%}%>
			  
             </form>
         </div>
       
    </div>
    
 
</div>
</div>
<jsp:include page="JSPFiles/includefooter.jsp" />  
</body>
</html>