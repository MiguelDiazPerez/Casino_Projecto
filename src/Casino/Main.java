package Casino;

import Casino.Controlador.*;
import Casino.Modelo.*;
import Casino.vista.VentanaHistorial;
import Casino.vista.VentanaRuleta;

import javax.swing.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SesionControl session = new SesionControl();
            Usuario u = new Usuario("diego", "2025", "Diegol");
            session.login(u);

            Ruleta ruleta = new Ruleta();
            RuletaControl ruletaCtrl = new RuletaControl(session, ruleta);

            RepositorioResultadosArchivo repo = new RepositorioResultadosArchivo(new File("data"));
            ResultadoControl resCtrl = new ResultadoControl(session, repo);
            resCtrl.cargarDesdeArchivo();

            new VentanaRuleta(ruletaCtrl, resCtrl).setVisible(true);
            new VentanaHistorial(resCtrl).setVisible(true);
        });
    }
}