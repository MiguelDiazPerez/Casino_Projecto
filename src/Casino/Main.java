package Casino;

import Casino.Controlador.SesionControl;
import Casino.vista.VentanaLogin;

public class Main {
    public static void main(String[] args) {
        SesionControl session = new SesionControl();
        new VentanaLogin(session).mostrar();
    }
}

