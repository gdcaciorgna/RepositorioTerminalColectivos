package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DataUsuario;
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

		
		Usuario user;
		DataUsuario datauser = new DataUsuario();
		
		sesion.setAttribute("mensajeEditarUsuario", null);

		String username = request.getParameter("username");
		
		user = datauser.getByUsuario(username);
		
		user.setNombre(request.getParameter("nombre"));
		user.setApellido(request.getParameter("apellido"));
		user.setEmail(request.getParameter("email"));
		user.setCuil(request.getParameter("cuil"));
		user.setRol(request.getParameter("rol"));
		user.setEstado(request.getParameter("estado"));
		
		datauser.editarUsuario(user);
		sesion.setAttribute("UsuarioAModificar", user); //ESTA BIEN ESTO PARA REESCRIBIR LAS VARIABLES DE SESSION O PUEDO TRAER LOS DATOS DE LA BASE DE DATOS DIRECTAMENTE Y MOSTRARLAS EN EL FORMULARIO??
		sesion.setAttribute("mensajeEditarUsuario", "�El usuario ha sido modificado correctamente!");
		response.sendRedirect("editarUsuario.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
