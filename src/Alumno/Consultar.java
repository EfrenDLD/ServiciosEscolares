package Alumno;
import Pojo.Calificacion;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class Consultar {
    public void mostrarCalificacionesPorCarrera(Map<String, ArrayList<Calificacion>> calificacionesPorCarrera) {
        JFrame frame = new JFrame("Calificaciones por Carrera");

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        for (String carrera : calificacionesPorCarrera.keySet()) {
            ArrayList<Calificacion> calificaciones = calificacionesPorCarrera.get(carrera);
            String[][] data = new String[calificaciones.size()][7];

            for (int i = 0; i < calificaciones.size(); i++) {
                Calificacion calificacion = calificaciones.get(i);
                data[i][0] = calificacion.getCarrera();
                data[i][1] = calificacion.getSemestre();
                data[i][2] = calificacion.getAlumno();
                data[i][3] = calificacion.getProfesor();
                data[i][4] = calificacion.getMateria();
                data[i][5] = calificacion.getParcial();
                data[i][6] = String.valueOf(calificacion.getCalificacion());
            }

            String[] columnNames = {"Carrera", "Semestre", "Alumno", "Profesor", "Materia", "Parcial", "Calificación"};
            JTable table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(scrollPane, BorderLayout.CENTER);

            tabbedPane.addTab(carrera, panel);
        }
        frame.add(tabbedPane);
        frame.setVisible(true);
    }
}





