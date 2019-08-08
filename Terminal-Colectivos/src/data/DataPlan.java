package data;

import java.sql.*;
import java.util.*;
import java.util.Date;

import entities.*;

public class DataPlan {
	
	private String sqlBuscarPlanes = "SELECT DISTINCT pla.fecha_hora_plan,pla.cod_ruta ,pla.fecha_hora_plan,pla.patente,pla.usuario_chofer,pla.precio,lor.nombre,ldr.nombre\r\n" + 
			"FROM planes pla\r\n" + 
			"INNER JOIN (\r\n" + 
			"SELECT pla.cod_ruta, pla.fecha_hora_plan, loc.nombre\r\n" + 
			"FROM planes pla\r\n" + 
			"INNER JOIN escalas esc on esc.cod_ruta=pla.cod_ruta\r\n" + 
			"INNER JOIN terminales ter on esc.cod_terminal=ter.cod_terminal\r\n" + 
			"INNER JOIN localidades loc on loc.id_localidad=ter.id_localidad\r\n" + 
			"where esc.orden=0\r\n" + 
			") lor ON pla.cod_ruta = lor.cod_ruta\r\n" + 
			"INNER JOIN (\r\n" + 
			"SELECT pla.cod_ruta, pla.fecha_hora_plan, loc.nombre\r\n" + 
			"FROM planes pla\r\n" + 
			"INNER JOIN escalas esc ON esc.cod_ruta=pla.cod_ruta\r\n" + 
			"INNER JOIN terminales ter ON esc.cod_terminal=ter.cod_terminal\r\n" + 
			"INNER JOIN localidades loc ON loc.id_localidad=ter.id_localidad\r\n" + 
			"where esc.orden=1) ldr ON pla.fecha_hora_plan = ldr.fecha_hora_plan\r\n";	

