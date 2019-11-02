package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Compania_Tarjeta;

public class DataCompaniaTarjeta {
	
	public Compania_Tarjeta getById(int cod_compania) {
		Compania_Tarjeta compania_tarjeta = new Compania_Tarjeta();
		String sql = "SELECT * FROM companias_tarjetas where cod_compania = ?";
				
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setInt(1, cod_compania);
			rs=pstmt.executeQuery();	
			
			//INICIO - Código sin aplicar herencia
			if(rs!=null && rs.next()) 
		{
				compania_tarjeta.setCod_compania(cod_compania);
				compania_tarjeta.setNombre(rs.getString("nombre"));
				
		}
			//FIN - Código sin aplicar herencia
			
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
		
		return compania_tarjeta;
	}

}
