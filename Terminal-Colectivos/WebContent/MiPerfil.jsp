<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="imports.jsp" />
<title>Mi Perfil</title>
</head>
<body>
<jsp:include page="header.jsp" /> 


<div>
<br> 
Informaci�n de Mi Perfil, con posibilidad de realizar Cambios
<br> 
</div>

<div class="card border-danger mb-3" style="max-width: 15rem;">
  <div class="card-header">Eliminar Cuenta</div>
  <div class="card-body text-danger">
    <h5 class="card-title">�Cuidado!</h5>
    <p class="card-text">Al eliminar tu cuenta, no podr�s recuperarla en un futuro.</p>
    	<div class="">
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
               
                 <label class="p-2" for="exampleFormControlInput1">Para que podamos dar de baja tu cuenta, es necesario que escribas tu contrase�a:</label>
               
                     <input type="password" class="form-control" name="txtpass1" placeholder="Ingresar contrase�a..." value="" />
                 </div>
                 <div class="form-group">
                     <input type="password" class="form-control" name="txtpass2" placeholder="Repetir contrase�a..." value="" />
                 </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Volver</button>
        <form action="DeleteAccountServlet">
        <button type="button" class="btn pull-right btn-danger">Eliminar Cuenta</button>
        </form>

      </div>
    </div>
  </div>
</div>
        
        
    	</div>
  </div>
</div>



</body>
</html>