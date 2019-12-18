<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<jsp:include page="/JSPFiles/includeimports.jsp" />

<script type="text/javascript" src="js/validarIgualdadPassword.js"></script>


<title>Editar Usuario</title>



</head>
<body>


<jsp:include page="/JSPFiles/includemenu.jsp" />  


<%@ page import = "entities.Usuario" %>
<% Usuario usuarioModificar; %>
 
<%

HttpSession sesion = request.getSession();
usuarioModificar = (Usuario) sesion.getAttribute("UsuarioAModificar");

%>



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
			    <input type="text" name="cuil" class="form-control" value=<% if(usuarioModificar.getCuil() != null)  { %> <%= usuarioModificar.getCuil() %> <% } else { %> <%= "" %> <% } %> >
			  </div>
			  <% } %>
			  
			  <div class="form-group">
			    <label>Rol</label>
			    <select name="rol" class="form-control">
			      <option value="admin" <% if(usuarioModificar.getRol().equals("admin")) { %> selected <% } %>> Administrador</option>
			      <option value="chofer" <% if(usuarioModificar.getRol().equals("chofer")) { %> selected <% } %>>Chofer</option>
			      <option value="cliente" <% if(usuarioModificar.getRol().equals("cliente")) { %> selected <% } %>>Cliente</option>
			    </select>
			  </div>
			  
			  <div class="form-group">
			    <label>Estado</label>
			    <select name="estado" class="form-control">
			      <option value="activo" <% if(usuarioModificar.getEstado().equals("activo")) { %> selected <% } %>> Activo</option>
			      <option value="eliminado" <% if(usuarioModificar.getEstado().equals("eliminado")) { %> selected <% } %>>Eliminado</option>
			    </select>
			  </div>
			  
			  <button type="submit" class="btn btn-primary">Confirmar cambios</button>
			
			  
			
			  
			</form>
			
		<br>	
		
		
		<% 
    		String mensajeError = (String) session.getAttribute("mensajeError");
    		String mensajeExito = (String) session.getAttribute("mensajeExito");
    		
    		if(mensajeExito!=null) { %>
			<br>
			<div class="alert alert-success" role="alert">
			<%= mensajeExito %>
			</div> 
			<%}%>
			
			
			<% if(mensajeError!=null) { %>
			<br>
			<div class="alert alert-danger" role="alert">
			Error: <%= mensajeError %>
			</div> 
			<%}%>
			
		<% 
	        session.setAttribute("mensajeError", null);
	        session.setAttribute("mensajeExito", null);
        %> 	 
			
			 
		<!-- FIN - Mensaje de Error -->
			
        </div>
   
  </div>
   
    
 
</div>
</div>



<jsp:include page="/JSPFiles/includefooter.jsp" />  

</body>
</html>