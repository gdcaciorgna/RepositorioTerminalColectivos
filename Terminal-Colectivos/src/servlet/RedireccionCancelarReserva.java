package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controlers.PlanReservaControlers;
import entities.Plan_Reserva;
import entities.Usuario;
import logic.UsuarioLogic;

/**
 * Servlet implementation class RedireccionCancelarReserva
 */
@WebServlet("/RedireccionCancelarReserva")
public class RedireccionCancelarReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedireccionCancelarReserva() {
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
		
		if(usuLog.validarCliente(usuarioActual)==true) 
		
		{
		
		String fechaReserva = request.getParameter("fechaReserva");
		String horaReserva = request.getParameter("horaReserva");
		String fechaHoraReserva = fechaReserva + " " + horaReserva;
		
		String fechaViaje = request.getParameter("fechaViaje");
		String horaViaje = request.getParameter("horaViaje");
		String fechaHoraViaje = fechaViaje + " " + horaViaje;

		

		String patenteColectivoViaje = request.getParameter("patenteColectivoViaje");
		String codRutaViajeString = request.getParameter("codRutaViaje");
		String UsernameReserva = request.getParameter("UsernameReserva");
		
		
		
		
		int codRutaViaje = Integer.parseInt(codRutaViajeString);
		
		PlanReservaControlers planReservaControlers = new PlanReservaControlers();
			
		
		Plan_Reserva planReserva = new Plan_Reserva();
		double importeADevolver = 0;

		try 
		{
		
			planReserva = planReservaControlers.getReservaPlanbyClavesPrimarias(fechaHoraReserva, fechaHoraViaje, patenteColectivoViaje, codRutaViaje, UsernameReserva);
			importeADevolver = planReservaControlers.getImporteADevolver(fechaHoraReserva, fechaHoraViaje, patenteColectivoViaje, codRutaViaje, UsernameReserva );
			sesion.setAttribute("importeADevolver", importeADevolver);
			sesion.setAttribute("reservaACancelar", planReserva.getReserva());
		
			
		} catch (Exception e) 
		{
			sesion.setAttribute("mensajeError", e.getMessage());
		}

		
		request.getRequestDispatcher("/WEB-INF/confirmarCancelacion.jsp").forward(request, response);	
		
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
