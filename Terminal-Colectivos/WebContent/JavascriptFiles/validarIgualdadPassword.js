
function validarIgualdadPassword(){ 
   	clave1 = document.formBorrarMiCuenta.password.value; 
   	clave2 = document.formBorrarMiCuenta.passwordrep.value; 

   	if (clave1 == clave2) 
   		document.formBorrarMiCuenta.submit() 
   	else 
   		document.getElementById('mensajeContraseniasNoCoinciden').style.display = 'block';
   		document.getElementById('mensajeContraseniasNoCoinciden').innerHTML='Las contrase&ntilde;as no coinciden';   	
   	} 

