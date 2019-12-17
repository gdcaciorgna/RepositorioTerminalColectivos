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
 * Servlet implementation class RedireccionReservarViaje
 */
@WebServlet("/RedireccionReservarViaje")
public class RedireccionReservarViaje extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedireccionReservarViaje() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion= request.getSession();
		String fechaViajeString = request.getParameter("fechaViajeString");
		String horaViajeString = request.getParameter("horaViajeString");
		String codRutaViajeString = request.getParameter("codRutaViajeString");
		String patenteColectivoViajeString = request.getParameter("patenteColectivoViajeString");
		

		
		PlanControlers planCon = new PlanControlers();
		Plan viajeSeleccionado = new Plan();
		
		try 
		{
			
			viajeSeleccionado = planCon.getPlanByFechaHoraCodRutaPatente(fechaViajeString, horaViajeString, codRutaViajeString, patenteColectivoViajeString);
		
		} catch (Exception e) 
		{
			sesion.setAttribute("mensajeError", e.getMessage());
		}
		
		sesion.setAttribute("ViajeSeleccionado", viajeSeleccionado);
			
		request.getRequestDispatcher("/WEB-INF/reservarviaje.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
