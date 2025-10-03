package Casino.Modelo;

public class Resultado {
    private final int numero;
    private final boolean esRojo;
    private final boolean esPar;
    private final TipoApuesta tipo;
    private final int monto;
    private final int ganancia;
    private final long timestamp;

    public Resultado(int numero, boolean esRojo, boolean esPar,
                     TipoApuesta tipo, int monto, int ganancia) {
        this.numero = numero;
        this.esRojo = esRojo;
        this.esPar = esPar;
        this.tipo = tipo;
        this.monto = monto;
        this.ganancia = ganancia;
        this.timestamp = System.currentTimeMillis();
    }

    public int getNumero() { return numero; }
    public boolean isEsRojo() { return esRojo; }
    public boolean isEsPar() { return esPar; }
    public TipoApuesta getTipo() { return tipo; }
    public int getMonto() { return monto; }
    public int getGanancia() { return ganancia; }
    public long getTimestamp() { return timestamp; }
}
