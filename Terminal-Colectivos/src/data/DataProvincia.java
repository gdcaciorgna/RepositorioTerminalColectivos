package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Provincia;
import util.AppDataException;

public class DataProvincia {
	
	public Provincia getById_Provincia(int id_provincia) throws AppDataException {
		Provincia provincia = new Provincia();
		String sql = "SELECT * FROM provincias where id_provincia = ?";
				
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setInt(1, id_provincia);
			rs=pstmt.executeQuery();	
			
			//INICIO - Código sin aplicar herencia
			if(rs!=null && rs.next()) 
		{
				provincia.setId_provincia(id_provincia);
				provincia.setNombre(rs.getString("nombre"));
				
		}
			//FIN - Código sin aplicar herencia
			
		} catch (SQLException e) {
			
			throw new AppDataException(e, "Error obtener provincia de la base de datos.");


		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(pstmt!=null) {pstmt.close();}
				Conectar.getInstancia().releasedConn();
			} catch (SQLException e) {
				throw new AppDataException(e, "Error al cerrar la base de datos.");
			}
		}
		
		return provincia;
	}

}
