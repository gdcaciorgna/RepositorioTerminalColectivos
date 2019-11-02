package controlers;

import data.DataPasajero;
import entities.Pasajero;
import logic.PasajeroLogic;

public class ABMPasajero {
	
	public void AddPasajero(Pasajero pasajero) 
	{
		PasajeroLogic plog = new PasajeroLogic();
		
		if(plog.validarPasajeroInexistente(pasajero.getDni())) 
		{
			DataPasajero dpas = new DataPasajero();
			dpas.addPasajero(pasajero);

		}
				
	}
	
	

}
