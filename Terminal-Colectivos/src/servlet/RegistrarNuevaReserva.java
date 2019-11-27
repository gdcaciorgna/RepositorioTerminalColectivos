package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controlers.ABMPasajero;
import entities.Usuario;
import entities.Pasajero;
import entities.Plan;

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
		
		
		String nroTarjeta = request.getParameter("nro_tarjeta");
	
		
		if( nroTarjeta.length() == 16) {
			
			Plan viajeSeleccionado = (Plan) sesion.getAttribute("ViajeSeleccionado");
			
			int cantPasajeros = (int) sesion.getAttribute("cantidadPasajeros");
			
			Usuario usuarioActual = (Usuario)sesion.getAttribute("usuarioActual");
			
			String codCompaniaString=  request.getParameter("codCompania");
			
			int codCompania = Integer.parseInt(codCompaniaString);
			
			@SuppressWarnings("unchecked")
			ArrayList<Pasajero> pasajeros = (ArrayList<Pasajero>) sesion.getAttribute("pasajerosViaje");
			
			ABMPasajero abmPas = new ABMPasajero();
			
			
			abmPas.registrarReserva(pasajeros, nroTarjeta, viajeSeleccionado, cantPasajeros, usuarioActual, codCompania);
			
			sesion.setAttribute("reservaExitosa", "Tu compra se ha realizado con exito!");
			sesion.setAttribute("MensajeCancelarReserva", null);
			request.getRequestDispatcher("/WEB-INF/misReservas.jsp").forward(request, response);		

			
			}
		else {
 		request.getSession().setAttribute("errorTarjeta", "Los numeros de la tajeta no son correctos");	
 		
		request.getRequestDispatcher("/WEB-INF/pagarviaje.jsp").forward(request, response);	
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
