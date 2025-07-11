package ui;

import javax.swing.*;

public class MenuForm extends JFrame {

    private JPanel panel1;

    public MenuForm() {

        setTitle("Menú Principal - SGIS");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();

        // Menú Archivo
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(e -> System.exit(0));
        menuArchivo.add(itemSalir);

        // Menú Módulos
       /* JMenu menuModulos = new JMenu("Módulos");
        JMenuItem itemModulo1 = new JMenuItem("Módulo");
        JMenuItem itemModulo2 = new JMenuItem("Sistema");
        menuModulos.add(itemModulo1);
        menuModulos.add(itemModulo2);*/
        JMenu menuModulos = new JMenu("Módulos");
        JMenuItem itemModulo1 = new JMenuItem("Módulo");
        JMenuItem itemModulo2 = new JMenuItem("Sistema");

        // Acción para abrir el formulario Modulo
        itemModulo1.addActionListener(e -> new ModuloForm());

        menuModulos.add(itemModulo1);
        menuModulos.add(itemModulo2);

        // Agregar menús a la barra
        menuBar.add(menuArchivo);
        menuBar.add(menuModulos);

        // Agregar la barra de menú al JFrame
        setJMenuBar(menuBar);

        // Contenido principal opcional
        JLabel label = new JLabel("Welcome to your security system.", SwingConstants.CENTER);
        add(label);

        setVisible(true);

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MenuForm();
        });
    }

}
