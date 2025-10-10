package Casino.vista;

import Casino.Controlador.ResultadoControl;
import Casino.Modelo.Resultado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaHistorial extends JFrame {
    private final ResultadoControl controller;
    private final DefaultTableModel model =
            new DefaultTableModel(new Object[]{"Fecha", "N°", "Color", "Paridad", "Tipo", "Monto", "Ganancia"}, 0);
    private final JTable tabla = new JTable(model);
    private final JLabel lblSaldo = new JLabel();

    public VentanaHistorial(ResultadoControl controller) {
        super("Historial de Jugadas");
        this.controller = controller;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(8,8));

        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(lblSaldo, BorderLayout.SOUTH);

        refrescar();
        setSize(700, 400);
        setLocationRelativeTo(null);
    }

    private void refrescar() {
        model.setRowCount(0);
        List<Resultado> lista = controller.obtenerHistorialOrdenadoReciente();
        for (Resultado r : lista) {
            model.addRow(new Object[]{
                    new java.util.Date(r.getTimestamp()),
                    r.getNumero(),
                    r.isEsRojo() ? "Rojo" : (r.getNumero()==0 ? "Verde" : "Negro"),
                    r.isEsPar() ? "Par" : (r.getNumero()==0 ? "-" : "Impar"),
                    r.getTipo(),
                    r.getMonto(),
                    r.getGanancia()
            });
        }
        lblSaldo.setText("Saldo neto: " + controller.calcularSaldoNeto());
    }
}