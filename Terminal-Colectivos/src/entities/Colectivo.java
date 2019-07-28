package entities;

public class Colectivo {
	
	private String patente;
	private Empresa_Colectivo empresa;
	private int capacidad;
	private String tipo_colectivo;
	
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public Empresa_Colectivo getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa_Colectivo empresa) {
		this.empresa = empresa;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public String getTipo_colectivo() {
		return tipo_colectivo;
	}
	public void setTipo_colectivo(String tipo_colectivo) {
		this.tipo_colectivo = tipo_colectivo;
	}
	
	

}
