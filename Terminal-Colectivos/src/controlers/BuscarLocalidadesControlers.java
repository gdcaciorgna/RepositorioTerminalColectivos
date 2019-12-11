package controlers;

import java.util.ArrayList;

import data.DataLocalidad;
import entities.Localidad;
import util.AppDataException;

public class BuscarLocalidadesControlers {
	
	
	
	public ArrayList<Localidad> getAllLocalidades() throws AppDataException
	{
		
		DataLocalidad dloc = new DataLocalidad();
	    return dloc.getAll();		
		
		
	}
	
	

}
