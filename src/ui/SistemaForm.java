package ui;

import javax.swing.*;

public class SistemaForm {

    private JTextField txtCodigo;
    private JTextField textField2;
    private JTable tbSistema;
    private JButton btnRegistrar;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JPanel Sistema;

    private SistemaForm(){
        // Set up the JFrame
        JFrame frame = new JFrame("Sistema");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 400);
        frame.setContentPane(Sistema);
        frame.pack();
        frame.setVisible(true);


        // Initialize components
        txtCodigo = new JTextField();
        textField2 = new JTextField();
        tbSistema = new JTable();
        btnRegistrar = new JButton("Registrar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");

        // Set up the layout and add components
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Código:"));
        panel.add(txtCodigo);
        panel.add(new JLabel("Nombre:"));
        panel.add(textField2);
        panel.add(new JScrollPane(tbSistema));
        panel.add(btnRegistrar);
        panel.add(btnEditar);
        panel.add(btnEliminar);




    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SistemaForm::new);
    }


}
