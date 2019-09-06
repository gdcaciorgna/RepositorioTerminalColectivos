package logic;


public class UsuarioLogic {
	

	
	public String setRolActivo (String rolInicial) 
	{
		String rolFinal="activo";
		
		if(rolInicial != null) 
		{
			rolFinal = rolInicial;
		}
		
		return rolFinal;
		
		
	}
	

}
