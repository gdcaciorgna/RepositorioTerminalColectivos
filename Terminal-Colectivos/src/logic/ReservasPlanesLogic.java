package logic;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;

import entities.*;

public class ReservasPlanesLogic {
	
	
	public ArrayList<Reserva> getReservasxPlanesReservas(ArrayList<Plan_Reserva> planes_reservas)
	{
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		for(Plan_Reserva plan_reserva : planes_reservas) 
		{
			reservas.add(plan_reserva.getReserva());
		}
		
		return reservas;
	}
	
	
	public double getImporteTotal(Plan_Reserva planRes)
	{
		double importeTotal;
		
		importeTotal = planRes.getReserva().getCant_pas() * planRes.getPlan().getPrecio();

		
		return importeTotal;
				
	}
	
	public Date SumaRestarFecha(Date fecha, int sumaresta, String opcion)
    {
        Calendar calendar = Calendar.getInstance();
        try
        {

            calendar.setTime(fecha);
            switch (opcion)
            {
                case "DAYS":
                    calendar.add(Calendar.DAY_OF_WEEK, sumaresta);

                    break;

                case "MONTHS":
                    calendar.add(Calendar.MONTH, sumaresta);

                    break;

                case "YEARS":
                    calendar.add(Calendar.YEAR, sumaresta);

                    break;

                default:
                    System.out.println("parametro enviado al Switch no concuerda");
                    break;
            }
        }
        catch(Exception e)
        {
            System.out.println("Error:\n" + e);
        }
        return calendar.getTime();
    }
	
	
	public double getImporteADevolver(Plan_Reserva planRes) 
	{
		double importeTotal = this.getImporteTotal(planRes);
		
		double importeADevolver = 0;
		
		Date fechaActual = new Date();
		Date fechaLimite = this.SumaRestarFecha(planRes.getPlan().getFechaHora(), -7, "DAYS"); //Devuelve la fecha a partir
		
		if(fechaActual.compareTo(fechaLimite)<0) 
		{	
			importeADevolver = importeTotal;
			
		}
		else 
		{
			importeADevolver = importeTotal * 0.2; 
		}
		
		return importeADevolver;
		
	}

}
