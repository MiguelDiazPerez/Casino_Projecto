package Casino.Modelo;

public class Resultado {
    private final int numero;
    private final String color;
    private final boolean esPar;
    private final String etiquetaApuesta;
    private final int ganancia;
    private final long timestamp;

    public Resultado(int numero, String color, boolean esPar,
                     String etiquetaApuesta, int monto, int ganancia) {
        this.numero = numero;
        this.color = color;
        this.esPar = esPar;
        this.etiquetaApuesta = etiquetaApuesta;
        this.monto = monto;
        this.ganancia = ganancia;
        this.timestamp = System.currentTimeMillis();
    }

    public int getNumero() { return numero; }
    public String getColor() { return color; }
    public boolean isEsPar() { return esPar; }
    public String getEtiquetaApuesta() { return etiquetaApuesta; }
    public int getMonto() { return monto; }
    public int getGanancia() { return ganancia; }
    public long getTimestamp() { return timestamp; }
}
