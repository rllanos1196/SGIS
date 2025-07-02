package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import utilerias.ArchivoHelper;

public class Login {
    private JTextField txtUser;
    private JLabel lblUser;
    private JLabel lblPasw;
    private JButton btnCancel;
    private JButton btnIngresar;
    private JPasswordField pwPassword;
    private JPanel Login;
    private JLabel lblArchivo;
    private JTextField txtRutaArchivo;
    private JButton btnSeleccionarArchivo;
    private JButton btnCargarArchivo;

    public Login() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

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

        lblArchivo = new JLabel("Archivo:");
        lblArchivo.setBounds(10, 110, 80, 25);
        panel.add(lblArchivo);

        txtRutaArchivo = new JTextField(20);
        txtRutaArchivo.setBounds(100, 110, 165, 25);
        panel.add(txtRutaArchivo);

        btnSeleccionarArchivo = new JButton("Seleccionar Archivo");
        btnSeleccionarArchivo.setBounds(100, 140, 165, 25);
        panel.add(btnSeleccionarArchivo);

        btnCargarArchivo = new JButton("Guardar");
        btnCargarArchivo.setBounds(100, 170, 165, 25);
        panel.add(btnCargarArchivo);


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

        btnSeleccionarArchivo.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Selecciona un archivo");

            int resultado = fileChooser.showOpenDialog(null); // o this si estás en un JFrame

            if (resultado == JFileChooser.APPROVE_OPTION) {
                File archivo = fileChooser.getSelectedFile();
                String ruta = archivo.getAbsolutePath();
                txtRutaArchivo.setText(ruta); // Supongamos que tienes un JTextField para mostrar la ruta
            }
        });


        btnCargarArchivo.addActionListener(e -> {
            String ruta = txtRutaArchivo.getText();
            if (ruta == null || ruta.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Seleccione un archivo primero.");
                return;
            }

            boolean exito = ArchivoHelper.copiarArchivoDesdeRuta(ruta);
            if (exito) {
                JOptionPane.showMessageDialog(null, "Archivo cargado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al cargar el archivo.");
            }
        });
    }


    public static void main(String[] args) {
        new Login();
    }

}
