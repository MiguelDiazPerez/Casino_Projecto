package Casino.vista;

import Casino.Controlador.SesionControl;
import Casino.Controlador.RuletaControl;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class VentanaPerfil {
    private final JDialog dialog;
    private final SesionControl session;
    private final RuletaControl ruletaCtrl;
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtUsuario = new JTextField();
    private final JTextField txtSaldo = new JTextField();
    private final JTextField txtRecarga = new JTextField("500");

    private final Runnable onCloseRefresh;

    public VentanaPerfil(SesionControl session, Runnable onCloseRefresh) {
        this.session = session;
        this.ruletaCtrl = new RuletaControl(session);
        this.onCloseRefresh = onCloseRefresh;

        JFrame owner = new JFrame(); // solo para centrar
        dialog = new JDialog(owner, "Perfil", true);
        dialog.setSize(360, 280);
        dialog.setLayout(new GridLayout(6, 2, 8, 8));
        dialog.setLocationRelativeTo(null);

        txtUsuario.setEditable(false);
        txtSaldo.setEditable(false);

        dialog.add(new JLabel("Usuario:"));
        dialog.add(txtUsuario);
        dialog.add(new JLabel("Nombre:"));
        dialog.add(txtNombre);
        dialog.add(new JLabel("Saldo actual:"));
        dialog.add(txtSaldo);
        dialog.add(new JLabel("Recargar $:"));
        dialog.add(txtRecarga);

        JButton btnGuardar = new JButton("Guardar nombre");
        JButton btnRecargar = new JButton("Recargar");
        JButton btnCerrar = new JButton("Cerrar");

        dialog.add(btnGuardar);
        dialog.add(btnRecargar);
        dialog.add(new JLabel());
        dialog.add(btnCerrar);

        cargarDatos();

        btnGuardar.addActionListener(e -> {
            try {
                session.getUsuarioActual().setNombre(txtNombre.getText());
                JOptionPane.showMessageDialog(dialog, "Nombre actualizado.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage());
            }
        });

        btnRecargar.addActionListener(e -> {
            try {
                int monto = Integer.parseInt(txtRecarga.getText());
                ruletaCtrl.depositar(monto);
                txtSaldo.setText("$" + ruletaCtrl.getSaldo());
                JOptionPane.showMessageDialog(dialog, "Saldo recargado.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage());
            }
        });

        btnCerrar.addActionListener(e -> {
            dialog.dispose();
            if (onCloseRefresh != null) onCloseRefresh.run();
        });
    }

    private void cargarDatos() {
        txtUsuario.setText(session.getUsuarioActual().getUsername());
        txtNombre.setText(session.getUsuarioActual().getNombre());
        txtSaldo.setText("$" + ruletaCtrl.getSaldo());
    }

    public void mostrar() { dialog.setVisible(true); }
}