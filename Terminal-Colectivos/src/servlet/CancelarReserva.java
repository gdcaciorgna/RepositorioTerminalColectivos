package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Reserva;
import entities.Usuario;
import logic.UsuarioLogic;
import util.AppDataException;

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
Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");
		
		
		UsuarioLogic usuLog = new UsuarioLogic();
		
		if(usuLog.validarCliente(usuarioActual)) 
		
		{
		
		
		Reserva reservaACancelar = (Reserva) sesion.getAttribute("reservaACancelar");
		
		
		Date fechaHoraActual = new Date();
		
		reservaACancelar.setFecha_canc(fechaHoraActual);
		
		double importeADevolver = (double) sesion.getAttribute("importeADevolver");
		
		
		controlers.CancelarReserva cancelarReserva = new controlers.CancelarReserva();
		
		try 
		{
			cancelarReserva.cancelarReserva(reservaACancelar);
			sesion.setAttribute("mensajeExito", "Reserva cancelada con �xito. Se devolvi� un total de $"+ importeADevolver + " pesos a la cuenta de la compa��a " + reservaACancelar.getCompania_tarjeta().getNombre()    );

		} catch (AppDataException e) 
		{
			sesion.setAttribute("mensajeError", e.getMessage());

		}
		
		
		
		
		 request.getRequestDispatcher("/WEB-INF/misReservas.jsp").forward(request, response);		

		
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
