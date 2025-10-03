package Casino.Modelo;

import java.util.Random;
import java.util.Set;

public class Ruleta {
    private static final Set<Integer> NUMEROS_ROJOS = Set.of(
            1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36
    );
    private final Random rng = new Random();

    public int girar() {
        return rng.nextInt(37); // 0..36
    }

    public boolean esRojo(int n) {
        return NUMEROS_ROJOS.contains(n);
    }

    public boolean esPar(int n) {
        return n != 0 && (n % 2 == 0);
    }
}