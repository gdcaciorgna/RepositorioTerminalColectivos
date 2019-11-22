package controlers;


import data.DataReserva;
import data.DataReservaPlan;
import entities.Plan_Reserva;
import entities.Reserva;
import logic.ReservasPlanesLogic;

public class CancelarReserva {
	
	public void cancelarReserva(Reserva reservaACancelar) 
	{
		DataReserva dRes = new DataReserva();

		
		this.limpiarAsientos(reservaACancelar);
		
		dRes.cancelarReserva(reservaACancelar); //Envio solo la reserva a cancelar. No hace falta el plan


		
	}
	
	public double getImporteADevolver(String fechaHoraReserva, String fechaHoraViaje, String patenteColectivoViaje, int codRutaViaje, String UsernameReserva) 
	
	{
		
		PlanReservaControlers conResPlan = new PlanReservaControlers();
		Plan_Reserva reservaPlan= new Plan_Reserva();
		ReservasPlanesLogic resPlanLog = new ReservasPlanesLogic();
		
		reservaPlan = conResPlan.getReservaPlanbyClavesPrimarias(fechaHoraReserva, fechaHoraViaje, patenteColectivoViaje, codRutaViaje, UsernameReserva);
				
		double importeADevolver = resPlanLog.getImporteADevolver(reservaPlan);
				
		
		return importeADevolver;
		
		
	}
	
	public void limpiarAsientos(Reserva reservaACancelar) 
	{
		DataReserva dreserva = new DataReserva();
		dreserva.limpiarAsientos(reservaACancelar);
		
	}
	
	
	
	
	


}
