package data;

import java.sql.*;

import entities.Usuario;




public class DataUsuario implements Validar 
{


	



	

	
	@Override
	public boolean validar(Usuario usu) {
		int r=0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "select * from usuario where usuario = ? and contrasenia = ?";

		
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setString(1, usu.getUsuario());
			pstmt.setString(2, usu.getContrasenia());
			rs = pstmt.executeQuery();
			
			if(rs!=null && rs.next())
			{
				usu.setUsuario(rs.getString("usuario"));
				usu.setEmail(rs.getString("email"));
				usu.setContrasenia(rs.getString("contrasenia"));
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
	


	
	

}
