package entities;

import java.util.Calendar;

public class Plan {
	private String fecha;
	private String hora;
	private Colectivo colectivo;
	private Usuario chofer;
	private Ruta ruta;
	private double precio;
	private String origen;
	private String destino;
	
	
	

	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
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
