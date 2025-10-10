package Casino.Controlador;

import Casino.Modelo.*;

public class RuletaControl {
    private final SesionControl session;
    private final Ruleta ruleta;

    public RuletaControl(SesionControl session, Ruleta ruleta) {
        this.session = session;
        this.ruleta = ruleta;
    }

    public Resultado apostarYGirar(TipoApuesta tipo, String eleccion, int monto) {
        if (!session.haySesion()) throw new IllegalStateException("No hay usuario en sesión");

        int numero = ruleta.girar();
        boolean esRojo = ruleta.esRojo(numero);
        boolean esPar  = ruleta.esPar(numero);
        boolean acierto = switch (tipo) {
            case COLOR -> {
                boolean eligioRojo = "rojo".equalsIgnoreCase(eleccion);
                boolean eligioNegro = "negro".equalsIgnoreCase(eleccion);
                yield (eligioRojo && esRojo) || (eligioNegro && !esRojo && numero != 0);
            }
            case PARIDAD -> {
                boolean eligioPar = "par".equalsIgnoreCase(eleccion);
                boolean eligioImpar = "impar".equalsIgnoreCase(eleccion);
                yield (eligioPar && esPar) || (eligioImpar && !esPar && numero != 0);
            }
            case NUMERO -> {
                try {
                    int n = Integer.parseInt(eleccion);
                    yield n == numero;
                } catch (NumberFormatException e) {
                    yield false;
                }
            }
        };

        int ganancia = calcularGanancia(tipo, acierto, monto);

        Resultado r = new Resultado(numero, esRojo, esPar, tipo, monto, ganancia);
        session.getUsuarioActual().agregarResultado(r);
        return r;
    }

    private int calcularGanancia(TipoApuesta tipo, boolean acierto, int monto) {
        if (!acierto) return -monto;
        return switch (tipo) {
            case COLOR, PARIDAD -> monto;
            case NUMERO          -> monto * 35;
        };
    }
}