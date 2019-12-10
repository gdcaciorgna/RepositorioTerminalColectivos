package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Empresa_Colectivo;
import util.AppDataException;

public class DataEmpresa_Colectivo {
	
	public Empresa_Colectivo getById_Empresa_Colectivo(int id_empresa_colectivo) throws AppDataException 
	{
		Empresa_Colectivo empresa = new Empresa_Colectivo();
		empresa.setId_empresa_colectivo(id_empresa_colectivo);
		String sql = "select * from empresas_colectivos where id_empresa_colectivo=?";
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setInt(1, id_empresa_colectivo);
			rs=pstmt.executeQuery();	
			
			if(rs!=null && rs.next()) 
		{
				empresa.setNombre(rs.getString("nombre"));
		}
			
		} catch (SQLException e) 
		{
 			throw new AppDataException(e, "Error al intentar recuperar empresa de colectivos de la base de datos");

		}			
		
		try {
				if(rs!=null) {rs.close();}
				if(pstmt!=null) {pstmt.close();}
				Conectar.getInstancia().releasedConn();
			} 
		catch (SQLException e) 
		{
	 		throw new AppDataException(e, "Error al intentar ingresar reserva de pasajero a la base de datos");
		}
		
		
		return empresa;
	}
	
	
	
	public Empresa_Colectivo getByPatente(String patente) throws AppDataException 
	{
		Empresa_Colectivo empresa = new Empresa_Colectivo();
		String sql = "SELECT ecol.id_empresa_colectivo, ecol.nombre FROM colectivos col INNER JOIN empresas_colectivos ecol ON ecol.id_empresa_colectivo=col.id_empresa_colectivo where patente like '?' ";
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setString(1, patente);
			rs=pstmt.executeQuery();	
			
			if(rs!=null && rs.next()) 
		{
				empresa.setId_empresa_colectivo(rs.getInt("ecol.id_empresa_colectivo"));
				empresa.setNombre(rs.getString("ecol.nombre"));
		}
			
		} 
		catch (SQLException e) 
		{
 			throw new AppDataException(e, "Error al intentar ingresar recuperar empresa de colectivo de la base de datos");

		}
		
		try {
			if(rs!=null) {rs.close();}
			if(pstmt!=null) {pstmt.close();}
			Conectar.getInstancia().releasedConn();
		} catch (SQLException e) 
		{
 			throw new AppDataException(e, "Error al intentar ingresar reserva de pasajero a la base de datos");

		}
		
		
		return empresa;

	}
	
	

}
