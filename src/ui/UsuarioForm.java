package ui;

import javax.swing.*;
import java.awt.*;

public class UsuarioForm {
    private JPanel jpUsuario;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JTextField txtUserF;
    private JPasswordField pwUserF;
    private JButton btnRegistrarUF;
    private JButton btnModificarUF;
    private JButton btnEliminarUF;
    private JTable tbUserF;

    //Frame contenido del panel

    public UsuarioForm (){
        JFrame frame = new JFrame("Mantenimiento Usuario");
        frame .setContentPane(jpUsuario);  // Esta línea es la que usa tu diseño
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);


        // Configuración de los botones
        btnRegistrarUF.setIcon(new ImageIcon("src/ImgIcon/save.png"));
        Image saveIcon = ((ImageIcon) btnRegistrarUF.getIcon()).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        btnRegistrarUF.setIcon(new ImageIcon(saveIcon));

        btnModificarUF.setIcon(new ImageIcon("src/ImgIcon/editar.png"));
        Image editIcon = ((ImageIcon) btnModificarUF.getIcon()).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        btnModificarUF.setIcon(new ImageIcon(editIcon));

        btnEliminarUF.setIcon(new ImageIcon("src/ImgIcon/delete.png"));
        Image deleteIcon = ((ImageIcon) btnEliminarUF.getIcon()).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        btnEliminarUF.setIcon(new ImageIcon(deleteIcon));

    }
//    public static void main(String[] args) {
//
//        new UsuarioForm(); // Crear una instancia para mostrar el formulario
//    }
}
