<%@ page import = "entities.Usuario" %>
<% Usuario usuario;%>
<% 
String nomUsuario="s/usuario", estado="s/estado"; 
usuario = (Usuario) session.getAttribute("Usuario");  
if(usuario!=null) 
{
	nomUsuario = usuario.getUsername(); 
	estado = usuario.getEstado(); 
}%>

<% if(nomUsuario.equals("s/usuario") || estado.equals("s/estado") || !estado.equals("activo"))  response.sendRedirect("../login.jsp"); %>
