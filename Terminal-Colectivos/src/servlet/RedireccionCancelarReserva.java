package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controlers.CancelarReserva;
import controlers.PlanReservaControlers;
import data.DataReservaPlan;
import entities.Plan_Reserva;

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
		
		CancelarReserva cancelarReserva = new CancelarReserva();
		
		PlanReservaControlers cResPlan = new PlanReservaControlers();
		
		
		
		Plan_Reserva planReserva =  cResPlan.getReservaPlanbyClavesPrimarias(fechaHoraReserva, fechaHoraViaje, patenteColectivoViaje, codRutaViaje, UsernameReserva);

		
		double importeADevolver = cancelarReserva.getImporteADevolver(fechaHoraReserva, fechaHoraViaje, patenteColectivoViaje, codRutaViaje, UsernameReserva ); //la funcion cancelarReserva devuelve el dinero a devolver. Solo se devolvera el importe total si se cancela la reserva al menos una semana antes. Caso contrario solo se devuelve el 20% 
		
		
		
		sesion.setAttribute("importeADevolver", importeADevolver);
		sesion.setAttribute("reservaACancelar", planReserva.getReserva());

		
		response.sendRedirect("confirmarCancelacion.jsp");

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
