package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Pasajero;
import util.AppDataException;

public class DataPasajero {
	
	public Pasajero getByDni(int dni) throws AppDataException {
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
			
		} catch (SQLException e) 
		{
 			throw new AppDataException(e, "Error al intentar recuperar pasajero de la base de datos");

		}
		
		try 
		{
			if(rs!=null) {rs.close();}
			if(pstmt!=null) {pstmt.close();}
			Conectar.getInstancia().releasedConn();
			
		} catch(SQLException e) 
		{
 			throw new AppDataException(e, "Error al intentar ingresar reserva de pasajero a la base de datos");

		}
	
		return pasajero;

	}
	
	public void addPasajero(Pasajero pasajero) throws AppDataException 
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
			
		    
			
		}catch(SQLException e) 
		{
 			throw new AppDataException(e, "Error al intentar ingresar pasajero a la base de datos");
		}
		
		
		try 
		{
			
			if(pstmt!=null) {pstmt.close();}
			Conectar.getInstancia().releasedConn();
			
			
			
		} catch(SQLException e) 
		{
 			throw new AppDataException(e, "Error al intentar ingresar reserva de pasajero a la base de datos");

		}
			


	

	}
	
	
	
}