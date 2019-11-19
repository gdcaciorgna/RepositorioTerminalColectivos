package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Pasajero;

public class DataPasajero {
	
	public Pasajero getByDni(int dni) {
		Pasajero pasajero = new Pasajero();
		String sql = "SELECT * FROM pasajeros where dni = ?";
				
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setInt(1, dni);
			rs=pstmt.executeQuery();	
			
			//INICIO - Código sin aplicar herencia
			if(rs!=null && rs.next()) 
		{
				pasajero.setDni(dni);
				pasajero.setNombre(rs.getString("nombre"));
				pasajero.setApellido(rs.getString("apellido"));
		}
			//FIN - Código sin aplicar herencia
			
		} catch (SQLException e) {
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
		
		return pasajero;

	}
	
	public void addPasajero(Pasajero pasajero) 
	{
		String sql = "INSERT INTO pasajeros (dni, nombre, apellido) VALUES (?,?,?) ";

		
		PreparedStatement pstmt = null;
		
	
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setInt(1, pasajero.getDni());
			pstmt.setString(2, pasajero.getNombre());
			pstmt.setString(3, pasajero.getApellido());
			
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
	
	
	public void addPasajeros(ArrayList<Pasajero> pasajeros)
	{
	}
}