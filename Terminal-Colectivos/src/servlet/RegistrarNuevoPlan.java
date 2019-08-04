package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DataColectivo;
import data.DataPlan;
import data.DataRuta;
import data.DataUsuario;
import entities.Usuario;
 import entities.Plan;
import entities.Ruta;
import entities.Colectivo;

/**
 * Servlet implementation class RegistrarNuevoPlan
 */
@WebServlet("/RegistrarNuevoPlan")
public class RegistrarNuevoPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarNuevoPlan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		DataPlan datplan= new DataPlan();
		DataColectivo datcol= new DataColectivo();
		DataRuta datrut= new DataRuta();
		DataUsuario datchof= new DataUsuario();
		String ros="ROSARIO";
		Plan nuevoPlan = new Plan();
		
		
		String origenViaje = request.getParameter("origenViaje");
		String destinoViaje = request.getParameter("destinoViaje");
	
		
		if(origenViaje.equals(ros)||destinoViaje.equals(ros)) {
			nuevoPlan.setFecha(request.getParameter("fecha")); 
			String usuarioChofer = request.getParameter("choferes");
			nuevoPlan.setHora(request.getParameter("hora")); 
			nuevoPlan.setOrigen(origenViaje); 
			nuevoPlan.setDestino(destinoViaje);
			nuevoPlan.setPrecio(Double.parseDouble(request.getParameter("precio")));
			String patente = request.getParameter("patente");
			
			Ruta rut= datrut.getRuta(origenViaje, destinoViaje);
			Colectivo cole = datcol.getByPatente(patente);
			Usuario chofer= datchof.getChofer(usuarioChofer);
			nuevoPlan.setRuta(rut);
			nuevoPlan.setColectivo(cole);
			nuevoPlan.setChofer(chofer);
			datplan.newPlan( nuevoPlan);
			request.getSession().setAttribute("registroExitoso", "Se ha registrado exitosamente el nuevo Plan!");	
			response.sendRedirect("registrarNuevoPlan.jsp");
			
			 }
		else {
			 request.getSession().setAttribute("errorOrigenDestino", "El Origen o el Destino debe ser Rosario");
			 response.sendRedirect("registrarNuevoPlan.jsp");
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
