<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<jsp:include page="JSPFiles/includeimports.jsp" />

<script type="text/javascript" src="js/validarIgualdadPassword.js"></script>


<title>Editar Usuario</title>



</head>
<body>


<jsp:include page="JSPFiles/includemenu.jsp" />  


<%@ page import = "entities.Usuario" %>
<% Usuario usuarioModificar; %>
 
<%

HttpSession sesion = request.getSession();
usuarioModificar = (Usuario) sesion.getAttribute("UsuarioAModificar");

%>

<!-- INICIO - REDIRECCION A LOGIN -->
<%
Usuario usuarioActual;

String username="s/usuario", estado="s/estado"; 
usuarioActual = (Usuario) session.getAttribute("Usuario");  
if(usuarioActual!=null) 
{
	username = usuarioActual.getUsername(); 
	estado = usuarioActual.getEstado(); 
}

 if(username.equals("s/usuario") || estado.equals("s/estado") || !estado.equals("activo")) 
	{   
 
	String sitioweb = "http://localhost:8080/Terminal-Colectivos/"; 
	response.sendRedirect(sitioweb+"login.jsp"); 
	} 
%>
<!-- FIN - REDIRECCION A LOGIN -->


<div class="container login-container">
<div class="row">
    
    <div class="col-sm">
       <div class="login-form-1 center-block">

       <h3>Editar datos de <%=usuarioModificar.getUsername() %></h3>
			
			
			<!--  -->
			
			<form action="EditarUsuarioServlet" method="post">
			  
			  <input type="hidden" name="username" class="form-control" value=<%= usuarioModificar.getUsername() %> hidden>
			  
			  <div class="form-group">
			    <label>Nombre</label>
			    <input type="text" name="nombre" class="form-control" value=<%= usuarioModificar.getNombre() %>>
			  </div>
			  
			  <div class="form-group">
			    <label>Apellido</label>
			    <input type="text" name="apellido" class="form-control" value=<%= usuarioModificar.getApellido() %>>
			  </div>
			  
			  <div class="form-group">
			    <label>Email</label>
			    <input type="email" name="email" class="form-control" value=<%= usuarioModificar.getEmail() %>>
			  </div>
			  
			  <% if(usuarioModificar.getRol().equals("chofer")) { %>
			  <div class="form-group">
			    <label>CUIL</label>
			    <input type="text" name="cuil" class="form-control" value=<%= usuarioModificar.getCuil() %>>
			  </div>
			  <% } %>
			  
			  <div class="form-group">
			    <label>Rol</label>
			    <select name="rol" class="form-control">
			      <option value="admin"> Administrador</option>
			      <option value="chofer">Chofer</option>
			      <option value="cliente">Cliente</option>
			    </select>
			  </div>
			  
			  <div class="form-group">
			    <label>Estado</label>
			    <select name="estado" class="form-control">
			      <option value="activo"> Activo</option>
			      <option value="eliminado">Eliminado</option>
			    </select>
			  </div>
			  
			  <button type="submit" class="btn btn-primary">Confirmar cambios</button>
			
			  
			</form>
			
			  <% String mensajeEditarUsuario = (String)session.getAttribute("mensajeEditarUsuario");%>
			<% if(session.getAttribute("mensajeEditarUsuario")!=null) { %>
			<br>
			<div class="text-center alert alert-success" role="alert">
			<%= mensajeEditarUsuario %>
			</div> 
			<%}%>
			
        </div>
   
  </div>
    
 
</div>
</div>


<jsp:include page="JSPFiles/includefooter.jsp" />  

</body>
</html>