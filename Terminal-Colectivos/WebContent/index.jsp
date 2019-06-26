<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="imports.jsp" />  
<meta charset="charset=UTF-8">
<title>Inicio</title>
</head>

<body>
<jsp:include page="header.jsp" />  

<div>



 Usuario: <%= session.getAttribute("usuario") %> - Rol: <%= session.getAttribute("rol") %>
 <%--Para recuperar el nombre de la sesión --%>

</div>

<br>

<div>
Aquí va el buscador de viajes (Origen - Destino - Fecha)
</div>


</body>
</html>