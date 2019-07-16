package data;

import java.sql.*;
import java.util.ArrayList;

import entities.Usuario;




public class DataUsuario implements Validar 
{
	
	public Usuario getByUsuario(String usu) {
		Usuario usuario = null;
		String sql = "select * from usuarios where usuario=?";
		
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setString(1, usu);
			rs=pstmt.executeQuery();	
			
			//INICIO - Código sin aplicar herencia
			if(rs!=null && rs.next()) 
		{
				usuario = setUsuario(rs);							
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
		
		return usuario;
	}
	





	
	
	
	

	@Override
	public boolean validar(Usuario usuario, String password) {
		boolean r = false;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "select * from usuarios where estado= 'activo' and usuario = ? and password = ?";
		
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setString(1, usuario.getUsuario());
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			if(rs!=null && rs.next())
			{
				r=true;
				
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

		

		return r;
		
	}
	
	@Override
	public boolean validar(Usuario usuario) {
		boolean r=false;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "select * from usuarios where  estado= 'activo' and usuario = ? and password = ?";
		
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setString(1, usuario.getUsuario());
			pstmt.setString(2, usuario.getPassword());
			rs = pstmt.executeQuery();
			
			if(rs!=null && rs.next())
			{
				r=true;
				
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

		

		return r;
		
	}
	
	public Integer eliminarUsuario(Usuario usuario) 
	{
		PreparedStatement pstmt = null;
		String sql = "update usuarios set estado = 'eliminado' where usuario = ?";
		Integer filasAfectadas = 0;
		
		try 
		{
		pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
		
			pstmt.setString(1, usuario.getUsuario());
			
			filasAfectadas = pstmt.executeUpdate();			
			
			
			
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
		return filasAfectadas;
		
	}
	
	public ArrayList <Usuario> getAll()
	{
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from usuarios";
		ArrayList<Usuario> usuarios = new ArrayList<>();
		try 
		{
			stmt = Conectar.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(sql);
			if(rs!=null) 
			{
				while(rs.next()) 
				{
					Usuario usuario = setUsuario(rs);
					usuarios.add(usuario);
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
		
	return usuarios;	
	}
	
	private Usuario setUsuario(ResultSet rs)
	{
		Usuario usuario = new Usuario();
		try {
			usuario.setUsuario(rs.getString("usuario"));
			usuario.setNombre(rs.getString("nombre"));
			usuario.setApellido(rs.getString("apellido"));
			usuario.setRol(rs.getString("rol"));
			usuario.setEmail(rs.getString("email"));
			usuario.setPassword(rs.getString("password"));
			usuario.setEstado(rs.getString("estado"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usuario;
	
	}
	



}
