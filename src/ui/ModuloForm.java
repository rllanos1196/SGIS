package ui;

import modelos.Modulo;
import procesos.ModuloService;
import procesos.ModuloServiceImpl;
import utilerias.ModoOperacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class ModuloForm extends JPanel{
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
    private JButton btnCancelarM;
    private JTextField txtId;

    private DefaultTableModel tableModel;

    private ModuloService modService;

    private ModoOperacion modoActual = ModoOperacion.REGISTRAR;
    private Long idMod;
    // public ModuloForm() {
    public ModuloForm() {
        modService = new ModuloServiceImpl();

        // Frame y contenido del panel generado automáticamente por el diseñador
        setLayout(new BorderLayout());
        add(jpModulo, BorderLayout.CENTER);
        // frame.setSize(800, 600);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Código", "Nombre", "Descripción","Estado"}, 0);
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

        //COLOR A LA CABECERA DE LA TABLA
        tbModulo.getTableHeader().setDefaultRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(Color.GRAY); // Fondo GRIS
                c.setForeground(java.awt.Color.WHITE); // Texto blanco
                setHorizontalAlignment(CENTER);
                return c;
            }
        });

        // Configuración de los botones
        btnRegistrar.setIcon(new ImageIcon("src/ImgIcon/save.png"));
        Image saveIcon = ((ImageIcon) btnRegistrar.getIcon()).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        btnRegistrar.setIcon(new ImageIcon(saveIcon));

        btnEditar.setIcon(new ImageIcon("src/ImgIcon/editar.png"));
        Image editIcon = ((ImageIcon) btnEditar.getIcon()).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        btnEditar.setIcon(new ImageIcon(editIcon));

        btnCancelarM.setIcon(new ImageIcon("src/ImgIcon/cancel.png"));
        Image cancelIcon = ((ImageIcon) btnCancelarM.getIcon()).getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
        btnCancelarM.setIcon(new ImageIcon(cancelIcon));

        btnEliminar.setIcon(new ImageIcon("src/ImgIcon/delete.png"));
        Image deleteIcon = ((ImageIcon) btnEliminar.getIcon()).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        btnEliminar.setIcon(new ImageIcon(deleteIcon));

        btnRegistrar.setText("Registrar");
        modoActual= ModoOperacion.REGISTRAR;
        // Evento del botón que SÍ existe en el .form
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = txtCodigo.getText();
                String nombre = txtNombre.getText();
                String descripcion = textArea1.getText();

                if (codigo.isEmpty() || nombre.isEmpty() || descripcion.isEmpty()) {
                    JOptionPane.showMessageDialog(ModuloForm.this, "Por favor, complete todos los campos.");
                    return;
                }

                if (modoActual == ModoOperacion.EDITAR) {
                    Modulo mod = modService.findById(idMod);
                    mod.setCodigo(codigo);
                    mod.setNombre(nombre);
                    mod.setDescripcion(descripcion);
                    modService.updateModulo(mod);
                    JOptionPane.showMessageDialog(ModuloForm.this, "Módulo actualizado correctamente.");
                } else {
                    Modulo modulo = new Modulo(codigo, nombre, descripcion);
                    modService.insertModulo(modulo);
                    JOptionPane.showMessageDialog(ModuloForm.this, "Módulo registrado correctamente.");
                }
                limpiarCamposEnContenedor(jpModulo);
                listarModulo();
                btnRegistrar.setText("Registrar");
                modoActual= ModoOperacion.REGISTRAR;
            }
        });

        listarModulo();

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbModulo.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(ModuloForm.this, "Por favor, seleccione un módulo para editar.");
                    return;
                }

                idMod = (Long) tableModel.getValueAt(selectedRow, 0);
                Modulo modulo = modService.findById(idMod);
//                jpModulo.setTittle("Editar Módulo");

                if (modulo != null) {
                    txtCodigo.setText(modulo.getCodigo());
                    txtNombre.setText(modulo.getNombre());
                    textArea1.setText(modulo.getDescripcion());
                    btnRegistrar.setText("Actualizar");
                    modoActual = ModoOperacion.EDITAR;
                } else {
                    JOptionPane.showMessageDialog(ModuloForm.this, "Módulo no encontrado.");
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbModulo.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(ModuloForm.this, "Por favor, seleccione un módulo para eliminar.");
                    return;
                }

                Long id = (Long) tableModel.getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(ModuloForm.this, "¿Está seguro de eliminar este módulo?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    modService.deleteModulo(id);
                    tableModel.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(ModuloForm.this, "Módulo eliminado correctamente.");
                    listarModulo();
                }
            }
        });
       // return jpModulo;
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