package entities;

public class Localidad {
	
	private int id_localidad;
	private Provincia provincia;
	String nombre;
	
	
	public int getId_localidad() {
		return id_localidad;
	}
	public void setId_localidad(int id_localidad) {
		this.id_localidad = id_localidad;
	}
	
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	

}
