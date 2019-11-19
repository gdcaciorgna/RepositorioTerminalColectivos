package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Compania_Tarjeta;
import entities.Localidad;
import entities.Provincia;

public class DataCompaniaTarjeta {
	
	public ArrayList<Compania_Tarjeta> getAll()
	{
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM companias_tarjetas";
		ArrayList<Compania_Tarjeta> companiasTarjetas = new ArrayList<Compania_Tarjeta>();
		try 
		{
			stmt = Conectar.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(sql);
			if(rs!=null) 
			{
				while(rs.next()) 
				{
					Compania_Tarjeta companiaTarjeta = new Compania_Tarjeta();
					
					companiaTarjeta.setCod_compania(rs.getInt("cod_compania"));
					companiaTarjeta.setNombre(rs.getString("nombre"));
					
					companiasTarjetas.add(companiaTarjeta);				}
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
		
	return companiasTarjetas;

		
	}
	
	public int getCodigo(String nom_compania) {
	
	int codigoComp=0;
	String sql = "SELECT * FROM companias_tarjetas where nombre = ?";
			
	
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	try {
		pstmt=Conectar.getInstancia().getConn().prepareStatement(sql);
		pstmt.setString(1, nom_compania);
		rs=pstmt.executeQuery();	
		
		//INICIO - Código sin aplicar herencia
		if(rs!=null && rs.next()) 
	{
			
			codigoComp=rs.getInt("cod_compania");
			
	}
		//FIN - Código sin aplicar herencia
		
	} 
	catch (SQLException e) {
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
	
	return codigoComp;
	}
	
	
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
	}}

	
	
	
	
		

