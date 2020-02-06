package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Pasajero;
import entities.Usuario;
import logic.PasajeroLogic;
import logic.UsuarioLogic;

/**
 * Servlet implementation class RedireccionPagoViajes
 */
@WebServlet("/RedireccionPagoViajes")
public class RedireccionPagoViajes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RedireccionPagoViajes() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion = request.getSession();
		
		Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");
		
		UsuarioLogic usuLog = new UsuarioLogic();
		
		if(usuLog.validarCliente(usuarioActual)==true) 
		
		{
		
		int cantPasajeros = (int) sesion.getAttribute("cantidadPasajeros");
		int cont=0;
	
		ArrayList<Pasajero> pasajeros = new ArrayList<Pasajero>();
		
		//FORMA UN POCO RARA DE TRAER LOS DATOS, pero funciona
		PasajeroLogic pas= new PasajeroLogic();
		for(int i = 1; i<=cantPasajeros; i++)
		{
			Pasajero pasajero = new Pasajero();
			String nombre =  request.getParameter("nombre"+i);
			String apellido =  request.getParameter("apellido"+i);
			String dniString=request.getParameter(("dni"+i).trim());
			if (pas.esNumero(dniString)) {
			int dni = Integer.parseInt(request.getParameter(("dni"+i).trim()));

			
			pasajero.setDni(dni);
			pasajero.setNombre(nombre);
			pasajero.setApellido(apellido);
			
			pasajeros.add(pasajero);
			cont++;
			}
			else 
			{
				sesion.setAttribute("mensajePasajeros", "Alguno/os  DNI ingresados  son invalidos");
				request.getRequestDispatcher("/WEB-INF/definirpasajeros.jsp").forward(request, response);		

			}
			
		
			
			
			
			
		
		}
		if(cont==cantPasajeros) {
		sesion.setAttribute("pasajerosViaje", pasajeros); // Guarda los pasajeros del viaje en la sesion del usuario
		
		request.getRequestDispatcher("/WEB-INF/pagarviaje.jsp").forward(request, response);		
		
		}
		
		else
		{
			response.sendRedirect("index.jsp"); 
		}
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
