package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controlers.FechaControlers;

/**
 * Servlet implementation class BuscarViajesChofer
 */
@WebServlet("/BuscarViajesChofer")
public class BuscarViajesChofer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarViajesChofer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion = request.getSession();			

		FechaControlers fechaCon = new FechaControlers();

		
		String origenViaje = "Cualquiera";
		String destinoViaje = "Cualquiera";
		
		Date fechaActual = new Date();
		String fechaString = fechaCon.dateToddMMyyyy(fechaActual);
		
	
			
		if(request.getParameter("origenViaje")!=null && request.getParameter("origenViaje")!=null && request.getParameter("fechaString")!=null ) 
		{
	        origenViaje = request.getParameter("origenViaje");
	        destinoViaje = request.getParameter("destinoViaje");
			fechaString = request.getParameter("fechaString");

		}
		
		fechaString = fechaCon.dateToddMMyyyy(fechaActual);

		
		
		Date fechaDate = new Date();
		
		fechaDate = fechaCon.yyyyMMddToDate(fechaString);
		
        sesion.setAttribute("fechaViaje", fechaDate);
        sesion.setAttribute("origenViaje", origenViaje);
        sesion.setAttribute("destinoViaje", destinoViaje);
        
        
        
		

        request.getRequestDispatcher("/WEB-INF/buscarviajeschofer.jsp").forward(request, response);
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
