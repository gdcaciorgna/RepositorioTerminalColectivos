package servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controlers.ABMPasajero;
import controlers.ABReserva;
import entities.Usuario;
import entities.Plan;
import entities.Reserva;

/**
 * Servlet implementation class RegistrarNuevaReserva
 */
@WebServlet("/RegistrarNuevaReserva")
public class RegistrarNuevaReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarNuevaReserva() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion = request.getSession();	
		
		Reserva res= new Reserva();
		
		res.setNro_tarjeta( request.getParameter("nro_tarjeta") );
	
		
		if( (res.getNro_tarjeta().length())== 16) {
			Plan planSelec = (Plan) sesion.getAttribute("ViajeSeleccionado");
			int cantPasajeros = (int) sesion.getAttribute("cantidadPasajeros");
			Usuario usuario= (Usuario)sesion.getAttribute("usuarioActual");
			res.setUsuario(usuario);
			res.setCant_pas(cantPasajeros);
			String compania= request.getParameter("compania");
			
			ABReserva creserva= new ABReserva();
			creserva.setReserva(res, compania, planSelec);
		
		request.getSession().setAttribute("reservaExitosa", "Tu compra se ha realizado con exito!");	
 		response.sendRedirect("misReservas.jsp");}
		else {
 		request.getSession().setAttribute("errorTarjeta", "Los numeros de la tajeta no son correctos");	
 		response.sendRedirect("pagarviaje.jsp");}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
