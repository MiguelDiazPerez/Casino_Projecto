package Casino.Controlador;

import Casino.Modelo.Usuario;

public class SesionControl {
    private Usuario usuarioActual;

    public void login(Usuario u) {
        this.usuarioActual = u;
    }

    public void logout() {
        this.usuarioActual = null;
    }

    public boolean haySesion() {
        return usuarioActual != null;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
}