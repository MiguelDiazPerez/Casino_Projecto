package Casino.Controlador;

import Casino.Modelo.Resultado;

import java.util.Comparator;
import java.util.List;

public class ResultadoControl {
    private final SesionControl session;
    private final RepositorioResultadosArchivo repo;

    public ResultadoControl(SesionControl session, RepositorioResultadosArchivo repo) {
        this.session = session;
        this.repo = repo;
    }

    public List<Resultado> obtenerHistorialOrdenadoReciente() {
        var u = session.getUsuarioActual();
        var lista = u.getHistorial();
        return lista.stream()
                .sorted(Comparator.comparingLong(Resultado::getTimestamp).reversed())
                .toList();
    }

    public int calcularSaldoNeto() {
        var u = session.getUsuarioActual();
        return u.getHistorial().stream().mapToInt(Resultado::getGanancia).sum();
    }

    public void persistirUltimo(Resultado r) {
        repo.guardarResultado(session.getUsuarioActual(), r);
    }

    public void cargarDesdeArchivo() {
        var u = session.getUsuarioActual();
        repo.cargarHistorial(u).forEach(u::agregarResultado);
    }
}