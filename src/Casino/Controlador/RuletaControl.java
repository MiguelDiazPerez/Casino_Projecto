package Casino.Controlador;

import Casino.Modelo.*;

public class RuletaControl {
    private final SesionControl session;
    private final Ruleta ruleta;

    public RuletaControl(SesionControl session, Ruleta ruleta) {
        this.session = session;
        this.ruleta = ruleta;
    }

    public ApuestaBase crearApuesta(String tipoUI, int monto) {
        return switch (tipoUI.toLowerCase()) {
            case "rojo"  -> new ApuestaRojo(monto);
            case "negro" -> new ApuestaNegro(monto);
            case "par"   -> new ApuestaPar(monto);
            case "impar" -> new ApuestaImpar(monto);
            default -> throw new IllegalArgumentException("Tipo de apuesta no soportado: " + tipoUI);
        };
    }

    public Resultado apostarYGirar(ApuestaBase apuesta) {
        if (!session.haySesion()) throw new IllegalStateException("No hay usuario en sesión");
        Resultado r = ruleta.jugar(apuesta);
        session.getUsuarioActual().agregarResultado(r);
        return r;
    }
}