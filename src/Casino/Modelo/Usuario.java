package Casino.Modelo;

public class Usuario {
    private String username;
    private String password;
    private String nombre;
    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        setNombre(nombre);
    }

    public Usuario() {
        this("invitado", "1234", "Invitado");
    }

    public boolean validarCredenciales(String u, String p) {
        return this.username.equals(u) && this.password.equals(p);
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        this.nombre = nombre.trim();
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
}

