package Casino;

import Casino.Controlador.*;
import Casino.Modelo.*;
import Casino.vista.VentanaHistorial;
import Casino.vista.VentanaRuleta;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SesionControl session = new SesionControl();
            Usuario u = new Usuario("diego", "2025", "Diegol");
            session.login(u);
            Ruleta ruleta = new Ruleta();
            RuletaControl rc = new RuletaControl(session, ruleta);
            HistorialController hc = new HistorialController(session);
            new VentanaRuleta(rc).setVisible(true);
            new VentanaHistorial(hc).setVisible(true);
        });
    }
}
