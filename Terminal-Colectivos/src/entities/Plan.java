package entities;

import java.sql.Timestamp;

public class Plan {
	private Timestamp fecha_hora_plan;
	private Colectivo colectivo;
	private Chofer chofer;
	private Ruta ruta;
	private double precio;
	
	
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
	public Chofer getChofer() {
		return chofer;
	}
	public void setChofer(Chofer chofer) {
		this.chofer = chofer;
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
	
	
	
}
