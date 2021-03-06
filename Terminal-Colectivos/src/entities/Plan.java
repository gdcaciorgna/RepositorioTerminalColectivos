package entities;

import java.util.Date;

public class Plan {
	private Date fechaHora;
	private Colectivo colectivo;
	private Usuario chofer;
	private Ruta ruta;
	private double precio;
	private String origen;
	private String destino;
	
	
	

	
	
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
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
