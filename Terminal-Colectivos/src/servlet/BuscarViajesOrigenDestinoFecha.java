package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class BuscarViajesOrigenDestinoFecha
 */
@WebServlet("/BuscarViajesOrigenDestinoFecha")
public class BuscarViajesOrigenDestinoFecha extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarViajesOrigenDestinoFecha() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion = request.getSession();

		
		
		
        String origenViaje = request.getParameter("origenViaje");
        String destinoViaje = request.getParameter("destinoViaje");
        String fechaViaje = request.getParameter("fechaViaje");
        
                
        sesion.setAttribute("origenViaje", origenViaje);
        sesion.setAttribute("destinoViaje", destinoViaje);
        sesion.setAttribute("fechaViaje", fechaViaje);
        
        
    	response.sendRedirect("reservarviaje.jsp");	




	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
