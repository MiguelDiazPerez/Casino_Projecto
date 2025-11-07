package Casino.Controlador;

import Casino.Modelo.Resultado;
import Casino.Modelo.Usuario;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class RepositorioResultadosArchivo {
    private final File baseDir;

    public RepositorioResultadosArchivo(File baseDir) {
        this.baseDir = baseDir;
        if (!baseDir.exists()) baseDir.mkdirs();
    }

    private File archivoDe(Usuario u) {
        return new File(baseDir, "resultados_" + u.getUsername() + ".csv");
    }

    public void guardarResultado(Usuario u, Resultado r) {
        File f = archivoDe(u);
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(f, true), StandardCharsets.UTF_8))) {
            pw.printf("%d;%d;%s;%b;%s;%d;%d%n",
                    r.getTimestamp(), r.getNumero(), r.getColor(), r.isEsPar(),
                    r.getEtiquetaApuesta(), r.getMonto(), r.getGanancia());
        } catch (IOException e) {
            throw new RuntimeException("Error guardando historial: " + e.getMessage(), e);
        }
    }

    public List<Resultado> cargarHistorial(Usuario u) {
        List<Resultado> lista = new ArrayList<>();
        File f = archivoDe(u);
        if (!f.exists()) return lista;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(f), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(";");
                if (p.length < 7) continue;
                long ts = Long.parseLong(p[0]);
                int numero = Integer.parseInt(p[1]);
                String color = p[2];
                boolean esPar = Boolean.parseBoolean(p[3]);
                String etiqueta = p[4];
                int monto = Integer.parseInt(p[5]);
                int ganancia = Integer.parseInt(p[6]);
                Resultado r = new Resultado(numero, color, esPar, etiqueta, monto, ganancia);
                lista.add(r);
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error leyendo historial: " + ex.getMessage(), ex);
        }
        return lista;
    }
}