	public ArrayList<Plan> getAll()
	{
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Plan> planes = new ArrayList<>();
		Plan plan = new Plan();
		
		
		try 
		{
			stmt = Conectar.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(sqlBuscarPlanes);
			if(rs!=null) 
			{
				while(rs.next()) 
				{
					
					plan = setPlan(rs);
					planes.add(plan);
					

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
				if(stmt!=null) stmt.close();
				Conectar.getInstancia().releasedConn();
			} catch(SQLException e) 
			{
				e.printStackTrace();
			} 
		}
		return planes;

	}
	
	public ArrayList<Plan> getViajesDia(String origen, String destino, Date fechaViaje)
	{
		
			
		String sql= sqlBuscarPlanes + " WHERE lor.nombre=? and ldr.nombre=? and DATE(pla.fecha_hora_plan) = DATE(?) \r\n";
		String sqlFecha= sqlBuscarPlanes + " WHERE DATE(pla.fecha_hora_plan) = DATE(?) \r\n";
		String sqlOrigenFecha= sqlBuscarPlanes + " WHERE lor.nombre=? and DATE(pla.fecha_hora_plan) = DATE(?)  \r\n";
		String sqlDestinoFecha= sqlBuscarPlanes + " WHERE ldr.nombre=? and DATE(pla.fecha_hora_plan) = DATE(?) \r\n";

		
		
		
		ArrayList<Plan> planes = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Plan plan = new Plan();
	
		
		try 
		{
			
			if(origen.equals("Cualquiera") && destino.equals("Cualquiera")) 
			{
				pstmt = Conectar.getInstancia().getConn().prepareStatement(sqlFecha); //Origen y destino sin especificar
				
				//INICIO - MANEJO DE FECHAS
				Timestamp fechaViajeTS = new Timestamp(fechaViaje.getTime());
                
				//FIN - MANEJO DE FECHAS
				
				pstmt.setTimestamp(1, fechaViajeTS );

			}
			
			else if(origen.equals("Cualquiera")) 
			{
				pstmt = Conectar.getInstancia().getConn().prepareStatement(sqlOrigenFecha); //Origen sin especificar
				pstmt.setString(1, destino);
				pstmt.setTimestamp(2, new Timestamp(fechaViaje.getTime()));

			}
			
			else if(destino.equals("Cualquiera")) 
			{
				pstmt = Conectar.getInstancia().getConn().prepareStatement(sqlDestinoFecha); //Destino sin especificar
				pstmt.setString(1, origen);
				pstmt.setTimestamp(2, new Timestamp(fechaViaje.getTime()));
			}
			
			else {
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setString(1, origen);
			pstmt.setString(2, destino);
			pstmt.setTimestamp(3, new Timestamp(fechaViaje.getTime()));
			}
		
			 
			
			rs = pstmt.executeQuery();
			
			if(rs!=null) 
			{
				while(rs.next()) 
				{
					plan = setPlan(rs);
					planes.add(plan);
				
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
		return planes;
		
		
	}
		
	
	
	
	
	private Plan setPlan(ResultSet rs) 
	{
		 Plan plan = new Plan();
		 Colectivo colectivo = new Colectivo();
		 Ruta ruta = new Ruta();
		 Usuario chofer = new Usuario();
		
		 DataColectivo dcol = new DataColectivo();
		 DataUsuario dusu = new DataUsuario();
		 DataRuta drut = new DataRuta();
		 
	
			
	try {
		
		
		String patente = rs.getString("pla.patente");
		int cod_ruta = rs.getInt("pla.cod_ruta");
		String usuario_chofer = rs.getString("pla.usuario_chofer");
		
		ruta = drut.getByRuta(cod_ruta);
		chofer = dusu.getByUsuario(usuario_chofer);
		colectivo = dcol.getByPatente(patente);
		
		
		//INICIO - MANEJO DE FECHAS 
		
		Timestamp fecha_hora_plan_timestamp = rs.getTimestamp("pla.fecha_hora_plan");
		

		Date fecha_hora_plan = new Date(fecha_hora_plan_timestamp.getTime());
		
		plan.setFechaHora(fecha_hora_plan);
		
		//FIN - MANEJO DE FECHAS 
		
		
		

		plan.setRuta(ruta);
		plan.setChofer(chofer);
		plan.setColectivo(colectivo);
		plan.setOrigen(rs.getString("lor.nombre"));
		plan.setDestino(rs.getString("ldr.nombre"));
		plan.setPrecio(rs.getDouble("pla.precio"));
		

		
					
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			
			return plan;
		}
	
	/*public void editarPlan(Plan planViejo, Plan planNuevo)
	{
	PreparedStatement pstmt = null;
	
	String fechayHoraNueva = planNuevo.getFecha() + " " + planNuevo.getHora();
	
	String sql = "UPDATE planes SET fecha_hora_plan = ?, patente = ?, cod_ruta = ?, precio= ?, usuario_chofer= ? WHERE DATE(fecha_hora_plan)= ? AND DATE_FORMAT(fecha_hora_plan, '%H:%i') = ? AND patente= ? and cod_ruta= ?; ";
	
	
	try 
	{
		pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
		pstmt.setString(1, fechayHoraNueva);
		pstmt.setString(2, planNuevo.getColectivo().getPatente());
		pstmt.setInt(3, planNuevo.getRuta().getCod_ruta());
		pstmt.setDouble(4, planNuevo.getPrecio());
		pstmt.setString(5, planNuevo.getChofer().getUsuario());
		pstmt.setString(6, planViejo.getFecha());
		pstmt.setString(7, planViejo.getHora());
		pstmt.setString(8, planViejo.getColectivo().getPatente());
		pstmt.setString(9, planViejo.getChofer().getUsuario()) ;



	    pstmt.executeUpdate();
		
		
	}catch(SQLException e) { 
		e.printStackTrace();
		}
	}
	
	
	public Integer eliminarPlan(Plan plan) 
	{
		PreparedStatement pstmt = null;
		String sql = "WHERE DATE(fecha_hora_plan)= ? AND DATE_FORMAT(fecha_hora_plan, '%H:%i') = ? AND patente= ? and cod_ruta= ? ";
		Integer filasAfectadas = 0;
		
		try 
		{
		pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
		
			pstmt.setString(1, plan.getFecha());
			pstmt.setString(2, plan.getHora());
			pstmt.setString(3, plan.getColectivo().getPatente());
			pstmt.setString(4, plan.getChofer().getUsuario());
			
			
			filasAfectadas = pstmt.executeUpdate();			
			
			
			
		} catch(SQLException e) 
		{
			
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(pstmt!=null) {pstmt.close();}
				Conectar.getInstancia().releasedConn();
				
				
				
			} catch(SQLException e) {e.printStackTrace();
			
			}
			
			
			
		}
		return filasAfectadas;
		
	}
	*/
	public void addPlan( Plan nuevoPlan)
	{
	PreparedStatement pstmt = null;

	
	
	String sql = "INSERT INTO  planes  (fecha_hora_plan , patente , cod_ruta, precio, usuario_chofer )VALUES (?,?,?,?,?) ";
	
	
	try 
	{
		pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
		pstmt.setTimestamp(1, new Timestamp(nuevoPlan.getFechaHora().getTime()));
		pstmt.setString(2, nuevoPlan.getColectivo().getPatente());
		pstmt.setInt(3,nuevoPlan.getRuta().getCod_ruta());
		pstmt.setDouble(4, nuevoPlan.getPrecio());
		pstmt.setString(5, nuevoPlan.getChofer().getUsuario());



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

}
