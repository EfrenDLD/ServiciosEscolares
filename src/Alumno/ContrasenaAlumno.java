package Alumno;

import Principal.IniciarInterfaz;
import Principal.MiImagen;
import ServicioEscolar.ContrasenaServicio;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ContrasenaAlumno extends JFrame {
    private JPanel panelContainer, panelDatos, panel1, panel2, panelBotones, imagen;
    private JButton ingresar, regresar,generador;
    private JLabel usuario, contrasena;
    private JTextField usuarioEntrada;
    private JPasswordField contrasenaEntrada;
    private MiImagen miImagen;

    public ContrasenaAlumno() {
        MiImagen miImagen = new MiImagen();
        this.miImagen = miImagen;
        initFrame();
        configFrame();
        initControl();
        setControl();
        configPanel();
        acciones();
    }

    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setVisible(true);
    }

    private void configFrame() {
        setTitle("Inicio");
        setLayout(new FlowLayout());
        setResizable(false);
    }

    private void initControl() {
        panelContainer = new JPanel();
        panelBotones = new JPanel();
        imagen = new JPanel();
        panelDatos = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();

        ingresar = new JButton();
        regresar = new JButton();
        generador = new JButton();

        usuario = new JLabel();
        contrasena = new JLabel();

        usuarioEntrada = new JTextField();
        contrasenaEntrada = new JPasswordField();
    }

    private void setControl() {
        generador.setText("Generar");
        ingresar.setText("Ingresar");
        regresar.setText("Regresar");
        usuario.setText("Usuario");
        contrasena.setText("Contraseña");
    }

    private void configPanel() {
        panel1.setLayout(new GridLayout(2, 1));
        panel1.add(usuario);
        panel1.add(contrasena);

        panel2.setLayout(new GridLayout(2, 1));
        panel2.add(usuarioEntrada);
        panel2.add(contrasenaEntrada);

        panelDatos.setLayout(new GridLayout(1, 2));
        panelDatos.add(panel1);
        panelDatos.add(panel2);

        panelBotones.setLayout(new FlowLayout());
        panelBotones.add(ingresar);
        panelBotones.add(regresar);
        panelBotones.add(generador);

        imagen.setLayout(new GridLayout(1, 1));
        imagen.setPreferredSize(new Dimension(250, 250));
        imagen.add(miImagen);

        panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.Y_AXIS));
        panelContainer.add(imagen);
        panelContainer.add(panelDatos);
        panelContainer.add(panelBotones);

        add(panelContainer);
    }

    private void acciones() {
        ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuarioIngresado = usuarioEntrada.getText();
                // Generar contraseña y matrícula aleatorias
                String contrasenaGenerada = CurpMatricula.generarContrasenaAleatoria();
                String matriculaGenerada = CurpMatricula.generarMatriculaAleatoria();

                // Validar las credenciales ingresadas
                if (validarCredenciales(usuarioIngresado, contrasenaGenerada)) {
                    ContrasenaServicio contrasenaServicio = new ContrasenaServicio();
                    contrasenaServicio.setVisible(true);
                    ContrasenaAlumno.this.dispose(); // Cerrar la ventana de ingreso
                } else {
                    JOptionPane.showMessageDialog(ContrasenaAlumno.this,
                            "Usuario o contraseña incorrectos.",
                            "Error de autenticación",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IniciarInterfaz iniciarInterfaz = new IniciarInterfaz();
                iniciarInterfaz.setVisible(true);
                ContrasenaAlumno.this.dispose(); // Cerrar la ventana de ingreso
            }
        });
       generador.addActionListener(e -> {
            CurpMatricula curpMatricula = new CurpMatricula();
            curpMatricula.setVisible(true);
            this.setVisible(false);
        });
    }
    private boolean validarCredenciales(String usuario, String contrasena) {
        return usuario.equals(contrasena);
    }
}

