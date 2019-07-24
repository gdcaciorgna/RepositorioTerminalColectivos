package data;

import entities.Usuario;

public interface Validar {
	public boolean validarUsuarioyPassword(Usuario usu);
	public boolean validarUsuarioyPassword(Usuario usu, String txtpass);
}
