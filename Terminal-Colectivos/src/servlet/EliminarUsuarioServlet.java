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

		boolean redirigidoComoAdminVoF = false;
		
		
		if(redirigidocomoadmin.equals("True"))
		{redirigidoComoAdminVoF = true;}
		else 
		{redirigidoComoAdminVoF = false;}				
		
		
		UsuariosControlers usuCon = new UsuariosControlers();
		
		try 
		{
		
		usuCon.validarEliminarUsuario(username, password);	
		
		usuCon.eliminarUsuario(username, password);
		
		if(redirigidoComoAdminVoF == false) //verifica si es redirigido desde micuenta.jsp -> Cierra sesion y redirige a bajasatisfactoria.jsp
		{
		sesion.setAttribute("Usuario", null);
		sesion.invalidate(); //CERRAR SESION
		request.getRequestDispatcher("bajasatisfactoria.jsp").forward(request, response);		

		}
			
		else  //verifica si es redirigido desde usuarios.jsp -> redirige a usuarios.jsp
		{

		sesion.setAttribute("mensajeExito", "Se ha eliminado al usuario de manera satisfactoria.");
		request.getRequestDispatcher("/WEB-INF/usuarios.jsp").forward(request, response);
		
		}
	
		}
		
		catch(Exception e) 
		{
			sesion.setAttribute("mensajeError", e.getMessage());
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
