package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controlers.UsuariosControlers;
import util.Emailer;

/**
 * Servlet implementation class RegistrarNuevaCuenta
 */
@WebServlet("/RegistrarNuevaCuenta")
public class RegistrarNuevaCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarNuevaCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub	
		
		HttpSession sesion = request.getSession();	

		
		String username = request.getParameter("usuario");
		String password = request.getParameter("password");
		String passwordrep= request.getParameter("passwordrep");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String cuil = request.getParameter("cuil");
		String rol = request.getParameter("rol");
		
		
		UsuariosControlers usuCon = new UsuariosControlers(); 
		
		try {
			
			usuCon.validarRegistro(username, password, passwordrep);
			usuCon.setUsuario(username, password, nombre, apellido, email, cuil, rol);
			Emailer.getInstance().send(email, "�Bienvenido a nuestro sitio web!", "Bienvenido" + nombre + " " +  apellido + " a nuestra plataforma digital de reserva y compra de pasajes. �Que tengas siempre un buen viaje! :) " );
			
	 		sesion.setAttribute("mensajeRegistro", "Te has registrado exitosamente!");	
	 		response.sendRedirect("login.jsp");

		} 
		catch (Exception e) {
			
			sesion.setAttribute("mensajeError", e.getMessage());	
			response.sendRedirect("registro.jsp");

			
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
