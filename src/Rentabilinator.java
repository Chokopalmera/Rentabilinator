import javax.swing.*; // Importa todas las clases de Swing (JFrame, JPanel, JButton, JTextField, JLabel, JOptionPane, BorderFactory, SwingConstants, SwingUtilities)
import java.awt.event.ActionEvent; // Para manejar eventos de acción (clics de botón)
import java.awt.event.ActionListener; // Interfaz para los oyentes de acción
import java.awt.Font; // Para controlar la fuente del texto
import java.awt.Color; // Para controlar los colores
import java.awt.Dimension; // Para especificar dimensiones (ancho, alto)
import java.awt.Cursor; // Para cambiar el cursor del ratón
import java.awt.Graphics; // Clase base para contextos gráficos
import java.awt.Graphics2D; // Clase para gráficos 2D avanzados
import java.awt.RenderingHints; // Para mejorar la calidad de renderizado (antialiasing)
import java.awt.Shape; // Para definir formas geométricas (útil para el redondeado personalizado)
import java.awt.geom.RoundRectangle2D; // Para dibujar rectángulos redondeados
/**
 * │ Proyecto: Rentabilinator
 * │ Autor: Chokopalmera
 * │ Descripción: Calculadora de rentabilidad anual inmobiliaria con interfaz Swing
 * │ Fecha de creación: 01/07/2025
 * │ Licencia: MIT
 */

/**
 * Clase principal de la aplicación de calculadora de rentabilidad inmobiliaria.
 * Extiende JFrame para crear la ventana principal de la interfaz de usuario.
 */
public class Rentabilinator extends JFrame {

    // --- Componentes de la Interfaz de Usuario (UI) ---
    /**
     * Panel principal donde se añadirán todos los demás componentes.
     * Utiliza un layout nulo (null) para posicionamiento absoluto.
     */
    private JPanel ventana = new JPanel();

    /**
     * Botón para iniciar el cálculo de la rentabilidad.
     */
    public JButton Rentabilidad = new JButton("Calcular Rentabilidad");
    /**
     * Botón para limpiar todos los campos de entrada y resultado.
     */
    public JButton Limpiar = new JButton("Limpiar");

    /**
     * Campos de texto para la entrada de datos por el usuario (Inversión, Gastos, Beneficio).
     * Campo4 se usará para mostrar el resultado.
     */
    public JTextField Campo1, Campo2, Campo3, Campo4;

    // --- Constantes de Estilo (Colores) ---
    /** Color de fondo para el panel principal (oscuro). */
    private final Color DARK_BACKGROUND_PANEL = new Color(40, 40, 40);
    /** Color de fondo para los campos de texto (gris un poco más claro que el fondo). */
    private final Color FIELD_BACKGROUND = new Color(60, 60, 60);
    /** Color del texto principal y de los valores de entrada (blanco/gris claro). */
    private final Color TEXT_COLOR_LIGHT = new Color(220, 220, 220);
    /** Color para los bordes de los campos de texto (gris oscuro). */
    private final Color BORDER_COLOR_DARK = new Color(80, 80, 80);

    /** Color de fondo para el botón de "Calcular" (azul vivo). */
    private final Color BUTTON_BLUE = new Color(59, 130, 246);
    /** Color de fondo para el botón de "Limpiar" (verde vivo). */
    private final Color BUTTON_GREEN = new Color(34, 197, 94);

