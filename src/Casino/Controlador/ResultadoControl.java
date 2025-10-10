package Casino.Controlador;

import Casino.Modelo.Resultado;

import java.util.Comparator;
import java.util.List;

public class HistorialController {
    private final SesionControl session;

    public HistorialController(SesionControl session) {
        this.session = session;
    }

    public List<Resultado> obtenerHistorialOrdenadoReciente() {
        List<Resultado> lista = session.getUsuarioActual().getHistorial();
        return lista.stream()
                .sorted(Comparator.comparingLong(Resultado::getTimestamp).reversed())
                .toList();
    }

    public int calcularSaldoNeto() {
        return session.getUsuarioActual().getHistorial()
                .stream().mapToInt(Resultado::getGanancia).sum();
    }
}