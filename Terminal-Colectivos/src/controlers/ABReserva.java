package controlers;



import java.util.Calendar;

import data.DataCompaniaTarjeta;
import data.DataReserva;

import entities.Reserva;


public class ABReserva {
	
	
	

	
	public void setReserva(Reserva reserva, String compania) 
	{
		
		
		
		
		
		if( (reserva.getNro_tarjeta().length())== 16) {
			
		DataCompaniaTarjeta dcomp=new DataCompaniaTarjeta();
		int codigo= dcomp.getCodigo(compania);
		DataReserva dres= new DataReserva();
		Calendar fecha= Calendar.getInstance();
		FechaControlers fec =new FechaControlers();
		String fechaHoraString= fec.fechaActual(fecha);
		
		dres.agregarReserva(reserva, codigo , fechaHoraString);
		}
		
	
		
	}
	

	
	
	
	
	
	
	


}
