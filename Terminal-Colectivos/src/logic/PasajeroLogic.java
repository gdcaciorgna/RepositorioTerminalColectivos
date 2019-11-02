package logic;

import data.DataPasajero;
import entities.Pasajero;

public class PasajeroLogic {
	
	public boolean validarPasajeroInexistente(int dni) 
	{
		
		DataPasajero dpas = new DataPasajero();
		Pasajero pasajero = new Pasajero();
		pasajero = dpas.getByDni(dni);
		if(pasajero.getDni() == 0) 
		{
			return true;
		}
		
		else {return false;}
		
	}
	
	

}
