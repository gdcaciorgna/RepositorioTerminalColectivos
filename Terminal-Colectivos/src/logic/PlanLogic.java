package logic;

public class PlanLogic {
	
	public boolean validarDouble(String num) 
	{
		 try 
	        {
	    		Double.parseDouble(num);
	    		return true;   		
	    	} catch (NumberFormatException nfe){
	    		return false;
	    	}
	}
	
	public String cambiarSeparador(String num) 
	{
		num = num.replaceAll(",",".");
		return num;
	}

}
