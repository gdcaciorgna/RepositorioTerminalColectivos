package entities;

import java.util.Date;

public class Reserva {
	
	private Date fecha_res;
	private Usuario usuario;
	private int cant_pas;
	private Date fecha_canc;
	private Compania_Tarjeta compania_tarjeta;
	private int nro_tarjeta;
	
	
	public Date getFecha_res() {
		return fecha_res;
	}
	public void setFecha_res(Date fecha_res) {
		this.fecha_res = fecha_res;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public int getCant_pas() {
		return cant_pas;
	}
	public void setCant_pas(int cant_pas) {
		this.cant_pas = cant_pas;
	}
	public Date getFecha_canc() {
		return fecha_canc;
	}
	public void setFecha_canc(Date fecha_canc) {
		this.fecha_canc = fecha_canc;
	}
	public Compania_Tarjeta getCompania_tarjeta() {
		return compania_tarjeta;
	}
	public void setCompania_tarjeta(Compania_Tarjeta compania_tarjeta) {
		this.compania_tarjeta = compania_tarjeta;
	}
	public int getNro_tarjeta() {
		return nro_tarjeta;
	}
	public void setNro_tarjeta(int nro_tarjeta) {
		this.nro_tarjeta = nro_tarjeta;
	}
	
	
	
	
	
	

}
