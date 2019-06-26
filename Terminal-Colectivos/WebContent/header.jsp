<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarText">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="index.jsp"><i class="fas fa-home"></i> Inicio</a>

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
          <a class="dropdown-item" href="MiPerfil.jsp"><i class="fas fa-id-card"></i> Mi Perfil</a>
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


