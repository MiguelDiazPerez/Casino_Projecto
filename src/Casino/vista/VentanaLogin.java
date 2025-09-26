package Casino.vista;

import Casino.Controlador.SesionControl;
import Casino.Controlador.SesionControl;

import javax.swing.*;
import java.awt.*;

public class VentanaLogin {
    private final JFrame frame = new JFrame("Login - Casino Black Cat");
    private final JTextField txtUsuario = new JTextField();
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnIngresar = new JButton("Ingresar");
    private final SesionControl session;

    public VentanaLogin(SesionControl session) {
        this.session = session;

        session.registrarUsuario("diego", "2025", "Diego");

        frame.setSize(320, 160);
        frame.setLayout(new GridLayout(3, 2, 8, 8));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.add(new JLabel("Usuario:"));
        frame.add(txtUsuario);
        frame.add(new JLabel("Clave:"));
        frame.add(txtClave);
        frame.add(new JLabel());
        frame.add(btnIngresar);

        btnIngresar.addActionListener(e -> intentarLogin());
    }

    private void intentarLogin() {
        String u = txtUsuario.getText().trim();
        String p = new String(txtClave.getPassword());
        if (session.iniciarSesion(u, p)) {
            JOptionPane.showMessageDialog(frame, "Bienvenido " + session.getUsuarioActual().getNombre());
            frame.dispose();
            new VentanaMenu(session).mostrar();
        } else {
            JOptionPane.showMessageDialog(frame, "Credenciales incorrectas");
        }
    }

    public void mostrar() { frame.setVisible(true); }
}

