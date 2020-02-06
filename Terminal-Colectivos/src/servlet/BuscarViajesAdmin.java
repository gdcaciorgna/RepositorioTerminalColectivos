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
import entities.Usuario;
import logic.UsuarioLogic;

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
		
		Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");
		
		try {
		
		UsuarioLogic usuLog = new UsuarioLogic();
		
		usuLog.validarAdministrador(usuarioActual);
		

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
		
		Date fechaDate = new Date();
		
		fechaDate = fechaCon.yyyyMMddToDate(fechaString);
		
        sesion.setAttribute("fechaViaje", fechaDate);
        sesion.setAttribute("origenViaje", origenViaje);
        sesion.setAttribute("destinoViaje", destinoViaje);
        
        
        
        
        
		

    	//response.sendRedirect("buscarviajesadmin.jsp");	
        request.getRequestDispatcher("/WEB-INF/buscarviajesadmin.jsp").forward(request, response);
        
		}
		
		catch( Exception e) 
		{
			if(usuarioActual == null) 
			{
				response.sendRedirect("login.jsp");
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
