package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controlers.PlanControlers;

/**
 * Servlet implementation class EliminarPlanServlet
 */
@WebServlet("/EliminarPlanServlet")
public class EliminarPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarPlanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		

	HttpSession sesion = request.getSession();
		
	
		String fechaString = request.getParameter("fechaViajeString");
		String horaString = request.getParameter("horaViajeString");		
		String codRutaViajeString = request.getParameter("codRutaViajeString");
		String patenteColectivoViaje = request.getParameter("patenteColectivoViajeString");
		
		int planesEliminados = 0;
		
		PlanControlers planCon = new PlanControlers();
		planesEliminados = planCon.eliminarPlan(fechaString, horaString, codRutaViajeString, patenteColectivoViaje);

	
		sesion.setAttribute("planesEliminados", planesEliminados); 
		sesion.setAttribute("planesEditados", null);
		response.sendRedirect("buscarviajesadmin.jsp");	

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
