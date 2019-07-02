<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="JSPFiles/imports.jsp" />  
<meta charset="ISO-8859-1">
<title>Usuarios</title>
</head>
<body>

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

<jsp:include page="JSPFiles/header.jsp" /> 


<div class="container" style=" margin-top: 2%; margin-bottom: 2%;  ">
<h1>Listado de Usuarios</h1>
<table class="table table-striped">
  <thead class="thead-dark">
    <tr>
   	  <th>#</th>
      <th>Usuario</th>
      <th>Rol</th>
      <th>Email</th>
      <th>Estado</th>
   	  <th></th>
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
   <td> <%= usu.getUsuario() %> </td>
   <td> <%= usu.getRol() %> </td>
   <td> <%= usu.getEmail() %> </td>
   <td>
   <% if(usu.getEstado().equalsIgnoreCase("activo")) { %>
   <p class="text-success"><%=usu.getEstado() %></p> 
  
    <% } else { %>
   <p class="text-danger"><%=usu.getEstado() %></p> 

   <% } %>
   </td>
   <td><button type="button" class="btn btn-warning"><i class="fas fa-edit"></i></button>  <button type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>  </td>
   </tr>
   
   
   <% 
   cont++;
   } %> 
    
  </tbody>
</table>
</div>

<jsp:include page="JSPFiles/footer.jsp" />  

</body>
</html>