package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DataReserva;
import entities.Reserva;

/**
 * Servlet implementation class CancelarReserva
 */
@WebServlet("/CancelarReserva")
public class CancelarReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelarReserva() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion = request.getSession();
		
		Reserva reservaACancelar = (Reserva) sesion.getAttribute("reservaACancelar");
		
		
		Date fechaHoraActual = new Date();
		
		reservaACancelar.setFecha_canc(fechaHoraActual);
		
		double importeADevolver = (double) sesion.getAttribute("importeADevolver");
		
		DataReserva dRes = new DataReserva();
		dRes.cancelarReserva(reservaACancelar); //Envio solo la reserva a cancelar. No hace falta el plan
		
		
		sesion.setAttribute("MensajeCancelarReserva", "Reserva cancelada con éxito. Se devolvió un total de $"+ importeADevolver + " pesos a la cuenta de la compañía " + reservaACancelar.getCompania_tarjeta().getNombre() + " a nombre de " + reservaACancelar.getUsuario().getNombre() + " " + reservaACancelar.getUsuario().getApellido()  );
		sesion.setAttribute("reservaExitosa", null);
		
		response.sendRedirect("misReservas.jsp");
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
