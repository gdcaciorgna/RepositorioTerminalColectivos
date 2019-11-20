package controlers;

import data.DataReservaPlan;
import entities.Plan_Reserva;
import logic.ReservasPlanesLogic;

public class CancelarReserva {
	
	public double getImporteADevolver(String fechaHoraReserva, String fechaHoraViaje, String patenteColectivoViaje, int codRutaViaje, String UsernameReserva) 
	{
		
		DataReservaPlan dResPlan = new DataReservaPlan();
		Plan_Reserva reservaPlan= new Plan_Reserva();
		ReservasPlanesLogic resPlanLog = new ReservasPlanesLogic();
		
		reservaPlan = dResPlan.getReservaPlanbyClavesPrimarias(fechaHoraReserva, fechaHoraViaje, patenteColectivoViaje, codRutaViaje, UsernameReserva);
				
		double importeADevolver = resPlanLog.getImporteADevolver(reservaPlan);
				
		
		return importeADevolver;
		
		
	}

}
