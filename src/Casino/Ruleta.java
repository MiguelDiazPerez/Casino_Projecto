package Casino;

import java.util.Random;
import java.util.Scanner;

public class Ruleta {
    public static final int MAX_HISTORIAL = 100;
    public static int[] historialNumeros = new int[MAX_HISTORIAL];
    public static int[] historialMontos = new int[MAX_HISTORIAL];
    public static char[] historialTiposApuesta = new char[MAX_HISTORIAL];
    public static boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    public static int historialSize = 0;
    public static Random rng = new Random();
    public static int[] numerosRojos = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner in = new Scanner(System.in);
        while (true) {
            mostrarMenu();
            int opcion = leerOpcion(in);
            ejecutarOpcion(opcion, in);
        }
    }

    public static void mostrarMenu() {
        System.out.println("1. Iniciar ronda");
        System.out.println("2. Ver estadisticas");
        System.out.println("3. Salir");
    }

    public static int leerOpcion(Scanner in) {
        if (in.hasNextInt()) {
            return in.nextInt();
        }
        in.next();
        return -1;
    }

    public static void ejecutarOpcion(int opcion, Scanner in) {
        if (opcion == 1) {
            iniciarRonda(in);
        } else if (opcion == 2) {
            mostrarEstadisticas();
        } else if (opcion == 3) {
            System.exit(0);
        }
    }

    public static void iniciarRonda(Scanner in) {
        if (historialSize >= MAX_HISTORIAL) return;
        System.out.println("Ingrese tipo de apuesta (P=Par, I=Impar, R=Rojo, N=Negro):");
        char tipo = leerTipoApuesta(in);
        System.out.println("Ingrese monto:");
        if (!in.hasNextInt()) {
            in.next();
            return;
        }
        int monto = in.nextInt();
        if (monto <= 0) return;
        int numero = girarRuleta();
        boolean acierto = evaluarResultado(numero, tipo);
        registrarResultado(numero, monto, tipo, acierto);
        mostrarResultado(numero, tipo, monto, acierto);
    }

    public static char leerTipoApuesta(Scanner in) {
        return in.next().toUpperCase().charAt(0);
    }

    public static int girarRuleta() {
        return rng.nextInt(37);
    }

    public static boolean evaluarResultado(int numero, char tipo) {
        if (numero == 0) return false;
        if (tipo == 'P') return numero % 2 == 0;
        if (tipo == 'I') return numero % 2 == 1;
        if (tipo == 'R') return esRojo(numero);
        if (tipo == 'N') return !esRojo(numero);
        return false;
    }

    public static boolean esRojo(int n) {
        for (int r : numerosRojos) {
            if (r == n) return true;
        }
        return false;
    }

    public static void registrarResultado(int numero, int monto, char tipo, boolean acierto) {
        historialNumeros[historialSize] = numero;
        historialMontos[historialSize] = monto;
        historialTiposApuesta[historialSize] = tipo;
        historialAciertos[historialSize] = acierto;
        historialSize++;
    }

    public static void mostrarResultado(int numero, char tipo, int monto, boolean acierto) {
        System.out.println("Numero: " + numero);
        System.out.println("Apuesta: " + tipo);
        System.out.println("Monto: " + monto);
        if (acierto) {
            System.out.println("Ganaste $" + (monto * 2));
        } else {
            System.out.println("Perdiste $" + monto);
        }
    }

    public static void mostrarEstadisticas() {
        if (historialSize == 0) {
            System.out.println("No hay rondas jugadas.");
            return;
        }
        int total = historialSize;
        int aciertos = 0;
        int neto = 0;
        for (int i = 0; i < historialSize; i++) {
            if (historialAciertos[i]) {
                aciertos++;
                neto += historialMontos[i];
            } else {
                neto -= historialMontos[i];
            }
        }
        System.out.println("Rondas: " + total);
        System.out.println("Aciertos: " + aciertos);
        System.out.println("Ganancia/Pérdida neta: " + neto);
    }
}