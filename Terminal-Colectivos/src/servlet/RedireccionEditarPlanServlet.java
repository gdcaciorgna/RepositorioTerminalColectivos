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
 * Servlet implementation class RedireccionEditarPlanServlet
 */
@WebServlet("/RedireccionEditarPlanServlet")
public class RedireccionEditarPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedireccionEditarPlanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion = request.getSession();

		
		String JuntosfechaHoraString = request.getParameter("fechaHoraString"); //Me gustar�a traer la fecha y hora todo junto, pero el string s�lo trae la fecha �?�?�?�
		String fechaString = request.getParameter("fechaString");
		String horaString = request.getParameter("horaString");
		
		String fechaHoraString = fechaString + " " + horaString;
		
		String codRutaViajeString = request.getParameter("codRutaViajeString");
		String patenteColectivoViaje = request.getParameter("patenteColectivoViaje");


		DataPlan dplan = new DataPlan();
		Plan plan = new Plan();
		
		
        SimpleDateFormat formatFechaHora1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date fechaHora = new Date();
        try {
			
        fechaHora = formatFechaHora1.parse(fechaHoraString);
		
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        int cod_ruta = Integer.parseInt(codRutaViajeString);
       
		
		plan = dplan.getByFechaHoraRutaPatente(fechaHora, cod_ruta, patenteColectivoViaje);
		
        SimpleDateFormat formatFechaHora2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String fechaHoraString2 = formatFechaHora2.format(fechaHora);


		//INICIO - SETEAR VALORES ORIGINALES (ANTES DE CAMBIAR)
        sesion.setAttribute("origenPlanViejo", plan.getOrigen());
		sesion.setAttribute("destinoPlanViejo", plan.getDestino());
		sesion.setAttribute("fechaHoraStringPlanViejo", fechaHoraString2);
		sesion.setAttribute("patenteColectivoPlanViejo", plan.getColectivo().getPatente());
		sesion.setAttribute("usuarioChoferPlanViejo", plan.getChofer().getUsuario());
		sesion.setAttribute("precioStringPlanViejo",String.valueOf(plan.getPrecio()));
        

		//FIN - SETEAR VALORES ORIGINALES (ANTES DE CAMBIAR)
		
		
		response.sendRedirect("plandeviaje.jsp");	
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
