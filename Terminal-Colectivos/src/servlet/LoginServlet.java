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

		Usuario usu = new Usuario();
		DataUsuario dusu = new DataUsuario();
		

        boolean r;
        Usuario usuarioEncontrado;
        String txtusu = request.getParameter("txtusu");
        String txtpass = request.getParameter("txtpass");
        
        
        usu.setUsuario(txtusu);
        usu.setContrasenia(txtpass);
        
       
        
        r= dusu.validar(usu);
        usuarioEncontrado = dusu.getByUsuario(txtusu);
        usu.setRol(usuarioEncontrado.getRol());
        usu.setEstado(usuarioEncontrado.getEstado());
       
 
        if(r==true) 
		{
        	sesion.setAttribute("usuario", txtusu);
        	sesion.setAttribute("rol", usu.getRol());
        	sesion.setAttribute("estado", usu.getEstado());
        	response.sendRedirect("index.jsp");	

            //redirijo a p�gina con informaci�n de login exitoso
        	
		}
        	 else 
        	 { 
		        	
		        	
        		 if (txtusu.isEmpty() || txtpass.isEmpty()) 
        		 	{ 
 		        	//l�gica para falta de datos
 		        	request.getSession().setAttribute("error", "Hay campos vac�os");
 		        	}
 		        	else 
 		        	{
	 		        request.getSession().setAttribute("error", "Usuario y/o contrase�a incorrecta");
 		        	}
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
