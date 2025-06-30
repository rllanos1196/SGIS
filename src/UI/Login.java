package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JTextField txtUser;
    private JLabel lblUser;
    private JLabel lblPasw;
    private JButton btnCancel;
    private JButton btnIngresar;
    private JPasswordField pwPassword;

    public Login() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 160);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);


        frame.setVisible(true);
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String user = txtUser.getText();
                String password = new String(pwPassword.getPassword());

                // Aquí puedes agregar la lógica de autenticación
                if (user.equals("MasterIvan") && password.equals("Botadaso")) {
                    JOptionPane.showMessageDialog(frame, "Login exitoso como administrador");
                    // Aquí puedes abrir la siguiente ventana o realizar otra acción
                } else if (user.equals("RamaJham") && password.equals("LimondeCevichero")) {
                    JOptionPane.showMessageDialog(frame, "Login exitoso como usuario");
                    // Aquí puedes abrir la siguiente ventana o realizar otra acción

                } else if (user.equals("Manuel") && password.equals("Porlascompaneras")) {
                    JOptionPane.showMessageDialog(frame, "Login exitoso como usuario");
                    // Aquí puedes abrir la siguiente ventana o realizar otra acción
                    
                } else {
                    JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Cerrar la aplicación al hacer clic en Cancelar
                System.exit(0);
            }
        });
    }


    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        lblUser = new JLabel("Usuario:");
        lblUser.setBounds(10, 20, 80, 25);
        panel.add(lblUser);

        txtUser = new JTextField(20);
        txtUser.setBounds(100, 20, 165, 25);
        panel.add(txtUser);

        lblPasw = new JLabel("Contraseña:");
        lblPasw.setBounds(10, 50, 80, 25);
        panel.add(lblPasw);

        pwPassword = new JPasswordField(20);
        pwPassword.setBounds(100, 50, 165, 25);
        panel.add(pwPassword);

        int buttonWidth = 80;
        int buttonHeight = 25;
        int gap = 20;
        int panelWidth = 300; // igual al ancho del frame
        int totalWidth = buttonWidth * 2 + gap;
        int startX = (panelWidth - totalWidth) / 2;

        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(startX, 80, buttonWidth, buttonHeight);
        panel.add(btnIngresar);

        btnCancel = new JButton("Cancelar");
        btnCancel.setBounds(startX + buttonWidth + gap, 80, buttonWidth, buttonHeight);
        panel.add(btnCancel);
    }


    public static void main(String[] args) {
        new Login();
    }

}
