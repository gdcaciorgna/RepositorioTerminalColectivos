package entities;

import java.sql.Timestamp;

public class Reserva {
	
	private Timestamp fecha_res;
	private Ruta ruta;
	private Usuario usuario;
	private int cant_pas;
	private Timestamp fecha_viaje;
	private Timestamp fecha_venc;
	private Timestamp fecha_canc;
	private Compania_Tarjeta compania_tarjeta;
	private int nro_tarjeta;
	private Timestamp fecha_pago;
	
	
	public Timestamp getFecha_res() {
		return fecha_res;
	}
	public void setFecha_res(Timestamp fecha_res) {
		this.fecha_res = fecha_res;
	}
	public Ruta getRuta() {
		return ruta;
	}
	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}
	
	public int getCant_pas() {
		return cant_pas;
	}
	public void setCant_pas(int cant_pas) {
		this.cant_pas = cant_pas;
	}
	public Timestamp getFecha_viaje() {
		return fecha_viaje;
	}
	public void setFecha_viaje(Timestamp fecha_viaje) {
		this.fecha_viaje = fecha_viaje;
	}
	public Timestamp getFecha_venc() {
		return fecha_venc;
	}
	public void setFecha_venc(Timestamp fecha_venc) {
		this.fecha_venc = fecha_venc;
	}
	public Timestamp getFecha_canc() {
		return fecha_canc;
	}
	public void setFecha_canc(Timestamp fecha_canc) {
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
	public Timestamp getFecha_pago() {
		return fecha_pago;
	}
	public void setFecha_pago(Timestamp fecha_pago) {
		this.fecha_pago = fecha_pago;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	

}
