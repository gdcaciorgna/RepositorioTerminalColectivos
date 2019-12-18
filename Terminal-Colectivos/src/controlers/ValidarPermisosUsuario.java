package controlers;

import entities.Usuario;
import logic.UsuarioLogic;

public class ValidarPermisosUsuario {
	
	public boolean validarAdministrador(Usuario usuario) throws Exception 
	{
		UsuarioLogic usuLog = new UsuarioLogic();
		return usuLog.validarAdministrador(usuario);
	}
	
	
	public boolean validarChofer(Usuario usuario) throws Exception 
	{
		UsuarioLogic usuLog = new UsuarioLogic();
		return usuLog.validarChofer(usuario);
	}
	
	
		

}
