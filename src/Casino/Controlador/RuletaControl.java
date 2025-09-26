package Casino.Controlador;

import Casino.Modelo.Ruleta;
import Casino.Modelo.TipoApuesta;

public class RuletaControl {
    private final SesionControl session;

    public RuletaControl(SesionControl session) {
        this.session = session;
    }

    public int getSaldo() { return session.getRuleta().getSaldo(); }

    public void depositar(int monto) { session.getRuleta().depositar(monto); }

    public Ruleta.ResultadoJugada jugar(TipoApuesta tipo, int monto) {
        return session.getRuleta().jugar(tipo, monto);
    }

    public java.util.List<String> historial() { return session.getRuleta().getHistorial(); }
}
