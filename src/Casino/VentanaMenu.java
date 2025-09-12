package Casino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaMenu {
    private final JFrame frame = new JFrame("Menú Casino");

    public VentanaMenu(String nombreUsuario) {
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel lblBienvenida = new JLabel("Bienvenido " + nombreUsuario, SwingConstants.CENTER);
        frame.add(lblBienvenida, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new GridLayout(3, 2, 10, 10));

        JButton btnJugar = new JButton("Jugar Ruleta");
        JLabel lblJugar = new JLabel("Abrir el juego de la ruleta", SwingConstants.LEFT);

        JButton btnSalir = new JButton("Cerrar Sesión");
        JLabel lblSalir = new JLabel("Volver al login", SwingConstants.LEFT);

        JButton btnSalirApp = new JButton("Salir del Programa");
        JLabel lblSalirApp = new JLabel("Cerrar completamente la aplicación", SwingConstants.LEFT);

        panelCentral.add(btnJugar);
        panelCentral.add(lblJugar);
        panelCentral.add(btnSalir);
        panelCentral.add(lblSalir);
        panelCentral.add(btnSalirApp);
        panelCentral.add(lblSalirApp);

        frame.add(panelCentral, BorderLayout.CENTER);

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

        btnSalirApp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
}
