package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
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
		response.getWriter().append("Served at: ").append(request.getContextPath());

		
		Usuario usu;
		DataUsuario dusu = new DataUsuario();
		boolean testPassword;
		
		
		String txtusu;
		String txtpass = request.getParameter("txtpass1");
		txtusu = (String) request.getParameter("txtusu");
		
		usu = dusu.getByUsuario(txtusu);
		
		testPassword= dusu.validar(usu,txtpass);
		
		if(testPassword) 
		{
			

			dusu.eliminarUsuario(usu);
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/bajasatisfactoria.jsp" );
			dispatcher.forward( request, response );


		}
        	 else 
        	 { 
		        	
		        	
        		 if (txtusu.isEmpty() || txtpass.isEmpty()) 
        		 	{ 
 		        	//lógica para falta de datos
 		        	sesion.setAttribute("error", "Hay campos vacíos");
 		        	}
 		        	else 
 		        	{
	 		        sesion.setAttribute("error", "Usuario y/o contraseña incorrecta");
 		        	}
    		 response.sendRedirect("miperfil.jsp");	
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
