package ui;
import modelos.Usuario;
import procesos.UsuarioService;
import procesos.UsuarioServiceImpl;
import utilerias.Response;

import java.awt.*;
import javax.swing.*;

public class Login {
    private JPanel Login;
    private JTextField txtUser;
    private JPasswordField pwUser;
    private JButton btnCancelar;
    private JButton btnIngresar;
    private JProgressBar progressBar1;
    private JLabel lblUser;
    private JLabel lblPassword;


    UsuarioService usuarioService;


    public Login(){
        usuarioService = new UsuarioServiceImpl();
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(300, 200);
        frame.setContentPane(Login);
        frame.pack();
        frame.setVisible(true);;

        // Establecer icono de la ventana
        lblUser.setIcon(new ImageIcon("src/ImgIcon/user.png"));
        Image userIcon = ((ImageIcon) lblUser.getIcon()).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        lblUser.setIcon(new ImageIcon(userIcon));

        lblPassword.setIcon(new ImageIcon("src/ImgIcon/key.png"));
        Image passIcon = ((ImageIcon) lblPassword.getIcon()).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        lblPassword.setIcon(new ImageIcon(passIcon));

// En tu constructor o donde creas el botón:
        btnIngresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.setForeground(Color.WHITE);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

        btnIngresar.addActionListener(e -> {
            // Validar usuario y contraseña
            String user = txtUser.getText();
            String pass = new String(pwUser.getPassword());

            Response<Usuario> response = usuarioService.autenticarUsuario(user,pass); // Sim
            Usuario usuario = response.getData();

            if (response.isEstado() && usuario != null) {
                btnIngresar.setEnabled(false);
                btnCancelar.setEnabled(false);
                progressBar1.setVisible(true);
                progressBar1.setValue(0);

                SwingWorker<Void, Integer> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        for (int i = 0; i <= 100; i += 10) {
                            Thread.sleep(100); // Simula carga
                            publish(i);
                        }
                        return null;
                    }

                    @Override
                    protected void process(java.util.List<Integer> chunks) {
                        int value = chunks.get(chunks.size() - 1);
                        progressBar1.setValue(value);
                    }

                    @Override
                    protected void done() {
                        JOptionPane.showMessageDialog(null, "¡Login exitoso!");
                        new MenuForm();
                        JFrame frame1 = (JFrame) SwingUtilities.getWindowAncestor(btnIngresar);
                        frame1.dispose();
                    }

                };
                worker.execute();
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos, por favor ingrese nuevamente.");
            }

        });

        btnCancelar.addActionListener(e -> System.exit(0));
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login login = new Login();
        });
    }

}
