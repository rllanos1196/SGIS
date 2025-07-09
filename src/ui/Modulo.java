package ui;

import javax.swing.*;

public class Modulo {

    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblDescripcion;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextArea textArea1;
    private JButton btnEliminar;
    private JButton btnRegistrar;
    private JTable tbModulo;
    private JButton btnEditar;
    private JPanel Modulo;


    public Modulo() {

        // Set up the JFrame
        JFrame frame = new JFrame("Módulo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 400);
        frame.setContentPane(Modulo);
        frame.pack();
        frame.setVisible(true);


        // Initialize components
        lblCodigo = new JLabel("Código:");
        txtCodigo = new JTextField();
        lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        lblDescripcion = new JLabel("Descripción:");
        textArea1 = new JTextArea();
        btnEliminar = new JButton("Eliminar");
        btnRegistrar = new JButton("Registrar");
        tbModulo = new JTable();
        btnEditar = new JButton("Editar");

        // Set up the layout and add components
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(lblCodigo);
        panel.add(lblNombre);
        panel.add(lblDescripcion);
        panel.add(txtCodigo);
        panel.add(txtNombre);
        panel.add(new JScrollPane(textArea1));
        panel.add(btnEliminar);
        panel.add(btnRegistrar);
        panel.add(new JScrollPane(tbModulo));
        panel.add(btnEditar);



    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Modulo modulo = new Modulo();
            modulo.Modulo.setVisible(true);
        });
    }


}
