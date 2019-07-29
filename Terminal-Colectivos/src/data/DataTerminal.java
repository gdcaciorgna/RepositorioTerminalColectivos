package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Localidad;
import entities.Terminal;


public class DataTerminal {
	
	
	public Terminal getByCod_Terminal(int cod_terminal) {
		Terminal terminal = new Terminal();
		Localidad localidad = new Localidad();
		String sql = "SELECT * FROM terminales";
		DataLocalidad dloc = new DataLocalidad();
		
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setInt(1, cod_terminal);
			rs=pstmt.executeQuery();	
			
			//INICIO - Código sin aplicar herencia
			if(rs!=null && rs.next()) 
		{
				terminal.setCod_terminal(cod_terminal);
				
				int id_localidad = rs.getInt("id_localidad");
				
				localidad = dloc.getById_Localidad(id_localidad);
				
				terminal.setLocalidad(localidad);
				
				
				
				
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
		
		return terminal;
	}

}
