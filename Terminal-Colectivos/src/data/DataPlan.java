package data;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import entities.*;

public class DataPlan {
	



	public ArrayList<Plan> getAll()
	{
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM planes pla INNER JOIN rutas rut on pla.cod_ruta=rut.cod_ruta INNER JOIN escalas esc on esc.cod_ruta=rut.cod_ruta INNER JOIN terminales ter on ter.cod_terminal=esc.cod_terminal INNER JOIN usuarios usu on usu.usuario=pla.usuario_chofer INNER JOIN localidades loc on loc.id_localidad=ter.id_localidad INNER JOIN colectivos col on col.patente=pla.patente INNER JOIN empresas_colectivos ecol on ecol.id_empresa_colectivo=col.id_empresa_colectivo  INNER JOIN provincias prov on prov.id_provincia=loc.id_provincia";
		ArrayList<Plan> planes = new ArrayList<>();
		
		Plan plan = new Plan();
	
		
		try 
		{
			stmt = Conectar.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(sql);
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
		
			
		String sql= "SELECT DISTINCT pla.fecha_hora_plan,pla.cod_ruta ,pla.fecha_hora_plan,pla.patente,pla.usuario_chofer,pla.precio,lor.nombre,ldr.nombre\r\n" + 
				"FROM planes pla\r\n" + 
				"INNER JOIN (\r\n" + 
				"SELECT pla.cod_ruta, pla.fecha_hora_plan, loc.nombre\r\n" + 
				"FROM planes pla\r\n" + 
				"INNER JOIN escalas esc on esc.cod_ruta=pla.cod_ruta\r\n" + 
				"INNER JOIN terminales ter on esc.cod_terminal=ter.cod_terminal\r\n" + 
				"INNER JOIN localidades loc on loc.id_localidad=ter.id_localidad\r\n" + 
				"where esc.orden=0\r\n" + 
				") lor ON pla.cod_ruta = lor.cod_ruta\r\n" + 
				"INNER JOIN (SELECT pla.cod_ruta, pla.fecha_hora_plan, loc.nombre\r\n" + 
				"FROM planes pla \r\n" + 
				"INNER JOIN escalas esc on esc.cod_ruta=pla.cod_ruta\r\n" + 
				"INNER JOIN terminales ter on ter.cod_terminal=esc.cod_terminal\r\n" + 
				"INNER JOIN localidades loc on loc.id_localidad=ter.id_localidad\r\n" + 
				"INNER JOIN (SELECT pla.cod_ruta, MAX(orden) orden\r\n" + 
				"FROM planes pla \r\n" + 
				"INNER JOIN escalas esc ON esc.cod_ruta=pla.cod_ruta\r\n" + 
				"GROUP BY pla.cod_ruta) odr on odr.cod_ruta=pla.cod_ruta and odr.orden=esc.orden) ldr on pla.fecha_hora_plan = ldr.fecha_hora_plan\r\n" + 
				"WHERE lor.nombre=? and ldr.nombre=? and DATE(pla.fecha_hora_plan)=? \r\n";
		
		
		ArrayList<Plan> planes = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DataUsuario dusu = new DataUsuario();
		DataRuta druta = new DataRuta();
		DataColectivo dcol = new DataColectivo();
		
		Ruta ruta = new Ruta();
		Usuario chofer = new Usuario();
		Colectivo colectivo = new Colectivo();


		
		
		Plan plan = new Plan();
	
		
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setString(1, origen);
			pstmt.setString(2, destino);
			pstmt.setString(3, fecha);
			rs = pstmt.executeQuery();
			
			if(rs!=null) 
			{
				while(rs.next()) 
				{
					
					String patente = rs.getString("pla.patente");
					int cod_ruta = rs.getInt("pla.cod_ruta");
					String usuario_chofer = rs.getString("pla.usuario_chofer");
					
					ruta = druta.getByRuta(cod_ruta);
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
		 Provincia provincia = new Provincia();
		 Localidad localidad = new Localidad();
		 Terminal terminal = new Terminal();
		 Ruta ruta = new Ruta();
		 Escala escala = new Escala();
		 Usuario chofer = new Usuario();
		 Empresa_Colectivo empresa = new Empresa_Colectivo();
	
			
	try {
		
		provincia.setId_provincia(rs.getInt("prov.id_provincia"));
		provincia.setNombre(rs.getString("prov.nombre"));
		
		localidad.setId_localidad(rs.getInt("loc.id_localidad"));
		localidad.setNombre(rs.getString("loc.nombre"));
		localidad.setProvincia(provincia);
		
		empresa.setId_empresa_colectivo(rs.getInt("col.id_empresa_colectivo"));
		empresa.setNombre(rs.getString("ecol.nombre"));
		
		colectivo.setPatente(rs.getString("col.patente"));
		colectivo.setCapacidad(rs.getInt("col.capacidad"));
		colectivo.setTipo_colectivo(rs.getString("col.tipo_colectivo"));
		colectivo.setEmpresa(empresa);
		
		
		
		
		terminal.setCod_terminal(rs.getInt("ter.cod_terminal"));
		terminal.setLocalidad(localidad);
		
		ruta.setCod_ruta(rs.getInt("rut.cod_ruta"));
		ruta.setDias_sem(rs.getString("rut.dias_sem"));
		
		
		escala.setOrden(rs.getInt("esc.orden"));
		escala.setTerminal(terminal);
		escala.setRuta(ruta);
		
		chofer.setUsuario(rs.getString("usu.usuario"));
		chofer.setNombre(rs.getString("usu.nombre"));
		chofer.setApellido(rs.getString("usu.apellido"));
		chofer.setEmail(rs.getString("usu.email"));
		chofer.setEstado(rs.getString("usu.estado"));
		chofer.setPassword(rs.getString("usu.password"));
		chofer.setRol(rs.getString("usu.rol"));
		chofer.setCuil(rs.getString("usu.cuil"));
		

		//INICIO - MANEJO DE FECHAS 
		
		Timestamp fecha_hora_plan_timestamp = rs.getTimestamp("fecha_hora_plan");
		Calendar fecha_hora_plan = Calendar.getInstance();
		fecha_hora_plan.setTimeInMillis(fecha_hora_plan_timestamp.getTime());
		
		SimpleDateFormat formatoFecha= new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatoHora= new SimpleDateFormat("H:mm");					
		
		plan.setFecha(formatoFecha.format(fecha_hora_plan.getTime()));
		plan.setHora(formatoHora.format(fecha_hora_plan.getTime()));
		
		//FIN - MANEJO DE FECHAS 		
		
		plan.setColectivo(colectivo);
		plan.setRuta(ruta);
		plan.setChofer(chofer);
		plan.setPrecio(rs.getDouble("precio"));
		

		
					
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			
			return plan;
		}


}
