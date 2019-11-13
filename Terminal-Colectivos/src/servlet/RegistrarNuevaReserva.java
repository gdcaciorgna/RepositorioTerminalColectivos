package servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controlers.ABReserva;
import entities.Usuario;
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
		int cantPasajeros = (int) sesion.getAttribute("cantidadPasajeros");
		Reserva res= new Reserva();
		Usuario usuario= (Usuario)sesion.getAttribute("usuarioActual");
		res.setUsuario(usuario);
		res.setCant_pas(cantPasajeros);
		res.setNro_tarjeta( request.getParameter("nro_tarjeta") );
		String compania= request.getParameter("compania");
		
		ABReserva creserva= new ABReserva();
		creserva.setReserva(res, compania);
		
		request.getSession().setAttribute("registroExitoso", "Tu compra se ha realizado con exito!");	
 		response.sendRedirect("login.jsp");
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
