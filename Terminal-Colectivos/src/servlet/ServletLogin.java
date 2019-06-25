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
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletLogin() {
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
        String txtusu = request.getParameter("txtusu");
        String txtpass = request.getParameter("txtpass");
        
        usu.setUsuario(txtusu);
        usu.setContrasenia(txtpass);
        
        r= dusu.validar(usu);
       
 
        if(r==true) 
		{
        	sesion.setAttribute("usuario", txtusu);
        	response.sendRedirect("index.jsp");	

            //redirijo a página con información de login exitoso
        	
		}
        	 else 
        	 { 
		        	
		        	
        		 if (txtusu.isEmpty() || txtpass.isEmpty()) 
        		 	{ 
 		        	//lógica para falta de datos
 		        	request.getSession().setAttribute("error", "Hay campos vacíos");
 		        	}
 		        	else 
 		        	{
	 		        request.getSession().setAttribute("error", "Usuario y/o contraseña incorrecta");
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
