package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Empresa_Colectivo;

public class DataEmpresa_Colectivo {
	
	public Empresa_Colectivo getById_Empresa_Colectivo(int id_empresa_colectivo) 
	{
		Empresa_Colectivo empresa = null;
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
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(pstmt!=null) {pstmt.close();}
				Conectar.getInstancia().releasedConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return empresa;
	}
	
	
	
	public Empresa_Colectivo getByPatente(String patente) 
	{
		Empresa_Colectivo empresa = null;
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
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(pstmt!=null) {pstmt.close();}
				Conectar.getInstancia().releasedConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return empresa;

	}
	
	

}
