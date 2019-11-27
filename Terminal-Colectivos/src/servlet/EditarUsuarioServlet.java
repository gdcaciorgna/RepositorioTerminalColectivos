package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controlers.UsuariosControlers;
import entities.Usuario;

/**
 * Servlet implementation class EditarUsuarioServlet
 */
@WebServlet("/EditarUsuarioServlet")
public class EditarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion = request.getSession();

		
		sesion.setAttribute("MensajeUsuarioAEditar", null); //limpia el atributo de la sesion
		
		
		String username = request.getParameter("username");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String cuil = request.getParameter("cuil");
		String rol = request.getParameter("rol");
		String estado = request.getParameter("estado");
		
		UsuariosControlers usuCon = new UsuariosControlers();
		
		String mensaje = "OK"; //NO ES NECESARIO VALIDAR DATOS
		
		Usuario usu = new Usuario();
		
		if(mensaje.equals("OK")) 
		{
			usu = usuCon.editarUsuario(username, nombre, apellido, email, cuil, rol, estado, null, null, null);
			sesion.setAttribute("UsuarioAModificar", usu); 
		
		}
		
		
		sesion.setAttribute("MensajeUsuarioAEditar", mensaje); 		
		 request.getRequestDispatcher("/WEB-INF/editarUsuario.jsp").forward(request, response);		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
