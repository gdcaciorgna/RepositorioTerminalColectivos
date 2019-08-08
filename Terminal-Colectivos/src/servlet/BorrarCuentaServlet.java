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
 * Servlet implementation class BorrarCuentaServlet
 */
@WebServlet("/BorrarCuentaServlet")
public class BorrarCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrarCuentaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion = request.getSession();

		
		Usuario usu;
		DataUsuario dusu = new DataUsuario();
		boolean testPassword;
		
		
		String username =  request.getParameter("username");
		String password = request.getParameter("password");
		
		
		usu = dusu.getByUsuario(username);
		
		testPassword= dusu.validarUsuarioyPassword(usu,password);
		
		if(testPassword) 
		{
			

			Integer filasAfectadas = dusu.eliminarUsuario(usu);
			String url = request.getHeader("Referer"); //Obtiene el URL de la página que llamó al servlet (String)
			if(url.contains("micuenta")) 
			{
			sesion.setAttribute("Usuario", null);
			sesion.invalidate(); //CERRAR SESION
			response.sendRedirect("bajasatisfactoria.jsp");		
			} else if(url.contains("usuarios")) 
			{
			sesion.setAttribute("UsuariosAfectados", filasAfectadas);
			response.sendRedirect("usuarios.jsp");	
			}

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
