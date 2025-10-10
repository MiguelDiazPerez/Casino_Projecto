package Casino.vista;

import Casino.Controlador.RuletaControl;
import Casino.Modelo.Resultado;
import Casino.Modelo.TipoApuesta;

import javax.swing.*;
import java.awt.*;

public class VentanaRuleta extends JFrame {
    private final RuletaControl controller;

    private final JComboBox<String> cmbTipo =
            new JComboBox<>(new String[]{"COLOR", "PARIDAD", "NUMERO"});
    private final JComboBox<String> cmbColor =
            new JComboBox<>(new String[]{"Rojo", "Negro"});
    private final JComboBox<String> cmbParidad =
            new JComboBox<>(new String[]{"Par", "Impar"});
    private final JTextField txtNumero = new JTextField("17");
    private final JTextField txtMonto = new JTextField("100");
    private final JTextArea txtResultado = new JTextArea(6, 40);
    private final JButton btnGirar = new JButton("Girar");

    public VentanaRuleta(RuletaControl controller) {
        super("Ruleta");
        this.controller = controller;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(8,8));

        JPanel pnlTop = new JPanel(new GridLayout(2, 1, 6, 6));
        JPanel fila1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fila1.add(new JLabel("Tipo:"));
        fila1.add(cmbTipo);
        fila1.add(new JLabel("Color:"));
        fila1.add(cmbColor);
        fila1.add(new JLabel("Paridad:"));
        fila1.add(cmbParidad);
        fila1.add(new JLabel("Número:"));
        fila1.add(txtNumero);

        JPanel fila2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fila2.add(new JLabel("Monto:"));
        fila2.add(txtMonto);
        fila2.add(btnGirar);

        pnlTop.add(fila1);
        pnlTop.add(fila2);

        add(pnlTop, BorderLayout.NORTH);
        add(new JScrollPane(txtResultado), BorderLayout.CENTER);

        btnGirar.addActionListener(e -> girar());

        pack();
        setLocationRelativeTo(null);
    }

    private void girar() {
        try {
            TipoApuesta tipo = TipoApuesta.valueOf(cmbTipo.getSelectedItem().toString());
            String eleccion = switch (tipo) {
                case COLOR -> cmbColor.getSelectedItem().toString();
                case PARIDAD -> cmbParidad.getSelectedItem().toString();
                case NUMERO -> txtNumero.getText().trim();
            };
            int monto = Integer.parseInt(txtMonto.getText().trim());
            Resultado r = controller.apostarYGirar(tipo, eleccion, monto);

            txtResultado.append(String.format(
                    "Giro: %d  | Rojo:%s Par:%s | Tipo:%s | Monto:%d | Ganancia:%d%n",
                    r.getNumero(), r.isEsRojo(), r.isEsPar(), r.getTipo(), r.getMonto(), r.getGanancia()
            ));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(),
                    "Apuesta inválida", JOptionPane.ERROR_MESSAGE);
        }
    }
}

