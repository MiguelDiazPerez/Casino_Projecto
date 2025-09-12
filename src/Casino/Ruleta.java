package Casino;
import java.util.Random;

public class Ruleta {
    private static final int[] NUMEROS_ROJOS =
            {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
    private final Random rng = new Random();

    public int girar() {
        return rng.nextInt(37);
    }

    public boolean esRojo(int n) {
        for (int r : NUMEROS_ROJOS) if (r == n) return true;
        return false;
    }

    public boolean evaluar(int numero, char tipo) {
        if (numero == 0) return false;
        if (tipo == 'P') return numero % 2 == 0;
        if (tipo == 'I') return numero % 2 == 1;
        if (tipo == 'R') return esRojo(numero);
        if (tipo == 'N') return !esRojo(numero);
        return false;
    }
}