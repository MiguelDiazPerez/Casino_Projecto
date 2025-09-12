package Casino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaRuleta {
    private final JFrame frame = new JFrame("Juego Ruleta");
    private final JComboBox<String> cmbTipo =
            new JComboBox<>(new String[]{"Color", "Paridad"});
    private final JComboBox<String> cmbColor =
            new JComboBox<>(new String[]{"Rojo", "Negro"});
    private final JComboBox<String> cmbParidad =
            new JComboBox<>(new String[]{"Par", "Impar"});
    private final JTextField txtMonto = new JTextField("100");
    private final JButton btnGirar = new JButton("Girar");
    private final JButton btnHistorial = new JButton("Ver Historial");
    private final JButton btnSalir = new JButton("Salir al Menú");
    private final JTextArea txtResultado = new JTextArea();
    private final Ruleta motor = new Ruleta();

    public VentanaRuleta() {
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panelTop = new JPanel(new GridLayout(4, 2, 10, 10));
        panelTop.add(new JLabel("Tipo de apuesta:"));
        panelTop.add(cmbTipo);
        panelTop.add(new JLabel("Seleccione color:"));
        panelTop.add(cmbColor);
        panelTop.add(new JLabel("Seleccione paridad:"));
        panelTop.add(cmbParidad);
        panelTop.add(new JLabel("Monto:"));
        panelTop.add(txtMonto);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBotones.add(btnGirar);
        panelBotones.add(btnHistorial);
        panelBotones.add(btnSalir);

        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Arial", Font.BOLD, 16));
        txtResultado.setRows(8);
        JScrollPane scroll = new JScrollPane(txtResultado);

        frame.add(panelTop, BorderLayout.NORTH);
        frame.add(panelBotones, BorderLayout.CENTER);
        frame.add(scroll, BorderLayout.SOUTH);

        btnGirar.addActionListener(new ActionListener() {
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

        cmbTipo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarOpciones();
            }
        });

        actualizarOpciones();
    }

    private void actualizarOpciones() {
        if (cmbTipo.getSelectedItem().equals("Color")) {
            cmbColor.setEnabled(true);
            cmbParidad.setEnabled(false);
        } else {
            cmbColor.setEnabled(false);
            cmbParidad.setEnabled(true);
        }
    }

    private void jugar() {
        try {
            int monto = Integer.parseInt(txtMonto.getText());
            String opcion = (String) cmbTipo.getSelectedItem();
            char tipo;
            String apuestaDesc;

            if (opcion.equals("Color")) {
                tipo = cmbColor.getSelectedItem().equals("Rojo") ? 'R' : 'N';
                apuestaDesc = "Color=" + cmbColor.getSelectedItem();
            } else {
                tipo = cmbParidad.getSelectedItem().equals("Par") ? 'P' : 'I';
                apuestaDesc = "Paridad=" + cmbParidad.getSelectedItem();
            }

            int numero = motor.girar();
            boolean acierto = motor.evaluar(numero, tipo);

            motor.registrarJugada(numero, tipo, monto, acierto);

            String resultado = "Número " + numero +
                    (motor.esRojo(numero) ? " (Rojo)" : " (Negro)") +
                    " | " + apuestaDesc +
                    " | Monto=$" + monto +
                    (acierto ? " | GANASTE" : " | PERDISTE");
            txtResultado.setText(resultado);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Monto inválido");
        }
    }

    private void mostrarHistorial() {
        StringBuilder sb = new StringBuilder();
        for (String jugada : motor.getHistorial()) {
            sb.append(jugada).append("\n");
        }
        txtResultado.setText(sb.toString());
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
}

