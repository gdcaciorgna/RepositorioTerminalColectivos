package controlers;

import java.sql.SQLException;
import java.util.ArrayList;

import data.DataColectivo;
import data.DataUsuario;
import entities.Colectivo;
import entities.Usuario;
import logic.UsuarioLogic;
import util.AppDataException;
import util.AppLogicException;
import util.Emailer;

public class UsuariosControlers {
	
	
	public int eliminarUsuario(String username, String password) throws AppDataException
	{
		Usuario usu;
		DataUsuario dusu = new DataUsuario();
		
		boolean testPassword;
		int filasEliminadas = 0;
		
		
		usu = dusu.getByUsername(username);
		
		testPassword= dusu.validarUsuarioyPassword(usu,password);
		
		if(testPassword) 
		{

			filasEliminadas = dusu.eliminarUsuario(usu);
			
			
		}
		
		return filasEliminadas;
		
	}
	
	
	public Usuario editarUsuario(String username, String nombre, String apellido, String email, String cuil, String rol, String estado, String passwordActual, String passwordNuevo, String passwordNuevoRep) throws AppDataException
	{
		UsuarioLogic usuLog = new UsuarioLogic();
		
		rol = usuLog.setRolCliente(rol);
		estado = usuLog.setEstadoActivo(estado);
		
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
	
	
	public Usuario loginUsuario(String username, String password) throws Exception
	{
		DataUsuario dusu = new DataUsuario();
		Usuario usu = new Usuario();
        
        
        if(dusu.validarUsuarioyPassword(username, password)==true)
        {
        	usu = this.getByUsername(username);
   	        	
        }
        
        else 
        {
        	throw new AppLogicException("Usuario y/o contrase�a inv�lido.");
        }
        
        
        
        return usu;

	}
	
	public Usuario getByUsername(String username) throws Exception
	{
		DataUsuario dusu = new DataUsuario();
		Usuario usu = new Usuario();
       
        usu = dusu.getByUsername(username);    
        
        return usu;
	}
	
	
	public void registrarUsuario(String username, String password, String passwordrep, String nombre, String apellido, String email, String cuil, String rol) throws Exception
	{
		this.validarRegistro(username, password, passwordrep);
		this.setUsuario(username, password, nombre, apellido, email, cuil, rol);
		//Emailer.getInstance().send(email, "�Bienvenido a nuestro sitio web!", "Bienvenido" + nombre + " " +  apellido + " a nuestra plataforma digital de reserva y compra de pasajes. �Que tengas siempre un buen viaje! :) " );

	}
	
	
	public void validarRegistro(String username, String password, String passwordrep) throws Exception 
	{
		DataUsuario dusu = new DataUsuario();
		
					
		if((dusu.validarUsuarioInexistente(username) == false))
		{
			//mensaje = "Error1";  // "El nombre de usuario esta en uso");
			throw new AppLogicException("El nombre del usuario ya est� registrado.");
		}
			
				
		else if(password.length()<8)
		{			
			//mensaje = "Error2";  // "La contrase�a debe contener 8 caracteres como minimo");
			throw new AppLogicException("La contrase�a debe contener 8 caracteres como m�nimo.");

	    }	
		
		else if(!password.equals(passwordrep)) 
		{ 
			//mensaje = "Error 3"; //"Las contrase�as no coinciden");	
			throw new AppLogicException("Las contrase�as no coinciden.");

		 
	   	}
						
							

	}
	
	public void validarEditarUsuario(String username, String passwordActual, String passwordNuevo, String passwordNuevoRep) throws SQLException, AppDataException, AppLogicException 
	{
		DataUsuario dusu = new DataUsuario();
		

		if(passwordNuevo.length()<8)
		{			
			throw new AppLogicException("La nueva contrase�a debe contener 8 caracteres como minimo");
	
	    }	
		
		else if(!passwordNuevo.equals(passwordNuevoRep)) 
		{ 
			throw new AppLogicException("Las contrase�as no coinciden");
		 
	   	}
		
		if(!dusu.validarUsuarioyPassword(username, passwordActual)) 
		{
			throw new AppLogicException("La contrase�a actual ingresada no es correcta");
		}
						
						
	}
	
	public Usuario setUsuario(String username, String password, String nombre, String apellido, String email, String cuil, String rol) throws AppDataException 
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
	
	public void validarEliminarUsuario(String username, String password) throws SQLException, AppDataException, AppLogicException 
	{
		
		DataUsuario dusu = new DataUsuario();
		
		
		if(username.isEmpty() || password.isEmpty()) 
		{
			throw new AppLogicException("Hay campos vac�os.");
		}
		
		else if(!dusu.validarUsuarioyPassword(username, password)) //En caso de no haber ingresado password correctamente, 
		{
			throw new AppLogicException("Contrase�a incorrecta.");
		}
        		 
		
		
	}
	
	public ArrayList<Usuario> getAllChoferes() throws AppDataException
	{
        DataUsuario dusu = new DataUsuario();
        return dusu.getAllChoferes();

	}
	
	
	
	public ArrayList<Colectivo> getAllColectivos() throws AppDataException
	{
        DataColectivo dCol = new DataColectivo();
        return dCol.getAll();

	}
	
	
	
	
	
	
	


}
