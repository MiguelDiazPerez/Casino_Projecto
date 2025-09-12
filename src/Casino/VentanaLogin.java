package Casino;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaLogin {
    public static final List<Usuario> USUARIOS = new ArrayList<>();
    private final JFrame frame = new JFrame("Login - Casino Black Cat");
    private final JTextField txtUsuario = new JTextField();
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnIngresar = new JButton("Ingresar");

    public VentanaLogin() {
        USUARIOS.add(new Usuario("admin", "1234", "Administrador"));
        USUARIOS.add(new Usuario("diego", "2025", "Diego"));

        frame.setSize(300, 150);
        frame.setLayout(new GridLayout(3, 2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.add(new JLabel("Usuario:"));
        frame.add(txtUsuario);
        frame.add(new JLabel("Clave:"));
        frame.add(txtClave);
        frame.add(btnIngresar);

        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }

    private void login() {
        String usuario = txtUsuario.getText();
        String clave = new String(txtClave.getPassword());
        String nombre = validarCredenciales(usuario, clave);

        if (!nombre.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Bienvenido " + nombre);
            frame.dispose();
            Ruleta.menu();
        } else {
            JOptionPane.showMessageDialog(frame, "Credenciales incorrectas");
        }
    }

    private String validarCredenciales(String u, String p) {
        for (Usuario user : USUARIOS) {
            if (user.validarCredenciales(u, p)) return user.getNombre();
        }
        return "";
    }
}
