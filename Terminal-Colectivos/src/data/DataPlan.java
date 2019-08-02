package data;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
	
	public ArrayList<Plan> getViajesDia(String origen, String destino, String fecha)
	{
		
			
		String sql= sqlBuscarPlanes + " WHERE lor.nombre=? and ldr.nombre=? and DATE(pla.fecha_hora_plan)=? \r\n";
		String sqlFecha= sqlBuscarPlanes + " WHERE DATE(pla.fecha_hora_plan)=? \r\n";
		String sqlOrigenFecha= sqlBuscarPlanes + " WHERE lor.nombre=? and DATE(pla.fecha_hora_plan)=? \r\n";
		String sqlDestinoFecha= sqlBuscarPlanes + " WHERE ldr.nombre=? and DATE(pla.fecha_hora_plan)=? \r\n";

		
		//Sólo dice el precio y el horario de salida correcto para el lugar de origen, pero no para los viajes intermedios
		//P.E.: Funciona para viajes Venado (Origen) - Rosario (Destino), pero no para Murphy (Origen) - Firmat (Destino)
		
		
		ArrayList<Plan> planes = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Plan plan = new Plan();
	
		
		try 
		{
			
			if(origen.equals("Cualquiera") && destino.equals("Cualquiera")) 
			{
				pstmt = Conectar.getInstancia().getConn().prepareStatement(sqlFecha); //Origen y destino sin especificar
				pstmt.setString(1, fecha);

			}
			
			else if(origen.equals("Cualquiera")) 
			{
				pstmt = Conectar.getInstancia().getConn().prepareStatement(sqlOrigenFecha); //Origen sin especificar
				pstmt.setString(1, destino);
				pstmt.setString(2, fecha);

			}
			
			else if(destino.equals("Cualquiera")) 
			{
				pstmt = Conectar.getInstancia().getConn().prepareStatement(sqlDestinoFecha); //Destino sin especificar
				pstmt.setString(1, origen);
				pstmt.setString(2, fecha);
			}
			
			else {
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setString(1, origen);
			pstmt.setString(2, destino);
			pstmt.setString(3, fecha);
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
		Calendar fecha_hora_plan = Calendar.getInstance();
		fecha_hora_plan.setTimeInMillis(fecha_hora_plan_timestamp.getTime());
		
		SimpleDateFormat formatoFecha= new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatoHora= new SimpleDateFormat("H:mm");					
		
		plan.setFecha(formatoFecha.format(fecha_hora_plan.getTime()));
		plan.setHora(formatoHora.format(fecha_hora_plan.getTime()));
		
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



}
