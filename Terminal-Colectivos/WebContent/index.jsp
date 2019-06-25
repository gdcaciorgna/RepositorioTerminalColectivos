<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/a0183d84ac.js"></script>

 <link rel="stylesheet" href="estilos.css"> 
<meta charset="ISO-8859-1">
<title>Inicio</title>




</head>
<body>

        
<div class="row no-gutters ">
<div class="col-sm-10 float-left">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
      <li class="nav-item active">
        <a class="nav-link" href="#"><i class="fas fa-home"></i> Inicio <span class="sr-only">(current)</span></a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="#"><i class="fas fa-suitcase"></i> Mis Reservas</a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="#"><i class="fas fa-users"></i> Usuarios</a>
      </li>
     <li class="nav-item">
        <a class="nav-link" href="#"><i class="fas fa-bus"></i> Viajes</a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="#"><i class="fas fa-address-book"></i> Información</a>
      </li>

    </ul>
    
  </div>
</nav>



</div>


<div class="col-sm-2 float-right">

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

  <div class=" collapse navbar-collapse" id="navbarTogglerDemo03">
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
          <li class="nav-item">
<%
String usuario=(String)session.getAttribute("usuario");
if(usuario==null)
{%>
<a href="login.jsp" class="nav-link" href="#"><i class="fas fa-user-cog"></i> Iniciar Sesión </a>
<% } else
{ %>
 <a href="ServletCerrarSesion?param=value" class="nav-link" href="#"><i class="fas fa-user-cog"></i> Cerrar Sesión </a>
<% 
}
%>


 

 </li>

    </ul>
    
    
  </div>
</nav>
</div>
</div>


</body>
</html>