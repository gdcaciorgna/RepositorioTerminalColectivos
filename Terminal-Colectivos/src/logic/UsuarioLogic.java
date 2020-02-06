package logic;

import entities.Usuario;

public class UsuarioLogic {
	

	
	public String setRolCliente (String rolInicial) 
	{
		String rolFinal="cliente";
		
		if(rolInicial != null) 
		{
			rolFinal = rolInicial;
		}
		
		return rolFinal;
		
		
	}
	
	public String setEstadoActivo (String estadoInicial) 
	{
		String estadoFinal="activo";
		
		if(estadoFinal != null) 
		{
			estadoFinal = estadoInicial;
		}
		
		return estadoFinal;
		
		
	}
	
	public String setPasswordActualNull (String passwordActual) 
	{
			
		if(passwordActual == "") 
		{
			passwordActual = null;
		}
		
		return passwordActual;
		
	}
	
	public String setPasswordNuevoNull (String passwordNuevo) 
	{
			
		if(passwordNuevo == "") 
		{
			passwordNuevo = null;
		}
		
		return passwordNuevo;
		
	}
	
	public String setPasswordNuevoRepNull (String passwordNuevoRep) 
	{
			
		if(passwordNuevoRep == "") 
		{
			passwordNuevoRep = null;
		}
		
		return passwordNuevoRep;
		
	}
	
	public boolean validarAdministrador (Usuario usuario) throws Exception 
	{
		boolean vof = false;
		
		if(usuario.getRol().equals("admin")) 
		{
			vof = true;
			
		}
		else 
		{
			throw new Exception("Error. Usted no tiene los permisos para ver esta p�gina");
		}
		
		return vof;
	}
	
	
	public boolean validarChofer(Usuario usuario) throws Exception 
	{
		boolean vof = false;
		
		if(usuario.getRol().equals("chofer") || usuario.getRol().equals("admin")) 
		{
			vof = true;
			
		}
		else 
		{
			throw new Exception("Error. Usted no tiene los permisos para ver esta p�gina");
		}
		
		return vof;
	}
	
	
	public boolean validarCliente(Usuario usuario) throws Exception 
	{
		boolean vof = false;
		
		if(usuario.getRol().equals("cliente") || usuario.getRol().equals("chofer") || usuario.getRol().equals("admin")) 
		{
			vof = true;
			
		}
		else 
		{
			throw new Exception("Error. Usted no tiene los permisos para ver esta p�gina");
		}
		
		return vof;
	}
	

}
