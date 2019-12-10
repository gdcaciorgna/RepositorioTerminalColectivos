package data;

import java.sql.*;
import java.util.ArrayList;

import entities.Usuario;
import util.AppDataException;




public class DataUsuario 
{
	
	public Usuario getByUsername(String usu) throws AppDataException {
		Usuario usuario = null;
		String sql = "select * from usuarios where usuario=?";
		
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setString(1, usu);
			rs=pstmt.executeQuery();	
			
			//INICIO - C�digo sin aplicar herencia
			if(rs!=null && rs.next()) 
		{
				usuario = setUsuario(rs);							
		}
			//FIN - C�digo sin aplicar herencia
			
		} catch (SQLException e) {
			
			throw new AppDataException(e, "Error al obtener el usuario de la bd.");
		
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(pstmt!=null) {pstmt.close();}
				Conectar.getInstancia().releasedConn();
			} catch (SQLException e) {
				throw new AppDataException(e, "Error al cerrar la base de datos.");
			}
		}
		
		return usuario;
	}
	


	public boolean validarUsuarioyPassword(Usuario usuario, String password) throws AppDataException {
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
		}catch(SQLException e) 
		{ 
			throw new AppDataException(e, "Error al intentar validar usuario y contrase�a en base de datos");		}
		
		try 
		{
			if(rs!=null) {rs.close();}
			if(pstmt!=null) {pstmt.close();}
			Conectar.getInstancia().releasedConn();
			
			
			
		} catch(SQLException e) 
		{
			throw new AppDataException(e, "Error al intentar cerrar la base de datos");
		}
			

		

		return r;
		
	}
	
	
	public boolean validarUsuarioyPassword(String username, String password) throws SQLException, AppDataException {
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
		}
		
		catch(SQLException e) 
		{
			if(e.getMessage()==null) {
			throw new AppDataException(e, "Error en base de datos al validar usuario y password");
			}
			else 
			{
				throw e;
			}
		}
		
		catch(AppDataException e) 
		{
			throw e;
		}
		
		
			
			
			try 
			{
				if(rs!=null) {
					rs.close();
					}
				if(pstmt!=null) {pstmt.close();}
				Conectar.getInstancia().releasedConn();
				
				
				
			} catch(SQLException e) 
			{
				throw e;
			}
		


		return r;
		
	}
	
	public boolean validarUsuarioInexistente (String username) throws Exception {
		
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
		}
		catch(SQLException e) 
		{ 
			throw new AppDataException(e, "Error en base de datos al validar usuario inexistente");
		}
		finally 
		{
			try 
			{
				if(rs!=null) {rs.close();}
				if(pstmt!=null) {pstmt.close();}
				Conectar.getInstancia().releasedConn();
				
				
				
			} catch(SQLException e) 
			{
				throw new AppDataException(e, "Error al cerrar la base de datos");
			}
		}	

		

		return r;
		
	}
	
	public Integer eliminarUsuario(Usuario usuario) throws AppDataException 
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
			throw new AppDataException(e, "Error al intentar eliminar usuario en la base de datos");

		}
		finally 
		{
			try 
			{
				if(pstmt!=null) {pstmt.close();}
				Conectar.getInstancia().releasedConn();
				
				
				
			} catch(SQLException e) 
			{
				throw new AppDataException(e, "Error al intentar cerrar la base de datos");
			
			}
			
			
			
		}
		return filasAfectadas;
		
	}
	
	public ArrayList <Usuario> getAll() throws AppDataException
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
			throw new AppDataException(e, "Error recuperar usuarios.");
			
		} finally
		{
			try 
			{
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				Conectar.getInstancia().releasedConn();
			} catch(SQLException e) 
			{
				throw new AppDataException(e, "Error al cerrar la base de datos.");

			} 
		}
		
	return usuarios;	
	}
	
	private Usuario setUsuario(ResultSet rs) throws AppDataException
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
			throw new AppDataException(e, "Error al recuperar usuario de la base de datos.");

		}
		
		return usuario;
	
	}
	
	public void editarUsuario(Usuario usu) throws AppDataException
	{
	PreparedStatement pstmt = null;
	
	String sqlSinPasswordConEstado = "UPDATE usuarios SET  nombre=?, apellido=?, email=?, cuil=?, estado=?, rol=? WHERE usuario=?";
	String sqlConPasswordConEstado = "UPDATE usuarios SET  nombre=?, apellido=?, email=?, cuil=?,  estado=?, rol=?,   password = ?WHERE usuario=?";

	try 
	{
	
		
		
		if(usu.getPassword() == null && usu.getEstado() != null ) //sin password con estado
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sqlSinPasswordConEstado);
		
		
			pstmt.setString(7, usu.getUsername());
		}
		
		else if(usu.getPassword() != null && usu.getEstado() != null ) //con password con estado
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sqlConPasswordConEstado);
			
			pstmt.setString(7, usu.getPassword());
			pstmt.setString(8, usu.getUsername());
		}
		
		pstmt.setString(1, usu.getNombre());
		pstmt.setString(2, usu.getApellido());
		pstmt.setString(3, usu.getEmail());
		pstmt.setString(4, usu.getCuil());
		pstmt.setString(5, usu.getEstado());
		pstmt.setString(6, usu.getRol());
		
	    pstmt.executeUpdate();
		
		
	}
	catch(SQLException e) 
	{ 
		throw new AppDataException(e, "Error al intentar editar usuario en la base de datos");
	}
	
			try 
			{
				
				if(pstmt!=null) {pstmt.close();}
				Conectar.getInstancia().releasedConn();
				
				
				
			} catch(SQLException e) 
			{
				throw new AppDataException(e, "Error al intentar cerrar la base de datos");

			}
	}	
	
	

	
	
	
	public void ingresarUsuario(Usuario usuario) throws AppDataException
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
		
		
	}catch(SQLException e) 
	{ 
		throw new AppDataException(e, "Error al intentar registrar usuario en la base de datos");

	}
	
	finally 
	{
		try 
		{
			
			if(pstmt!=null) {pstmt.close();}
			Conectar.getInstancia().releasedConn();
			
			
			
		} catch(SQLException e) 
		{
			throw new AppDataException(e, "Error al intentar cerrar la base de datos");

		}
	}	
	
	}
	/*
	 * public ArrayList <Usuario> getAall() throws AppDataException { Statement stmt
	 * = null; ResultSet rs = null; String sql =
	 * "select * from usuarios where rol='chofer'"; ArrayList<Usuario> choferes =
	 * new ArrayList<>(); try { stmt =
	 * Conectar.getInstancia().getConn().createStatement(); rs =
	 * stmt.executeQuery(sql); if(rs!=null) { while(rs.next()) { Usuario chofer =
	 * setUsuario(rs);
	 * 
	 * choferes.add(chofer); } } } catch (SQLException e) { throw new
	 * AppDataException(e, "Error al intentar recuperar usuarios");
	 * 
	 * 
	 * } finally { try { if(rs!=null) rs.close(); if(stmt!=null) stmt.close();
	 * Conectar.getInstancia().releasedConn(); } catch(SQLException e) { throw new
	 * AppDataException(e, "Error al intentar cerrar la base de datos");
	 * 
	 * } }
	 * 
	 * return choferes; }
	 */
}
