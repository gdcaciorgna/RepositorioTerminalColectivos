package data;

import java.sql.*;
import java.util.ArrayList;

import entities.Usuario;




public class DataUsuario implements Validar 
{
	
	public Usuario getByUsuario(String txtusu) {
		Usuario usuario = null;
		String sql = "select * from usuarios where usuario=?";
		
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=Conectar.getInstancia().getConn().prepareStatement(sql);
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
				usuario = new Usuario();
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setRol(rs.getString("rol"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setEmail(rs.getString("email"));
				usuario.setContrasenia(rs.getString("contrasenia"));
				usuario.setEstado(rs.getString("estado"));
							
				
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
	public boolean validar(Usuario usuario, String txtpass) {
		boolean r = false;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "select * from usuarios where estado= 'activo' and usuario = ? and contrasenia = ?";
		
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setString(1, usuario.getUsuario());
			pstmt.setString(2, txtpass);
			rs = pstmt.executeQuery();
			
			if(rs!=null && rs.next())
			{
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setRol(rs.getString("rol"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setEmail(rs.getString("email"));
				usuario.setContrasenia(rs.getString("contrasenia"));
				usuario.setEstado(rs.getString("estado"));
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
		String sql = "select * from usuarios where  estado= 'activo' and usuario = ? and contrasenia = ?";
		
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setString(1, usuario.getUsuario());
			pstmt.setString(2, usuario.getContrasenia());
			rs = pstmt.executeQuery();
			
			if(rs!=null && rs.next())
			{
				usuario.setUsuario(rs.getString("usuario"));
				
				usuario.setRol(rs.getString("rol"));
				usuario.setEmail(rs.getString("email"));
				usuario.setContrasenia(rs.getString("contrasenia"));
				usuario.setEstado(rs.getString("estado"));
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
	
	public void eliminarUsuario(Usuario usuario) 
	{
		PreparedStatement pstmt = null;
		String sql = "update usuarios set estado = 'eliminado' where usuario = ?";
		
		
		try 
		{
		pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
		
			pstmt.setString(1, usuario.getUsuario());
			
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
					Usuario usuario = new Usuario();
					usuario.setUsuario(rs.getString("usuario"));
					usuario.setNombre(rs.getString("nombre"));
					usuario.setApellido(rs.getString("apellido"));
					usuario.setRol(rs.getString("rol"));
					usuario.setEmail(rs.getString("email"));
					usuario.setEstado(rs.getString("estado"));
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
	



}
