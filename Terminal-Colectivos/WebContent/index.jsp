<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="JSPFiles/imports.jsp" />  
<meta charset="charset=UTF-8">
<title>Inicio</title>
</head>

<body>
<jsp:include page="JSPFiles/header.jsp" />  
<script type="text/javascript" src="JavascriptFiles/datepickerEs.js"></script>
<div>
 <%@ page import = "entities.Usuario" %>
 <%
 	Usuario usuario = (Usuario) session.getAttribute("Usuario"); 
  String nomUsuario = null;
  String rol  = null;
 if(usuario!=null)
 	{ 
 	nomUsuario = usuario.getUsuario();
 	rol = usuario.getRol();
 	}
 %>
	Usuario: <%=nomUsuario%>
	Rol: <%=rol%>



</div>


<br>


<div id="base"  class="container-fluid">
<div class="row">

<!--Grid column-->
        <div class="col-sm-7 mb-4">
            <div class="card card-image" style="background-image: url('Imagenes/fondoColectivos1.jpg'); background-color: #343a40 ; background-position: center;">
                <div class="text-white text-center d-flex align-items-center rgba-black-strong py-5 px-4">
                    <div>
                    	<hr>
                        <h3 class="card-title py-3 font-weight-bold"><strong>Terminal de Colectivos "Mariano Moreno"</strong></h3>
                        <hr>
                        <p class="pb-3">La Estación de Ómnibus Mariano Moreno es la terminal de micros de corta,
                         media y larga distancia donde confluye el transporte de pasajeros por vía terrestre de carácter 
                         interurbano, interprovincial e internacional desde y hacia la ciudad de Rosario, Argentina.</p>
                        <a class="btn btn-success btn-rounded" href=""><i class="fas fa-info-circle"></i> Más información</a>
                        <hr>
                    </div>
                </div>
            </div>
        </div>
        <!--Grid column-->




<div class="col-sm-5">
<jsp:include page="JSPFiles/buscadorViajes.jsp" />  
</div>

</div>
</div>



<jsp:include page="JSPFiles/footer.jsp" />  

</body>
</html>