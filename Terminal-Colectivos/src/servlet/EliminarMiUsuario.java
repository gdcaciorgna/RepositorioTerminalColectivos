package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controlers.UsuariosControlers;

/**
 * Servlet implementation class EliminarMiUsuario
 */
@WebServlet("/EliminarMiUsuario")
public class EliminarMiUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarMiUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		HttpSession sesion = request.getSession();
		
		
		String username =  request.getParameter("username");
		String password = request.getParameter("password");
		
		
		UsuariosControlers usuCon = new UsuariosControlers();
		
		if(usuCon.eliminarMiUsuario(username, password))
		{
			sesion.setAttribute("Usuario", null);
			sesion.invalidate(); //CERRAR SESION
			response.sendRedirect("bajasatisfactoria.jsp");					

		}
        	 else 
        	 { 
		        	
		        	
        		 if (username.isEmpty() || password.isEmpty()) 
        		 	{ 
 		        	//lógica para falta de datos
 		        	sesion.setAttribute("errorEliminarUsuario", "Hay campos vacíos");
 		        	}
 		        	else 
 		        	{
	 		        sesion.setAttribute("errorEliminarUsuario", "Contraseña incorrecta");
 		        	}
        		 
    		 response.sendRedirect("micuenta.jsp");	
        		 
        		 

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
