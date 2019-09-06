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

		boolean vof = false;
		
		
		if(redirigidocomoadmin.equals("True"))
		{vof = true;}
		
		
		UsuariosControlers usuCon = new UsuariosControlers();
		
		String mensaje = usuCon.getMensajeEliminarUsuario(username, password);
		
		int filasEliminadas = usuCon.eliminarUsuario(username, password);
		
		if(mensaje.equals("OK")) 
		{
			
			if(vof = false) //verifica si es redirigido desde micuenta.jsp -> Cierra sesion y redirige a bajasatisfactoria.jsp
			{
			sesion.setAttribute("Usuario", null);
			sesion.invalidate(); //CERRAR SESION
			response.sendRedirect("bajasatisfactoria.jsp");
			}
			
			else //verifica si es redirigido desde usuarios.jsp -> redirige a usuarios.jsp
			{
				sesion.setAttribute("UsuariosAfectados", filasEliminadas);
				response.sendRedirect("usuarios.jsp");

			}
		}
		
		else
        	 {
				sesion.setAttribute("errorEliminarUsuario", "Hay campos vacíos");
				response.sendRedirect("micuenta.jsp");
        	 }	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
