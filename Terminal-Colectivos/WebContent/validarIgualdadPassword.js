

function validarIgualdadPassword(){ 
   	clave1 = document.formBorrarMiCuenta.txtpass1.value 
   	clave2 = document.formBorrarMiCuenta.txtpass2.value 

   	if (clave1 == clave2) 
   		document.formBorrarMiCuenta.submit() 
   	else 
   		document.getElementById('mensajeContraseniasNoCoinciden').style.display = 'block';
   		document.getElementById('mensajeContraseniasNoCoinciden').innerHTML='Las contrase&ntilde;as no coinciden';   	
   	} 
