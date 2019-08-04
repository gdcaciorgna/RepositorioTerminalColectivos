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
	
	public Ruta getRuta(String origenViaje, String destinoViaje) 
	{
		Ruta ruta = new Ruta();
		String sql= "select distinct e.cod_ruta,r.dias_sem\r\n" + 
				"from escalas e\r\n" + 
				"inner join rutas r on r.cod_ruta=e.cod_ruta\r\n" +
				"join(\r\n" + 
				"SELECT es.cod_ruta\r\n" + 
				"FROM escalas es\r\n" + 
				" JOIN terminales te   on es.cod_terminal=te.cod_terminal\r\n" + 
				" join localidades lo on lo.id_localidad=te.id_localidad\r\n" + 
				" where orden=0 and  lo.nombre=?) ori on e.cod_ruta=ori.cod_ruta\r\n" + 
				" join(\r\n" + 
				" select esc.cod_ruta\r\n" + 
				" from escalas esc \r\n" + 
				" join  terminales ter   on esc.cod_terminal=ter.cod_terminal\r\n" + 
				" join localidades loc on loc.id_localidad=ter.id_localidad\r\n" + 
				" where orden=1 and loc.nombre=?) des on e.cod_ruta=des.cod_ruta;";
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			pstmt=Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setString(1, origenViaje);
			pstmt.setString(2, destinoViaje);
			rs=pstmt.executeQuery();	
			
			//INICIO - Código sin aplicar herencia
			if(rs!=null && rs.next()) 
		{
				
				ruta.setCod_ruta(rs.getInt("cod_ruta"));
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
