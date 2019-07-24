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

		
		String passrep= request.getParameter("passwordrep");
		String username = request.getParameter("usuario");
		
	
	   boolean r=false;
		usuario.setUsuario((username));
		usuario.setPassword(request.getParameter("password"));
		
		if((dusu.validarUsuarioInexistente(usuario) == false)) {
			
			
			 request.getSession().setAttribute("errorRegistro", "El nombre de usuario esta en uso");	
			 
			 }
		
			
			 else if(usuario.getPassword().length()<8)
       	 { 
	 		 request.getSession().setAttribute("errorRegistro", "La contraseña debe contener 8 caracteres como minimo");	
   		 	
   		
       	 }		
			
					 	else if(!usuario.getPassword().equals(passrep)) 
			    	 { 
				 		 request.getSession().setAttribute("errorRegistro", "Las contraseñas no coinciden");	
			   		 
			   		
			       	 }	
			
								
								else 
						   	 { 
									
									usuario.setNombre(request.getParameter("nombre")); 
									usuario.setApellido(request.getParameter("apellido")); 
									usuario.setEmail(request.getParameter("email"));
									usuario.setCuil(request.getParameter("cuil"));
									usuario.setRol(request.getParameter("rol"));
									
									
									dusu.ingresarUsuario( usuario);
								
							 		request.getSession().setAttribute("registroExitoso", "Te has registrado exitosamente!");	
									
									r=true;
								}
		if (r==true) {
			response.sendRedirect("login.jsp");
		}
		else {response.sendRedirect("registro.jsp");	}
	    
								
   	 }	
		
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
		
	}
	
}
