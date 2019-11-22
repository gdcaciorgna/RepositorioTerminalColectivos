package controlers;

import java.util.Date;

import data.DataPlan;
import data.DataReserva;
import entities.Plan;
import entities.Plan_Reserva;
import entities.Reserva;

public class PlanReservaControlers {
	
	
	public Plan_Reserva getReservaPlanbyClavesPrimarias(String fechaHoraReserva, String fechaHoraViaje, String patenteColectivoViaje, int codRutaViaje, String UsernameReserva) 
	{
	Reserva reserva = new Reserva();
	DataReserva dres = new DataReserva();
	DataPlan dplan = new DataPlan();
	Plan plan = new Plan();
	
	FechaControlers fCon = new FechaControlers();
	
	Date fechaHoraReservaDate = fCon.ddMMyyyyHHmmssToDate(fechaHoraReserva);
	
	Date fechaHoraViajeDate = fCon.ddMMyyyyHHmmToDate(fechaHoraViaje);
	
	reserva = dres.getByFechaUsuario(fechaHoraReservaDate, UsernameReserva);
	
	plan = dplan.getByFechaHoraRutaPatente(fechaHoraViajeDate, codRutaViaje, patenteColectivoViaje);
	
	
	Plan_Reserva planReserva = new Plan_Reserva();
	planReserva.setPlan(plan);
	planReserva.setReserva(reserva);
	
	
	
	return planReserva;
	
	
	}
	



}
