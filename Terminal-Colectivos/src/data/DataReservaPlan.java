package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import controlers.FechaControlers;
import entities.Plan;
import entities.Plan_Reserva;
import entities.Reserva;
import entities.Usuario;
import util.AppDataException;

public class DataReservaPlan {
	
	private String sqlReservasdeunPlan = "SELECT DISTINCT pl.fecha_hora_plan, pl.cod_ruta, pl.patente, re.fecha_res, re.usuario, re.cant_pas, pl.precio, pl.usuario_chofer, re.fecha_canc, re.cod_compania, re.nro_tarjeta  \r\n" + 
			"FROM reservas re \r\n" + 
			"inner join planes_reservas pr on re.fecha_res = pr.fecha_res and re.usuario = pr.usuario_reserva \r\n" + 
			"inner join planes pl on pl.fecha_hora_plan = pr.fecha_hora_plan and pr.cod_ruta = pl.cod_ruta and pr.patente = pl.patente \r\n" + 
			"inner join pasajeros_reservas pasres on pasres.fecha_res = re.fecha_res and pasres.usuario = re.usuario \r\n" + 
			"where pr.fecha_hora_plan = ? and pr.patente = ? and pr.cod_ruta = ? ";	

	
	public ArrayList<Plan_Reserva> getReservasPlan(Plan planElegido) throws AppDataException
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Plan_Reserva> planes_reservas = new ArrayList<>();
		Plan_Reserva plan_reserva = new Plan_Reserva();
		
		
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sqlReservasdeunPlan);
			pstmt.setTimestamp(1, new Timestamp(planElegido.getFechaHora().getTime()));
			pstmt.setString(2, planElegido.getColectivo().getPatente());
			pstmt.setInt(3, planElegido.getRuta().getCod_ruta());
			
			
			rs = pstmt.executeQuery();
			
			if(rs!=null) 
			{
				while(rs.next()) 
				{
					Reserva reserva = new Reserva();
					Plan plan = new Plan();
					
					
					DataPlan dplan = new DataPlan();
					DataReserva dres = new DataReserva();
					
					String patente = rs.getString("pl.patente");
					int cod_ruta = rs.getInt("pl.cod_ruta");
					
					String username_reserva = rs.getString("re.usuario");
							
					
					

					//INICIO - MANEJO DE FECHAS 
					
					Timestamp fecha_hora_plan_timestamp = rs.getTimestamp("pl.fecha_hora_plan");
					Date fecha_hora_plan = new Date(fecha_hora_plan_timestamp.getTime());
				
					
					Timestamp fecha_res_timestamp = rs.getTimestamp("re.fecha_res");
					Date fecha_res = new Date(fecha_res_timestamp.getTime());

					
					//FIN - MANEJO DE FECHAS 
					
					plan = dplan.getByFechaHoraRutaPatente(fecha_hora_plan, cod_ruta, patente);
					
					
					
					reserva = dres.getByFechaUsuario(fecha_res , username_reserva);
										
					
					plan_reserva.setPlan(plan);
					plan_reserva.setReserva(reserva);
					
					planes_reservas.add(plan_reserva);
				}
			}
		}
		catch(SQLException e) 
		{
			throw new AppDataException(e, "Error al intentar recuperar reservas para el plan elegido");
		}
		
		try 
		{
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			Conectar.getInstancia().releasedConn();
		} catch(SQLException e) 
		{
			throw new AppDataException(e, "Error al intentar cerrar la base de datos");
		} 
		
		
		return planes_reservas;

	}
	
	
	
	public ArrayList<Plan_Reserva> getReservasxUsuario(Usuario usu) throws AppDataException
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Plan_Reserva> reservas = new ArrayList<>();
		Plan_Reserva reserva = new Plan_Reserva();
		String sql = "select * from planes_reservas planres \r\n" + 
				"inner join reservas res\r\n" + 
				"where usuario_reserva= ? and res.fecha_res=planres.fecha_res and (fecha_canc is null)\r\n" + 
				"order by fecha_hora_plan";
		
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setString(1, usu.getUsername());
			
			rs = pstmt.executeQuery();
			
				while(rs.next()) 
				{
					
					reserva = setPlanReserva(rs);
					reservas.add(reserva);
					

				}
			
		}
		catch(SQLException e) 
		{
			throw new AppDataException(e, "Error al intentar recuperar reservas para el usuario seleccionado la base de datos");
		}
		
		try 
		{
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			Conectar.getInstancia().releasedConn();
		} catch(SQLException e) 
		{
			throw new AppDataException(e, "Error al intentar cerrar la base de datos");
		} 
		
		return reservas;

	}	
	
	

		
	public ArrayList<Plan_Reserva> getViajesxChofer(Usuario chofer) throws AppDataException
	{
		
			
		String sql= "SELECT DISTINCT * FROM planes_reservas pr\r\n" + 
				"INNER JOIN planes p on p.fecha_hora_plan = pr.fecha_hora_plan and p.cod_ruta = pr.cod_ruta and p.patente = pr.patente "
				+ " where usuario_reserva = ? ";
		
		ArrayList<Plan_Reserva> planes_reservas = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Plan_Reserva plan_reserva = new Plan_Reserva();
	
		
		try 
		{
			
				pstmt = Conectar.getInstancia().getConn().prepareStatement(sql); //Origen y destino sin especificar	
				pstmt.setString(1, chofer.getUsername());
			
			rs = pstmt.executeQuery();
			
			if(rs!=null) 
			{
				while(rs.next()) 
				{
					
					Plan plan= new Plan();
					Reserva reserva=new Reserva();
					DataPlan dplan= new DataPlan();
					 FechaControlers fec= new FechaControlers();
				    DataReserva dres= new DataReserva();
						
				
					String fecPlan=(rs.getString("p.fecha_hora_plan"));
					Date fechaPlan=fec.fechaConGuion(fecPlan);
					String patente= rs.getString("p.patente");
					int codRuta=rs.getInt("p.cod_ruta");
					plan=dplan.getByFechaHoraRutaPatente(fechaPlan, codRuta, patente);
					
					String fecRes = rs.getString("pr.fecha_res");
					String username = rs.getString("pr.usuario_reserva");
					Date fechaRes= fec.fechaConGuion(fecRes);
					reserva=dres.getByFechaUsuario( fechaRes,  username);
					
					plan_reserva.setReserva(reserva);
					plan_reserva.setPlan(plan);

					planes_reservas.add(plan_reserva);

				}
									
				
				
			}
		}
		catch(SQLException e) 
		{
			throw new AppDataException(e, "Error al intentar recuperar viajes por chofer en la base de datos");
		}
		
		try 
		{
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			Conectar.getInstancia().releasedConn();
		} catch(SQLException e) 
		{
			throw new AppDataException(e, "Error al intentar cerrar la base de datos");
		} 
		
		return planes_reservas;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public  void agregarReservaPlan(Plan_Reserva planReserva) throws AppDataException {
		
		
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO planes_reservas (fecha_res, fecha_hora_plan, patente, usuario_reserva,  cod_ruta) VALUES (?,?,?,?,?) ";
		
		
		
		try 
		{
			pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
			pstmt.setTimestamp(1, new Timestamp(planReserva.getReserva().getFecha_res().getTime()));
			pstmt.setTimestamp(2, new Timestamp(planReserva.getPlan().getFechaHora().getTime()));
			pstmt.setString(3, planReserva.getPlan().getColectivo().getPatente());
			pstmt.setString(4, planReserva.getReserva().getUsuario().getUsername());
			pstmt.setInt(5, planReserva.getPlan().getRuta().getCod_ruta());
		
			
		    pstmt.executeUpdate();
			
			
 		}catch(SQLException e) 
		{ 
 			throw new AppDataException(e, "Error al intentar ingresar una nueva reserva para el plan seleccionado en base de datos");
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
	
	private Plan_Reserva setPlanReserva(ResultSet rs) throws AppDataException 
	{   
		
		Plan_Reserva planRes= new Plan_Reserva();
		Plan plan= new Plan();
		Reserva reserva=new Reserva();
		DataPlan dplan= new DataPlan();
		 FechaControlers fec= new FechaControlers();
	    DataReserva dres= new DataReserva();
			
	try {
		String fecPlan=(rs.getString("fecha_hora_plan"));
		Date fechaPlan=fec.fechaConGuion(fecPlan);
		String patente= rs.getString("patente");
		int codRuta=rs.getInt("cod_ruta");
		plan=dplan.getByFechaHoraRutaPatente(fechaPlan, codRuta, patente);
		
		String fecRes = rs.getString("fecha_res");
		String username = rs.getString("usuario_reserva");
		Date fechaRes= fec.fechaConGuion(fecRes);
		reserva=dres.getByFechaUsuario( fechaRes,  username);
		
		planRes.setReserva(reserva);
		planRes.setPlan(plan);
		
		
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new AppDataException(e, "Error al intentar recuperar plan reserva en la base de datos");
			}
	
			
			return planRes;
		}
	
		
	public void eliminarPlanesReservasxPlan(Plan plan) throws AppDataException 
	{

		PreparedStatement pstmt = null;

		String sql = "DELETE FROM planes_reservas where fecha_hora_plan = ? and cod_ruta = ? and patente = ? ";
			
		
		try 
		{
			
			
		pstmt = Conectar.getInstancia().getConn().prepareStatement(sql);
		
			pstmt.setTimestamp(1, new Timestamp(plan.getFechaHora().getTime()));
			pstmt.setInt(2, plan.getRuta().getCod_ruta());
			pstmt.setString(3, plan.getColectivo().getPatente());
			
			
			
			pstmt.executeUpdate();			
			
			
			
		} catch(SQLException e) 
		{
			
			throw new AppDataException(e, "Error al intentar eliminar filas de la tabla pasajeros_reservas en baase de datos.");

		}
		
		try 
		{
			if(pstmt!=null) {pstmt.close();}
			Conectar.getInstancia().releasedConn();
			
			
			
		} catch(SQLException e) 
		{
			throw new AppDataException(e, "Error al intentar cerrar la base de datos.");
		
		}

	}
	
}
	
	
	
	
	
