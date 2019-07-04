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
<jsp:include page="JSPFiles/redireccionlogin.jsp" /> 

<div>
<br> 
Informaci�n de Mi Perfil, con posibilidad de realizar Cambios
<br> 
</div>
<%@ page import = "entities.Usuario" %>
<% Usuario usuario = (Usuario) session.getAttribute("ClaseUsuario");  //Recupera el atributo "usuario" de la sesi�n actual para pasarlo como parametro al BorrarCuentaServlet
%> 

<form name="formBorrarMiCuenta" action="BorrarCuentaServlet" method="get">
<div class="card border-danger mb-3" style="max-width: 15rem;">
  <div class="card-header">Eliminar Cuenta</div>
  <div class="card-body text-danger">
    <h5 class="card-title">�Cuidado!</h5>
    <p class="card-text">Al eliminar tu cuenta, no podr�s recuperarla en un futuro.</p>
    	
        <button type="button" class="btn pull-right btn-danger btn-lg btn-block" data-toggle="modal" data-target="#exampleModalCenter">Eliminar Cuenta</button>
    		<br>
    		<% String error = (String)session.getAttribute("errorEliminarUsuario");%>
			<% if(error != null) { %>      
   			<div class="alert alert-danger" role="alert">
			Error: <%= error %>
			</div> 
			<% } %>
			
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
               
                 <label class="p-2" for="exampleFormControlInput1">Para que podamos dar de baja tu cuenta, es necesario que escribas tu contrase�a:</label>
           
                 <input type="password" class="form-control" name="txtpass1" placeholder="Ingresar contrase�a..." value="" />
    	</div>
        <div class="form-group">
            <input type="password" class="form-control" name="txtpass2" placeholder="Repetir contrase�a..." value="" />
            
        </div>
        <input type="hidden" name="txtusu" value=<%= usuario.getUsuario() %> /> 
      </div>
      <div class="modal-footer">
        <button type="button" class = "btn btn-secondary" data-dismiss="modal">Volver</button>
                <input type="button" class="btn pull-right btn-danger" value="Eliminar mi Cuenta" onClick="validarIgualdadPassword()"/>
            
            <div id="mensajeContraseniasNoCoinciden" class="alert alert-danger" role="alert"></div> 
            
            
		</div> 
    

      </div>
    </div>
  </div>
</div>
        
        
    	
  </div>


</form>
	
<jsp:include page="JSPFiles/footer.jsp" />  

</body>
</html>