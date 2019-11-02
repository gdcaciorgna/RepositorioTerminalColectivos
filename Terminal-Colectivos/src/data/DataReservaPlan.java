package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import entities.Plan;
import entities.Plan_Reserva;
import entities.Reserva;

public class DataReservaPlan {
	
	private String sqlReservasdeunPlan = "SELECT pl.fecha_hora_plan, pl.cod_ruta, pl.patente, re.fecha_res, re.usuario, re.cant_pas, pl.precio, pl.usuario_chofer, re.fecha_canc, re.cod_compania, re.nro_tarjeta, pas.dni    \r\n" + 
			"FROM reservas re \r\n" + 
			"inner join planes_reservas pr on re.fecha_res = pr.fecha_res and re.usuario = pr.usuario_reserva \r\n" + 
			"inner join planes pl on pl.fecha_hora_plan = pr.fecha_hora_plan and pr.cod_ruta = pl.cod_ruta and pr.patente = pl.patente \r\n" + 
			"inner join pasajeros_reservas pasres on pasres.fecha_res = re.fecha_res and pasres.usuario = re.usuario \r\n" + 
			"inner join pasajeros pas on pas.dni = pasres.dni \r\n" + 
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


}
