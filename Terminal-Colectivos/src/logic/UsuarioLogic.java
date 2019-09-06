package logic;


public class UsuarioLogic {
	
	public String setNullCuil(String cuilInicial) 
	{
		String cuilFinal="";
		
		if(cuilInicial!=null) 
		{
			cuilFinal=cuilInicial;
		}
		
		return cuilFinal;
		
		
	}
	
	public String setNullRol (String rolInicial) 
	{
		String rolFinal="";
		
		if(rolInicial != null) 
		{
			rolFinal = rolInicial;
		}
		
		return rolFinal;
		
		
	}
	
	

}
