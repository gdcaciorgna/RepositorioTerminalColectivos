package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import controlers.FechaControlers;
import entities.Plan;
import entities.Plan_Reserva;
import entities.Reserva;
import entities.Usuario;

public class DataReservaPlan {
	
	private String sqlReservasdeunPlan = "SELECT DISTINCT pl.fecha_hora_plan, pl.cod_ruta, pl.patente, re.fecha_res, re.usuario, re.cant_pas, pl.precio, pl.usuario_chofer, re.fecha_canc, re.cod_compania, re.nro_tarjeta  \r\n" + 
			"FROM reservas re \r\n" + 
			"inner join planes_reservas pr on re.fecha_res = pr.fecha_res and re.usuario = pr.usuario_reserva \r\n" + 
			"inner join planes pl on pl.fecha_hora_plan = pr.fecha_hora_plan and pr.cod_ruta = pl.cod_ruta and pr.patente = pl.patente \r\n" + 
			"inner join pasajeros_reservas pasres on pasres.fecha_res = re.fecha_res and pasres.usuario = re.usuario \r\n" + 
			"where pr.fecha_hora_plan = ? and pr.patente = ? and pr.cod_ruta = ? ";	

	
	public ArrayList<Plan_Reserva> getReservasPlan(Plan planElegido)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Plan_Reserva> planes_reservas = new ArrayList<>();
		Plan_Reserva plan_reserva = new Plan_Reserva();
		
		
		
		
		
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sqlReservasdeunPlan);
			pstmt.setTimestamp(1, new Timestamp(planElegido.getFechaHora().getTime()));
			pstmt.setString(2, planElegido.getColectivo().getPatente());
			pstmt.setInt(3, planElegido.getRuta().getCod_ruta());
			
			
			rs = pstmt.executeQuery();
			
			if(rs!=null) 
			{
				while(rs.next()) 
				{
					Reserva reserva = new Reserva();
					Plan plan = new Plan();
					
					
					DataPlan dplan = new DataPlan();
					DataReserva dres = new DataReserva();
					
					String patente = rs.getString("pl.patente");
					int cod_ruta = rs.getInt("pl.cod_ruta");
					
					String username_reserva = rs.getString("re.usuario");
							
					
					

					//INICIO - MANEJO DE FECHAS 
					
					Timestamp fecha_hora_plan_timestamp = rs.getTimestamp("pl.fecha_hora_plan");
					Date fecha_hora_plan = new Date(fecha_hora_plan_timestamp.getTime());
				
					
					Timestamp fecha_res_timestamp = rs.getTimestamp("re.fecha_res");
					Date fecha_res = new Date(fecha_res_timestamp.getTime());

					
					//FIN - MANEJO DE FECHAS 
					
					plan = dplan.getByFechaHoraRutaPatente(fecha_hora_plan, cod_ruta, patente);
					
					
					
					reserva = dres.getByFechaUsuario(fecha_res , username_reserva);
										
					
					plan_reserva.setPlan(plan);
					plan_reserva.setReserva(reserva);
					
					planes_reservas.add(plan_reserva);
				}
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				Conectar.getInstancia().releasedConn();
			} catch(SQLException e) 
			{
				e.printStackTrace();
			} 
		}
		
		return planes_reservas;

	}
	
	
	
	public ArrayList<Plan_Reserva> getReservasxUsuario(Usuario usu)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Plan_Reserva> reservas = new ArrayList<>();
		Plan_Reserva reserva = new Plan_Reserva();
		String sql = "select * from planes_reservas planres \r\n" + 
				"inner join reservas res\r\n" + 
				"where usuario_reserva= ? and (fecha_canc is null)\r\n" + 
				"order by fecha_hora_plan";
		
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setString(1, usu.getUsername());
			
			rs = pstmt.executeQuery();
			
				while(rs.next()) 
				{
					
					reserva = setPlanReserva(rs);
					reservas.add(reserva);
					

				}
			
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				Conectar.getInstancia().releasedConn();
			} catch(SQLException e) 
			{
				e.printStackTrace();
			} 
		}
		return reservas;

	}	
	
	
	private Plan_Reserva setPlanReserva(ResultSet rs) 
	{   
		
		Plan_Reserva planRes= new Plan_Reserva();
		Plan plan= new Plan();
		Reserva reserva=new Reserva();
		DataPlan dplan= new DataPlan();
		 FechaControlers fec= new FechaControlers();
	    DataReserva dres= new DataReserva();
			
	try {
		String fecPlan=(rs.getString("fecha_hora_plan"));
		Date fechaPlan=fec.fechaConGuion(fecPlan);
		String patente= rs.getString("patente");
		int codRuta=rs.getInt("cod_ruta");
		plan=dplan.getByFechaHoraRutaPatente(fechaPlan, codRuta, patente);
		
		String fecRes = rs.getString("fecha_res");
		String username = rs.getString("usuario_reserva");
		Date fechaRes= fec.fechaConGuion(fecRes);
		reserva=dres.getByFechaUsuario( fechaRes,  username);
		
		planRes.setReserva(reserva);
		planRes.setPlan(plan);
		
		
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			
			return planRes;}
		
	
	
	
	
	
	public Plan_Reserva getReservaPlanbyClavesPrimarias(String fechaHoraReserva, String fechaHoraViaje, String patenteColectivoViaje, int codRutaViaje, String UsernameReserva) 
	{
		Reserva reserva = new Reserva();
		DataReserva dres = new DataReserva();
		DataPlan dplan = new DataPlan();
		Plan plan = new Plan();
		
		FechaControlers fCon = new FechaControlers();
		
		Date fechaHoraReservaDate = fCon.ddMMyyyyHHmmssToDate(fechaHoraReserva);
		
		Date fechaHoraViajeDate = fCon.ddMMyyyyHHmmToDate(fechaHoraViaje);
		
		reserva = dres.getByFechaUsuario(fechaHoraReservaDate, UsernameReserva);
		
		plan = dplan.getByFechaHoraRutaPatente(fechaHoraViajeDate, codRutaViaje, patenteColectivoViaje);
		
		
		Plan_Reserva planReserva = new Plan_Reserva();
		planReserva.setPlan(plan);
		planReserva.setReserva(reserva);
		
		
		
		return planReserva;
		
		
	}
	
	
	
	
	
	
	
	
	
	public  void agregarReservaPlan(Plan_Reserva planReserva) {
		
		
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO planes_reservas (fecha_res, fecha_hora_plan, patente, usuario_reserva,  cod_ruta) VALUES (?,?,?,?,?) ";
		
		
		
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setTimestamp(1, new Timestamp(planReserva.getReserva().getFecha_res().getTime()));
			pstmt.setTimestamp(2, new Timestamp(planReserva.getPlan().getFechaHora().getTime()));
			pstmt.setString(3, planReserva.getPlan().getColectivo().getPatente());
			pstmt.setString(4, planReserva.getReserva().getUsuario().getUsername());
			pstmt.setInt(5, planReserva.getPlan().getRuta().getCod_ruta());
		
			
		    pstmt.executeUpdate();
			
			
 		}catch(SQLException e) { e.printStackTrace();}
		
		finally 
		{
			try 
			{
				
				if(pstmt!=null) {pstmt.close();}
				Conectar.getInstancia().releasedConn();
				
				
				
			} catch(SQLException e) {e.printStackTrace();}
		}}}
	
	
	
	
	
	


