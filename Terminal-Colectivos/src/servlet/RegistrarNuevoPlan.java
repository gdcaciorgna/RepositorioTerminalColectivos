package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controlers.PlanControlers;
import entities.Plan;

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

		String origenViaje = request.getParameter("origenViaje");
		String destinoViaje = request.getParameter("destinoViaje");
		String fechaString = request.getParameter("fechaString");
		String horaString = request.getParameter("horaString");
		String precioString = request.getParameter("precioString");
		String usuarioChofer = request.getParameter("usuarioChoferViaje");
		String patente = request.getParameter("patenteColectivoViaje");
     		
		
		sesion.setAttribute("origenViaje", origenViaje);
		sesion.setAttribute("destinoViaje", destinoViaje);
		sesion.setAttribute("precioString", precioString);
		sesion.setAttribute("usuarioChoferViaje", usuarioChofer);
		sesion.setAttribute("patenteColectivoViaje", patente);

		
		PlanControlers planCon = new PlanControlers();
		Plan planNuevo = new Plan();
		
		
		try {
			
		
		planCon.validarRegistrarPlan(origenViaje, destinoViaje, fechaString, horaString, precioString, usuarioChofer, patente);
		
		planNuevo = planCon.getPlan(origenViaje, destinoViaje, fechaString, horaString, precioString, usuarioChofer, patente);
	
		planCon.agregarPlan(planNuevo);
		
				
		sesion.setAttribute("mensajeExito", "Se ha registrado el plan de viaje de manera satisfactoria");
		request.getRequestDispatcher("/WEB-INF/buscarviajesadmin.jsp").forward(request, response);


		
		}
		catch(Exception e) 
		{
			sesion.setAttribute("mensajeError", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/registrarNuevoPlan.jsp").forward(request, response);
		}


		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
