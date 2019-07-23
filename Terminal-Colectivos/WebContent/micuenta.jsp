
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<jsp:include page="JSPFiles/imports.jsp" />




<title>Mi Cuenta</title>

<script type="text/javascript" src="JavascriptFiles/validarIgualdadPassword.js"></script>


</head>
<body>
<jsp:include page="JSPFiles/header.jsp" /> 


<!-- INICIO - REDIRECCION A LOGIN --> 
<%@ page import = "entities.Usuario" %>
<% Usuario usuario;%>
 
<%
String username="s/usuario", estado="s/estado"; 
usuario = (Usuario) session.getAttribute("Usuario");  
if(usuario!=null) 
{
	username = usuario.getUsuario(); 
	estado = usuario.getEstado(); 
}

 if(username.equals("s/usuario") || estado.equals("s/estado") || !estado.equals("activo")) 
	{   
 
	String sitioweb = "http://localhost:8080/Terminal-Colectivos/"; 
	response.sendRedirect(sitioweb+"login.jsp"); 
	} 
%>
<!-- FIN - REDIRECCION A LOGIN -->


<div class="container-fluid">
		<h1>Editar datos de usuario</h1>
		<div class="row">
		<div class="col-6">
			
			
			<!--  -->
			
			<form action="EditarUsuarioServlet" method="post">
			  
			  <input type="hidden" name="username" class="form-control" value=<%= usuario.getUsuario() %> hidden>
			  
			  <div class="form-group">
			    <label>Nombre</label>
			    <input type="text" name="nombre" class="form-control" value=<%= usuario.getNombre() %>>
			  </div>
			  
			  <div class="form-group">
			    <label>Apellido</label>
			    <input type="text" name="apellido" class="form-control" value=<%= usuario.getApellido() %>>
			  </div>
			  
			  <div class="form-group">
			    <label>Email</label>
			    <input type="email" name="email" class="form-control" value=<%= usuario.getEmail() %>>
			  </div>
			  
			  <div class="form-group">
			    <label>CUIL</label>
			    <input type="text" name="cuil" class="form-control" value=<%= usuario.getCuil() %>>
			  </div>
			  
			  <div class="form-group">
			    <label>Rol</label>
			    <select name="rol" class="form-control">
			      <option value="admin"> Administrador</option>
			      <option value="chofer">Chofer</option>
			      <option value="cliente">Cliente</option>
			    </select>
			  </div>
			  
			  <button type="submit" class="btn btn-primary">Editar</button>
			</form>
			
			
			<form name="formBorrarMiCuenta" action="BorrarCuentaServlet" method="post">
			<div class="card border-danger mb-3">
			  <div class="card-header">Eliminar Cuenta</div>
			  <div class="card-body text-danger">
			    <h5 class="card-title">¡Cuidado!</h5>
			    <p class="card-text">Al eliminar tu cuenta, no podrás recuperarla en un futuro.</p>
			    	
			        <button type="button" class="btn pull-right btn-danger btn-lg btn-block" data-toggle="modal" data-target="#exampleModalCenter">Eliminar Cuenta</button>
			    		<br>
			    		<% String error = (String)session.getAttribute("errorEliminarUsuario");%>
						<% if(error != null) { %>      
			   			<div class="alert alert-danger" role="alert">
						Error: <%= error %>
						</div> 
						<% } %>
						
				</div>
			  </div>
			</form>
		</div>
		</div>
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Eliminar Cuenta</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        
        
        <div class="form-group">
               
                 <label class="p-2" for="exampleFormControlInput1">Para que podamos dar de baja tu cuenta, es necesario que escribas tu contraseña:</label>
           
                 <input type="password" class="form-control" name="password" placeholder="Ingresar contraseña..." value="" />
    	</div>
        <div class="form-group">
            <input type="password" class="form-control" name="passwordrep" placeholder="Repetir contraseña..." value="" />            
        </div>
            <input type="hidden" name="username" value=<%= username %> /> 
      </div>
      <div class="modal-footer">
        <button type="button" class = "btn btn-secondary" data-dismiss="modal">Volver</button>
                <input type="submit" class="btn pull-right btn-danger" value="Eliminar mi Cuenta" onClick="validarIgualdadPassword()"/>
            
            <div id="mensajeContraseniasNoCoinciden" class="alert alert-danger" role="alert"></div> 
            
            
		</div> 
    

      </div>
    </div>
  </div>

<jsp:include page="JSPFiles/footer.jsp" />  

</body>
</html>