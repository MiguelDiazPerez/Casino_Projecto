package Casino.Modelo;

import java.util.*;

public class Ruleta {
    private static final Set<Integer> NUMEROS_ROJOS = Set.of(
            1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36
    );

    private final Random rng = new Random();
    private final List<String> historial = new ArrayList<>();
    private int gananciaNeta = 0;
    private int saldo;
    public Ruleta() { this(0); }
    public Ruleta(int saldoInicial) {
        this.saldo = Math.max(0, saldoInicial);
    }

    public int getSaldo() { return saldo; }
    public void depositar(int monto) {
        if (monto <= 0) throw new IllegalArgumentException("Monto inválido");
        this.saldo += monto;
    }

    private int girar() { return rng.nextInt(37); }

    public static boolean esRojo(int n) { return NUMEROS_ROJOS.contains(n); }

    public boolean evaluarResultado(int numero, TipoApuesta tipo) {
        return switch (tipo) {
            case ROJO -> esRojo(numero);
            case NEGRO -> numero != 0 && !esRojo(numero);
            case PAR   -> numero != 0 && numero % 2 == 0;
            case IMPAR -> numero % 2 == 1;
        };
    }

    public List<String> getHistorial() { return Collections.unmodifiableList(historial); }
    public int getGananciaNeta() { return gananciaNeta; }
    public ResultadoJugada jugar(TipoApuesta tipo, int monto) {
        if (monto <= 0) throw new IllegalArgumentException("Monto inválido");
        if (monto > saldo) throw new IllegalArgumentException("Saldo insuficiente");

        saldo -= monto;
        int numero = girar();
        boolean acierta = evaluarResultado(numero, tipo);

        int delta = acierta ? monto * 2 : 0; // premio bruto
        saldo += delta;
        gananciaNeta += acierta ? monto : -monto;

        String linea = "Número: " + numero +
                (esRojo(numero) ? " (Rojo)" : " (Negro)") +
                " | Apuesta: " + tipo +
                " | Monto: $" + monto +
                (acierta ? " | GANÓ $" + (monto * 2) : " | PERDIÓ $" + monto) +
                " | Saldo: $" + saldo;
        historial.add(linea);

        return new ResultadoJugada(numero, acierta, delta, saldo, linea);
    }

    public static class ResultadoJugada {
        public final int numero;
        public final boolean acierto;
        public final int premio;
        public final int saldoFinal;
        public final String descripcion;

        public ResultadoJugada(int numero, boolean acierto, int premio, int saldoFinal, String descripcion) {
            this.numero = numero;
            this.acierto = acierto;
            this.premio = premio;
            this.saldoFinal = saldoFinal;
            this.descripcion = descripcion;
        }
    }
}