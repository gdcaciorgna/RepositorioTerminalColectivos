package logic;

import data.DataPasajero;
import entities.Pasajero;
import util.AppDataException;

public class PasajeroLogic {
	
	public boolean validarPasajeroInexistente(int dni) throws AppDataException 
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
	
	 public  boolean esNumero(String cadena) {

	        boolean resultado;

	        try {
	            Integer.parseInt(cadena);
	            resultado = true;
	        } catch (NumberFormatException excepcion) {
	            resultado = false;
	        }

	        return resultado;
	    }
	

}
