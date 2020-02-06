package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Usuario;
import logic.UsuarioLogic;

/**
 * Servlet implementation class MisReservas
 */
@WebServlet("/MisReservas")
public class RedireccionMisReservas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedireccionMisReservas() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion= request.getSession();

		Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");
		
		UsuarioLogic usuLog = new UsuarioLogic();
		
		if(usuLog.validarCliente(usuarioActual)==true) 
		
		{
		 request.getRequestDispatcher("/WEB-INF/misReservas.jsp").forward(request, response);
		
		}
		
		else
		{
			response.sendRedirect("index.jsp"); 
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
