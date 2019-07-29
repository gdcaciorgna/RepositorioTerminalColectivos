package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Ruta;

public class DataRuta {
	
	public Ruta getByRuta(int cod_ruta) 
	{
		Ruta ruta = new Ruta();
		String sql= "SELECT * FROM rutas WHERE cod_ruta=?";
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ruta.setCod_ruta(cod_ruta);
		try {
			pstmt=Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setInt(1, cod_ruta);
			rs=pstmt.executeQuery();	
			
			//INICIO - Código sin aplicar herencia
			if(rs!=null && rs.next()) 
		{
				
				ruta.setDias_sem(rs.getString("dias_sem"));
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
		
		return ruta;
	}
	
	

}
