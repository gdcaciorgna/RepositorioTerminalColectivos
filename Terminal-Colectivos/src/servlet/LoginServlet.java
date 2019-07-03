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
        String txtusu = request.getParameter("txtusu");
        String txtpass = request.getParameter("txtpass");
        
        
        
        usuario.setUsuario(txtusu);
        usuario.setContrasenia(txtpass);
        
       
        
        r= dusu.validar(usuario);
 
        if(r==true) 
		{
        	usuario = dusu.getByUsuario(txtusu);
            
            String txtEstado = usuario.getEstado();
        	sesion.setAttribute("usuario", txtusu);
        	sesion.setAttribute("rol", usuario.getRol());
        	sesion.setAttribute("estado", txtEstado);
        	response.sendRedirect("index.jsp");	

            //redirijo a página con información de login exitoso
        	
		}
        	 else 
        	 { 
	 		 request.getSession().setAttribute("error", "Usuario y/o contraseña incorrecta");	
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
