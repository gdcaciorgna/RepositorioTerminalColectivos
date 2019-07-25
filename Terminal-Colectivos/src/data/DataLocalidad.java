package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Localidad;
import entities.Provincia;

public class DataLocalidad {
	
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
					Provincia provincia = new Provincia();
					Localidad localidad = new Localidad();
					provincia.setId_provincia(rs.getInt("prov.id_provincia"));
					provincia.setNombre(rs.getString("loc.nombre"));
					
					localidad.setId_localidad(rs.getInt("loc.id_localidad"));
					localidad.setNombre(rs.getString("loc.nombre"));
					localidad.setProvincia(provincia);
					
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
	
	
}
