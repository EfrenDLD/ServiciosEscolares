package Principal;
import Alumno.ContrasenaAlumno;
import ServicioEscolar.ContrasenaServicio;

import javax.swing.*;
import java.awt.*;

public class IniciarInterfaz extends JFrame {
    private JPanel panelContainer, panelBotones, imagen;
    private JButton botonAlumno, botonServicio;
    private MiImagen miImagen;
    private JLabel espacio, titulo, espacio2;

    public IniciarInterfaz() {
        MiImagen miImagen = new MiImagen();
        this.miImagen = miImagen;
        initFrame();
        configFrame();
        initControl();
        setControl();
        configPanel();
        movimientos();
    }

    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(miImagen, BorderLayout.CENTER);
        setSize(300, 800);
        setVisible(true);
    }

    private void configFrame() {
        setTitle("Iniciar sesion");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(600, 600);
    }

    private void initControl() {
        panelContainer = new JPanel();
        panelBotones = new JPanel();
        imagen = new JPanel();

        titulo = new JLabel();
        espacio2 = new JLabel();
        botonAlumno = new JButton();
        espacio = new JLabel();
        botonServicio = new JButton();
    }
    private void setControl() {
        titulo.setText("Iniciar sesión");
        espacio2.setText("");
        botonAlumno.setText("Alumno");
        espacio.setText("");
        botonServicio.setText("Servicios escolares");
    }
    private void configPanel() {
        panelBotones.setLayout(new GridLayout(5, 1));
        panelBotones.setPreferredSize(new Dimension(100, 100));
        panelBotones.add(titulo);
        panelBotones.add(espacio2);
        panelBotones.add(botonAlumno);
        panelBotones.add(espacio);
        panelBotones.add(botonServicio);

        imagen.setLayout(new GridLayout(1, 0));
        imagen.setPreferredSize(new Dimension(250, 250));
        imagen.add(miImagen);

        panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.Y_AXIS));
        panelContainer.add(imagen);
        panelContainer.add(panelBotones);
        add(panelContainer);
    }
    private void movimientos() {
        botonServicio.addActionListener(e -> {
            ContrasenaServicio contrasenaServicio = new ContrasenaServicio();
            contrasenaServicio.setVisible(true);
            this.setVisible(false);
        });

        botonAlumno.addActionListener(e -> {
            ContrasenaAlumno contrasenaAlumno = new ContrasenaAlumno();
            contrasenaAlumno.setVisible(true);
            this.setVisible(false);
        });
    }
}
