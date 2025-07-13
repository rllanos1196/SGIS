package ui;

import javax.swing.*;
import java.awt.*;

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


        JMenu menuModulos = new JMenu("Módulos");
        JMenuItem itemModulo1 = new JMenuItem("Módulo");
        JMenuItem itemModulo2 = new JMenuItem("Sistema");
        JMenuItem itemModulo3 = new JMenuItem("Usuario");


        menuModulos.add(itemModulo1);
        menuModulos.add(itemModulo2);
        menuModulos.add(itemModulo3);

        // Acción para abrir el formulario Modulo
        itemModulo1.addActionListener(e -> new ModuloForm());
        // Acción para abrir el formulario Sistema
        itemModulo2.addActionListener(e -> new SistemaForm());
        itemModulo3.addActionListener(e -> new UsuarioForm());


        //FONDO PARA EL MENU PRINCIPAL
        JPanel fondoPanel = new JPanel() {
            private Image bg = new ImageIcon("src/ImgIcon/sistema.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                float alpha = 0.85f; // Opacidad (0.0f = transparente, 1.0f = opaco)
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                g2d.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
                g2d.dispose();
            }
        };
        fondoPanel.setLayout(new BorderLayout());

        JLabel label1 = new JLabel("Bienvenido al SGIS");
        label1.setOpaque(false); // Hacer que el JLabel sea transparente
        label1.setFont(new Font("Roboto", Font.BOLD, 30));
        label1.setForeground(new Color(5, 168, 239));
        label1.setHorizontalAlignment(SwingConstants.LEFT);
        fondoPanel.add(label1, BorderLayout.CENTER);
        setContentPane(fondoPanel);


        // Agregar menús a la barra
        menuBar.add(menuArchivo);
        menuBar.add(menuModulos);

        // Agregar la barra de menú al JFrame
        setJMenuBar(menuBar);


        label1.setIcon(new ImageIcon("src/ImgIcon/security.png"));
        Image passIcon = ((ImageIcon) label1.getIcon()).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        label1.setIcon(new ImageIcon(passIcon));

        setVisible(true);

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MenuForm();
        });
    }

}
