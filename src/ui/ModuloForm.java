package ui;

import modelos.Modulo;
import procesos.ModuloService;
import procesos.ModuloServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModuloForm {

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

    private ModuloService modService;

    public ModuloForm() {

        modService = new ModuloServiceImpl();
        // Set up the JFrame
        JFrame frame = new JFrame("Módulo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 400);
        frame.setContentPane(Modulo);
        frame.pack();
        frame.setVisible(true);


        //YA NO SE INICIALIZA LOS COMPONENTES AQUÍ, SE HACEN EN EL DISEÑADOR DE INTELLIJ
//        // Initialize components
//        lblCodigo = new JLabel("Código:");
//        txtCodigo = new JTextField();
//        lblNombre = new JLabel("Nombre:");
//        txtNombre = new JTextField();
//        lblDescripcion = new JLabel("Descripción:");
//        textArea1 = new JTextArea();
//        btnEliminar = new JButton("Eliminar");
//        btnRegistrar = new JButton("Registrar");
//        tbModulo = new JTable();
//        btnEditar = new JButton("Editar");
//
//        // Set up the layout and add components
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//
//        panel.add(lblCodigo);
//        panel.add(lblNombre);
//        panel.add(lblDescripcion);
//        panel.add(txtCodigo);
//        panel.add(txtNombre);
//        panel.add(new JScrollPane(textArea1));
//        panel.add(btnEliminar);
//        panel.add(btnRegistrar);
//        panel.add(new JScrollPane(tbModulo));
//        panel.add(btnEditar);

       // setVisible(true);
        btnRegistrar.setIcon(new ImageIcon("src/ImgIcon/save.png"));
        Image saveIcon = ((ImageIcon) btnRegistrar.getIcon()).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        btnRegistrar.setIcon(new ImageIcon(saveIcon));

        btnEditar.setIcon(new ImageIcon("src/ImgIcon/editar.png"));
        Image editIcon = ((ImageIcon) btnEditar.getIcon()).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        btnEditar.setIcon(new ImageIcon(editIcon));

        btnEliminar.setIcon(new ImageIcon("src/ImgIcon/delete.png"));
        Image deleteIcon = ((ImageIcon) btnEliminar.getIcon()).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        btnEliminar.setIcon(new ImageIcon(deleteIcon));

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = txtCodigo.getText();
                String nombre = txtNombre.getText();
                String descripcion = textArea1.getText();

                if (codigo.isEmpty() || nombre.isEmpty() || descripcion.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos.");
                    return;
                }
                 Modulo modulo = new Modulo(codigo, nombre, descripcion);
                 modService.insert(modulo);
                JOptionPane.showMessageDialog(frame, "Módulo registrado correctamente.");

            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ModuloForm());
    }



}
