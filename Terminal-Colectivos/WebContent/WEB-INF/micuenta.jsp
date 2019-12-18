
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<jsp:include page="/JSPFiles/includeimports.jsp" />



<title>Mi Cuenta</title>

</head>
<body>
<script type="text/javascript" src="js/validarIgualdadPassword.js"></script>
<jsp:include page="/JSPFiles/includemenu.jsp" />  

<% HttpSession sesion = request.getSession(); %>

<%@ page import = "entities.Usuario" %>
<% Usuario usuarioActual;%>
 
<%
usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");  
%>

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
			    <input type="text" name="cuil" class="form-control" value=<% if(usuarioActual.getCuil() != null)  { %> <%= usuarioActual.getCuil() %> <% } else { %> <%= "" %> <% } %> >
			  </div>
			  <%} %>
			  
			  <% if(usuarioActual.getRol().equals("admin")) { %>
			  <div class="form-group">
			    <label>Rol</label>
			    <select name="rol" class="form-control">
			      <option value="admin" <% if(usuarioActual.getRol().equals("Administrador")) { %> selected <% } %>> Administrador</option>
			      <option value="chofer" <% if(usuarioActual.getRol().equals("Chofer")) { %> selected <% } %>>Chofer</option>
			      <option value="cliente"<% if(usuarioActual.getRol().equals("Cliente")) { %> selected <% } %>>Cliente</option>
			    </select>
			  </div>
			  <% } %>
			  
		<div class="collapse" id="collapseExample">
		<div class="card card-body">
		<div class="form-group">
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
		</div>
		</div>
		
		<p>
	
		<button type="submit" class="btn btn-primary">Confirmar Cambios</button> 
		
	  	<a class="btn btn-secondary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
	  	Modificar Contraseña
	  	</a>
		</p>
		
		
		</form>
		
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
		
		
		<br>
		<br>
		
		
			
			<!-- INICIO - Botón - ELIMINAR CUENTA -->
			 <div class="card border-danger mb-3">
			  <div class="card-header">Eliminar Cuenta</div>
			  <div class="card-body text-danger">
			    <h5 class="card-title">¡Cuidado!</h5>
			    <p class="card-text">Al eliminar tu cuenta, no podrás recuperarla en un futuro.</p>
			    	
			        <button type="button" class="btn pull-right btn-danger btn-lg btn-block" data-toggle="modal" data-target="#exampleModalCenter">Eliminar Cuenta</button>
			    		<br>
			    		<% String error = (String)sesion.getAttribute("errorEliminarUsuario");%>
						<% if(error != null) { %>      
			   			<div class="alert alert-danger" role="alert">
						Error: <%= error %>
						</div> 
						<% } %>
						<% 
               session.setAttribute("errorEliminarUsuario",null);%>
				</div>
			  </div>
			  <!--FIN - Botón - ELIMINAR CUENTA -->

		</div>
		</div>
</div>
</div>


  
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
        
        
       
        <form name="formBorrarMiCuenta" action="EliminarUsuarioServlet" method="post">
        
	 	<input type="hidden" name="username" class="form-control" value=<%= usuarioActual.getUsername() %> hidden>
           <input type="hidden" value="False" name="redirigidocomoadmin"/>
        
        <div class="form-group">
               
                 <label class="p-2" for="exampleFormControlInput1">Para que podamos dar de baja tu cuenta, es necesario que escribas tu contraseña:</label>
           
                 <input type="password" class="form-control" name="password" placeholder="Ingresar contraseña..." />
    	</div>
         
        <div class="form-group">
            <input type="password" class="form-control" name="passwordrep" placeholder="Repetir contraseña..."  />            
        </div>
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
 


<jsp:include page="/JSPFiles/includefooter.jsp" />  

</body>
</html>