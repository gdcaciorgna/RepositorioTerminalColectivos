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
		
		 
		int cantAsientosReservados = this.calcularAsientosReservados(plan, plan_reservas);
		int cantAsientosDisponibles;
		
		cantAsientosDisponibles = plan.getColectivo().getCapacidad() - cantAsientosReservados;
		
		return cantAsientosDisponibles;
		
		
	}
	
	public int calcularAsientosReservados(Plan plan, ArrayList<Plan_Reserva> plan_reservas) 
	{
		
		int cantAsientosReservados = 0;
		
		for(Plan_Reserva plan_reserva : plan_reservas) {
		    
				if(plan_reserva.getReserva().getFecha_canc() ==null) 
				{
				cantAsientosReservados= cantAsientosReservados + plan_reserva.getReserva().getCant_pas();
				}
			
		}
				
		return cantAsientosReservados;
	}

}
