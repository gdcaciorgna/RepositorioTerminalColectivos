<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="JSPFiles/includeimports.jsp" />
 
<meta charset="ISO-8859-1">
<title>Usuarios</title>
</head>
<body>

	<% 	HttpSession sesion = request.getSession();
		sesion.setAttribute("mensajeEditarUsuario", null);%>

   <%@ page import = "data.DataUsuario" %>
    <%@ page import = "java.util.*" %>
    <%@ page import = "entities.Usuario" %>
    <% 
    //Inicialización de variables
    DataUsuario dusu = new DataUsuario();
    ArrayList<Usuario> usuarios = dusu.getAll();
    Iterator<Usuario> itr = usuarios.iterator();
    Usuario usu = null;
    
    %>

<% Usuario usuario;%>

<jsp:include page="JSPFiles/includemenu.jsp" /> 


<div class="container" style=" margin-top: 2%; margin-bottom: 2%;  ">
 <div class="container">
        <div class="table-wrapper">
            <div class="table-title">
               
                    <span class="float-left"><h2>Listado de <b>Usuarios</b></h2></span>
                    <span class="float-right">
                        <a href="registro.jsp"> <button type="button" class="btn btn-info add-new">  <i class="fa fa-plus"></i> Nuevo Usuario</button></a>
                    </span>
                
            </div> </div> </div>
           
<table class="table table-striped">
  <thead class="thead-dark">
    <tr>
   	  <th>#</th>
      <th>Usuario</th>
      <th>Rol</th>
      <th>Email</th>
      <th>Estado</th>
   	  <th> </th>
    </tr>
  </thead>
  <tbody>
    
   <tr>
   <% 
   int cont = 1;
   while(itr.hasNext()){
	   usu = itr.next();
    %>
   <th scope="row"><%= cont %></th>
   <td> <%= usu.getUsername() %> </td>
   <td> <%= usu.getRol() %> </td>
   <td> <%= usu.getEmail() %> </td>
   <td>
   <% if(usu.getEstado().equalsIgnoreCase("activo")) { %>
   <p class="text-success"><%=usu.getEstado() %></p> 
  
    <% } else { %>
   <p class="text-danger"><%=usu.getEstado() %></p> 

   <% } %>
   </td>
   <td>
   
   <form action="RedireccionEditarUsuarioSevlet" method="post">
   <input type="hidden" value=<%=usu.getUsername()%> name="username"/>

   <button type="submit" class="btn btn-warning"><i class="fas fa-edit"></i></button></form> 
   <form action="BorrarCuentaServlet" method="post">
   <input type="hidden" value=<%=usu.getUsername()%> name="username"/>
   <input type="hidden" value=<%=usu.getPassword() %> name="password"/>
   <button type="submit" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button></form>
   </td>
   
   </tr>
   
   
   <% 
   cont++;
   } %> 
    
  </tbody>
</table>
 
</div>

<% Integer filasAfectadas = 0;%> 
<% filasAfectadas = (Integer) session.getAttribute("UsuariosAfectados");%>
<% if(filasAfectadas != null) { %>
<br>
<div class="alert alert-danger" role="alert">
Filas Afectadas: <%= filasAfectadas %>
</div> 
<%}%>

<jsp:include page="JSPFiles/includefooter.jsp" />  

</body>
</html>