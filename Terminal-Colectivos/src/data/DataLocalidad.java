package data;

import java.sql.*;
import java.util.ArrayList;

import entities.Localidad;
import entities.Provincia;
import entities.Usuario;

public class DataLocalidad {
	
	
	public Localidad getById_Localidad(int id_localidad) 
	{
		Localidad localidad = null;
		Provincia provincia = null;
		DataProvincia dprov = new DataProvincia();
		String sql = "select * from localidades where id_localidad=?";
		
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setInt(1, id_localidad);
			rs=pstmt.executeQuery();	
			
			//INICIO - C�digo sin aplicar herencia
			if(rs!=null && rs.next()) 
		{
				localidad.setId_localidad(id_localidad);
				localidad.setNombre(rs.getString("nombre"));
				
				provincia = dprov.getById_Provincia(rs.getInt("id_provincia"));
					
				
				localidad.setProvincia(provincia);
		}
			//FIN - C�digo sin aplicar herencia
			
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
		
		return localidad;

	}
	
	
	public ArrayList<Localidad> getAll()
	{
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM localidades loc INNER JOIN provincias prov ON loc.id_provincia=prov.id_provincia where prov.nombre = 'Santa Fe' order by loc.nombre asc";
		ArrayList<Localidad> localidades = new ArrayList<>();
		try 
		{
			stmt = Conectar.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(sql);
			if(rs!=null) 
			{
				while(rs.next()) 
				{
					Localidad localidad = setLocalidad(rs);
					localidades.add(localidad);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally
		{
			try 
			{
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				Conectar.getInstancia().releasedConn();
			} catch(SQLException e) 
			{
				e.printStackTrace();
			} 
		}
		
	return localidades;
	}
	
	
		
	private Localidad setLocalidad(ResultSet rs) 
	{
		Provincia provincia = new Provincia();
		Localidad localidad = new Localidad();
		try {
			
			provincia.setId_provincia(rs.getInt("prov.id_provincia"));
			provincia.setNombre(rs.getString("loc.nombre"));
			
			localidad.setId_localidad(rs.getInt("loc.id_localidad"));
			localidad.setNombre(rs.getString("loc.nombre"));
			localidad.setProvincia(provincia);			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return localidad;

	}

	
	
}
