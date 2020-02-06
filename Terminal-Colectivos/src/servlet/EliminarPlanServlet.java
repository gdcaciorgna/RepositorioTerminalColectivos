package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controlers.PlanControlers;
import entities.Usuario;
import logic.UsuarioLogic;
import util.AppDataException;

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
	Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");
	
	
	UsuarioLogic usuLog = new UsuarioLogic();
	
	if(usuLog.validarAdministrador(usuarioActual)) 
	
	{
	
		
	
		String fechaString = request.getParameter("fechaViajeString");
		String horaString = request.getParameter("horaViajeString");		
		String codRutaViajeString = request.getParameter("codRutaViajeString");
		String patenteColectivoViaje = request.getParameter("patenteColectivoViajeString");
		
		
		PlanControlers planCon = new PlanControlers();
		
		try 
		{
			planCon.eliminarPlan(fechaString, horaString, codRutaViajeString, patenteColectivoViaje);
			sesion.setAttribute("mensajeExito", "Se ha eliminado al plan de viaje de manera correcta."); 	
			
		} catch (AppDataException e) 
		{
			sesion.setAttribute("mensajeError", e.getMessage()); 
		}
	
		request.getRequestDispatcher("/WEB-INF/buscarviajesadmin.jsp").forward(request, response);		
	}
	
	else
	{
			response.sendRedirect("index.jsp"); 
	}



		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
