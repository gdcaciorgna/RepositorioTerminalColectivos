package controlers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import data.DataColectivo;
import data.DataPlan;
import data.DataReservaPlan;
import data.DataRuta;
import data.DataUsuario;
import entities.Colectivo;
import entities.Plan;
import entities.Plan_Reserva;
import entities.Ruta;
import entities.Usuario;
import logic.PlanLogic;
import util.AppDataException;
import util.AppLogicException;

public class PlanControlers {
	
	
	public int editarPlan(Plan planViejo, Plan planNuevo) throws AppDataException 
	{
		
		int planesEditados = 0;
			
		DataPlan dplan = new DataPlan();			
		planesEditados = dplan.editarPlan(planViejo, planNuevo);
	
		return planesEditados;
	
		
	}
	
	public void validarRegistrarPlan(String origenPlan,String destinoPlan,String fechaStringPlan,String horaStringPlan,String precioStringPlan,String usuarioChoferPlan,String patenteColectivoPlan) throws AppLogicException, AppDataException 
	{
		String localidadPrincipal = "VENADO TUERTO"; //Localidad principal de la terminal
		
		
		DataPlan dplan = new DataPlan();
		Ruta rutNuevo = new Ruta();
		
		DataRuta drut = new DataRuta();
		
			
		rutNuevo = drut.getByOrigenDestino(origenPlan, destinoPlan);
		
		FechaControlers fechaCon = new FechaControlers();
		PlanLogic planL = new PlanLogic();
		
		String fechaHoraStringPlanNuevo =  fechaCon.unirFechaHora(fechaStringPlan, horaStringPlan);
		Date fechaHoraPlanNuevo = fechaCon.yyyyMMddhhmmToDate(fechaHoraStringPlanNuevo);
					
		
		if(origenPlan.equals("Origen") || destinoPlan.equals("Destino") || fechaStringPlan=="" || horaStringPlan=="" || precioStringPlan=="" || patenteColectivoPlan.equals("Patente") || usuarioChoferPlan.equals("Chofer")) 
		{
			throw new AppLogicException("Se deben completar todos los campos.");
		}
		
		else if(! origenPlan.equals(localidadPrincipal) && !destinoPlan.equals(localidadPrincipal)) 
		{
			throw new AppLogicException("El origen o el destino debe ser Venado Tuerto.");

		}	
		
		else if(origenPlan.equals(destinoPlan)) 
		{
			throw new AppLogicException("El Origen y el destino no pueden ser iguales.");
		}
		
		
		else if(rutNuevo.getCod_ruta()==0) 
		{
			throw new AppLogicException("Ruta no encontrada para origen y destino seleccionados.");

		}
		
		else if(planL.validarDouble(precioStringPlan)==false) 
		{
			throw new AppLogicException("El precio no es un valor numérico.");
		}
		
		else if(!dplan.validarPlanSinExistencia(fechaHoraPlanNuevo, rutNuevo.getCod_ruta(), patenteColectivoPlan)) 
		{
			throw new AppLogicException("Ya existe un plan para la fecha, hora, patente y ruta designada");
		}
				

	

	}
	
	public Plan getPlan(String origenPlan,String destinoPlan,String fechaStringPlan,String horaStringPlan,String precioStringPlan,String usuarioChoferPlan,String patenteColectivoPlan) throws AppDataException, SQLException 
	{
	
		Ruta rut = new Ruta();
		Plan plan = new Plan();
		Colectivo cole = new Colectivo();
		Usuario chofer = new Usuario();
		
		DataRuta drut = new DataRuta();
		DataColectivo dcol = new DataColectivo();
		DataUsuario dchof = new DataUsuario();
		
		FechaControlers fechaCon = new FechaControlers();
		
		rut = drut.getByOrigenDestino(origenPlan, destinoPlan);
		
		String fechaHoraStringPlan = fechaCon.unirFechaHora(fechaStringPlan, horaStringPlan);

		Date fechaHoraPlan = fechaCon.yyyyMMddhhmmToDate(fechaHoraStringPlan);
		


		plan.setFechaHora(fechaHoraPlan); 
		plan.setOrigen(origenPlan); 
		plan.setDestino(destinoPlan);
		plan.setPrecio(Double.parseDouble(precioStringPlan));
		
		rut = drut.getByOrigenDestino(origenPlan, destinoPlan);			
		cole = dcol.getByPatente(patenteColectivoPlan);
		chofer= dchof.getByUsername(usuarioChoferPlan);
		
		plan.setRuta(rut);
		plan.setColectivo(cole);
		plan.setChofer(chofer);
		
		return plan;

	}

	public void eliminarPlan(String fechaString, String horaString, String codRutaViajeString, String patenteColectivoViaje) throws AppDataException 
	{
		Plan plan = new Plan();
		DataPlan dplan = new DataPlan();
				
		FechaControlers fCon = new FechaControlers();
		
		String fechaHoraString = fCon.unirFechaHora(fechaString, horaString);
		 
		Date fechaHora = fCon.ddMMyyyyHHmmToDate(fechaHoraString);
        
		int cod_ruta = Integer.parseInt(codRutaViajeString);
		
		plan = dplan.getByFechaHoraRutaPatente(fechaHora, cod_ruta, patenteColectivoViaje);
		
		dplan.eliminarPlan(plan);
		
	}
	
	public Plan getPlanByFechaHoraCodRutaPatente(String fechaViajeString, String horaViajeString, String codRutaViajeString, String patenteColectivoViajeString) throws AppDataException
	{
		DataPlan dplan =  new DataPlan();
		
		String fechaHoraViajeString = fechaViajeString + " " + horaViajeString;
		
	    FechaControlers fechaCon = new FechaControlers();
	    
	    Date fechaHoraViaje = fechaCon.ddMMyyyyHHmmToDate(fechaHoraViajeString);
	    
		int codRutaViaje = Integer.parseInt(codRutaViajeString);
		
		Plan planViejo = dplan.getByFechaHoraRutaPatente(fechaHoraViaje, codRutaViaje, patenteColectivoViajeString);
		
		return planViejo;
	}
	
	public int agregarPlan(Plan planNuevo) throws AppDataException 
	{
		int planesAgregados = 0;
		
		DataPlan dplan = new DataPlan();	
		
		planesAgregados = dplan.addPlan(planNuevo);
		
		return planesAgregados;
	}
	
	public ArrayList<Plan_Reserva> getViajesxChofer(Usuario chofer) throws AppDataException
	{
		DataReservaPlan  dplan_reserva = new DataReservaPlan();
		return dplan_reserva.getViajesxChofer(chofer);
		
		
	}
	
}
