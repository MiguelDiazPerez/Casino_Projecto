package Casino.vista;

import Casino.Controlador.SesionControl;
import Casino.Controlador.RuletaControl;

import javax.swing.*;
import java.awt.*;

public class VentanaMenu {
    private final JFrame frame = new JFrame("Menú Casino");
    private final SesionControl session;
    private final RuletaControl ruletaCtrl;
    private final JLabel lblBienvenida = new JLabel("", SwingConstants.CENTER);
    private final JLabel lblSaldo = new JLabel("", SwingConstants.CENTER);

    public VentanaMenu(SesionControl session) {
        this.session = session;
        this.ruletaCtrl = new RuletaControl(session);

        frame.setSize(420, 240);
        frame.setLayout(new BorderLayout(10,10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        lblBienvenida.setText("Bienvenido " + session.getUsuarioActual().getNombre());
        lblBienvenida.setFont(lblBienvenida.getFont().deriveFont(Font.BOLD, 16f));
        frame.add(lblBienvenida, BorderLayout.NORTH);

        JPanel centro = new JPanel(new GridLayout(3, 2, 10, 10));
        JButton btnJugar = new JButton("Jugar Ruleta");
        JButton btnPerfil = new JButton("Perfil");
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        JButton btnSalirApp = new JButton("Salir");

        centro.add(btnJugar);      centro.add(new JLabel("Abrir el juego de la ruleta"));
        centro.add(btnPerfil);     centro.add(new JLabel("Editar nombre / Recargar saldo"));
        centro.add(btnCerrarSesion);centro.add(new JLabel("Volver al login"));
        frame.add(centro, BorderLayout.CENTER);

        lblSaldo.setFont(lblSaldo.getFont().deriveFont(Font.PLAIN, 14f));
        frame.add(lblSaldo, BorderLayout.SOUTH);
        refrescarSaldo();

        btnJugar.addActionListener(e -> {
            new VentanaRuleta(session).mostrar();
            frame.dispose();
        });

        btnPerfil.addActionListener(e -> new VentanaPerfil(session, this::refrescarSaldo).mostrar());

        btnCerrarSesion.addActionListener(e -> {
            frame.dispose();
            session.cerrarSesion();
            new VentanaLogin(session).mostrar();
        });

        btnSalirApp.addActionListener(e -> System.exit(0));
    }

    public void refrescarSaldo() {
        lblSaldo.setText("Saldo actual: $" + ruletaCtrl.getSaldo());
    }

    public void mostrar() { frame.setVisible(true); }
}
