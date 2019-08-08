package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BuscarViajesAdmin
 */
@WebServlet("/BuscarViajesAdmin")
public class BuscarViajesAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarViajesAdmin() {
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
      
        
		String fechaString = request.getParameter("fechaString");
		Date fechaDate = new Date();
		

		try {
		   
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");  	

			fechaDate = formatter.parse(fechaString);
			

			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
        sesion.setAttribute("fechaViaje", fechaDate);
        sesion.setAttribute("origenViaje", origenViaje);
        sesion.setAttribute("destinoViaje", destinoViaje);

                
                
    	response.sendRedirect("buscarviajesadmin.jsp");	

	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
