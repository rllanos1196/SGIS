package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaForm {

    private JTextField txtCodigoS;
    private JTextField txtNombreS;
    private JTable tbSistema;
    private JButton btnRegistrarS;
    private JButton btnEditarS;
    private JButton btnEliminarS;
    private JPanel Sistema;
    private DefaultTableModel tableModel;

    private SistemaForm(){
        // Set up the JFrame
        JFrame frame = new JFrame("Sistema");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 400);
        frame.setContentPane(Sistema);
        frame.pack();
        frame.setVisible(true);


        btnRegistrarS.setIcon(new ImageIcon("src/ImgIcon/save.png"));
        Image saveIcon = ((ImageIcon) btnRegistrarS.getIcon()).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        btnRegistrarS.setIcon(new ImageIcon(saveIcon));

        btnEditarS.setIcon(new ImageIcon("src/ImgIcon/editar.png"));
        Image userIcon = ((ImageIcon) btnEditarS.getIcon()).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        btnEditarS.setIcon(new ImageIcon(userIcon));

        btnEliminarS.setIcon(new ImageIcon("src/ImgIcon/delete.png"));
        Image passIcon = ((ImageIcon) btnEliminarS.getIcon()).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        btnEliminarS.setIcon(new ImageIcon(passIcon));


        btnRegistrarS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //validar los campos
                long id = System.currentTimeMillis(); // Genera un ID único basado en el tiempo actual
                String codigo = txtCodigoS.getText();
                String nombre = txtNombreS.getText();
                if (codigo.isEmpty() || nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos.");
                    return;
                }
                JOptionPane.showMessageDialog(frame, "Registro exitoso","Información", JOptionPane.INFORMATION_MESSAGE);



            }
        });

        btnEditarS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar los campos
                String codigo = txtCodigoS.getText();
                String nombre = txtNombreS.getText();
                if (codigo.isEmpty() || nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos.");
                    return;
                }
                // Muestra de una alerta
                JOptionPane.showMessageDialog(frame, "Edición exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SistemaForm::new);
    }

}
