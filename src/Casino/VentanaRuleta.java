package Casino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaRuleta {
    private final JFrame frame = new JFrame("Juego Ruleta");
    private final JTextField txtMonto = new JTextField();
    private final JComboBox<String> cmbApuesta =
            new JComboBox<>(new String[]{"Par (P)", "Impar (I)", "Rojo (R)", "Negro (N)"});
    private final JButton btnJugar = new JButton("Girar");
    private final JButton btnHistorial = new JButton("Ver Historial");
    private final JButton btnSalir = new JButton("Salir al Menú");
    private final JTextArea txtResultado = new JTextArea();
    private final Ruleta motor = new Ruleta();

    public VentanaRuleta() {
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panelTop = new JPanel(new GridLayout(4, 2));
        panelTop.add(new JLabel("Monto a apostar:"));
        panelTop.add(txtMonto);
        panelTop.add(new JLabel("Tipo de apuesta:"));
        panelTop.add(cmbApuesta);
        panelTop.add(btnJugar);
        panelTop.add(btnHistorial);
        panelTop.add(btnSalir);

        txtResultado.setEditable(false);

        frame.add(panelTop, BorderLayout.NORTH);
        frame.add(new JScrollPane(txtResultado), BorderLayout.CENTER);

        btnJugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jugar();
            }
        });

        btnHistorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarHistorial();
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new VentanaMenu("Usuario").mostrarVentana();
            }
        });
    }

    private void jugar() {
        try {
            int monto = Integer.parseInt(txtMonto.getText());
            String opcion = (String) cmbApuesta.getSelectedItem();
            char tipo = opcion.charAt(opcion.length() - 2); // P, I, R, N
            int numero = motor.girar();
            boolean acierto = motor.evaluar(numero, tipo);

            motor.registrarJugada(numero, tipo, monto, acierto);

            StringBuilder sb = new StringBuilder();
            sb.append("Número: ").append(numero).append("\n");
            sb.append("Apuesta: ").append(opcion).append("\n");
            sb.append("Monto: $").append(monto).append("\n");
            sb.append(acierto ? "Ganaste $" + (monto * 2) : "Perdiste $" + monto);
            txtResultado.setText(sb.toString());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Monto inválido");
        }
    }

    private void mostrarHistorial() {
        StringBuilder sb = new StringBuilder();
        for (String jugada : motor.getHistorial()) {
            sb.append(jugada).append("\n");
        }
        sb.append("\nGanancia/Pérdida neta: $").append(motor.getGananciaNeta());
        txtResultado.setText(sb.toString());
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
}