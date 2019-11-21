<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<jsp:include page="JSPFiles/includeimports.jsp" /> 

<title>Iniciar Sesión</title>

</head>
<body>

<jsp:include page="JSPFiles/includemenu.jsp" />  

<div class="container login-container">
<div class="row">
    
    <div class="col-sm">
       <div class="login-form-1 center-block">
             <h3>Iniciar Sesión</h3>
             <form action="LoginServlet" method = "post">
                 <div class="form-group">
                     <input type="text" class="form-control" name="username" placeholder="Ingresar usuario..." value="" required />
                 </div>
                 <div class="form-group">
                     <input type="password" class="form-control" name="password" placeholder="Ingresar contraseña..." value=""  required/>
                 </div>
                 
           
                 <div class="form-group">
                     <input type="submit" class="btnSubmit" value="Ingresar" />
                 </div>
                  <div align="center"> <a href="registro.jsp">Crear una nueva cuenta</a> </div>
                 		<% String registroExitoso = (String)session.getAttribute("registroExitoso");%>
			<% if(session.getAttribute("registroExitoso")!=null) { %>
			<br>
			<div class="alert alert-success" role="alert">
			Felicitaciones: <%= registroExitoso %>
			</div> 
			<%}%>
            
            <% String errorLogin = (String)session.getAttribute("errorLogin");%>
			<% if(session.getAttribute("errorLogin")!=null) { %>
			<br>
			<div class="alert alert-danger" role="alert">
			Error: <%= errorLogin %>
			</div> 
			<%}%>
		
       
       
        <% session.setAttribute("registroExitoso",null);
              session.setAttribute("errorLogin",null);%>
              
             </form>
         </div>
       
    </div>
    
 
  </div>
      
</div>

<jsp:include page="JSPFiles/includefooter.jsp" />  
</body>
</html>