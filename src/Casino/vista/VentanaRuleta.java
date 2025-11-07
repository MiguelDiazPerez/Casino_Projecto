package Casino.vista;

import Casino.Controlador.RuletaControl;
import Casino.Controlador.ResultadoControl;
import Casino.Modelo.*;

import javax.swing.*;
import java.awt.*;

public class VentanaRuleta extends JFrame {
    private final RuletaControl ruletaCtrl;
    private final ResultadoControl resultadoCtrl;

    private final JComboBox<String> cmbTipo = new JComboBox<>(new String[]{"Rojo","Negro","Par","Impar"});
    private final JTextField txtMonto = new JTextField("100");
    private final JTextArea txtLog = new JTextArea(6, 40);
    private final JButton btnJugar = new JButton("Jugar");

    public VentanaRuleta(RuletaControl ruletaCtrl, ResultadoControl resultadoCtrl) {
        super("Ruleta");
        this.ruletaCtrl = ruletaCtrl;
        this.resultadoCtrl = resultadoCtrl;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(8,8));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Apuesta:"));
        top.add(cmbTipo);
        top.add(new JLabel("Monto:"));
        top.add(txtMonto);
        top.add(btnJugar);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(txtLog), BorderLayout.CENTER);

        btnJugar.addActionListener(e -> jugar());

        pack();
        setLocationRelativeTo(null);
    }

    private void jugar() {
        try {
            int monto = Integer.parseInt(txtMonto.getText().trim());
            String tipo = cmbTipo.getSelectedItem().toString();
            ApuestaBase apuesta = ruletaCtrl.crearApuesta(tipo, monto);
            Resultado r = ruletaCtrl.apostarYGirar(apuesta);

            resultadoCtrl.persistirUltimo(r);

            txtLog.append(String.format("N° %d | %s | %s | $%d | Ganancia: %d%n",
                    r.getNumero(), r.getColor(),
                    r.getEtiquetaApuesta(), r.getMonto(), r.getGanancia()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
