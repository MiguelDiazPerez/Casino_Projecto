package Casino.Modelo;

public abstract class ApuestaBase {
    protected final int monto;
    protected final String etiqueta; // "Rojo", "Negro", "Par", "Impar", etc.

    protected ApuestaBase(int monto, String etiqueta) {
        if (monto <= 0) throw new IllegalArgumentException("Monto debe ser > 0");
        this.monto = monto;
        this.etiqueta = etiqueta;
    }

    public int getMonto() { return monto; }
    public String getEtiqueta() { return etiqueta; }

    /** Regla de acierto polimórfica */
    public abstract boolean acierta(int numero, String color);
}