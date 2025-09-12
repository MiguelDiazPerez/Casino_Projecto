package Casino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaMenu {
    private final JFrame frame = new JFrame("Menú Casino");

    public VentanaMenu(String nombreUsuario) {
        frame.setSize(300, 150);
        frame.setLayout(new GridLayout(3, 1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel lbl = new JLabel("Bienvenido " + nombreUsuario, SwingConstants.CENTER);
        JButton btnJugar = new JButton("Jugar Ruleta");
        JButton btnSalir = new JButton("Cerrar Sesión");

        frame.add(lbl);
        frame.add(btnJugar);
        frame.add(btnSalir);

        btnJugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentanaRuleta().mostrarVentana();
                frame.dispose();
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new VentanaLogin().mostrarVentana();
            }
        });
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
}