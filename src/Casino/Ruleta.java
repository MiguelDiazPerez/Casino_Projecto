package Casino;

import java.util.*;

public class Ruleta {
    private static final Set<Integer> NUMEROS_ROJOS = Set.of(
            1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36
    );
    private final Random rng = new Random();
    private final List<String> historial = new ArrayList<>();
    private int gananciaNeta = 0;

    public int girar() {
        return rng.nextInt(37);
    }

    public boolean esRojo(int n) {
        return NUMEROS_ROJOS.contains(n);
    }

    public boolean evaluar(int numero, char tipo) {
        return switch (tipo) {
            case 'P' -> numero != 0 && numero % 2 == 0;
            case 'I' -> numero % 2 == 1;
            case 'R' -> esRojo(numero);
            case 'N' -> numero != 0 && !esRojo(numero);
            default -> false;
        };
    }

    public void registrarJugada(int numero, char tipo, int monto, boolean acierto) {
        historial.add("Número: " + numero +
                " | Apuesta: " + tipo +
                " | Monto: $" + monto +
                (acierto ? " | GANÓ $" + (monto * 2) : " | PERDIÓ $" + monto));
        gananciaNeta += acierto ? monto : -monto;
    }

    public List<String> getHistorial() {
        return historial;
    }

    public int getGananciaNeta() {
        return gananciaNeta;
    }
}