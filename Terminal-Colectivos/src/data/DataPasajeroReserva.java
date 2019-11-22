package data;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import entities.Pasajero;
import entities.Pasajero_Reserva;
import entities.Plan;
import entities.Reserva;

public class DataPasajeroReserva {
	
	
	
	
	public void agregarPasajeroReserva(Pasajero_Reserva pasajero_reserva) 
	{
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO pasajeros_reservas (dni, fecha_res, usuario, asiento) VALUES (?,?,?,?) ";
		
		
		
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setInt(1, pasajero_reserva.getPasajero().getDni());
			pstmt.setTimestamp(2, new Timestamp(pasajero_reserva.getReserva().getFecha_res().getTime()));
			pstmt.setString(3, pasajero_reserva.getReserva().getUsuario().getUsername());
			pstmt.setInt(4, pasajero_reserva.getAsiento());	
				
		    pstmt.executeUpdate();
			
			
 		}catch(SQLException e) { 
 			e.printStackTrace();
 			}
		
		finally 
		{
			try 
			{
				
				if(pstmt!=null) {pstmt.close();}
				Conectar.getInstancia().releasedConn();
				
			} catch(SQLException e) {e.printStackTrace();}
		}
	}
	
	public int getUltimoAsientoxPlan(Plan plan) 
	{
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		int ultimoAsiento = 0;
		
		String sql = "SELECT pl.fecha_hora_plan, pl.patente, pl.cod_ruta, max(asiento) \r\n" + 
				"FROM pasajeros_reservas pasres\r\n" + 
				"INNER JOIN reservas re on pasres.fecha_res = re.fecha_res and re.usuario = pasres.usuario\r\n" + 
				"INNER JOIN planes_reservas planres on planres.fecha_res = re.fecha_res and planres.usuario_reserva = re.usuario\r\n" + 
				"INNER JOIN planes pl on pl.fecha_hora_plan = planres.fecha_hora_plan and pl.cod_ruta = planres.cod_ruta and pl.patente = planres.patente\r\n" + 
				"GROUP BY pl.fecha_hora_plan, pl.patente, pl.cod_ruta\r\n" + 
				"HAVING pl.fecha_hora_plan = ? and pl.cod_ruta = ? and pl.patente = ? ";
		
		
		
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setTimestamp(1, new Timestamp(plan.getFechaHora().getTime()));
			pstmt.setInt(2, plan.getRuta().getCod_ruta());
			pstmt.setString(3, plan.getColectivo().getPatente());	
			
		    rs = pstmt.executeQuery();
			
		    if(rs!=null && rs.next()) 
		    {
		    	ultimoAsiento = rs.getInt(4) ;
		    }
			
 		}catch(SQLException e) { 
 			e.printStackTrace();
 			}
		
		finally 
		{
			try 
			{
				if(rs!=null) {rs.close();}
				if(pstmt!=null) {pstmt.close();}
				Conectar.getInstancia().releasedConn();
				
			} catch(SQLException e) {e.printStackTrace();}
		}
		
		return ultimoAsiento+1;

	}
	
	public ArrayList<Pasajero> getPasajerosxReserva(Reserva reserva)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM pasajeros_reservas pasres \r\n" + 
				"INNER JOIN pasajeros pas  on pas.dni=pasres.dni \r\n" + 
				"INNER JOIN reservas res on res.fecha_res = pasres.fecha_res and res.usuario= pasres.usuario\r\n" +  
				" where pasres.fecha_res = ? and pasres.usuario = ? and (res.fecha_canc is null)";
		ArrayList<Pasajero> pasajeros = new ArrayList<Pasajero>();
		try 
		{
			java.util.Date fechaHoraReserva = reserva.getFecha_res();
			
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql) ;
			
			pstmt.setTimestamp(1, new Timestamp(fechaHoraReserva.getTime()));
			pstmt.setString(2, reserva.getUsuario().getUsername());
			
			
			rs = pstmt.executeQuery();
			
			
				while(rs.next()) 
				{
					Pasajero pasajero = new Pasajero();
					
					pasajero.setDni(rs.getInt("pasres.dni"));
					pasajero.setNombre(rs.getString("nombre"));
					pasajero.setApellido(rs.getString("apellido"));
					pasajeros.add(pasajero);
					
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally
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
		
	return pasajeros;	
	}
	
	public ArrayList<Pasajero_Reserva> getPasajerosxPlan (Plan plan)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT DISTINCT pasres.dni, pasres.fecha_res, pasres.usuario , pasres.asiento\r\n" + 
				"FROM planes_reservas planres\r\n" + 
				"INNER JOIN reservas r on r.fecha_res = planres.fecha_res and r.usuario= planres.usuario_reserva\r\n" + 
				"INNER JOIN planes p on p.cod_ruta=planres.cod_ruta and p.patente=planres.patente and p.fecha_hora_plan= planres.fecha_hora_plan\r\n" + 
				"INNER JOIN pasajeros_reservas  pasres on planres.fecha_res = r.fecha_res and pasres.usuario = planres.usuario_reserva\r\n" + 
				"INNER JOIN pasajeros pas on pas.dni=pasres.dni\r\n" + 
				"WHERE (r.fecha_canc is null) and (asiento is not null) \r\n" + 
				"and p.fecha_hora_plan = ? and p.cod_ruta= ? and p.patente= ? "
				+ "ORDER BY pasres.asiento";
		
		
		ArrayList<Pasajero_Reserva> pasajeros_reservas = new ArrayList<Pasajero_Reserva>();
		
		
		try 
		{

			
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql) ;
			
			pstmt.setTimestamp(1, new Timestamp(plan.getFechaHora().getTime()));
			pstmt.setInt(2, plan.getRuta().getCod_ruta());
			pstmt.setString(3, plan.getColectivo().getPatente());
			
			
			rs = pstmt.executeQuery();
			
			
				while(rs.next()) 
				{
					Pasajero_Reserva pasajero_reserva = new Pasajero_Reserva();
					
					Reserva reserva = new Reserva();
					Pasajero pasajero = new Pasajero();
					
					DataReserva dRes = new DataReserva();
					DataPasajero dPas = new DataPasajero();
					
					Date fechaReserva = new Date(rs.getTimestamp("pasres.fecha_res").getTime());
					String usuario = rs.getString("pasres.usuario");
					
					int dni = rs.getInt("pasres.dni");
					
					reserva = dRes.getByFechaUsuario(fechaReserva, usuario);
					pasajero = dPas.getByDni(dni);
					
					
					pasajero_reserva.setPasajero(pasajero);
					pasajero_reserva.setReserva(reserva);
					pasajero_reserva.setAsiento(rs.getInt("pasres.asiento"));
					
					pasajeros_reservas.add(pasajero_reserva);							
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally
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
		
	return pasajeros_reservas;	
	}
	
	
	
}
		

	
	
	
	


