package controlers;

import java.util.ArrayList;
import java.util.Date;

import data.DataPlan;
import data.DataReservaPlan;
import entities.Plan;
import entities.Plan_Reserva;
import entities.Usuario;

public class BuscarViajes {
	
	public ArrayList<Plan> getViajesxChofer(Usuario usuarioActual) throws Exception 
	{
		
	    DataPlan dplan = new DataPlan();

		return dplan.getViajesxChofer(usuarioActual);
		
		 
		
	}
	
	public ArrayList<Plan> getViajesxCliente(String origen, String destino, Date fechaViaje) throws Exception 
	{
		
	    DataPlan dplan = new DataPlan();

		try 
		{
			return dplan.getViajesDia(origen, destino, fechaViaje) ;
		}
		
		catch(Exception e) 
		{
			throw e;
		}
		
		 
		
	}

}
