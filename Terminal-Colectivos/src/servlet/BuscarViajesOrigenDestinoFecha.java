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

import data.DataPlan;
import entities.Plan;

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

		
		DataPlan dplan = new DataPlan();
		Plan plan = new Plan();
		
        String origenViaje = request.getParameter("origenViaje");
        String destinoViaje = request.getParameter("destinoViaje");
        String fechaViaje = request.getParameter("date");
        
        
        //INICIO - CAMBIAR EL FORMATO DE DD/MM/YYYY -> YYYYY/MM/DD
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        fechaViaje = LocalDate.parse(fechaViaje, formatter).format(formatter2);
        //FIN - CAMBIAR EL FORMATO DE DD/MM/YYYY -> YYYYY/MM/DD
        
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
