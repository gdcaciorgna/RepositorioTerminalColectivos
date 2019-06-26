<%@ page contentType="text/html; charset=UTF-8" %>
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
<meta charset="charset=UTF-8">
<title>Inicio</title>

</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarText">
    <ul class="navbar-nav mr-auto">
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
   
    <span class="navbar-item">
    <ul class="navbar-nav mr-auto">
      <%
String usuario=(String)session.getAttribute("usuario");
if(usuario==null)
{%>
	<li class="nav-item">
	<a class="nav-link" href="login.jsp"><i class="fas fa-user"></i> Iniciar Sesión</a>
	</li>
<% } else
{ %>
         
       <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fas fa-user"></i> Mi cuenta
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="#"><i class="fas fa-id-card"></i> Mi Perfil</a>
          <a class="dropdown-item" href="LogoutServlet"><i class="fas fa-times-circle"></i> Cerrar Sesión</a>
        </div>
      </li>
    </ul>
<% 
}
%>
    </span>
    
  </div>
</nav>


<div>
 Bienvenido ${sessionScope.usuario} - Rol: ${sessionScope.rol}
 <%--Para recuperar el nombre de la sesión --%>
</div>




</body>
</html>