    // --- Constantes de Estilo (Fuentes) ---
    /** Fuente general para los campos de texto y etiquetas. */
    private final Font GENERAL_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    /** Fuente para el texto de los botones (negrita y tamaño más grande). */
    private final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 16);

    // --- Constructor de la Clase Rentabilinator ---
    /**
     * Constructor de la ventana principal de la aplicación.
     * Configura las propiedades básicas de la ventana y sus componentes.
     */
    public Rentabilinator() {
        // Establece el tamaño de la ventana (similar a un smartphone vertical)
        this.setSize(360, 380);
        // Evita que el usuario pueda redimensionar la ventana, manteniendo el diseño fijo.
        this.setResizable(false);
        // Establece el título de la ventana.
        this.setTitle("Calculadora de rentabilidad anual inmobiliaria");
        // Establece el icono de la aplicación (asume que la clase Icono y su método setAppIcon existen).
        Icono.setAppIcon(this);
        // Centra la ventana en la pantalla al iniciar.
        this.setLocationRelativeTo(null);
        // Define la operación por defecto al cerrar la ventana (termina la aplicación).
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Añade el JPanel 'ventana' al contenido de la ventana principal.
        this.getContentPane().add(ventana);
        // Esta línea permite que Enter ejecute el botón Calcular
        this.getRootPane().setDefaultButton(Rentabilidad);

        // Llama al método para configurar todos los componentes dentro del panel 'ventana'.
        configurarventanaybotones();

        // Hace que la ventana sea visible una vez que todos los componentes están configurados.
        this.setVisible(true);
    }

    // --- Método de Configuración de la Interfaz y Botones ---
    /**
     * Configura el JPanel 'ventana', añade y posiciona todos los campos de texto, etiquetas
     * y botones, y asigna sus listeners.
     */
    public void configurarventanaybotones() {
        // Establece el layout del panel a nulo para un posicionamiento absoluto con setBounds().
        ventana.setLayout(null);
        // Establece el color de fondo del panel según la constante definida para el modo oscuro.
        ventana.setBackground(DARK_BACKGROUND_PANEL);

        // --- Títulos de la Aplicación ---
        // Primer título (línea superior)
        JLabel titulo1 = new JLabel("CALCULADORA DE RENTABILIDAD");
        titulo1.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Fuente negrita
        titulo1.setForeground(Color.WHITE); // Texto blanco
        titulo1.setHorizontalAlignment(SwingConstants.CENTER); // Centra el texto horizontalmente
        // Posiciona el título. Ajusta el ancho para incluir márgenes a cada lado.
        titulo1.setBounds(20, 5, this.getWidth() - 40, 30);
        ventana.add(titulo1); // Añade el título al panel

        // Segundo título (línea inferior, "INMOBILIARIA")
        JLabel titulo2 = new JLabel("INMOBILIARIA");
        titulo2.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Fuente negrita
        titulo2.setForeground(Color.WHITE); // Texto blanco
        titulo2.setHorizontalAlignment(SwingConstants.CENTER); // Centra el texto horizontalmente
        // Posiciona el título un poco más abajo que el primero.
        titulo2.setBounds(20, 20, this.getWidth() - 40, 30);
        ventana.add(titulo2); // Añade el título al panel

        // --- Configuración de Posicionamiento Común para Campos ---
        int margen = 20; // Margen lateral para los campos y botones
        int anchoCampo = this.getWidth() - (2 * margen); // Calcula el ancho de los campos basado en el ancho de la ventana y márgenes
        int alturaCampo = 40; // Altura fija para todos los campos de texto
        int espacioVertical = 50; // Espacio vertical entre el inicio de cada campo


        // --- Campo1: Inversión inicial ---
        Campo1 = createStyledInputField(); // Crea un JTextField con el estilo predefinido
        Campo1.setBounds(margen, 50, anchoCampo, alturaCampo); // Posiciona el campo
        ventana.add(Campo1); // Añade el campo al panel
        // Asigna un placeholder al campo utilizando la clase TextPromptPlaceholder.
        new TextPromptPlaceholder(Campo1, "Inversión inicial");


        // --- Campo2: Gastos Anuales ---
        Campo2 = createStyledInputField(); // Crea un JTextField con estilo
        // Posiciona el campo debajo de Campo1 con el espacio vertical definido.
        Campo2.setBounds(margen, 50 + espacioVertical, anchoCampo, alturaCampo);
        ventana.add(Campo2);
        new TextPromptPlaceholder(Campo2, "Gastos Anuales");


        // --- Campo3: Beneficio Mensual ---
        Campo3 = createStyledInputField(); // Crea un JTextField con estilo
        // Posiciona el campo debajo de Campo2.
        Campo3.setBounds(margen, 50 + 2 * espacioVertical, anchoCampo, alturaCampo);
        ventana.add(Campo3);
        new TextPromptPlaceholder(Campo3, "Beneficio Mensual");

        // Acción que valida y ejecuta el cálculo si todo está correcto
        AbstractAction validarYCalcular = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    float inversion = Float.parseFloat(Campo1.getText().replace(",", "."));
                    float gastos = Float.parseFloat(Campo2.getText().replace(",", "."));
                    float beneficio = Float.parseFloat(Campo3.getText().replace(",", "."));

                    if (inversion < 0 || gastos < 0 || beneficio < 0) {
                        throw new IllegalArgumentException("Valores negativos");
                    }

                    // Si todo es válido, simula clic en el botón
                    Rentabilidad.doClick();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "⚠️ Por favor, introduce solo números válidos.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "🚫 No se permiten valores negativos.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

// Asignamos la acción a los 3 campos de entrada
        Campo1.addActionListener(validarYCalcular);
        Campo2.addActionListener(validarYCalcular);
        Campo3.addActionListener(validarYCalcular);


        // --- Campo4: Rentabilidad anual (Resultado) ---
        Campo4 = createStyledInputField(); // Crea un JTextField con estilo
        // Posiciona el campo debajo de Campo3.
        Campo4.setBounds(margen, 50 + 3 * espacioVertical, anchoCampo, alturaCampo);
        Campo4.setEditable(false); // Hace que este campo no sea editable por el usuario
        ventana.add(Campo4);
        // Asigna un placeholder al campo de resultado (se mostrará cuando esté vacío).
        new TextPromptPlaceholder(Campo4, "Rentabilidad anual");

        // --- Configuración de Posicionamiento Común para Botones ---
        // Calcula el ancho de cada botón para que quepan dos en el ancho total con un pequeño espacio entre ellos.
        int anchoBoton = (anchoCampo - 10) / 2;

        // --- Botón 'Calcular Rentabilidad' ---
        // Crea el botón con texto HTML para permitir salto de línea y el color azul.
        Rentabilidad = createStyledButton("<html><center>Calcular<br>Rentabilidad</center></html>", BUTTON_BLUE);
        // Posiciona el botón. La fórmula ajusta su posición vertical y horizontal.
        Rentabilidad.setBounds(margen, 40 + 4 * espacioVertical + 20, anchoBoton, 60);
        ventana.add(Rentabilidad);
        // Añade un ActionListener para definir qué ocurre cuando se hace clic en el botón.
        Rentabilidad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Intenta convertir el texto de los campos a números.
                    // .replace(",", ".") permite usar comas como separadores decimales.
                    float inversion = Float.parseFloat(Campo1.getText().replace(",", "."));
                    float gastos = Float.parseFloat(Campo2.getText().replace(",", "."));
                    float beneficio = Float.parseFloat(Campo3.getText().replace(",", "."));

                    // Validación para asegurar que los valores no sean negativos.
                    if (inversion < 0 || gastos < 0 || beneficio < 0) {
                        // Lanza una excepción personalizada si se encuentran valores negativos.
                        throw new IllegalArgumentException("No se permiten valores negativos.");
                    }

                    // Calcula la rentabilidad: (beneficio mensual * 12) / (inversión inicial + gastos anuales) * 100
                    float rentabilidad = ((beneficio * 12f) / (inversion + gastos)) * 100;

                    // Muestra el resultado en Campo4, formateado a dos decimales con el símbolo %.
                    Campo4.setText(String.format("%.2f", rentabilidad) + "%");
                } catch (NumberFormatException ex) {
                    // Captura la excepción si el usuario introduce texto no numérico.
                    JOptionPane.showMessageDialog(null, "⚠️ Por favor, introduce solo números válidos.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    // Captura la excepción si se introducen valores negativos.
                    JOptionPane.showMessageDialog(null, "🚫 " + ex.getMessage(), "Error de entrada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // --- Botón 'Limpiar' ---
        Limpiar = createStyledButton("Limpiar", BUTTON_GREEN); // Crea el botón con el texto y color verde.
        // Posiciona el botón a la derecha del botón 'Calcular Rentabilidad'.
        Limpiar.setBounds(margen + anchoBoton + 10, 40 + 4 * espacioVertical + 20, anchoBoton, 60);
        ventana.add(Limpiar); // Añade el botón al panel
        // Añade un ActionListener para definir qué ocurre cuando se hace clic en el botón.
        Limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpia el texto de todos los campos.
                Campo1.setText("");
                Campo2.setText("");
                Campo3.setText("");
                Campo4.setText("");

                // Para asegurar que los placeholders vuelvan a aparecer correctamente
                // al limpiar, ya que la lógica de TextPromptPlaceholder solo actúa
                // en focusLost si el campo está vacío. Esto fuerza su reaparición.
                // Se podría necesitar un método público en TextPromptPlaceholder para esto.
                // Sin embargo, como el placeholder se asigna al objeto, se gestiona internamente
                // cuando el campo se vacía y pierde el foco.
            }
        });
    }

    // --- Métodos Auxiliares para Crear Componentes con Estilo ---
    /**
     * Crea y devuelve un JTextField con el estilo predefinido para campos de entrada
     * en el tema oscuro (fondo, texto, cursor, borde redondeado).
     *
     * @return Un JTextField estilizado.
     */
    private JTextField createStyledInputField() {
        // Utiliza la clase interna personalizada RoundedTextField para los bordes redondeados.
        RoundedTextField field = new RoundedTextField(20); // 20 es el tamaño de columna sugerido
        field.setFont(GENERAL_FONT); // Asigna la fuente general
        field.setBackground(FIELD_BACKGROUND); // Color de fondo del campo
        field.setForeground(TEXT_COLOR_LIGHT); // Color del texto de entrada
        field.setCaretColor(TEXT_COLOR_LIGHT); // Color del cursor de texto
        // Elimina el borde por defecto del JTextField ya que RoundedTextField dibuja el suyo.
        field.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Añade padding interno
        return field;
    }

    /**
     * Crea y devuelve un JButton con el estilo predefinido para botones
     * en el tema oscuro (fondo, texto, fuente, y efecto redondeado).
     *
     * @param text El texto a mostrar en el botón.
     * @param bgColor El color de fondo del botón.
     * @return Un JButton estilizado.
     */
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        // Deshabilita el área de contenido y el pintado del borde y el foco para control manual.
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        button.setFont(BUTTON_FONT); // Asigna la fuente de botón
        button.setBackground(bgColor); // Color de fondo del botón
        button.setForeground(Color.WHITE); // Color del texto del botón
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor a una mano al pasar por encima

        // Sobreescribe el UI del botón para pintar un fondo redondeado.
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create(); // Crea una copia del contexto gráfico
                // Habilita el antialiasing para que los bordes redondeados se vean suaves.
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                JButton b = (JButton) c;
                Dimension size = b.getSize();
                int arc = 20; // Radio de redondeo para las esquinas

                // Pinta un rectángulo redondeado con el color de fondo del botón.
                g2.setColor(b.getBackground());
                g2.fillRoundRect(0, 0, size.width, size.height, arc, arc);

                // Llama al método original para pintar el texto y otros elementos del botón.
                super.paint(g2, c);
                g2.dispose(); // Libera los recursos gráficos
            }
        });
        // Asegura que el texto del botón esté centrado.
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);

        return button;
    }

    // --- Clase Interna: RoundedTextField ---
    /**
     * Clase interna que extiende JTextField para proporcionar un campo de texto
     * con esquinas redondeadas personalizadas.
     */
    class RoundedTextField extends JTextField {
        private Shape shape; // Almacena la forma del campo (rectángulo redondeado)
        private int arc = 15; // Radio de redondeo de las esquinas del campo

        /**
         * Constructor de RoundedTextField.
         * @param size Tamaño preferido del campo de texto.
         */
        public RoundedTextField(int size) {
            super(size);
            setOpaque(false); // Hace que el JTextField no pinte su propio fondo, permitiendo el pintado personalizado.
        }

        /**
         * Sobreescribe el método paintComponent para dibujar el fondo redondeado del campo.
         * @param g El contexto gráfico.
         */
        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(getBackground()); // Establece el color de fondo personalizado
            // Dibuja un rectángulo redondeado relleno con el color de fondo.
            g.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
            super.paintComponent(g); // Llama al método original para pintar el texto y el caret.
        }

        /**
         * Sobreescribe el método paintBorder para dibujar el borde redondeado del campo.
         * @param g El contexto gráfico.
         */
        @Override
        protected void paintBorder(Graphics g) {
            // Usa el color del foreground del campo para el borde (puede ser ajustado si se desea otro color de borde).
            g.setColor(getForeground());
            // Dibuja solo el contorno redondeado.
            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
        }

        /**
         * Sobreescribe el método contains para que los clics del ratón
         * sean detectados dentro de la forma redondeada del campo.
         * @param x Coordenada X del clic.
         * @param y Coordenada Y del clic.
         * @return true si el clic está dentro de la forma redondeada, false en caso contrario.
         */
        @Override
        public boolean contains(int x, int y) {
            // Si la forma no está creada o ha cambiado de tamaño, la recrea.
            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arc, arc);
            }
            return shape.contains(x, y); // Devuelve si la coordenada está dentro de la forma.
        }
    }

    // --- Método Principal (main) ---
    /**
     * Punto de entrada principal de la aplicación.
     * Crea una instancia de Rentabilinator en el Event Dispatch Thread (EDT) de Swing.
     * Esto asegura que las operaciones de la UI sean seguras y se ejecuten correctamente.
     *
     * @param args Argumentos de la línea de comandos (no usados en esta aplicación).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Rentabilinator());
    }
}