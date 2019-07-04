<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<jsp:include page="JSPFiles/imports.jsp" /> 

<title>Iniciar Sesi�n</title>

</head>
<body>

<jsp:include page="JSPFiles/header.jsp" />  

<div class="container login-container">
<div class="row">
    
    <div class="col-sm">
       <div class="login-form-1 center-block">
             <h3>Iniciar Sesi�n</h3>
             <form action="LoginServlet" method = "post">
                 <div class="form-group">
                     <input type="text" class="form-control" name="username" placeholder="Ingresar usuario..." value="" required />
                 </div>
                 <div class="form-group">
                     <input type="password" class="form-control" name="password" placeholder="Ingresar contrase�a..." value=""  required/>
                 </div>
                 
           
                 <div class="form-group">
                     <input type="submit" class="btnSubmit" value="Ingresar" />
                 </div>
                  <div align="center"> <a href="#">Crear una nueva cuenta</a> </div>
              	<% String error = (String)session.getAttribute("error");%>
			<% if(session.getAttribute("error")!=null) { %>
			<br>
			<div class="alert alert-danger" role="alert">
			Error: <%= error %>
			</div> 
			<%}%>
             </form>
         </div>
       
    </div>
    
 
  </div>
      
</div>

<jsp:include page="JSPFiles/footer.jsp" />  
</body>
</html>