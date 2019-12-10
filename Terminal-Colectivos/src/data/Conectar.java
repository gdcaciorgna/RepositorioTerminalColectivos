package data;

import java.sql.*;

import util.AppDataException;

public class Conectar {
	

	public static String driver = "com.mysql.jdbc.Driver";
	private String host ="localhost";
	private String port = "3306";
	private String user ="root";
	private String pass="root";
	private String db= "db_terminal";
	private Connection conn = null;
	private int conectados = 0;
	
	
	 //INICIO SINGLETON
	
	
	private static Conectar instancia = null; //paso 1 del singleton
	
	private Conectar() //paso 2 del singleton
	{
		try 
		{
			Class.forName(driver);
			
		} catch(ClassNotFoundException e) 
		{
			new AppDataException(e, "Error en el driver de mysql connector");

		}
	}
	
	public static Conectar getInstancia()  //paso 3 del singleton
	{
		if(instancia==null) 
		{
			instancia = new Conectar();
		}
		return instancia;
	}
	
	//FIN SINGLETON
	
	public Connection getConn() throws SQLException, AppDataException 
	{
		try {
		
		if(conn==null || conn.isClosed()) 
		{
			conn = DriverManager.getConnection("jdbc:mysql://"+ host + ":"+ port+"/" + db , user, pass);

		}
		
		} catch (SQLException e) 
		{			
			throw new AppDataException(e, "Error al conectar con la base de datos");
		}
		conectados++;
		return conn;
	}
	
	public void releasedConn() throws AppDataException 
	{
		conectados--;
		try 
		{
			if(conectados<=0) 
			{
				conn.close();
			}
		} catch(SQLException e) 
		{
			if(e.getMessage()==null) {
			throw new AppDataException(e, "Error al desconectar usuario de la base de datos");
			}
		}
	}
	
			


}
