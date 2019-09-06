package logic;


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
	

}
