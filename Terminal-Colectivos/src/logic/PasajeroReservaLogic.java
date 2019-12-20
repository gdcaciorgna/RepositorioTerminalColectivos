package logic;

import java.util.ArrayList;
import java.util.Collections;

import data.DataPasajeroReserva;
import entities.Pasajero_Reserva;
import entities.Plan;
import util.AppDataException;

public class PasajeroReservaLogic 
{
	
	public int getProximoAsientoxPlan(Plan plan) throws AppDataException 
	{
		int proxAsiento = 0;
		
		ArrayList<Pasajero_Reserva> pasajeros_reservas = new ArrayList<Pasajero_Reserva>();
		DataPasajeroReserva dPasRes = new DataPasajeroReserva();
		
		pasajeros_reservas = dPasRes.getPasajerosxPlan(plan);
		
		Collections.sort( pasajeros_reservas);
	
		
		for(int i = 1; i <= plan.getColectivo().getCapacidad(); i++) 
		{
			boolean asientoExistenteVoF = false;
			
			for(Pasajero_Reserva pasres : pasajeros_reservas) 
			{
				
				
				if(i == pasres.getAsiento())
				{
					asientoExistenteVoF=true;
					break;
				}
				
				
				
			}
			
			if(asientoExistenteVoF==false) 
			{
				proxAsiento = i;
				break;
			}
		}
		
		return proxAsiento;
	}
	

	
	
	
	

}


