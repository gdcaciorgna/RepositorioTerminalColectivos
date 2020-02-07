package controlers;

import java.util.ArrayList;
import java.util.Date;

import data.DataPasajeroReserva;
import data.DataPlan;
import data.DataReserva;
import data.DataReservaPlan;
import entities.Pasajero;
import entities.Plan;
import entities.Plan_Reserva;
import entities.Reserva;
import entities.Usuario;
import logic.ReservasPlanesLogic;
import util.AppDataException;

public class PlanReservaControlers {
	
	
	public Plan_Reserva getReservaPlanbyClavesPrimarias(String fechaHoraReserva, String fechaHoraViaje, String patenteColectivoViaje, int codRutaViaje, String UsernameReserva) throws AppDataException 
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
	
	public ArrayList<Plan_Reserva> getReservasxUsuario(Usuario usuario) throws AppDataException
	{
		
	    DataReservaPlan dres = new DataReservaPlan();
	    
	    return dres.getReservasxUsuario(usuario);

	}
	
	public ArrayList<Plan_Reserva> getReservasxPlan(Plan planElegido) throws AppDataException
	{
		DataReservaPlan dResPlan = new DataReservaPlan();
		return dResPlan.getReservasPlan(planElegido);
		
	}
	
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
