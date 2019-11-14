package controlers;





import java.util.Date;

import data.DataCompaniaTarjeta;
import data.DataReserva;
import data.DataReservaPlan;
import entities.Plan;
import entities.Reserva;


public class ABReserva {
	
	
	

	
	public void setReserva(Reserva reserva, String compania, Plan planSelec) 
	{
		
	
		
		
	
			
		DataCompaniaTarjeta dcomp=new DataCompaniaTarjeta();
		int codigo= dcomp.getCodigo(compania);
		DataReserva dres= new DataReserva();
		
		FechaControlers fec =new FechaControlers();
		Date fechaHoraActual= new Date();
		Date fechaHoraPlan= planSelec.getFechaHora();
		DataReservaPlan drplan= new DataReservaPlan();
		dres.agregarReserva(reserva, codigo, fechaHoraActual);
		drplan.agregarReserva(reserva, planSelec , fechaHoraActual,fechaHoraPlan );
		
	
		
	}
	

	
	
	
	
	
	
	


}
