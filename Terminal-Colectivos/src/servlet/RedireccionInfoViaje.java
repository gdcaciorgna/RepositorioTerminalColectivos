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
import entities.Usuario;
import logic.UsuarioLogic;
import util.AppDataException;

/**
 * Servlet implementation class RedireccionInfoViaje
 */
@WebServlet("/RedireccionInfoViaje")
public class RedireccionInfoViaje extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedireccionInfoViaje() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion= request.getSession();
Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");
		
		
		UsuarioLogic usuLog = new UsuarioLogic();
		
		if(usuLog.validarAdministrador(usuarioActual)) 
		
		{
		
		
		
		
		String fechaViajeString = request.getParameter("fechaViaje");
		String horaViajeString = request.getParameter("horaViaje");
		String codRutaViajeString = request.getParameter("codRutaViaje");
		String patenteColectivoViajeString = request.getParameter("patenteColectivoViaje");
		

		
		PlanControlers planCon = new PlanControlers();
		Plan planSeleccionado = new Plan();
		
		try 
		{
			planSeleccionado = planCon.getPlanByFechaHoraCodRutaPatente(fechaViajeString, horaViajeString, codRutaViajeString, patenteColectivoViajeString);
		} catch (AppDataException e) 
		{
			sesion.setAttribute("mensajeError", e.getMessage());
		}
		
		sesion.setAttribute("planSeleccionado", planSeleccionado);
		request.getRequestDispatcher("/WEB-INF/verInfoViaje.jsp").forward(request, response);
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
