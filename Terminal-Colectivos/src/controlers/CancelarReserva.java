package controlers;


import java.util.ArrayList;

import data.DataPasajeroReserva;
import data.DataReserva;
import entities.Pasajero;
import entities.Plan_Reserva;
import entities.Reserva;
import logic.ReservasPlanesLogic;
import util.AppDataException;

public class CancelarReserva {
	
	public void cancelarReserva(Reserva reservaACancelar) throws AppDataException 
	{
		DataReserva dRes = new DataReserva();

		
		this.limpiarAsientos(reservaACancelar);
		
		dRes.cancelarReserva(reservaACancelar); //Envio solo la reserva a cancelar. No hace falta el plan


		
	}
	
	public double getImporteADevolver(String fechaHoraReserva, String fechaHoraViaje, String patenteColectivoViaje, int codRutaViaje, String UsernameReserva) throws AppDataException 
	
	{
		
		PlanReservaControlers conResPlan = new PlanReservaControlers();
		Plan_Reserva reservaPlan= new Plan_Reserva();
		ReservasPlanesLogic resPlanLog = new ReservasPlanesLogic();
		
		reservaPlan = conResPlan.getReservaPlanbyClavesPrimarias(fechaHoraReserva, fechaHoraViaje, patenteColectivoViaje, codRutaViaje, UsernameReserva);
				
		double importeADevolver = resPlanLog.getImporteADevolver(reservaPlan);
				
		
		return importeADevolver;
		
		
	}
	
	public void limpiarAsientos(Reserva reservaACancelar) throws AppDataException 
	{
		DataReserva dreserva = new DataReserva();
		dreserva.limpiarAsientos(reservaACancelar);
		
	}
	
	
	public ArrayList<Pasajero> getPasajerosxReserva(Reserva reservaACancelar) throws AppDataException
	{
		DataPasajeroReserva dpasRes = new DataPasajeroReserva();
		return dpasRes.getPasajerosxReserva(reservaACancelar);
	}
	
	
	
	
	
	


}
