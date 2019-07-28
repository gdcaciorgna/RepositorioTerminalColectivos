package entities;

import java.sql.Timestamp;

public class Plan {
	private Timestamp fecha_hora_plan;
	private Colectivo colectivo;
	private Usuario chofer;
	private Ruta ruta;
	private double precio;
	private String origen;
	private String destino;
	
	
	public Timestamp getFecha_hora_plan() {
		return fecha_hora_plan;
	}
	public void setFecha_hora_plan(Timestamp fecha_hora_plan) {
		this.fecha_hora_plan = fecha_hora_plan;
	}
	public Colectivo getColectivo() {
		return colectivo;
	}
	public void setColectivo(Colectivo colectivo) {
		this.colectivo = colectivo;
	}
	
	public Ruta getRuta() {
		return ruta;
	}
	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public Usuario getChofer() {
		return chofer;
	}
	public void setChofer(Usuario chofer) {
		this.chofer = chofer;
	}
	
	
	
	
}
