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
    private final JTextArea txtResultado = new JTextArea();
    private final Ruleta motor = new Ruleta();

    public VentanaRuleta() {
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.add(new JLabel("Monto a apostar:"));
        frame.add(txtMonto);
        frame.add(new JLabel("Tipo de apuesta:"));
        frame.add(cmbApuesta);
        frame.add(btnJugar);
        frame.add(new JScrollPane(txtResultado));

        btnJugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jugar();
            }
        });
    }

    private void jugar() {
        try {
            int monto = Integer.parseInt(txtMonto.getText());
            String opcion = (String) cmbApuesta.getSelectedItem();
            char tipo = opcion.charAt(opcion.length() - 2); // P, I, R o N
            int numero = motor.girar();
            boolean acierto = motor.evaluar(numero, tipo);

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

    public void mostrarVentana() {
        frame.setVisible(true);
    }
}