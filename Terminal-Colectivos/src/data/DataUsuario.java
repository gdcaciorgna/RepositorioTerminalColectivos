package data;

import java.sql.*;
import entities.Usuario;




public class DataUsuario implements Validar 
{
	
	public Usuario getByUsuario(String txtusu) {
		Usuario u = null;
		
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=Conectar.getInstancia().getConn().prepareStatement(
					"select * from usuario where usuario=?"
					);
			pstmt.setString(1, txtusu);
			rs=pstmt.executeQuery();
			
			//INICIO - Código aplicando herencia (incompleto)
			
/*			if(rs!=null && rs.next()) {
				if(rs.getString("rol").equals("admin") ) 
				{
					adm.setUsuario(rs.getString("usuario"));
					adm.setRol(rs.getString("rol"));
					adm.setEmail(rs.getString("email"));
					adm.setContrasenia(rs.getString("contrasenia"));
					
					
					
				}
				
				else if(rs.getString("rol").equals("chofer") ) 
				{
					cho.setUsuario(rs.getString("usuario"));
					cho.setRol(rs.getString("rol"));
					cho.setEmail(rs.getString("email"));
					cho.setContrasenia(rs.getString("contrasenia"));
					
					return cho;
				}
				
				else if(rs.getString("rol").equals("cliente") ) 
				{
					cli.setUsuario(rs.getString("usuario"));
					cli.setRol(rs.getString("rol"));
					cli.setEmail(rs.getString("email"));
					cli.setContrasenia(rs.getString("contrasenia"));
					
					return cli;
				}
				*/
			
			//FIN - Código aplicando herencia (incompleto)
			
			
			//INICIO - Código sin aplicar herencia
			if(rs!=null && rs.next()) 
		{
				u = new Usuario();
				u.setUsuario(rs.getString("usuario"));
				u.setRol(rs.getString("rol"));
				u.setEmail(rs.getString("email"));
				u.setContrasenia(rs.getString("contrasenia"));
				u.setEstado(rs.getString("estado"));
							
				
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
		
		return u;
	}
	





	
	
	
	

	@Override
	public boolean validar(Usuario usu, String txtpass) {
		int r=0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement("select * from usuario where  estado= 'activo' and usuario = ? and contrasenia = ?");
			pstmt.setString(1, usu.getUsuario());
			pstmt.setString(2, txtpass);
			rs = pstmt.executeQuery();
			
			if(rs!=null && rs.next())
			{
				usu.setUsuario(rs.getString("usuario"));
				usu.setRol(rs.getString("rol"));
				usu.setEmail(rs.getString("email"));
				usu.setContrasenia(rs.getString("contrasenia"));
				usu.setEstado(rs.getString("estado"));
				r=r+1;
				
			}
		}catch(SQLException e) { e.printStackTrace();}
		finally 
		{
			try 
			{
				if(rs!=null) {rs.close();}
				if(pstmt!=null) {pstmt.close();}
				Conectar.getInstancia().releasedConn();
				
				
				
			} catch(SQLException e) {e.printStackTrace();}
		}	

		

		if(r==1) 
		{
			return true;
		}
		else {return false;}
		
	}
	
	@Override
	public boolean validar(Usuario usu) {
		int r=0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement("select * from usuario where  estado= 'activo' and usuario = ? and contrasenia = ?");
			pstmt.setString(1, usu.getUsuario());
			pstmt.setString(2, usu.getContrasenia());
			rs = pstmt.executeQuery();
			
			if(rs!=null && rs.next())
			{
				usu.setUsuario(rs.getString("usuario"));
				usu.setRol(rs.getString("rol"));
				usu.setEmail(rs.getString("email"));
				usu.setContrasenia(rs.getString("contrasenia"));
				usu.setEstado(rs.getString("estado"));
				r=r+1;
				
			}
		}catch(SQLException e) { e.printStackTrace();}
		finally 
		{
			try 
			{
				if(rs!=null) {rs.close();}
				if(pstmt!=null) {pstmt.close();}
				Conectar.getInstancia().releasedConn();
				
				
				
			} catch(SQLException e) {e.printStackTrace();}
		}	

		

		if(r==1) 
		{
			return true;
		}
		else {return false;}
		
	}
	
	public void eliminarUsuario(Usuario usu) 
	{
		PreparedStatement pstmt = null;
		
		
		try 
		{
		pstmt = Conectar.getInstancia().getConn().prepareStatement("update usuario set estado = 'eliminado' where usuario = ?");
		
			pstmt.setString(1, usu.getUsuario());
			
			 pstmt.executeUpdate();			
			
			
			
		} catch(SQLException e) 
		{
			
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(pstmt!=null) {pstmt.close();}
				Conectar.getInstancia().releasedConn();
				
				
				
			} catch(SQLException e) {e.printStackTrace();
			
			}
			
			
			
		}
		
	}



}
