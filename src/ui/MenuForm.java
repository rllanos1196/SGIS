package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuForm extends JFrame {
    public JPanel panel1;
    private JDesktopPane desktopPane; // Este será el contenedor de las ventanas internas

    public MenuForm() {
        setTitle("Menú Principal - SGIS");
        setSize(900, 700); // Aumenta el tamaño del menú principal para las internas
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializar el JDesktopPane
        desktopPane = new JDesktopPane();
        // Establecer el JDesktopPane como el contenido principal del JFrame
        setContentPane(desktopPane);

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();

        // Menú Archivo
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(e -> System.exit(0));
        menuArchivo.add(itemSalir);

        // Menú Módulos
        JMenu menuModulos = new JMenu("Módulos");
        JMenuItem itemModulo1 = new JMenuItem("Módulo");
        JMenuItem itemModulo2 = new JMenuItem("Sistema");
        JMenuItem itemModulo3 = new JMenuItem("Usuario");

        menuModulos.add(itemModulo1);
        menuModulos.add(itemModulo2);
        menuModulos.add(itemModulo3);


        // Acción para abrir el formulario Modulo (ahora un JInternalFrame)
        itemModulo1.addActionListener(e -> abrirVentanaInterna(new ModuloInternalFrame())); // Usamos una nueva clase ModuloInternalFrame
        // Acción para abrir el formulario Sistema (ahora un JInternalFrame)
        itemModulo2.addActionListener(e -> abrirVentanaInterna(new SistemaInternalFrame())); // Usamos una nueva clase SistemaInternalFrame
        // Acción para abrir el formulario Usuario (ahora un JInternalFrame)
        itemModulo3.addActionListener(e -> abrirVentanaInterna(new UsuarioInternalFrame())); // Usamos una nueva clase UsuarioInternalFrame

        // Agregar menús a la barra
        menuBar.add(menuArchivo);
        menuBar.add(menuModulos);

        // Agregar la barra de menú al JFrame
        setJMenuBar(menuBar);


        JDesktopPane fondoPanel = new JDesktopPane() {
            private Image bg = new ImageIcon("src/ImgIcon/sistema.jpg").getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        fondoPanel.setLayout(new BorderLayout());

        JLabel label1 = new JLabel("Bienvenido al SGIS");
        label1.setOpaque(false); // Hacer que el JLabel sea transparente
        label1.setFont(new Font("Roboto", Font.BOLD, 55));
        label1.setForeground(new Color(255, 255, 255));
        fondoPanel.add(label1, BorderLayout.CENTER);

        label1.setIcon(new ImageIcon("src/ImgIcon/security.png"));
        Image segIcon = ((ImageIcon) label1.getIcon()).getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
        label1.setIcon(new ImageIcon(segIcon));
        label1.setHorizontalAlignment(SwingConstants.LEFT);


        // Añadir el fondo al JDesktopPane
        desktopPane.add(fondoPanel, JLayeredPane.DEFAULT_LAYER);
        fondoPanel.setBounds(0, 0, getWidth(), getHeight()); // Ajustar el tamaño del fondo al JDesktopPane


        setVisible(true);
    }

    /**
     * Método para añadir y mostrar un JInternalFrame en el desktopPane.
     */
    private void abrirVentanaInterna(JInternalFrame internalFrame) {
        desktopPane.add(internalFrame); // Añade la ventana interna al desktop
        internalFrame.setVisible(true); // Hace visible la ventana interna
        try {
            internalFrame.setSelected(true); // Opcional: selecciona la ventana para que tenga el foco
            // Opcional: Centrar la ventana interna en el desktop
            internalFrame.setLocation(
                    (desktopPane.getWidth() - internalFrame.getWidth()) / 2,
                    (desktopPane.getHeight() - internalFrame.getHeight()) / 2
            );
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MenuForm();
        });
    }
}

// --- CLASES DE VENTANAS INTERNAS DE EJEMPLO ---
// Tendrás que adaptar tus ModuloForm, SistemaForm y UsuarioForm
// para que extiendan JInternalFrame en lugar de JFrame.

class ModuloInternalFrame extends JInternalFrame {
    public ModuloInternalFrame() {
        super("Módulo", true, true, true, true); // Título, resizable, closable, maximizable, iconifiable
        setSize(650, 450);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Importante: solo cierra esta ventana interna

        // Contenido de tu formulario Modulo
        ModuloForm moduloForm = new ModuloForm();
        setContentPane(moduloForm);
    }
}

class SistemaInternalFrame extends JInternalFrame {
    public SistemaInternalFrame() {
        super("Sistema", true, true, true, true);
        setSize(500, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Contenido de tu formulario Sistema
        SistemaForm moduloForm = new SistemaForm();
        setContentPane(moduloForm);
    }
}

class UsuarioInternalFrame extends JInternalFrame {
    public UsuarioInternalFrame() {
        super("Usuario", true, true, true, true);
        setSize(800, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Contenido de tu formulario Usuario
        UsuarioForm usurioForm = new UsuarioForm();
        setContentPane(usurioForm);
    }
}