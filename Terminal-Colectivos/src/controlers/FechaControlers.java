package controlers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class FechaControlers {
	
	public String fechaActual(Calendar fecha) {
	int anio=(fecha.get(Calendar.YEAR)) ;
	int mes=(fecha.get(Calendar.MONTH)) ;
	mes++;
	String mesPosta;
	String diaPosta;
	String segundoPosta;
	String minutoPosta;
	String horaPosta;
	int dia=(fecha.get(Calendar.DAY_OF_MONTH)) ;
	int hora=(fecha.get(Calendar.HOUR)) ;
	int minuto=(fecha.get(Calendar.MINUTE)) ;
	int seg=(fecha.get(Calendar.SECOND)) ;
	if (mes<10) {
		 mesPosta= ("0"+Integer.toString(mes));
	}
	else {
		mesPosta= (Integer.toString(mes));}
	if (dia<10) {
		diaPosta= ("0"+Integer.toString(dia));
	}
	else {
		diaPosta= (Integer.toString(dia));}
	if (hora<10) {
		horaPosta= ("0"+Integer.toString(hora));
	}
	else {
		horaPosta= (Integer.toString(hora));}
	if (minuto<10) {
		minutoPosta= ("0"+Integer.toString(minuto));
	}
	else {
		minutoPosta= (Integer.toString(minuto));}
	if (seg<10) {
		segundoPosta= ("0"+Integer.toString(seg));
	}
	else {
		segundoPosta= (Integer.toString(seg));}
	
	
	
	String fechaHoraString= (Integer.toString(anio)+":"+mesPosta+":"+diaPosta+" "+horaPosta+":"+minutoPosta+":"+segundoPosta);
	
	
	return fechaHoraString;
		
		
	}
	
	
	public String unirFechaHora(String fecha, String hora) 
	{
		String fechaHora = fecha + " " + hora;
		return fechaHora;

	}
	
	
	
	//INICIO - STRING TO DATE
	
	public Date ddMMyyyyHHmmToDate(String fechaHoraString) 
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
	
	//FIN - STRING TO DATE

	
	
	//INICIO - DATE TO STRING
	
	
	public String dateToyyyyMMdd(Date fecha) 
	{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");  
        String fechaHoraString = dateFormat.format(fecha);  
		
		return fechaHoraString;
	}
	
	public String dateToddMMyyyyhhmm(Date fecha) 
	{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");  
        String fechaHoraString = dateFormat.format(fecha);  
		
		return fechaHoraString;
	}
	
	public String dateTohhmm(Date fecha) 
	{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");  
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
