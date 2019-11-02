package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RedireccionDefinirPasajeros
 */
@WebServlet("/RedireccionDefinirPasajeros")
public class RedireccionDefinirPasajeros extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedireccionDefinirPasajeros() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion= request.getSession();
		String cantidadPasajerosString = request.getParameter("cantidadPasajeros");
		
		int cantidadPasajeros = Integer.parseInt(cantidadPasajerosString);
		
		sesion.setAttribute("cantidadPasajeros", cantidadPasajeros);
		
		response.sendRedirect("definirpasajeros.jsp");	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
