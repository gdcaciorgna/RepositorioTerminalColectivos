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
import entities.Pasajero;

/**
 * Servlet implementation class RedireccionPagoViajes
 */
@WebServlet("/RedireccionPagoViajes")
public class RedireccionPagoViajes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedireccionPagoViajes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion = request.getSession();
		
		int cantPasajeros = (int) sesion.getAttribute("cantidadPasajeros");
		
		ArrayList<Pasajero> pasajeros = new ArrayList<Pasajero>();
		
		//FORMA UN POCO RARA DE TRAER LOS DATOS, pero funciona
		for(int i = 1; i<=cantPasajeros; i++)
		{
			Pasajero pasajero = new Pasajero();
			String nombre =  request.getParameter("nombre"+i);
			String apellido =  request.getParameter("apellido"+i);
			int dni = Integer.parseInt(request.getParameter(("dni"+i)));

			
			pasajero.setDni(dni);
			pasajero.setNombre(nombre);
			pasajero.setApellido(apellido);
			
			pasajeros.add(pasajero);
			
			
			//ABMPasajero abmPasajero = new ABMPasajero();
			//abmPasajero.AddPasajero(pasajero);
			
			
			
			
		
		}
		sesion.setAttribute("pasajerosViaje", pasajeros); // Guarda los pasajeros del viaje en la sesion del usuario
		
		response.sendRedirect("pagarviaje.jsp");

		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
