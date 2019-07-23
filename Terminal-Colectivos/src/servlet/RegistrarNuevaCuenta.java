package servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Conectar;
import data.DataUsuario;
import entities.Usuario;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		

		Usuario usuario = new Usuario();
		DataUsuario dusu = new DataUsuario();

		
		
		String username = request.getParameter("usuario");
		
	
	   
		usuario.setUsuario((username));
		
		
		if((dusu.validarUsuario(usuario) == false)) {
			usuario.setPassword(request.getParameter("password")); 
			if(usuario.getPassword().length()>=8) {
				
				usuario.setNombre(request.getParameter("nombre")); 
				usuario.setApellido(request.getParameter("apellido")); 
				usuario.setEmail(request.getParameter("email"));
				usuario.setCuil(request.getParameter("cuil"));
				usuario.setRol(request.getParameter("rol"));
				
				
				dusu.ingresarUsuario( usuario);
			
		 		request.getSession().setAttribute("registroExitoso", "Te has registrado exitosamente!");	
				response.sendRedirect("login.jsp");
				
				
			}
			else 
       	 { 
	 		 request.getSession().setAttribute("error2", "La contraseña debe contener 8 caracteres como minimo");	
   		 response.sendRedirect("registro.jsp");	
       	 }	
			
			
		}
		else 
   	 { 
		 request.getSession().setAttribute("error1", "El nombre de usuario esta en uso");	
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
