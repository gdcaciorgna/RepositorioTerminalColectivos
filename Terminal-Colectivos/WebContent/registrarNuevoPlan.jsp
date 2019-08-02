<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<%@include file="JSPFiles/includeimports.jsp" %>
<meta charset="ISO-8859-1">
<title>Registrar nuevo plan de viaje</title>



</head>
<body>

<jsp:include page="JSPFiles/includemenu.jsp" />  

<div class="container login-container">
<div class="row">
    
    <div class="col-sm">
       <div class="login-form-1 center-block">
             <h3>Ingrese datos del nuevo plan</h3>
             <form action="RegistrarNuevaCuenta" method = "post">
               
            <div class="form-group">
                <div class="input-group date form_date " data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                   <div class="input-group-prepend">
				   <div class="input-group-text"><i class="fas fa-calendar"></i></div>
				   </div>
                   <input class="form-control" type="text" value="" readonly>
                   <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span> <!-- No tengo idea para que es el span pero es inevitable  -->
                   <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>              
                <input type="hidden" id="dtp_input2" value="" /> 
                </div>	
            </div>
      
      
			<div class="form-group">
                <div class="input-group date form_time" data-date="" data-date-format="hh:ii" data-link-field="dtp_input3" data-link-format="hh:ii">
                   <div class="input-group-prepend">
				   <div class="input-group-text"><i class="fas fa-clock"></i></div>
				   </div>
                    <input class="form-control" type="text" value="" readonly>
					<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                
                <input type="hidden" id="dtp_input3" value="" />
                
                </div>
				
            </div>
               
                
                 <div class="form-group">
                     <input type="text" class="form-control" name="usuario" placeholder="Usuario" value=""  required/>
                 </div>
                 <div class="form-group">
                     <input type="password" class="form-control" name="password" placeholder="Contraseña" value=""  required/>
                 </div>
           		 <div class="form-group">
                     <input type="password" class="form-control" name="passwordrep" placeholder="Confirmar Contraseña" value=""  required/>
                 </div>
                  <div class="form-group">
                     <input type="email" class="form-control" name="email" placeholder="E-mail" value=""  required/>
                 </div>
               
                
               <div class="form-group">
                     <input type="submit" class="btnSubmit" value="Registrarse" />
                 </div>
              		
			  
             </form>
         </div>
       
    </div>
    
 
</div>
</div>
<jsp:include page="JSPFiles/includefooter.jsp" />  


<!-- Este script se debería realizar en un archivo js diferente para poder reutilizarlo en otras ocasiones -->

<script type="text/javascript">
$('.form_date').datetimepicker({
    language:  'es',
    weekStart: 1,
    todayBtn:  1,
	autoclose: 1,
	todayHighlight: 1,
	startView: 2,
	minView: 2,
	forceParse: 0,
	format: 'dd/mm/yyyy'

});
$('.form_time').datetimepicker({
    language:  'es',
    weekStart: 1,
    todayBtn:  1,
	autoclose: 1,
	todayHighlight: 1,
	startView: 1,
	minView: 0,
	maxView: 1,
	forceParse: 0
});
</script>


</body>
</html>