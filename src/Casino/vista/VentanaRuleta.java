package Casino.vista;

import Casino.Controlador.RuletaControl;
import Casino.Controlador.SesionControl;
import Casino.Modelo.TipoApuesta;
import Casino.Modelo.Ruleta;

import javax.swing.*;
import java.awt.*;

public class VentanaRuleta {
    private final JFrame frame = new JFrame("Juego Ruleta (MVC)");
    private final JComboBox<TipoApuesta> cboTipo = new JComboBox<>(TipoApuesta.values());
    private final JTextField txtMonto = new JTextField("100");
    private final JButton btnGirar = new JButton("Girar");
    private final JButton btnHistorial = new JButton("Ver Historial");
    private final JButton btnVolver = new JButton("Volver al Menú");
    private final JTextArea txtSalida = new JTextArea();

    private final RuletaControl ruletaCtrl;
    private final SesionControl session;

    public VentanaRuleta(SesionControl session) {
        this.session = session;
        this.ruletaCtrl = new RuletaControl(session);

        frame.setSize(820, 420);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel north = new JPanel(new GridLayout(2, 3, 10, 10));
        north.add(new JLabel("Tipo apuesta:"));
        north.add(cboTipo);
        north.add(new JLabel("Saldo: $" + ruletaCtrl.getSaldo()));
        north.add(new JLabel("Monto:"));
        north.add(txtMonto);
        north.add(new JLabel());

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        acciones.add(btnGirar);
        acciones.add(btnHistorial);
        acciones.add(btnVolver);

        txtSalida.setEditable(false);
        txtSalida.setFont(new Font("Arial", Font.BOLD, 15));
        txtSalida.setRows(15);
        JScrollPane scroll = new JScrollPane(txtSalida);

        frame.add(north, BorderLayout.NORTH);
        frame.add(acciones, BorderLayout.CENTER);
        frame.add(scroll, BorderLayout.SOUTH);

        btnGirar.addActionListener(e -> jugar());
        btnHistorial.addActionListener(e -> mostrarHistorial());
        btnVolver.addActionListener(e -> {
            frame.dispose();
            new VentanaMenu(session).mostrar();
        });
    }

    private void jugar() {
        try {
            int monto = Integer.parseInt(txtMonto.getText().trim());
            TipoApuesta tipo = (TipoApuesta) cboTipo.getSelectedItem();
            Ruleta.ResultadoJugada r = ruletaCtrl.jugar(tipo, monto);
            txtSalida.setText(r.descripcion);
            ((JLabel)((JPanel)frame.getContentPane().getComponent(0)).getComponent(2))
                    .setText("Saldo: $" + ruletaCtrl.getSaldo());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Monto inválido");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage());
        }
    }

    private void mostrarHistorial() {
        StringBuilder sb = new StringBuilder();
        for (String h : ruletaCtrl.historial()) sb.append(h).append("\n");
        txtSalida.setText(sb.toString());
    }

    public void mostrar() { frame.setVisible(true); }
}

