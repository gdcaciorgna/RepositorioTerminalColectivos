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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion = request.getSession();

		Usuario usuario = new Usuario();
		DataUsuario dusu = new DataUsuario();
		

        boolean r;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        
        
        usuario.setUsername(username);
        usuario.setPassword(password);
        
       
        
        r= dusu.validar(usuario);
 
        if(r==true) 
		{
        	usuario = dusu.getByUsuario(username);
            
            String txtEstado = usuario.getEstado();
        	sesion.setAttribute("usuario", username);
        	sesion.setAttribute("rol", usuario.getRol());
        	sesion.setAttribute("estado", txtEstado);
        	sesion.setAttribute("Usuario", usuario);
        	response.sendRedirect("index.jsp");	

            //redirijo a p�gina con informaci�n de login exitoso
        	
		}
        	 else 
        	 { 
	 		 request.getSession().setAttribute("error", "Usuario y/o contrase�a incorrecta");	
    		 response.sendRedirect("login.jsp");	
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
