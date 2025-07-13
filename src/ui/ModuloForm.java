package ui;

import modelos.Modulo;
import procesos.ModuloService;
import procesos.ModuloServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


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
    private JPanel jpModulo;
    private JScrollPane scrollPane;

    private DefaultTableModel tableModel;

    private ModuloService modService;

    public ModuloForm() {
        modService = new ModuloServiceImpl();

        // Frame y contenido del panel generado automáticamente por el diseñador
        JFrame frame = new JFrame("Módulo");
        frame.setContentPane(jpModulo);  // ¡Esta línea es la que usa tu diseño!
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        // frame.setSize(800, 600);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Código", "Nombre", "Descripción","Estado"}, 0); // Ajusta las columnas si son de Usuarios
        tbModulo.setModel(tableModel);
        tbModulo.getTableHeader().repaint();
        tbModulo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbModulo.setDefaultEditor(Object.class, null); // Deshabilita la edición directa en la tabla
        tbModulo.getColumnModel().getColumn(0).setPreferredWidth(50); // Ajusta el ancho de la columna ID
        tbModulo.getColumnModel().getColumn(1).setPreferredWidth(100); // Ajusta el ancho de la columna Código
        tbModulo.getColumnModel().getColumn(2).setPreferredWidth(150); // Ajusta el ancho de la columna Nombre
        tbModulo.getColumnModel().getColumn(3).setPreferredWidth(200); // Ajusta el ancho de la columna Descripción
        tbModulo.getColumnModel().getColumn(4).setPreferredWidth(100); // Ajusta el ancho de la columna Estado
        tbModulo.setRowHeight(30); // Ajusta la altura de las filas
        tbModulo.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Deshabilita el ajuste automático de columnas
        scrollPane.setViewportView(tbModulo); // Asegúrate de que el JScrollPane esté configurado correctamente


        // Evento del botón que SÍ existe en el .form
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
                modService.insertModulo(modulo);
                JOptionPane.showMessageDialog(frame, "Módulo registrado correctamente.");

                limpiarCamposEnContenedor(jpModulo);
            }
        });

        listarModulo();
    }
    private void limpiarCamposEnContenedor(java.awt.Container contenedor) {
        for (java.awt.Component c : contenedor.getComponents()) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            } else if (c instanceof JTextArea) {
                ((JTextArea) c).setText("");
            } else if (c instanceof JFormattedTextField) {
                ((JFormattedTextField) c).setText("");
            } else if (c instanceof java.awt.Container) {
                limpiarCamposEnContenedor((java.awt.Container) c);
            }
        }
    }

    private void listarModulo() {
        tableModel.setRowCount(0);
        List<Modulo> modulos = modService.getAll();
        if (modulos != null && !modulos.isEmpty()) {
            for (Modulo m : modulos) {
                tableModel.addRow(new Object[]{m.getId(), m.getCodigo(), m.getNombre(), m.getDescripcion(),m.getEstado()== Boolean.TRUE ? "Activo" : "Inactivo"});
            }
        } else {
            System.out.println("DEBUG: No se encontraron libros para cargar en la tabla.");
        }
        tbModulo.revalidate();
        tbModulo.repaint();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(ModuloForm::new);
    }
}