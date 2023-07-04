package Principal;

import java.awt.*;
public class MiImagen extends Canvas {
    @Override
    public void paint(Graphics g) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("C:\\InterfazGrafica\\perfil.png");
        g.drawImage(image,20,20,this);
    }
}
