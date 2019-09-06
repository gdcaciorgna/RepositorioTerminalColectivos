package controlers;

import java.util.Date;

import data.DataColectivo;
import data.DataPlan;
import data.DataRuta;
import data.DataUsuario;
import entities.Colectivo;
import entities.Plan;
import entities.Ruta;
import entities.Usuario;
import logic.PlanLogic;

public class PlanControlers {
	
	
	public int editarPlan(Plan planViejo, Plan planNuevo) 
	{
		
		int planesEditados = 0;
			
		DataPlan dplan = new DataPlan();			

		planesEditados = dplan.editarPlan(planViejo, planNuevo);
		
		return planesEditados;
		
		
	}
	
	public String getMensajeRegistro(String origenPlan,String destinoPlan,String fechaStringPlan,String horaStringPlan,String precioStringPlan,String usuarioChoferPlan,String patenteColectivoPlan)
	{
		String localidadPrincipal = "ROSARIO"; //Localidad principal de la terminal
		
		String mensajeRegistro = "OK";
		
		DataPlan dplan = new DataPlan();
		Ruta rutNuevo = new Ruta();
		
		FechaControlers fechaCon = new FechaControlers();
		PlanLogic planL = new PlanLogic();
		
		String fechaHoraStringPlanNuevo =  fechaCon.unirFechaHora(fechaStringPlan, horaStringPlan);
		Date fechaHoraPlanNuevo = fechaCon.yyyyMMddhhmmToDate(fechaHoraStringPlanNuevo);
	
						
		if(origenPlan.equals("Origen") || destinoPlan.equals("Destino") || fechaStringPlan=="" || horaStringPlan=="" || precioStringPlan=="" || patenteColectivoPlan.equals("Patente") || usuarioChoferPlan.equals("Chofer")) 
		{
			mensajeRegistro = "Error1"; //Se deben completar todos los campos
		}
		
		else if(! origenPlan.equals(localidadPrincipal) && !destinoPlan.equals(localidadPrincipal)) 
		{
		
			mensajeRegistro = "Error2"; //El origen o el destino debe ser Rosario
		}	
		
		else if(origenPlan.equals(destinoPlan)) 
		{
			mensajeRegistro = "Error3"; //El Origen y el destino no pueden ser iguales 

		}
		
		
		else if(rutNuevo.getCod_ruta()==0) 
		{
			mensajeRegistro = "Error4"; //RUTA NO ENCONTRADA PARA ORIGEN Y DESTINO 

		}
		
		else if(planL.validarDouble(precioStringPlan)==false) 
		{
			mensajeRegistro = "Error5"; // PRECIO NO ES UN VALOR NUMERICO 

		}
		
		else if(!dplan.validarPlanSinExistencia(fechaHoraPlanNuevo, rutNuevo.getCod_ruta(), patenteColectivoPlan)) 
		{
			mensajeRegistro = "Error6"; // YA EXISTE UN PLAN PARA LA FECHA, HORA, PATENTE Y RUTA DESIGNADA

		}
		
		return mensajeRegistro;
	

	}
	
	public Plan getPlan(String origenPlan,String destinoPlan,String fechaStringPlan,String horaStringPlan,String precioStringPlan,String usuarioChoferPlan,String patenteColectivoPlan) 
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

	public int eliminarPlan(String fechaString, String horaString, String codRutaViajeString, String patenteColectivoViaje) 
	{
		Plan plan = new Plan();
		DataPlan dplan = new DataPlan();
		
		int planesEliminados = 0;
		
		FechaControlers fCon = new FechaControlers();
		
		String fechaHoraString = fCon.unirFechaHora(fechaString, horaString);
		 
		Date fechaHora = fCon.ddMMyyyyHHmmToDate(fechaHoraString);
        
		int cod_ruta = Integer.parseInt(codRutaViajeString);
		
		plan = dplan.getByFechaHoraRutaPatente(fechaHora, cod_ruta, patenteColectivoViaje);
		
		planesEliminados = dplan.eliminarPlan(plan);
		
		return planesEliminados;
	}
	
	public Plan getPlanByFechaHoraCodRutaPatente(String fechaViajeString, String horaViajeString, String codRutaViajeString, String patenteColectivoViajeString)
	{
		DataPlan dplan =  new DataPlan();
		
		String fechaHoraViajeString = fechaViajeString + " " + horaViajeString;
		
	    FechaControlers fechaCon = new FechaControlers();
	    
	    Date fechaHoraViaje = fechaCon.ddMMyyyyHHmmToDate(fechaHoraViajeString);
	    
		int codRutaViaje = Integer.parseInt(codRutaViajeString);
		
		Plan planViejo = dplan.getByFechaHoraRutaPatente(fechaHoraViaje, codRutaViaje, patenteColectivoViajeString);
		
		return planViejo;
	}
	
	public int agregarPlan(Plan planNuevo) 
	{
		int planesAgregados = 0;
		
		DataPlan dplan = new DataPlan();	
		
		planesAgregados = dplan.addPlan(planNuevo);
		
		return planesAgregados;
	}
	
}
