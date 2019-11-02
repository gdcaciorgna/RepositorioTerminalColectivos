package logic;

import java.util.ArrayList;

import entities.Plan;
import entities.Plan_Reserva;

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
	
	public int calcularAsientosDisponibles(Plan plan, ArrayList<Plan_Reserva> plan_reservas) 
	{
		int cantAsientosReservados = 0;
		int cantAsientosDisponibles;
		
		for(Plan_Reserva plan_reserva : plan_reservas) {
		    
			
				cantAsientosReservados++;
			
		}
		
		cantAsientosDisponibles = plan.getColectivo().getCapacidad() -  cantAsientosReservados;
		
		return cantAsientosDisponibles;
		
	}

}
