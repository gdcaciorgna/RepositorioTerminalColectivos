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


		HttpSession sesion = request.getSession();
		
		sesion.setAttribute("mensajeRegistro", null);
		
		

		
		Plan planViejo = (Plan) sesion.getAttribute("PlanViejo"); 
		
		String origenPlanNuevo = request.getParameter("origenPlanNuevo");
		String destinoPlanNuevo = request.getParameter("destinoPlanNuevo");
		String fechaStringPlanNuevo = request.getParameter("fechaStringPlanNuevo");
		String horaStringPlanNuevo = request.getParameter("horaStringPlanNuevo");
		String precioStringPlanNuevo = request.getParameter("precioStringPlanNuevo");
		String usuarioChoferPlanNuevo = request.getParameter("usuarioChoferPlanNuevo");
		String patenteColectivoPlanNuevo = request.getParameter("patenteColectivoPlanNuevo");
		
		
		PlanControlers planCon = new PlanControlers();
		Plan planNuevo = new Plan();
		
		int planesEditados = 0;
		
		try 
		{
			
		planCon.validarRegistrarPlan(origenPlanNuevo, destinoPlanNuevo, fechaStringPlanNuevo, horaStringPlanNuevo, precioStringPlanNuevo, usuarioChoferPlanNuevo, patenteColectivoPlanNuevo);
		
		planNuevo = planCon.getPlan(origenPlanNuevo, destinoPlanNuevo, fechaStringPlanNuevo, horaStringPlanNuevo, precioStringPlanNuevo, usuarioChoferPlanNuevo, patenteColectivoPlanNuevo);
	
		planesEditados = planCon.editarPlan(planViejo, planNuevo);
		
		sesion.setAttribute("mensajeExito", "Las modificaciones se han guardado de manera satisfactoria"); 

		
		}
		catch(Exception e) 
		{
			sesion.setAttribute("mensajeError", e.getMessage()); 
		}

		sesion.setAttribute("PlanViejo", planNuevo);
		sesion.setAttribute("planesEditados", planesEditados);
		sesion.setAttribute("planesEliminados", null);
		
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
