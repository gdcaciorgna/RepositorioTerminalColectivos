<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import = "entities.Usuario" %>
<% Usuario usuario = (Usuario) session.getAttribute("ClaseUsuario"); %>


<nav class="navbar navbar-expand-lg navbar-dark bg-dark ">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarText">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="index.jsp"><i class="fas fa-home"></i> Inicio</a><!-- Disponible para todos -->

      </li>
      <%! String rol, nomUsuario; %>
      <% 
      
      if(usuario!=null) {
      rol = usuario.getRol();
      nomUsuario = usuario.getUsuario();
      }
      else { rol= "visitante" ;} %>
      <% if(!rol.equals("visitante")) {  %>
   <li class="nav-item">
      <a class="nav-link" href="#"><i class="fas fa-suitcase"></i> Mis Reservas</a> <!-- Disponible para clientes, choferes y admin -->
      </li>
    	<% } %>
    	    
    	<% if(rol.equals("admin")) {%>     
       <li class="nav-item">
        <a class="nav-link" href="usuarios.jsp"><i class="fas fa-users"></i> Usuarios</a> <!-- Disponible para Administradores -->
      </li>
      <% } %>
    
    <% if (!rol.equals("visitante")) { %>
     <li class="nav-item">
        <a class="nav-link" href="#"><i class="fas fa-bus"></i> 
       
       
         <% if(rol.equals("admin"))
        {%>
        Planes de Viaje
        <% } else if(rol.equals("chofer")) { %>      
        Viajes asignados
        <% } else if(rol.equals("cliente")) { %>    
        Viajes
        <% } %> 
        
        </a> <!-- Planes de Viaje (Administradores) / Viajes asignados (Chofer) / Viajes (Cliente) -> Redirección a la misma página pero con distinto nombre dependiendo del rol -->
      </li>
      
      <% } %>
         
         <%  if(rol.equals("admin")) { %>
         <li class="nav-item">
        <a class="nav-link" href="#"><i class="fas fa-road"></i> Rutas</a> <!-- Disponible para Administradores -->
      </li>
      <%  } %>
       <li class="nav-item">
        <a class="nav-link" href="#"><i class="fas fa-address-book"></i> Información</a> <!-- Disponible para todos -->
      </li>
    </ul>
   
    <span class="navbar-item">
    <ul class="navbar-nav mr-auto">
      <%
  if(usuario==null || usuario.getEstado()=="eliminado")
 {%>
	<li class="nav-item">
	<a class="nav-link" href="login.jsp"><i class="fas fa-user"></i> Iniciar Sesión</a>
	</li>
<%  } else
{ %>
         
       <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fas fa-user"></i> Mi cuenta
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="micuenta.jsp"><i class="fas fa-id-card"></i> Mi Cuenta</a>
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


