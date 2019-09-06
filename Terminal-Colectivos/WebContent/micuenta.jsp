
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<jsp:include page="JSPFiles/includeimports.jsp" />

<script type="text/javascript" src="js/validarIgualdadPassword.js"></script>


<title>Mi Cuenta</title>

</head>
<body>
<jsp:include page="JSPFiles/includemenu.jsp" />  


<!-- INICIO - REDIRECCION A LOGIN --> 
<%@ page import = "entities.Usuario" %>
<% Usuario usuarioActual;%>
 
<%
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
		<h3>Editar datos de mi cuenta</h3>

			
			
			<!--  -->
			
			<form action="EditarMiUsuarioServlet" method="post">
			  
			  <input type="hidden" name="username" class="form-control" value=<%= usuarioActual.getUsername() %> hidden>
			  
			  <div class="form-group">
			    <label>Nombre</label>
			    <input type="text" name="nombre" class="form-control" value=<%= usuarioActual.getNombre() %>>
			  </div>
			  
			  <div class="form-group">
			    <label>Apellido</label>
			    <input type="text" name="apellido" class="form-control" value=<%= usuarioActual.getApellido() %>>
			  </div>
			  
			  <div class="form-group">
			    <label>Email</label>
			    <input type="email" name="email" class="form-control" value=<%= usuarioActual.getEmail() %>>
			  </div>
			  
			  <% if(!usuarioActual.getRol().equals("cliente")) {%>
			  <div class="form-group">
			    <label>CUIL</label>
			    <input type="text" name="cuil" class="form-control" value=<%= usuarioActual.getCuil() %>>
			  </div>
			  <%} %>
			  
			  <% if(usuarioActual.getRol().equals("admin")) { %>
			  <div class="form-group">
			    <label>Rol</label>
			    <select name="rol" class="form-control">
			      <option value="admin"> Administrador</option>
			      <option value="chofer">Chofer</option>
			      <option value="cliente">Cliente</option>
			    </select>
			  </div>
			  <% } %>
			  
			<button type="submit" class="btn btn-primary">Confirmar Cambios</button>
			
			</form>
			
			
				<!-- <div class="form-group">
			    <label>Contraseña Actual</label>
			    <input type="password" name="passwordActual" class="form-control">
			 	</div>
			  
			  	<div class="form-group">
			    <label>Nueva Contraseña</label>
			    <input type="password" name="passwordNuevo" class="form-control">
			  	</div>
			  
			    <div class="form-group">
			    <label>Repetir Contraseña</label>
			    <input type="password" name="passwordNuevoRep" class="form-control">
			    </div>  

				<button type="submit" class="btn btn-primary">Confirmar cambio de contraseña</button>  -->
			
			
			<!-- INICIO - Botón - CAMBIAR CONTRASEÑA -->
			<div class="card border-warning mb-3">
			  <div class="card-header">Modificar Contraseña</div>
			  <div class="card-body text-warning">
			    	
			        <button type="button" class="btn pull-right btn-warning btn-lg btn-block" data-toggle="modal" data-target="#exampleModalCenter2">Modificar Contrasaeña</button>
			    		<br>
			    		<% String errorModificarPassword = (String)session.getAttribute("errorModificarPassword");%>
						<% if(errorModificarPassword != null) { %>      
			   			<div class="alert alert-warning" role="alert">
						Error: <%= errorModificarPassword %>
						</div> 
						<% } %>
						
				</div>
			  </div>
			<!-- FIN - Botón - CAMBIAR CONTRASEÑA -->
			
			
			<!-- INICIO - Botón - ELIMINAR CUENTA -->
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
			  <!--FIN - Botón - ELIMINAR CUENTA -->

		</div>
		</div>
</div>
</div>

  <!-- INICIO MODAL - MODIFICAR CONTRASEÑA -->
<div class="modal fade" id="exampleModalCenter2" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenter2Title" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Modificar Contraseña</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        
        
       
        <form name="formBorrarMiCuenta" action="EliminarMiUsuario" method="post">
        <div class="form-group">
	    <label>Contraseña Actual</label>
	    <input type="password" name="passwordActual" class="form-control">
	 	</div>
	  
	  	<div class="form-group">
	    <label>Nueva Contraseña</label>
	    <input type="password" name="password" class="form-control">
	  	</div>
	  
	    <div class="form-group">
	    <label>Repetir Contraseña</label>
	    <input type="password" name="passwordRep" class="form-control">
	    </div> 
	    <input type="hidden" name="username" value=<%= username %> /> 
	    
	     
	    </form>
        
      </div>
      <div class="modal-footer">
        <button type="button" class = "btn btn-secondary" data-dismiss="modal">Volver</button>
            <input type="submit" class="btn pull-right btn-warning" value="Modificar Contraseña" onClick="validarIgualdadPassword()"/>
            
            <div id="mensajeContraseniasNoCoinciden" class="alert alert-warning" role="alert"></div> 
            
            
		</div> 
    

      </div>
    </div>
  </div>
  <!-- FIN MODAL - MODIFICAR CONTRASEÑA -->
  
  <!-- INICIO MODAL - ELIMINAR CUENTA -->
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
        
        
       
        <form name="formBorrarMiCuenta" action="BorrarCuentaServlet" method="post">
        
        <div class="form-group">
               
                 <label class="p-2" for="exampleFormControlInput1">Para que podamos dar de baja tu cuenta, es necesario que escribas tu contraseña:</label>
           
                 <input type="password" class="form-control" name="password" placeholder="Ingresar contraseña..." value="" />
    	</div>
         
        <div class="form-group">
            <input type="password" class="form-control" name="passwordrep" placeholder="Repetir contraseña..." value="" />            
        </div>
            <input type="hidden" name="username" value=<%= username %> /> 
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class = "btn btn-secondary" data-dismiss="modal">Volver</button>
                <input type="submit" class="btn pull-right btn-danger" value="Eliminar mi Cuenta" onClick="validarIgualdadPassword()"/>
            
            <div id="mensajeContraseniasNoCoinciden" class="alert alert-danger" role="alert"></div> 
            
            
		</div> 
    

      </div>
    </div>
  </div>
  <!-- FIN MODAL - ELIMINAR CUENTA -->
 


<jsp:include page="JSPFiles/includefooter.jsp" />  

</body>
</html>