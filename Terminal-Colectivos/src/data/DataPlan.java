package data;

import java.sql.*;
import java.util.*;
import java.util.Date;

import controlers.FechaControlers;
import entities.*;
import util.AppDataException;

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

	public ArrayList<Plan> getAll() throws AppDataException
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
			throw new AppDataException(e, "Error al recuperar todos los viajes de la base de datos.");
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
				throw new AppDataException(e, "Error al intentar cerrar la base de datos.");
			} 
		}
		return planes;

	}
	
	
	
	public ArrayList<Plan> getViajesDia(String origen, String destino, Date fechaViaje) throws AppDataException
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
			throw new AppDataException(e, "Error al recuperar viajes del día");
		}
		
		try 
		{
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			Conectar.getInstancia().releasedConn();
		} catch(SQLException e) 
		{
			throw new AppDataException(e, "Error al intentar cerrar la base de datos.");
		} 
		
		return planes;
		
		
	}
		
	
	public Plan getByFechaHoraRutaPatente(Date fechaHoraViaje, int cod_ruta, String patente) throws AppDataException
	{
		
			
		String sql= sqlBuscarPlanes + " WHERE pla.fecha_hora_plan = ? and pla.cod_ruta= ? and pla.patente= ? ";
	

			
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Plan plan = new Plan();
	
		
		try 
		{
			
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setTimestamp(1, new Timestamp(fechaHoraViaje.getTime()));
			pstmt.setInt(2, cod_ruta);
			pstmt.setString(3, patente);
					 
			
			rs = pstmt.executeQuery();
			
			if(rs!=null) 
			{
				while(rs.next()) 
				{
					plan = setPlan(rs);
									
				}
			}
		}
		
		catch(SQLException e) 
		{
			throw new AppDataException(e, "Error al recuperar plan de viaje de la base de datos.");

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
				throw new AppDataException(e, "Error al intentar cerrar la base de datos.");

			} 
		}
		return plan;
		
		
	}
	
	public ArrayList<Plan> getViajesxChofer(Usuario chofer) throws AppDataException
	{
		
			
		String sql= "SELECT DISTINCT fecha_hora_plan , patente , cod_ruta  FROM planes \r\n" + 
				"where usuario_chofer= ? " ;
		
		ArrayList<Plan> planes = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
	
		
		try 
		{
			
				pstmt = Conectar.getInstancia().getConn().prepareStatement(sql); //Origen y destino sin especificar	
				pstmt.setString(1, chofer.getUsername());
			
			rs = pstmt.executeQuery();
			
			if(rs!=null) 
			{
				while(rs.next()) 
				{
					
					Plan plan= new Plan();
					
					DataPlan dplan= new DataPlan();
					 FechaControlers fec= new FechaControlers();
				
						
				
					String fecPlan=(rs.getString("fecha_hora_plan"));
					Date fechaPlan=fec.fechaConGuion(fecPlan);
					String patente= rs.getString("patente");
					int codRuta=rs.getInt("cod_ruta");
					plan=dplan.getByFechaHoraRutaPatente(fechaPlan, codRuta, patente);
					
					planes.add(plan);

				}
									
				
				
			}
		}
		catch(SQLException e) 
		{
			throw new AppDataException(e, "Error al intentar recuperar viajes por chofer en la base de datos");
		}
		
		try 
		{
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			Conectar.getInstancia().releasedConn();
		} catch(SQLException e) 
		{
			throw new AppDataException(e, "Error al intentar cerrar la base de datos");
		} 
		
		return planes;
		
		
	}
	
	
	
	private Plan setPlan(ResultSet rs) throws AppDataException 
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
		chofer = dusu.getByUsername(usuario_chofer);
		colectivo = dcol.getByPatente(patente);
		
		
		//INICIO - MANEJO DE FECHAS 
		
		Timestamp fecha_hora_plan_timestamp = rs.getTimestamp("pla.fecha_hora_plan");
		

		Date fecha_hora_plan = new Date(fecha_hora_plan_timestamp.getTime());
		
		plan.setFechaHora(fecha_hora_plan);
		
		//FIN - MANEJO DE FECHAS 
		
		
		

		plan.setRuta(ruta);
		plan.setChofer(chofer);
		plan.setColectivo(colectivo);
		plan.setPrecio(rs.getDouble("pla.precio"));
		plan.setOrigen(rs.getString(7)); //"lor.nombre" ME TIRA ERROR
		plan.setDestino(rs.getString(8));
		

		
					
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new AppDataException(e, "Error al intentar recuperar plan de viaje de la base de datos.");
			}
	
			
			return plan;
		}
	
	public int editarPlan(Plan planViejo, Plan planNuevo) throws AppDataException
	{
	PreparedStatement pstmt = null;
	
	String sql = "UPDATE planes SET fecha_hora_plan = ?, patente = ?, cod_ruta = ?, precio= ?, usuario_chofer= ? WHERE fecha_hora_plan= ? AND patente= ? and cod_ruta= ?; ";
	
	int filasAfectadas = 0;
	
	try 
	{
		pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
		pstmt.setTimestamp(1, new Timestamp(planNuevo.getFechaHora().getTime()));
		pstmt.setString(2, planNuevo.getColectivo().getPatente());
		pstmt.setInt(3, planNuevo.getRuta().getCod_ruta());
		pstmt.setDouble(4, planNuevo.getPrecio());
		pstmt.setString(5, planNuevo.getChofer().getUsername());
		pstmt.setTimestamp(6, new Timestamp(planViejo.getFechaHora().getTime()));
		pstmt.setString(7, planViejo.getColectivo().getPatente());
		pstmt.setInt(8, planViejo.getRuta().getCod_ruta()) ;



	    filasAfectadas = pstmt.executeUpdate();
	    		
		
		
	} catch(SQLException e) 
	{
		
		throw new AppDataException(e, "Error al intentar editar plan de viaje en la base de datos.");
	}
	finally 
	{
		try 
		{
			if(pstmt!=null) {pstmt.close();}
			Conectar.getInstancia().releasedConn();
			
			
			
		} catch(SQLException e) 
		{
			throw new AppDataException(e, "Error al intentar cerrar la base de datos.");		
		}
			
		
	}
	return filasAfectadas;
	}
	
	
	
	public void eliminarPlan(Plan plan) throws AppDataException 
	{
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM planes where fecha_hora_plan = ? and cod_ruta = ? and patente = ? ";		
		
		DataReserva dRes = new DataReserva();
		DataPasajeroReserva dPasRes = new DataPasajeroReserva();
		DataReservaPlan dResPlan = new DataReservaPlan();
		
		dPasRes.eliminarPasajerosReservaxPlan(plan);
		dRes.eliminarReservasxPlan(plan); //probar que funcione correctamente
		dResPlan.eliminarPlanesReservasxPlan(plan);
		
		try 
		{
			
			
		pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
		
			pstmt.setTimestamp(1, new Timestamp(plan.getFechaHora().getTime()));
			pstmt.setInt(2, plan.getRuta().getCod_ruta());
			pstmt.setString(3, plan.getColectivo().getPatente());
			
			
			pstmt.executeUpdate();			
			
			
			
		} catch(SQLException e) 
		{
			
			throw new AppDataException(e, "Error al intentar eliminar el plan de viaje de la base de datos.");

		}
		
		try 
		{
			if(pstmt!=null) {pstmt.close();}
			Conectar.getInstancia().releasedConn();
			
			
			
		} catch(SQLException e) 
		{
			throw new AppDataException(e, "Error al intentar cerrar la base de datos.");
		
		}
			
			
		
	}
	
	
	
	
	public int addPlan( Plan nuevoPlan) throws AppDataException
	{
	PreparedStatement pstmt = null;
	
	int filasAgregadas = 0;
	
	
	String sql = "INSERT INTO  planes  (fecha_hora_plan , patente , cod_ruta, precio, usuario_chofer )VALUES (?,?,?,?,?) ";
	
	
	try 
	{
		pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
		pstmt.setTimestamp(1, new Timestamp(nuevoPlan.getFechaHora().getTime()));
		pstmt.setString(2, nuevoPlan.getColectivo().getPatente());
		pstmt.setInt(3,nuevoPlan.getRuta().getCod_ruta());
		pstmt.setDouble(4, nuevoPlan.getPrecio());
		pstmt.setString(5, nuevoPlan.getChofer().getUsername());



	    filasAgregadas = pstmt.executeUpdate();
	    
	    
		
	}catch(SQLException e) 
	{
		throw new AppDataException(e, "Error al intentar insertar un nuevo plan de viajes en la base de datos.");

	}
	
	
		try 
		{
			
			if(pstmt!=null) {pstmt.close();}
			Conectar.getInstancia().releasedConn();
			
			
			
		} catch(SQLException e) 
		{
			throw new AppDataException(e, "Error al intentar cerrar la base de datos.");

		}
		
		
		
	
	return filasAgregadas;
	
	}
	
	public boolean validarPlanSinExistencia(Date fechaHoraViaje, int cod_ruta, String patente) throws AppDataException 
	{
		Plan plan = new Plan();
		
		plan = this.getByFechaHoraRutaPatente(fechaHoraViaje, cod_ruta, patente);
		
		if(plan.getFechaHora()==null) 
		{
			return true;
		}
		else {return false;}
	}

}
