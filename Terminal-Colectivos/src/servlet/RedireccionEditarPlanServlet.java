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
 * Servlet implementation class RedireccionEditarPlanServlet
 */
@WebServlet("/RedireccionEditarPlanServlet")
public class RedireccionEditarPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedireccionEditarPlanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion = request.getSession();
		
		String fechaViajeString = request.getParameter("fechaViajeString");
		String horaViajeString = request.getParameter("horaViajeString");
		String codRutaViajeString = request.getParameter("codRutaViajeString");
		String patenteColectivoViajeString = request.getParameter("patenteColectivoViajeString");
		

		
		PlanControlers planCon = new PlanControlers();
		Plan planViejo = new Plan();
		
		planViejo = planCon.getPlanByFechaHoraCodRutaPatente(fechaViajeString, horaViajeString, codRutaViajeString, patenteColectivoViajeString);
		
		sesion.setAttribute("PlanViejo", planViejo);
			
		 request.getRequestDispatcher("/WEB-INF/plandeviaje.jsp").forward(request, response);		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
