package controlers;

import data.DataUsuario;
import entities.Usuario;
import logic.UsuarioLogic;

public class UsuariosControlers {
	
	
	public boolean eliminarMiUsuario(String username, String password)
	{
		Usuario usu;
		DataUsuario dusu = new DataUsuario();
		
		boolean testPassword;
		boolean vof = false;
		
		
		usu = dusu.getByUsername(username);
		
		testPassword= dusu.validarUsuarioyPassword(usu,password);
		
		if(testPassword) 
		{

			dusu.eliminarUsuario(usu);
			vof = true;
			
		}
		
		return vof;
		
	}
	
	
	public Usuario editarUsuario(String username, String nombre, String apellido, String email, String cuil, String rol, String estado, String passwordActual, String passwordNuevo, String passwordNuevoRep)
	{
		UsuarioLogic usuLog = new UsuarioLogic();
		
		cuil = usuLog.setNullCuil(cuil); //se puede evitar?
		rol = usuLog.setNullRol(rol); //se puede evitar?
		
		DataUsuario datauser = new DataUsuario();
		Usuario usuNuevo = new Usuario();
				
		usuNuevo.setUsername(username);
		usuNuevo.setNombre(nombre);
		usuNuevo.setApellido(apellido);
		usuNuevo.setEmail(email);
		usuNuevo.setPassword(passwordNuevo);
		usuNuevo.setCuil(cuil);
		usuNuevo.setRol(rol);
		usuNuevo.setEstado(estado);
		
		
		datauser.editarUsuario(usuNuevo);	

		
		return usuNuevo; 
 
	}
	
	
	public Usuario loginUsuario(String username, String password) 
	{
		DataUsuario dusu = new DataUsuario();
		Usuario usu = new Usuario();
        
        
        if(dusu.validarUsuarioyPassword(username, password)==true)
        {
        	usu = dusu.getByUsername(username);
   	        	
        }
        
        
        return usu;

	}
	
	public Usuario getByUsername(String username) 
	{
		DataUsuario dusu = new DataUsuario();
		Usuario usu = new Usuario();
       
        usu = dusu.getByUsername(username);    
        
        return usu;
	}
	
	
	
	public String getMensajeRegistro(String username, String password, String passwordrep) 
	{
		DataUsuario dusu = new DataUsuario();
		
		String mensaje = "OK";
			    
			
		if((dusu.validarUsuarioInexistente(username) == false))
		{
			mensaje = "Error1";  // "El nombre de usuario esta en uso");				 
		}
			
				
		else if(password.length()<8)
		{			
			mensaje = "Error2";  // "La contrase�a debe contener 8 caracteres como minimo");		
	    }	
		
		else if(!password.equals(passwordrep)) 
		{ 
			mensaje = "Error 3"; //"Las contrase�as no coinciden");	
		 
	   	}
		
		return mensaje;						
							

	}
	
	public String getMensajeEditarUsuario(String username, String passwordActual, String passwordNuevo, String passwordNuevoRep) 
	{
		DataUsuario dusu = new DataUsuario();
		
		String mensaje = "OK";
			    			
		if((dusu.validarUsuarioInexistente(username) == false))
		{
			mensaje = "Error1";  // "El nombre de usuario esta en uso");				 
		}
			
				
		else if(passwordNuevo.length()<8)
		{			
			mensaje = "Error2";  // "La nueva contrase�a debe contener 8 caracteres como minimo");		
	    }	
		
		else if(!passwordNuevo.equals(passwordNuevoRep)) 
		{ 
			mensaje = "Error 3"; //"Las contrase�as no coinciden");	
		 
	   	}
		
		if(dusu.validarUsuarioyPassword(username, passwordActual)) 
		{
			mensaje = "Error4"; //"La contrase�a actual ingresada no es correcta"
		}
		
		return mensaje;						
							

	}
	
	public Usuario setUsuario(String username, String password, String nombre, String apellido, String email, String cuil, String rol) 
	{
		Usuario usuario = new Usuario();
		DataUsuario dusu = new DataUsuario();
		
		if(rol == null) { rol = "cliente"; }
		
		usuario.setUsername(username);
		usuario.setPassword(password);
		usuario.setNombre(nombre); 
		usuario.setApellido(apellido); 
		usuario.setEmail(email);
		usuario.setCuil(cuil);
		usuario.setRol(rol);
		
		
		dusu.ingresarUsuario(usuario);
		
		return usuario;
	
		
	}
	
	
	
	
	
	
	
	


}
