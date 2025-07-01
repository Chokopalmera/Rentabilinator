import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TextPromptPlaceholder extends FocusAdapter {

    private final JTextField field;
    private final String placeholder;
    // Colores ajustados para el tema oscuro:
    private static final Color PLACEHOLDER_COLOR = new Color(150, 150, 150); // Gris claro para el placeholder
    private static final Color INPUT_COLOR = Color.WHITE; // Blanco para el texto que escribe el usuario


    public TextPromptPlaceholder(JTextField field, String placeholder) {
        this.field = field;
        this.placeholder = placeholder;

        // Establece el texto inicial y color
        field.setText(placeholder);
        field.setForeground(PLACEHOLDER_COLOR); // Usa el color del placeholder

        // Asocia el listener
        field.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        // Cuando el campo gana el foco Y su texto es el placeholder, se vacía y se pone el color de entrada
        if (field.getText().equals(placeholder)) {
            field.setText("");
            field.setForeground(INPUT_COLOR); // Color para el texto real del usuario
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        // Cuando el campo pierde el foco Y está vacío, vuelve a mostrar el placeholder
        if (field.getText().isEmpty()) {
            field.setForeground(PLACEHOLDER_COLOR); // Vuelve al color del placeholder
            field.setText(placeholder);
        }
    }

    // Método estático para obtener el color del placeholder, útil para el botón limpiar
    public static Color getPlaceholderColorStatic() {
        return PLACEHOLDER_COLOR;
    }
}
