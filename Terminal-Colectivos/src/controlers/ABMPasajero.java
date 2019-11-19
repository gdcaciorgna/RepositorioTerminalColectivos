package controlers;

import java.util.ArrayList;
import java.util.Date;

import data.DataCompaniaTarjeta;
import data.DataEmpresa_Colectivo;
import data.DataPasajero;
import data.DataPasajeroReserva;
import data.DataPlan;
import data.DataReserva;
import data.DataReservaPlan;
import entities.Compania_Tarjeta;
import entities.Empresa_Colectivo;
import entities.Pasajero;
import entities.Pasajero_Reserva;
import entities.Plan;
import entities.Plan_Reserva;
import entities.Reserva;
import entities.Usuario;
import logic.PasajeroLogic;

public class ABMPasajero {
	
	
	

	
	
	public void addPasajero(Pasajero pasajero) 
	{
		PasajeroLogic plog = new PasajeroLogic();
		
		if(plog.validarPasajeroInexistente(pasajero.getDni())) 
		{
			DataPasajero dpas = new DataPasajero();
			dpas.addPasajero(pasajero);

		}
		
				
	}
	
	
	public void addPasajeros(ArrayList<Pasajero> pasajeros) 
	{
		for(Pasajero pas : pasajeros) 
		{
			this.addPasajero(pas);
		}
		
	}
	
	public void registrarReserva(ArrayList<Pasajero> pasajeros, String nroTarjeta, Plan viajeSeleccionado, int cantidadPasajeros, Usuario usuarioActual, int codCompania) 
	{
		Compania_Tarjeta companiaTarjeta = new Compania_Tarjeta();
		DataCompaniaTarjeta dCompaniaTarjeta = new DataCompaniaTarjeta();
		companiaTarjeta = dCompaniaTarjeta.getById(codCompania);
		
		Date fechaRes = new Date();
		
		Reserva reserva = new Reserva();
		reserva.setCant_pas(cantidadPasajeros);
		reserva.setCompania_tarjeta(companiaTarjeta);
		reserva.setFecha_res(fechaRes);
		reserva.setNro_tarjeta(nroTarjeta);
		reserva.setUsuario(usuarioActual);
		
		
		Plan_Reserva planRes = new Plan_Reserva();
		planRes.setPlan(viajeSeleccionado);
		planRes.setReserva(reserva);
		
		
		
		DataReserva dres = new DataReserva();
		
		
		
		this.addPasajeros(pasajeros);
		
		dres.agregarReserva(reserva);
		
		DataReservaPlan reservaPlan = new DataReservaPlan();
		
		reservaPlan.agregarReservaPlan(planRes);
		
		
		Pasajero_Reserva pasajeroReserva = new Pasajero_Reserva();
		
		DataPasajeroReserva dPasRes = new DataPasajeroReserva();
		
		
		for(Pasajero pasajero : pasajeros) 
		{
			
			
			int asiento = dPasRes.getUltimoAsientoxPlan(viajeSeleccionado);
			
			pasajeroReserva.setAsiento(asiento);
			pasajeroReserva.setPasajero(pasajero);
			pasajeroReserva.setReserva(reserva);

			
			
			dPasRes.agregarPasajeroReserva(pasajeroReserva);
			
			
			
		}
		
		
		
		
		
		
		
		
	}
	
	

}
