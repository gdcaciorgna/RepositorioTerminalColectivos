<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="imports.jsp" />
<title>Mi Perfil</title>

<script> 
function comprobarClave(){ 
   	clave1 = document.formBorrarMiCuenta.txtpass1.value 
   	clave2 = document.formBorrarMiCuenta.txtpass2.value 

   	if (clave1 == clave2) 
   		document.formBorrarMiCuenta.submit() 
   	else 
   		document.getElementById('mensajeContraseniasNoCoinciden').style.display = 'block';
   		document.getElementById('mensajeContraseniasNoCoinciden').innerHTML='Las contraseñas no coinciden';   	
   	} 
</script> 


</head>
<body>
<jsp:include page="header.jsp" /> 
<% if(session.getAttribute("usuario")==null) request.getRequestDispatcher("login.jsp").forward(request, response); %>
<% String txtusuario = session.getAttribute("usuario").toString(); %> 

<div>
<br> 
Información de Mi Perfil, con posibilidad de realizar Cambios
<br> 
</div>

<form name="formBorrarMiCuenta" action="BorrarCuentaServlet" method="get">
<div class="card border-danger mb-3" style="max-width: 15rem;">
  <div class="card-header">Eliminar Cuenta</div>
  <div class="card-body text-danger">
    <h5 class="card-title">¡Cuidado!</h5>
    <p class="card-text">Al eliminar tu cuenta, no podrás recuperarla en un futuro.</p>
    	
        <button type="button" class="btn pull-right btn-danger btn-lg btn-block" data-toggle="modal" data-target="#exampleModalCenter">Eliminar Cuenta</button>
        
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
           
                 <input type="password" class="form-control" name="txtpass1" placeholder="Ingresar contraseña..." value="" />
    	</div>
        <div class="form-group">
            <input type="password" class="form-control" name="txtpass2" placeholder="Repetir contraseña..." value="" />
            
        </div>
        <input type="hidden" name="txtusu" value=<%=txtusuario %> /> 
      </div>
      <div class="modal-footer">
        <button type="button" class = "btn btn-secondary" data-dismiss="modal">Volver</button>
                <input type="button" class="btn pull-right btn-danger" value="Eliminar mi Cuenta" onClick="comprobarClave()"/>
            
            <div id="mensajeContraseniasNoCoinciden" class="alert alert-danger" role="alert"><br></div> 
               
			</div> 
			
    

      </div>
    </div>
  </div>
</div>
        
        
    	
  </div>


</form>



</body>
</html>