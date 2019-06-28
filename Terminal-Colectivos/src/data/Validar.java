package data;

import entities.Usuario;

public interface Validar {
	public boolean validar(Usuario usu);
	public boolean validar(Usuario usu, String txtpass);
}
