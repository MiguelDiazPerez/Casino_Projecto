package Casino.Modelo;

import java.util.Random;
import java.util.Set;

public class Ruleta {
    private static final Set<Integer> NUMEROS_ROJOS = Set.of(
            1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36
    );
    private final Random rng = new Random();

    public int girar() { return rng.nextInt(37); }

    public String colorDe(int n) {
        if (n == 0) return "Verde";
        return NUMEROS_ROJOS.contains(n) ? "Rojo" : "Negro";
    }

    public boolean esPar(int n) { return n != 0 && (n % 2 == 0); }

    public Resultado jugar(ApuestaBase apuesta) {
        int numero = girar();
        String color = colorDe(numero);
        boolean acierta = apuesta.acierta(numero, color);

        int ganancia = calcularGanancia(apuesta, acierta);
        return new Resultado(
                numero, color, esPar(numero),
                apuesta.getEtiqueta(), apuesta.getMonto(), ganancia
        );
    }

    private int calcularGanancia(ApuestaBase apuesta, boolean acierta) {
        if (!acierta) return -apuesta.getMonto();
        return apuesta.getMonto();
    }
}