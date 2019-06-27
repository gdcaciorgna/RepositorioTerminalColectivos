package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Usuario usuarioEncontrado;
		DataUsuario dusu = new DataUsuario();
		boolean testIgualdad, r;
		
		
		String txtusu;
		String txtpass1 = request.getParameter("txtpass1");
		String txtpass2 = request.getParameter("txtpass2");
		txtusu = (String) request.getParameter("txtusu");
		
		usuarioEncontrado = dusu.getByUsuario(txtusu);
		
		testIgualdad = validarIgualdadContraseñas(txtpass1, txtpass2);
		if (testIgualdad) 
		{
			request.getSession().setAttribute("error", "Las contraseñas SI coinciden");
		}
		else 
		{
			request.getSession().setAttribute("error", "Las contraseñas no coinciden.");
		}
		
		 response.sendRedirect("#");
	
		

	
	
	}
 
	

	private boolean validarIgualdadContraseñas(String txtpass1, String txtpass2) {
		if(txtpass1.equals(txtpass2))
		return true;
		else return false;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
