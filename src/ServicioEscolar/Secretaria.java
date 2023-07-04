package ServicioEscolar;

import Alumno.Consultar;
import Pojo.Calificacion;
import Principal.IniciarInterfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Secretaria extends JFrame {
    private JPanel panelContainer, panelFormulario, panelBotones;
    private JLabel labelCarrera, labelSemestre, labelAlumno, labelProfesor, labelMateria, labelParcial, labelCalificacion;
    private JComboBox<String> comboCarrera, comboSemestre, comboParcial;
    private JTextField campoAlumno, campoProfesor, campoMateria, campoCalificacion;
    private JButton botonGuardar, botonModificar, botonMostrar, regresar;
    private ArrayList<Calificacion> calificaciones = new ArrayList<>();
    private Map<String, ArrayList<Calificacion>> calificacionesPorCarrera = new HashMap<>();

    private static final String[] listaCarreras = {"Biologia", "Forestal", "Informatica", "Turismo", "Ing. Madera", "Ambiental"};
    private static final String[] listaSemestres = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private static final String[] listaParciales = {"1", "2", "3", "Ordinario", "Extra-1", "Extra-2"};

    private Consultar consultar;

    public Secretaria() {
        initFrame();
        configFrame();
        initControl();
        setControl();
        acciones();
    }
    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setVisible(true);
    }
    private void configFrame() {
        setTitle("ServicioEscolar.Secretaria");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(500, 300);
    }

    private void initControl() {
        consultar = new Consultar();
        panelContainer = new JPanel();
        panelFormulario = new JPanel();
        panelBotones = new JPanel();

        labelCarrera = new JLabel("Carrera:");
        labelSemestre = new JLabel("Semestre:");
        labelAlumno = new JLabel("Alumno:");
        labelProfesor = new JLabel("Profesor:");
        labelMateria = new JLabel("Materia:");
        labelParcial = new JLabel("Parcial:");
        labelCalificacion = new JLabel("Calificación:");

        comboCarrera = new JComboBox<>(listaCarreras);
        comboSemestre = new JComboBox<>(listaSemestres);
        comboParcial = new JComboBox<>(listaParciales);

        campoAlumno = new JTextField(15);
        campoProfesor = new JTextField(15);
        campoMateria = new JTextField(15);
        campoCalificacion = new JTextField(15);

        botonGuardar = new JButton("Guardar");
        botonModificar = new JButton("Modificar");
        botonMostrar = new JButton("Mostrar");
        regresar = new JButton("Regresar");

        consultar = new Consultar();
    }

    private void setControl() {
        panelFormulario.setLayout(new GridLayout(7, 2));
        panelFormulario.add(labelCarrera);
        panelFormulario.add(comboCarrera);

        panelFormulario.add(labelSemestre);
        panelFormulario.add(comboSemestre);

        panelFormulario.add(labelParcial);
        panelFormulario.add(comboParcial);

        panelFormulario.add(labelAlumno);
        panelFormulario.add(campoAlumno);

        panelFormulario.add(labelProfesor);
        panelFormulario.add(campoProfesor);

        panelFormulario.add(labelMateria);
        panelFormulario.add(campoMateria);

        panelFormulario.add(labelCalificacion);
        panelFormulario.add(campoCalificacion);

        panelBotones.add(botonGuardar);
        panelBotones.add(botonModificar);
        panelBotones.add(botonMostrar);
        panelBotones.add(regresar);

        panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.Y_AXIS));
        panelContainer.add(panelFormulario);
        panelContainer.add(panelBotones);

        add(panelContainer);
    }

    private void acciones() {
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String carrera = comboCarrera.getSelectedItem().toString();
                String semestre = comboSemestre.getSelectedItem().toString();
                String alumno = campoAlumno.getText();
                String profesor = campoProfesor.getText();
                String materia = campoMateria.getText();
                String parcial = comboParcial.getSelectedItem().toString();
                String calificacionTexto = campoCalificacion.getText();

                // Validar que los datos sean de tipo caracter
                if (!isString(alumno)) {
                    JOptionPane.showMessageDialog(null, "Ingrese datos válidos en alumno", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!isString(profesor)) {
                    JOptionPane.showMessageDialog(null, "Ingrese datos válidos en profesor", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!isString(materia)) {
                    JOptionPane.showMessageDialog(null, "Ingrese datos válidos en materia", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double calificacionValor;
                try {
                    calificacionValor = Double.parseDouble(calificacionTexto);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese una calificación válida", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Validar el porcentaje de asistencias
                int asistencias = obtenerAsistencias();
                if (asistencias > 100) {
                    JOptionPane.showMessageDialog(null, "Asistencia inválida. Ingrese un valor menor o igual a 10", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                boolean asistenciasSuficientes = verificarAsistencias(asistencias);
                if (!asistenciasSuficientes) {
                    JOptionPane.showMessageDialog(null, "El estudiante no tiene asistencias suficientes. Se asigna 0 en el parcial.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    campoCalificacion.setText("0");
                    parcial = "1";
                }

                // Validar que la calificación esté entre 0 y 10
                if (calificacionValor < 0 || calificacionValor > 10) {
                    JOptionPane.showMessageDialog(null, "Ingrese una calificación válida (entre 0 y 10)", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Calificacion calificacion = new Calificacion(carrera, semestre, alumno, profesor, materia, parcial, calificacionValor);
                calificaciones.add(calificacion);

                if (calificacionValor >= 6) {
                    // Estudiante aprobado, deshabilitar los campos de los textos
                   /*comboCarrera.setEnabled(false);
                    comboSemestre.setEnabled(false);
                    campoAlumno.setEnabled(false);
                    campoProfesor.setEnabled(false);
                    campoMateria.setEnabled(false);
                    comboParcial.setEnabled(false);
                    campoCalificacion.setEnabled(false);*/
                }

                if (calificacionesPorCarrera.containsKey(carrera)) {
                    calificacionesPorCarrera.get(carrera).add(calificacion);
                } else {
                    ArrayList<Calificacion> calificacionesCarrera = new ArrayList<>();
                    calificacionesCarrera.add(calificacion);
                    calificacionesPorCarrera.put(carrera, calificacionesCarrera);
                }
                JOptionPane.showMessageDialog(null, "Calificación guardada correctamente", "Registrado", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        botonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String alumno = campoAlumno.getText();
                String profesor = campoProfesor.getText();
                String materia = campoMateria.getText();
                // Verificar que se haya ingresado un alumno, profesor y materia
                if (alumno.isEmpty() || profesor.isEmpty() || materia.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese el alumno, profesor y materia a modificar", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });
        botonMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultar.mostrarCalificacionesPorCarrera(calificacionesPorCarrera);
            }
        });
        regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new IniciarInterfaz();
                dispose();
            }
        });
    }

    private int obtenerAsistencias() {
        String input = JOptionPane.showInputDialog(null, "Ingrese la cantidad de asistencias del estudiante:", "Asistencias", JOptionPane.QUESTION_MESSAGE);
        int asistencias;
        try {
            asistencias = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            asistencias = -1;
        }
        return asistencias;
    }
    private boolean verificarAsistencias(int asistencias) {
        return asistencias >= 85;
    }

    private boolean isString(String text) {
        return text != null && !text.trim().isEmpty() && text.trim().matches("[a-zA-Z ]+");
    }
    public static void main(String[] args) {
        new Secretaria();
    }
}





