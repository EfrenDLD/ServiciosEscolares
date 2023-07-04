package ServicioEscolar;

import Principal.IniciarInterfaz;
import Principal.MiImagen;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContrasenaServicio extends JFrame {
    private JPanel panelContainer, panelDatos, panel1, panel2, panelBotones, imagen;
    private JButton ingresar, regresar;
    private JLabel usuario, contrasena,espacio,titulo;
    private JTextField usuarioEntrada;
    private JPasswordField contrasenaEntrada;
    private MiImagen miImagen;
    private final String contrasenaCorrecta = "admin";

    public ContrasenaServicio() {
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
        setSize(400, 500);
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

        titulo = new JLabel();
        espacio = new JLabel();
        ingresar = new JButton();
        regresar = new JButton();

        usuario = new JLabel();
        contrasena = new JLabel();

        usuarioEntrada = new JTextField();
        contrasenaEntrada = new JPasswordField();
    }

    private void setControl() {
        titulo.setText("Servicios Escolares");
        espacio.setText("");
        ingresar.setText("Ingresar");
        regresar.setText("Regresar");
        usuario.setText("Usuario");
        contrasena.setText("Contraseña");
    }
    private void configPanel() {
        panel1.setLayout(new GridLayout(4, 1));
        panel1.add(titulo);
        panel1.add(usuario);
        panel1.add(contrasena);

        panel2.setLayout(new GridLayout(4, 1));
        panel2.add(espacio);
        panel2.add(usuarioEntrada);
        panel2.add(contrasenaEntrada);

        panelDatos.setLayout(new GridLayout(1, 2));
        panelDatos.add(panel1);
        panelDatos.add(panel2);

        panelBotones.setLayout(new FlowLayout());
        panelBotones.add(ingresar);
        panelBotones.add(regresar);

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
                String contrasenaIngresada = new String(contrasenaEntrada.getPassword());
                if (contrasenaIngresada.equals(contrasenaCorrecta)) {
                    Secretaria secretaria = new Secretaria();
                    secretaria.setVisible(true);
                    ContrasenaServicio.this.dispose(); // Cerrar la ventana de ingreso
                } else {
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta. Inténtalo nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IniciarInterfaz iniciarInterfaz = new IniciarInterfaz();
                iniciarInterfaz.setVisible(true);
                ContrasenaServicio.this.dispose(); // Cerrar la ventana de ingreso
            }
        });
    }
}


