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
import logic.UsuarioLogic;

/**
 * Servlet implementation class RedireccionEditarUsuarioSevlet
 */
@WebServlet("/RedireccionEditarUsuarioSevlet")
public class RedireccionEditarUsuarioSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedireccionEditarUsuarioSevlet() {
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
		
		UsuarioLogic usuLog = new UsuarioLogic();
		
		if(usuLog.validarAdministrador(usuarioActual)==true) 
		
		{
		
		String username = request.getParameter("username");
		
		UsuariosControlers usuCon = new UsuariosControlers();
		
		Usuario usu = new Usuario();
		
		try 
		{
			usu = usuCon.getByUsername(username);
		} 
		catch (Exception e) 
		{
			sesion.setAttribute("mensajeError", e.getMessage());
		}
		
		sesion.setAttribute("UsuarioAModificar", usu);
		
		request.getRequestDispatcher("/WEB-INF/editarUsuario.jsp").forward(request, response);		

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
