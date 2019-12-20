package data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import controlers.FechaControlers;

import entities.Compania_Tarjeta;
import entities.Plan;
import entities.Reserva;

import entities.Usuario;
import util.AppDataException;

public class DataReserva {
	
	
	public  void agregarReserva(Reserva reserva) throws AppDataException {
		
		
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO reservas (fecha_res, usuario, cant_pas, fecha_canc, cod_compania, nro_tarjeta) VALUES (?,?,?,?,?,?) ";
		
		
		
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setTimestamp(1, new Timestamp(reserva.getFecha_res().getTime()));
			pstmt.setString(2, reserva.getUsuario().getUsername());
			pstmt.setInt(3, reserva.getCant_pas());
			pstmt.setString(4, null);
			pstmt.setInt(5, reserva.getCompania_tarjeta().getCod_compania());
			pstmt.setString(6, reserva.getNro_tarjeta());
		
			
		    pstmt.executeUpdate();
			
			
		}catch(SQLException e) 
		{ 
 			throw new AppDataException(e, "Error al intentar ingresar reserva a la base de datos");
		}
		
		
		try 
		{
			
			if(pstmt!=null) {pstmt.close();}
			Conectar.getInstancia().releasedConn();
			
			
			
		} catch(SQLException e) 
		{
			throw new AppDataException(e, "Error al intentar cerrar la base de datos");
		}
		}	
		
		
	
	
		
	public ArrayList<Reserva> getReservasxUsuario(Usuario usu) throws AppDataException
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Reserva> reservas = new ArrayList<>();
		Reserva reserva = new Reserva();
		String sql = "select * from reservas where usuario=?";
		
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setString(1, usu.getUsername());
			
			rs = pstmt.executeQuery(sql);
			if(rs!=null) 
			{
				while(rs.next()) 
				{
					
					reserva = setReserva(rs);
					reservas.add(reserva);
					

				}
			}
		}
		catch(SQLException e) 
		{
 			throw new AppDataException(e, "Error al intentar obtener reservas del usuairo seleccionado en la base de datos");

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
		
		return reservas;

	}	
		
	private Reserva setReserva(ResultSet rs) throws AppDataException 
	{
		Reserva reserva=new Reserva();
		 Usuario usu = new Usuario();
		Compania_Tarjeta comp= new Compania_Tarjeta();
		 DataUsuario dusu = new DataUsuario();
		DataCompaniaTarjeta dcomp= new DataCompaniaTarjeta();
		 FechaControlers fec= new FechaControlers();
	
			
	try {
		
		
		String fecRes = rs.getString("fecha_res");
		String usuario = rs.getString("usuario");
		int cant_pas= rs.getInt("cant_pas");
		String fecCan = rs.getString("fecha_can");
		int cod_comp = rs.getInt("cod_compania");
		String nroTar = rs.getString("nro_tarjeta");
		Date fechaRes= fec.yyyyMMddhhmmToDate(fecRes);
		Date fechaCan= fec.yyyyMMddhhmmToDate(fecCan);
		usu= dusu.getByUsername(usuario);
		comp = dcomp.getById(cod_comp);
		
		
		reserva.setCant_pas(cant_pas);
		reserva.setCompania_tarjeta(comp);
		reserva.setUsuario(usu);
		reserva.setFecha_canc(fechaCan);
		reserva.setFecha_res(fechaRes);
		reserva.setNro_tarjeta(nroTar);
  
		

		
					
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
	 			throw new AppDataException(e, "Error al intentar recuperar reserva en la base de datos");

			}
	
			
			return reserva;}
		
		
		
		
		
		
	
	
	
	
	
	public Reserva getByFechaUsuario(Date fecha_res, String username) throws AppDataException
	{
		Usuario usuario_reserva = new Usuario();
		Compania_Tarjeta compania_tarjeta = new Compania_Tarjeta();

		DataCompaniaTarjeta dcomp = new DataCompaniaTarjeta();
		DataUsuario dusu = new DataUsuario();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * from reservas where fecha_res = ? and usuario = ? ";
		
		Reserva res = new Reserva();

		
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setTimestamp(1, new Timestamp(fecha_res.getTime()));
			pstmt.setString(2, username);

			
			rs = pstmt.executeQuery();
			
			if(rs.next()) 
			{
				
				//INICIO - MANEJO DE FECHAS 
				
				Date fecha_reserva = new Date(rs.getTimestamp("fecha_res").getTime());
				
				Timestamp fecha_cancelacionTimestamp = rs.getTimestamp("fecha_canc");
				Date fecha_cancelacion = null;
				
				if(fecha_cancelacionTimestamp!=null) {
				 fecha_cancelacion =  new Date(fecha_cancelacionTimestamp.getTime()); 				
				}
				//FIN - MANEJO DE FECHAS 
				
				
				int cod_compania = rs.getInt("cod_compania");
				compania_tarjeta = dcomp.getById(cod_compania);
				
				
				usuario_reserva = dusu.getByUsername(rs.getString("usuario"));
				
			
				res.setFecha_res(fecha_reserva);
				res.setCant_pas(rs.getInt("cant_pas"));
				res.setFecha_canc(fecha_cancelacion);
				res.setCompania_tarjeta(compania_tarjeta);
				res.setUsuario(usuario_reserva);
				res.setNro_tarjeta(("nro_tarjeta"));
				
				
				
			
			}
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		finally 
		{
			
			try {
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new AppDataException(e, "Error al intentar cerrar la base de datos");
			}
			
		}
		
		return res;

		
		
	}
	
	public void cancelarReserva(Reserva reserva) throws AppDataException 
	{
		PreparedStatement pstmt = null;
		
		String sql = "update reservas SET fecha_canc = ?  "
				+ "where usuario = ? and fecha_res = ?;";
		
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
			
			Date fechaReserva = reserva.getFecha_res();
			Date fechaCancelacion = reserva.getFecha_canc();
		
			pstmt.setTimestamp(1, new Timestamp(fechaCancelacion.getTime()));
			pstmt.setString(2, reserva.getUsuario().getUsername());
			pstmt.setTimestamp(3, new Timestamp(fechaReserva.getTime()));
			
			
			pstmt.executeUpdate();			
			
			
			
		} catch(SQLException e) 
		{
			
 			throw new AppDataException(e, "Error al intentar cancelar reserva en base de datos");

		}
		
		try 
		{
			if(pstmt!=null) {pstmt.close();}
			Conectar.getInstancia().releasedConn();
			
			
			
		} catch(SQLException e) 
		{
 			throw new AppDataException(e, "Error al intentar cerrar la base de datos");
		}
			
			
			
		
	}
	
	public void limpiarAsientos(Reserva reserva) throws AppDataException 
	{
		String sql = "UPDATE pasajeros_reservas pasres INNER JOIN\r\n" + 
				"	reservas res\r\n" + 
				"    on res.fecha_res = pasres.fecha_res and pasres.usuario = res.usuario\r\n" + 
				"    \r\n" + 
				"SET pasres.asiento = null\r\n" + 
				"WHERE res.fecha_res = ? and res.usuario = ?";
		
		PreparedStatement pstmt = null;
		
		
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
			
			Date fechaReserva = reserva.getFecha_res();
		
			pstmt.setTimestamp(1, new Timestamp(fechaReserva.getTime()));
			pstmt.setString(2, reserva.getUsuario().getUsername());
			
			
			pstmt.executeUpdate();			
			
			
			
		} catch(SQLException e) 
		{
			
 			throw new AppDataException(e, "Error al intentar limpiar asientos en la base de datos");
		}
		
		try 
		{
			if(pstmt!=null) {pstmt.close();}
			Conectar.getInstancia().releasedConn();
			
			
			
		} catch(SQLException e) 
		{
 			throw new AppDataException(e, "Error al intentar cerrar la base de datos");		
		}
		
		
		
	}
	

	public void eliminarReservasxPlan(Plan plan) throws AppDataException 
	{
		PreparedStatement pstmt = null;

		String sql = "DELETE re FROM reservas re inner join planes_reservas pr on re.fecha_res = pr.fecha_res and re.usuario = pr.usuario_reserva where fecha_hora_plan = ? and pr.cod_ruta = ? and pr.patente = ?";
			
		
		try 
		{
			
			
		pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
		
			pstmt.setTimestamp(1, new Timestamp(plan.getFechaHora().getTime()));
			pstmt.setInt(2, plan.getRuta().getCod_ruta());
			pstmt.setString(3, plan.getColectivo().getPatente());
			
			
			
			pstmt.executeUpdate();			
			
			
			
		} catch(SQLException e) 
		{
			
			throw new AppDataException(e, "Error al intentar eliminar filas de la tabla reservas en baase de datos.");

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
	

}
