package data;

import java.sql.*;
import java.util.ArrayList;

import entities.Usuario;




public class DataUsuario 
{
	
	public Usuario getByUsername(String usu) {
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
	


	public boolean validarUsuarioyPassword(Usuario usuario, String password) {
		boolean r = false;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "select * from usuarios where estado= 'activo' and usuario = ? and password = ?";
		
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setString(1, usuario.getUsername());
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
	
	
	public boolean validarUsuarioyPassword(String username, String password) {
		boolean r=false;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "select * from usuarios where  estado= 'activo' and usuario = ? and password = ?";
		
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setString(1, username);
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
	
	public boolean validarUsuarioInexistente (String username) {
		boolean r=true;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "select * from usuarios where usuario = ? ";
		
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			
			if(rs!=null && rs.next())
			{
				r=false;
				
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
		
			pstmt.setString(1, usuario.getUsername());
			
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
			usuario.setUsername(rs.getString("usuario"));
			usuario.setNombre(rs.getString("nombre"));
			usuario.setApellido(rs.getString("apellido"));
			usuario.setRol(rs.getString("rol"));
			usuario.setEmail(rs.getString("email"));
			usuario.setPassword(rs.getString("password"));
			usuario.setEstado(rs.getString("estado"));
			usuario.setCuil(rs.getString("cuil"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usuario;
	
	}
	
	public void editarUsuario(Usuario usuario)
	{
	PreparedStatement pstmt = null;
	
	String sql = "UPDATE usuarios SET nombre=?, apellido=?, email=?, cuil=?, rol=?, estado=? WHERE usuario=?";
	
	if(usuario.getCuil()==null)
	{
		usuario.setCuil("");
	}
	
	try 
	{
		pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
		pstmt.setString(1, usuario.getNombre());
		pstmt.setString(2, usuario.getApellido());
		pstmt.setString(3, usuario.getEmail());
		pstmt.setString(4, usuario.getCuil());
		pstmt.setString(5, usuario.getRol());
		pstmt.setString(6, usuario.getEstado());
		pstmt.setString(7, usuario.getUsername());
	    pstmt.executeUpdate();
		
		
	}catch(SQLException e) { 
		e.printStackTrace();
		}
	}
	

	public void ingresarUsuario(Usuario usuario)
	{
	PreparedStatement pstmt = null;
	
	String sql = "INSERT INTO usuarios (nombre, apellido, email, cuil, rol, usuario,password,estado) VALUES (?,?,?,?,?,?,?,?) ";
	
	if(usuario.getRol()==null)
	{
		usuario.setRol("cliente");
		usuario.setCuil("");
	}
	
	try 
	{
		pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
		pstmt.setString(1, usuario.getNombre());
		pstmt.setString(2, usuario.getApellido());
		pstmt.setString(3, usuario.getEmail());
		pstmt.setString(4, usuario.getCuil());
		pstmt.setString(5, usuario.getRol());
		pstmt.setString(6, usuario.getUsername());
		pstmt.setString(7, usuario.getPassword());
		pstmt.setString(8, "activo");
		
	    pstmt.executeUpdate();
		
		
	}catch(SQLException e) { e.printStackTrace();}
	
	finally 
	{
		try 
		{
			
			if(pstmt!=null) {pstmt.close();}
			Conectar.getInstancia().releasedConn();
			
			
			
		} catch(SQLException e) {e.printStackTrace();}
	}	
	
	}
	public ArrayList <Usuario> getAall()
	{
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from usuarios where rol='chofer'";
		ArrayList<Usuario> choferes = new ArrayList<>();
		try 
		{
			stmt = Conectar.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(sql);
			if(rs!=null) 
			{
				while(rs.next()) 
				{
					Usuario chofer = setUsuario(rs);
					
					choferes.add(chofer);
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
		
	return choferes;	
	}
}
