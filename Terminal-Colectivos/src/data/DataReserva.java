package data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import entities.Compania_Tarjeta;
import entities.Reserva;
import entities.Usuario;

public class DataReserva {
	
	
	public  void agregarReserva(Reserva reserva, int codCompania, String fechaHoraString) {
		
		
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO reservas (fecha_res, usuario, cant_pas, fecha_canc, cod_compania, nro_tarjeta) VALUES (?,?,?,?,?,?) ";
		
		
		
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setString(1, fechaHoraString);
			pstmt.setString(2, reserva.getUsuario().getUsername());
			pstmt.setInt(3, reserva.getCant_pas());
			pstmt.setString(4, null);
			pstmt.setInt(5, reserva.getCant_pas());
			pstmt.setString(6, reserva.getNro_tarjeta());
		
			
		    pstmt.executeUpdate();
			
			
		}catch(SQLException e) { e.printStackTrace();}
		
		finally 
		{
			try 
			{
				
				if(pstmt!=null) {pstmt.close();}
				Conectar.getInstancia().releasedConn();
				
				
				
			} catch(SQLException e) {e.printStackTrace();}
		}	
		
		}
		
		
		
		
		
		
		
		
		
		
	
	
	
	
	
	public Reserva getByFechaUsuario(Date fecha_res, String username)
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
				
				Timestamp fecha_reserva_timestamp = rs.getTimestamp("fecha_res");
				Date fecha_reserva = new Date(fecha_reserva_timestamp.getTime());
				
				
				Timestamp fecha_cancelacion_timestamp = rs.getTimestamp("fecha_res");
				Date fecha_cancelacion = new Date(fecha_cancelacion_timestamp.getTime());

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
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return res;

		
		
	}
	

	
	

}
