package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DataPlan;
import entities.Plan;

/**
 * Servlet implementation class EliminarPlanServlet
 */
@WebServlet("/EliminarPlanServlet")
public class EliminarPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarPlanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		

		HttpSession sesion = request.getSession();
		
		String JuntosfechaHoraString = request.getParameter("fechaHoraString"); //Me gustaría traer la fecha y hora todo junto, pero el string sólo trae la fecha ¡?¡?¡?¡
		String fechaString = request.getParameter("fechaString");
		String horaString = request.getParameter("horaString");
		
		String fechaHoraString = fechaString + " " + horaString;
		
		String codRutaViajeString = request.getParameter("codRutaViajeString");
		String patenteColectivoViaje = request.getParameter("patenteColectivoViaje");
		
		DataPlan dplan = new DataPlan();
		Plan plan = new Plan();
		
		
        SimpleDateFormat formatFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date fechaHora = new Date();
        try {
			
        fechaHora = formatFechaHora.parse(fechaHoraString);
		
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        int cod_ruta = Integer.parseInt(codRutaViajeString);
       
		
		plan = dplan.getByFechaHoraRutaPatente(fechaHora, cod_ruta, patenteColectivoViaje);
		
		int filasEliminadas = 0;
		
		filasEliminadas = dplan.eliminarPlan(plan);
		
		sesion.setAttribute("planesAfectados", filasEliminadas);
		
		response.sendRedirect("buscarviajesadmin.jsp");	
		


		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
