import javax.swing.*;
import java.awt.*;

public class Icono {
    //Este es el metodo que utilizaremos para buscar la imagen
    public static void setAppIcon(JFrame frame) {
        //toolkit es una clase abstracta del paquete awt que nos permite integrar la API de AWTcon nuestro sistema
        //getDefaultToolkit() es el metodo que actua como puerta de enlace (busca en el sistema la ruta que le demos)
        //getImage() es el metodo que nos permite acceder especificamente a la imagen
        Image icon = Toolkit.getDefaultToolkit().getImage("src/imagenes/rentabicon.jpg");
        //frame.setIconImage(icon) es el metodo que permite cambiar el icono por defecto de java al que le hemos dicho
        //es el "resultado" de todo.
        frame.setIconImage(icon);
    }
}
