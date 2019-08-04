package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Colectivo;
import entities.Empresa_Colectivo;
import entities.Usuario;

public class DataColectivo {
	
	public Colectivo getByPatente(String patente) 
	{
		Colectivo colectivo = new Colectivo();
		String sql = "select * from colectivos where patente=?";
		
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setString(1, patente);
			rs=pstmt.executeQuery();	
			
			if(rs!=null && rs.next()) 
		{
				colectivo = setColectivo(rs);							
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
		
		return colectivo;
	} 
	
	private Colectivo setColectivo(ResultSet rs) 
	{
		DataEmpresa_Colectivo demp = new DataEmpresa_Colectivo()
;		Empresa_Colectivo empresa= new Empresa_Colectivo();
		Colectivo colectivo = new Colectivo();
		try {
			
			colectivo.setPatente(rs.getString("patente"));
			
			int id_empresa_colectivo = rs.getInt("id_empresa_colectivo");
			empresa = demp.getById_Empresa_Colectivo(id_empresa_colectivo);
			
			colectivo.setEmpresa(empresa);
			colectivo.setCapacidad(rs.getInt("capacidad"));
			colectivo.setTipo_colectivo(rs.getString("tipo_colectivo"));
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return colectivo;

	}
	
	public ArrayList <Colectivo> getAll()
	{
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from colectivos";
		ArrayList<Colectivo> colectivos = new ArrayList<>();
		try 
		{
			stmt = Conectar.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(sql);
			if(rs!=null) 
			{
				while(rs.next()) 
				{
					Colectivo colec = setColectivo(rs);
					
					colectivos.add(colec);
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
		
	return colectivos;	
	}

}
