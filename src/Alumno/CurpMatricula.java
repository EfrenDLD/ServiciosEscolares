package Alumno;

import Principal.MiImagen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class CurpMatricula extends JFrame {
    private static final String[] LETRAS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private MiImagen miImagen;
    private boolean visible;
    private JLabel contrasenaLabel;
    private JLabel matriculaLabel;

    public CurpMatricula() {
        miImagen = new MiImagen();
        configFrame();
        initControl();
        createComponents();
    }

    private void configFrame() {
        setTitle("Generador de CURP y Matricula");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(600, 600);
    }

    private void initControl() {
        contrasenaLabel = new JLabel();
        matriculaLabel = new JLabel();
    }

    private void createComponents() {
        // Botón "Regresar"
        JButton regresarButton = new JButton("Regresar");
        regresarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                // Aquí puedes implementar la lógica para regresar a la pantalla anterior
                // Por ejemplo, cerrar la ventana actual y abrir la anterior
                ContrasenaAlumno contrasenaAlumno = new ContrasenaAlumno();
                contrasenaAlumno.setVisible(true);
                CurpMatricula.this.dispose(); // Cerrar la ventana actual
            }
        });
        add(regresarButton);

        // Botón "Generar CURP y Matrícula"
        JButton generarButton = new JButton("Generar");
        generarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String contrasena = generarContrasenaAleatoria();
                String matricula = generarMatriculaAleatoria();
                contrasenaLabel.setText("Contraseña generada: " + contrasena);
                matriculaLabel.setText("Matrícula generada: " + matricula);
            }
        });
        add(generarButton);
        // Etiquetas para mostrar las contraseñas generadas
        add(contrasenaLabel);
        add(matriculaLabel);
    }
    public static String generarContrasenaAleatoria() {
        // Generar una contraseña aleatoria de 8 caracteres alfanuméricos
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(8);
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(caracteres.length());
            sb.append(caracteres.charAt(index));
        }
        return sb.toString();
    }
    public static String generarMatriculaAleatoria() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(LETRAS.length);
            sb.append(LETRAS[index]);
        }
        sb.append("-");
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
    public boolean isVisible() {
        return visible;
    }
}




