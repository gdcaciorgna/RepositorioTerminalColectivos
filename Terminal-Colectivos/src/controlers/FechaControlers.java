package controlers;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import util.AppDataException;



public class FechaControlers {
	
	
	
	
	public String unirFechaHora(String fecha, String hora) 
	{
		String fechaHora = fecha + " " + hora;
		return fechaHora;

	}
	
	
	
	//INICIO - STRING TO DATE
	
	public Date ddMMyyyyHHmmToDate(String fechaHoraString) //throws? 
	{
		Date fechaDate = new Date();
		try {
			   
			SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm");  	

			fechaDate = formatter.parse(fechaHoraString);
			
			return fechaDate;

		} 
		
		catch (ParseException e) {
			// TODO Auto-generated catch block
			
			return null;
			
			//throw new AppDataException(e, "Error al convertir fecha");
		}
		
		
		
		
		
	}
	
	
	public Date ddMMyyyyHHmmssToDate(String fechaHoraString) 
	{
		Date fechaDate = new Date();
		try {
			   
			SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  	

			fechaDate = formatter.parse(fechaHoraString);
			
			return fechaDate;

		} 
		
		catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
		
		
		
		
	}
	
	
	public Date yyyyMMddToDate(String yyyymmdd)
	{	
		Date fechaDate = new Date();
		try {
			   
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy/MM/dd");  	

			fechaDate = formatter.parse(yyyymmdd);
			
			return fechaDate;

		} 
		
		catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
	}
	
	public Date yyyyMMddhhmmToDate(String fechaHoraString)
	{
		SimpleDateFormat formatoPlanNuevo = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date fechaHoraPlanNuevo = new Date();
		
		try {
			   
			fechaHoraPlanNuevo = formatoPlanNuevo.parse(fechaHoraString);
			return fechaHoraPlanNuevo;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
		
	}
	
	public Date fechaConGuion(String fechaHoraString)
	{
		SimpleDateFormat formatoPlanNuevo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
		Date fechaHoraPlanNuevo = null;
		
		try {
			   
			fechaHoraPlanNuevo = formatoPlanNuevo.parse(fechaHoraString);
			
			return fechaHoraPlanNuevo;
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
		
	}
	
	
	
	//FIN - STRING TO DATE

	
	
	//INICIO - DATE TO STRING
	
	
	public String dateToyyyyMMdd(Date fecha) 
	{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");  
        String fechaHoraString = dateFormat.format(fecha);  
		
		return fechaHoraString;
	}
	
	public String dateToHHmmss(Date fecha) 
	{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");  
        String fechaHoraString = dateFormat.format(fecha);  
		
		return fechaHoraString;
	}
	
	
	public String dateToddMMyyyyhhmm(Date fecha) 
	{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");  
        String fechaHoraString = dateFormat.format(fecha);  
		
		return fechaHoraString;
	}
	
	public String dateToddMMyyyyhhmmss(Date fecha) 
	{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        String fechaHoraString = dateFormat.format(fecha);  
		
		return fechaHoraString;
	}
	
	public String dateTohhmm(Date fecha) 
	{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");  
        String fechaHoraString = dateFormat.format(fecha);  
		
		return fechaHoraString;
	}
	
	public String dateToyyyyMMddHHmmss(Date fecha) 
	{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
        String fechaHoraString = dateFormat.format(fecha);  
		
		return fechaHoraString;
	}
	
	public String dateToddMMyyyy(Date fecha) 
	{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        String fechaHoraString = dateFormat.format(fecha);  
		
		return fechaHoraString;
	}
	
	
	//FIN - DATE TO STRING


}
