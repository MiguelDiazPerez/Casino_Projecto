package Casino.Controlador;

import Casino.Modelo.Usuario;
import Casino.Modelo.Ruleta;

public class SesionControl {
    private Usuario usuarioActual;
    private final Ruleta ruleta;

    public SesionControl() {
        this.ruleta = new Ruleta(1000);
    }

    public void registrarUsuario(String u, String p, String n) {
        if (u == null || u.isBlank() || p == null || p.isBlank() || n == null || n.isBlank())
            throw new IllegalArgumentException("Datos requeridos");
        this.usuarioActual = new Usuario(u, p, n);
    }

    public boolean iniciarSesion(String u, String p) {
        if (usuarioActual == null) return false;
        return usuarioActual.validarCredenciales(u, p);
    }

    public boolean hayUsuario() { return usuarioActual != null; }
    public Usuario getUsuarioActual() { return usuarioActual; }
    public void cerrarSesion() { usuarioActual = null; }
    public Ruleta getRuleta() { return ruleta; }
}