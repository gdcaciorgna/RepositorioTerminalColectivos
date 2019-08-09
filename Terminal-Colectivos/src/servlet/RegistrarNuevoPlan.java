package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DataColectivo;
import data.DataPlan;
import data.DataRuta;
import data.DataUsuario;
import entities.Usuario;
import logic.PlanLogic;
import entities.Plan;
import entities.Ruta;
import entities.Colectivo;

/**
 * Servlet implementation class RegistrarNuevoPlan
 */
@WebServlet("/RegistrarNuevoPlan")
public class RegistrarNuevoPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarNuevoPlan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		HttpSession sesion = request.getSession();
		String localidadPrincipal = "ROSARIO";

		String origenViaje = request.getParameter("origenViaje");
		String destinoViaje = request.getParameter("destinoViaje");
		String fechaString = request.getParameter("fechaString");
		String horaString = request.getParameter("horaString");
		String precioString = request.getParameter("precioString");
		String usuarioChofer = request.getParameter("usuarioChoferViaje");
		String patente = request.getParameter("patenteColectivoViaje");


		String fechaHoraString = fechaString + " " + horaString;
		
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
       		
		
		sesion.setAttribute("origenViaje", origenViaje);
		sesion.setAttribute("destinoViaje", destinoViaje);
		sesion.setAttribute("precioString", precioString);
		sesion.setAttribute("usuarioChoferViaje", usuarioChofer);
		sesion.setAttribute("patenteColectivoViaje", patente);


		DataRuta drut = new DataRuta();
		DataPlan dplan = new DataPlan();
		DataColectivo dcol = new DataColectivo();
		DataUsuario dchof = new DataUsuario();
		
		Plan nuevoPlan = new Plan();
		Ruta rut = new Ruta();
		Colectivo cole = new Colectivo();
		Usuario chofer = new Usuario();
		
		PlanLogic planl = new PlanLogic();
		
		rut = drut.getByOrigenDestino(origenViaje, destinoViaje);
		
		precioString = planl.cambiarSeparador(precioString);
		
		
		if(origenViaje.equals("Origen") || destinoViaje.equals("Destino") || fechaString=="" || horaString=="" || patente.equals("Patente") || usuarioChofer.equals("Chofer")) 
		{
			
			sesion.setAttribute("mensajeRegistro", "Error1"); //Se deben completar todos los campos
		}
		
		else if(!origenViaje.equals(localidadPrincipal) && !destinoViaje.equals(localidadPrincipal)) 
		{
		
			sesion.setAttribute("mensajeRegistro", "Error2"); //El origen o el destino debe ser Rosario
		}	
		
		else if(origenViaje.equals(destinoViaje)) 
		{
			sesion.setAttribute("mensajeRegistro", "Error3"); //El Origen y el destino no pueden ser iguales 

		}
		
		
		else if(rut.getCod_ruta()==0) 
		{
			sesion.setAttribute("mensajeRegistro", "Error4"); //RUTA NO ENCONTRADA PARA ORIGEN Y DESTINO 

		}
		
		else if(planl.validarDouble(precioString)==false) 
		{
			sesion.setAttribute("mensajeRegistro", "Error5"); // PRECIO NO ES UN VALOR NUMERICO 

		}
		
		else 
		{
			

			
			Date fechaDate = new Date();
			
			try {
			   
				fechaDate = formato.parse(fechaHoraString);
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			nuevoPlan.setFechaHora(fechaDate); 
			
			nuevoPlan.setOrigen(origenViaje); 
			nuevoPlan.setDestino(destinoViaje);
			nuevoPlan.setPrecio(Double.parseDouble(precioString));
			
			rut= drut.getByOrigenDestino(origenViaje, destinoViaje);
			cole = dcol.getByPatente(patente);
			chofer= dchof.getByUsuario(usuarioChofer);
			nuevoPlan.setRuta(rut);
			nuevoPlan.setColectivo(cole);
			nuevoPlan.setChofer(chofer);
			dplan.addPlan(nuevoPlan);
			
			sesion.setAttribute("mensajeRegistro", "OK"); //Se ha registrado exitosamente el nuevo Plan! - Se puede obviar esta linea
			
			
			//INICIO - LIMPIAR CAMPOS
			sesion.setAttribute("origenViaje", null);
			sesion.setAttribute("destinoViaje", null);
			sesion.setAttribute("precioString", null);
			sesion.setAttribute("usuarioChoferViaje", null);
			sesion.setAttribute("patenteColectivoViaje", null);
			sesion.setAttribute("fechaString", null);
			sesion.setAttribute("horaString", null);
			//FIN - LIMPIAR CAMPOS
			
		}
		
		
		 response.sendRedirect("registrarNuevoPlan.jsp");

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
