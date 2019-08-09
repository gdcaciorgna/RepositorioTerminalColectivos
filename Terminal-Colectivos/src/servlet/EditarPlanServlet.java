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
import entities.Colectivo;
import entities.Plan;
import entities.Ruta;
import entities.Usuario;
import logic.PlanLogic;

/**
 * Servlet implementation class EditarPlanServlet
 */
@WebServlet("/EditarPlanServlet")
public class EditarPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarPlanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		HttpSession sesion = request.getSession();
		
		String localidadPrincipal = "ROSARIO";

		String fechaStringPlanViejo = request.getParameter("fechaStringPlanViejo");
		String horaStringPlanViejo = request.getParameter("horaStringPlanViejo");
		String origenPlanViejo = request.getParameter("origenPlanViejo");
		String destinoPlanViejo = request.getParameter("destinoPlanViejo");
		String patenteColectivoPlanViejo = request.getParameter("patenteColectivoPlanViejo");
		
		
		Plan planViejo = new Plan();
		

		

		
		String origenPlanNuevo = request.getParameter("origenPlanNuevo");
		String destinoPlanNuevo = request.getParameter("destinoPlanNuevo");
		String fechaStringPlanNuevo = request.getParameter("fechaStringPlanNuevo");
		String horaStringPlanNuevo = request.getParameter("horaStringPlanNuevo");
		String precioStringPlanNuevo = request.getParameter("precioStringPlanNuevo");
		String usuarioChoferPlanNuevo = request.getParameter("usuarioChoferPlanNuevo");
		String patenteColectivoPlanNuevo = request.getParameter("patenteColectivoPlanNuevo");
		
		
		String fechaHoraStringPlanViejo = fechaStringPlanViejo + " " + horaStringPlanViejo;
		String fechaHoraStringPlanNuevo = fechaStringPlanNuevo + " " + horaStringPlanNuevo;
        
        SimpleDateFormat formatoPlanViejo = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		SimpleDateFormat formatoPlanNuevo = new SimpleDateFormat("yyyy/MM/dd HH:mm");

	
     

		DataRuta drut = new DataRuta();
		DataPlan dplan = new DataPlan();
		DataColectivo dcol = new DataColectivo();
		DataUsuario dchof = new DataUsuario();
		
		
		Plan planNuevo = new Plan();
		
		Ruta rutNuevo = new Ruta();
		Ruta rutViejo = new Ruta();

		Colectivo coleNuevo = new Colectivo();
		Usuario choferNuevo = new Usuario();
		
		PlanLogic planl = new PlanLogic();
		
		rutNuevo = drut.getByOrigenDestino(origenPlanNuevo, destinoPlanNuevo);
		
		precioStringPlanNuevo = planl.cambiarSeparador(precioStringPlanNuevo);
		
		Date fechaHoraPlanNuevo = new Date();
        Date fechaHoraPlanViejo = new Date();

		
		try {
		   
			fechaHoraPlanNuevo = formatoPlanNuevo.parse(fechaHoraStringPlanNuevo);
	        fechaHoraPlanViejo = formatoPlanViejo.parse(fechaHoraStringPlanViejo);

			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(origenPlanNuevo.equals("Origen") || destinoPlanNuevo.equals("Destino") || fechaStringPlanNuevo=="" || horaStringPlanNuevo=="" || patenteColectivoPlanNuevo.equals("Patente") || usuarioChoferPlanNuevo.equals("Chofer")) 
		{
			
			sesion.setAttribute("mensajeRegistro", "Error1"); //Se deben completar todos los campos
		}
		
		else if(!origenPlanNuevo.equals(localidadPrincipal) && !destinoPlanNuevo.equals(localidadPrincipal)) 
		{
		
			sesion.setAttribute("mensajeRegistro", "Error2"); //El origen o el destino debe ser Rosario
		}	
		
		else if(origenPlanNuevo.equals(destinoPlanNuevo)) 
		{
			sesion.setAttribute("mensajeRegistro", "Error3"); //El Origen y el destino no pueden ser iguales 

		}
		
		
		else if(rutNuevo.getCod_ruta()==0) 
		{
			sesion.setAttribute("mensajeRegistro", "Error4"); //RUTA NO ENCONTRADA PARA ORIGEN Y DESTINO 

		}
		
		else if(planl.validarDouble(precioStringPlanNuevo)==false) 
		{
			sesion.setAttribute("mensajeRegistro", "Error5"); // PRECIO NO ES UN VALOR NUMERICO 

		}
		
		else if(!dplan.validarPlanSinExistencia(fechaHoraPlanNuevo, rutNuevo.getCod_ruta(), patenteColectivoPlanNuevo)) 
		{
			sesion.setAttribute("mensajeRegistro", "Error6"); // YA EXISTE UN PLAN PARA LA FECHA, HORA, PATENTE Y RUTA DESIGNADA

		}
		
		
		
		else 
		{
			

			
			
			
			rutViejo = drut.getByOrigenDestino(origenPlanViejo, destinoPlanViejo);
			planViejo = dplan.getByFechaHoraRutaPatente(fechaHoraPlanViejo, rutViejo.getCod_ruta(), patenteColectivoPlanViejo);
			
			
			planNuevo.setFechaHora(fechaHoraPlanNuevo); 
			
			planNuevo.setOrigen(origenPlanNuevo); 
			planNuevo.setDestino(destinoPlanNuevo);
			planNuevo.setPrecio(Double.parseDouble(precioStringPlanNuevo));
			
			rutNuevo = drut.getByOrigenDestino(origenPlanNuevo, destinoPlanNuevo);			
			coleNuevo = dcol.getByPatente(patenteColectivoPlanNuevo);
			choferNuevo= dchof.getByUsuario(usuarioChoferPlanNuevo);
			
			planNuevo.setRuta(rutNuevo);
			planNuevo.setColectivo(coleNuevo);
			planNuevo.setChofer(choferNuevo);
						
	
			int planesModificados = dplan.editarPlan(planViejo, planNuevo);
			
			sesion.setAttribute("mensajeRegistro", "OK"); //Se ha registrado exitosamente el nuevo Plan! - Se puede obviar esta linea
			sesion.setAttribute("planesModificados", planesModificados); //Se ha registrado exitosamente el nuevo Plan! - Se puede obviar esta linea

			
		}
		
		
		 response.sendRedirect("plandeviaje.jsp");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
