package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controlers.UsuariosControlers;
import util.AppDataException;

/**
 * Servlet implementation class EliminarUsuarioServlet
 */
@WebServlet("/EliminarUsuarioServlet")
public class EliminarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarUsuarioServlet() {
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
		String redirigidocomoadmin = request.getParameter("redirigidocomoadmin");

		boolean vof = false;
		
		
		if(redirigidocomoadmin.equals("True"))
		{vof = true;}
		else 
		{vof=false;}				
		
		
		UsuariosControlers usuCon = new UsuariosControlers();
		
		String mensaje;
		try 
		{
			mensaje = usuCon.getMensajeEliminarUsuario(username, password);
		
		
		int filasEliminadas = usuCon.eliminarUsuario(username, password);
		
		if(mensaje.equals("OK")) 
		{
			
			if(vof == false) //verifica si es redirigido desde micuenta.jsp -> Cierra sesion y redirige a bajasatisfactoria.jsp
			{
			sesion.setAttribute("Usuario", null);
			sesion.invalidate(); //CERRAR SESION
			request.getRequestDispatcher("/WEB-INF/bajasatisfactoria.jsp").forward(request, response);		

			}
			
			else //verifica si es redirigido desde usuarios.jsp -> redirige a usuarios.jsp
			{
				sesion.setAttribute("UsuariosAfectados", filasEliminadas);
				
				request.getRequestDispatcher("/WEB-INF/usuarios.jsp").forward(request, response);		


			}
		}
	
		
		else
        	 {
				sesion.setAttribute("errorEliminarUsuario", "La contraseña ingresada no es correcta.");
				request.getRequestDispatcher("/WEB-INF/micuenta.jsp").forward(request, response);		

        	 }	
		
		} catch (SQLException | AppDataException e) 
		
			{	
				sesion.setAttribute("errorEliminarUsuario", e.getMessage());
				request.getRequestDispatcher("/WEB-INF/micuenta.jsp").forward(request, response);		

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
