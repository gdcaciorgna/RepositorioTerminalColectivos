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
	
	
	public Usuario editarMiUsuario(String username, String nombre, String apellido, String email, String cuil, String rol, String passwordActual, String passwordNuevo, String passwordNuevoRep)
	{
		UsuarioLogic usuLog = new UsuarioLogic();
		
		cuil = usuLog.setNullCuil(cuil);
		rol = usuLog.setNullRol(rol);
		
		DataUsuario datauser = new DataUsuario();
		Usuario user;
		
		user = datauser.getByUsername(username);
		
		user.setNombre(nombre);
		user.setApellido(apellido);
		user.setEmail(email);
		user.setCuil(cuil);
		user.setRol(rol);
		
		//FALTA MANEJO DE CONTRASEÑA
		
		datauser.editarUsuario(user);
		
		return user; 
 
	}
	
	public Usuario editarUsuario(String username, String nombre, String apellido, String email, String cuil, String rol, String estado)
	{
		
		
		UsuarioLogic usuLog = new UsuarioLogic();
		cuil = usuLog.setNullCuil(cuil);

		Usuario user;
		DataUsuario datauser = new DataUsuario();
		
		user = datauser.getByUsername(username);
		user.setNombre(nombre);
		user.setApellido(apellido);
		user.setEmail(email);
		user.setCuil(cuil);
		user.setRol(rol);
		user.setEstado(estado);
		
		datauser.editarUsuario(user);
		
		return user;
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
		
		String mensajeRegistro = "OK";
			    
			
		if((dusu.validarUsuarioInexistente(username) == false))
		{
			mensajeRegistro = "Error1";  // "El nombre de usuario esta en uso");				 
		}
			
				
		else if(password.length()<8)
		{			
			mensajeRegistro = "Error2";  // "La contraseña debe contener 8 caracteres como minimo");		
	    }	
		
		else if(!password.equals(passwordrep)) 
		{ 
			mensajeRegistro = "Error 3"; //"Las contraseñas no coinciden");	
		 
	   	}
		
		return mensajeRegistro;						
							

	}
	
	public String getMensajeEditarUsuario(String username, String password, String passwordrep) 
	{
		DataUsuario dusu = new DataUsuario();
		
		String mensajeRegistro = "OK";
			    			
		if((dusu.validarUsuarioInexistente(username) == false))
		{
			mensajeRegistro = "Error1";  // "El nombre de usuario esta en uso");				 
		}
			
				
		else if(password.length()<8)
		{			
			mensajeRegistro = "Error2";  // "La contraseña debe contener 8 caracteres como minimo");		
	    }	
		
		else if(password.equals(passwordrep)) 
		{ 
			mensajeRegistro = "Error 3"; //"Las contraseñas no coinciden");	
		 
	   	}
		
		return mensajeRegistro;						
							

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
