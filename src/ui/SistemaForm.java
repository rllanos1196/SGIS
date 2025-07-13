package ui;

import modelos.Modulo;
import procesos.SistemaService;
import procesos.SistemaServiceImpl;
import utilerias.ModoOperacion;
import utilerias.Response;
import modelos.Sistema;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SistemaForm extends JPanel {

    private JTextField txtCodigoS;
    private JTextField txtNombreS;
    private JTable tbSistema;
    private JButton btnRegistrarS;
    private JButton btnEditarS;
    private JButton btnEliminarS;
    private JPanel jpSistema;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;

    private SistemaService sistemaService;
    private Long idSistema;
    private ModoOperacion modoActual = ModoOperacion.REGISTRAR;

    public SistemaForm() {
        // Set up the JFrame
        sistemaService = new SistemaServiceImpl();

        setLayout(new BorderLayout());
        add(jpSistema, BorderLayout.CENTER);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Código", "Nombre"}, 0);
        tbSistema.setModel(tableModel);
        tbSistema.getTableHeader().repaint();
        tbSistema.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbSistema.setDefaultEditor(Object.class, null); // Deshabilita la edición directa en la tabla
        tbSistema.getColumnModel().getColumn(0).setPreferredWidth(50); // Ajusta el ancho de la columna ID
        tbSistema.getColumnModel().getColumn(1).setPreferredWidth(100); // Ajusta el ancho de la columna Código
        tbSistema.getColumnModel().getColumn(2).setPreferredWidth(150); // Ajusta el ancho de la columna Nombre
        tbSistema.setRowHeight(30); // Ajusta la altura de las filas
        tbSistema.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Deshabilita el ajuste automático de columnas
        scrollPane.setViewportView(tbSistema);


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

                String codigo = txtCodigoS.getText();
                String nombre = txtNombreS.getText();
                if (codigo.isEmpty() || nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(SistemaForm.this, "Por favor, complete todos los campos.");
                    return;
                }
                // Actualizar el sistema existente
                Sistema sistema = new Sistema();
                sistema.setId(idSistema);
                sistema.setCodigo(codigo);
                sistema.setNombre(nombre);
                Response<Boolean> response = sistemaService.registrarSistema(sistema);
                JOptionPane.showMessageDialog(SistemaForm.this, response.getMensaje(), "Información", JOptionPane.INFORMATION_MESSAGE);
                btnRegistrarS.setText("Registrar");
                limpiarCamposEnContenedor(jpSistema);
                listarSistema();
                idSistema= null;
            }
        });

        btnEditarS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbSistema.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(SistemaForm.this, "Por favor, seleccione un módulo para editar.");
                    return;
                }

                idSistema = (Long) tableModel.getValueAt(selectedRow, 0);
                Response<Sistema> response = sistemaService.findById(idSistema);
                Sistema sistema = response.getData();
//                jpModulo.setTittle("Editar Módulo");

                if (sistema != null) {
                    txtCodigoS.setText(sistema.getCodigo());
                    txtNombreS.setText(sistema.getNombre());
                    btnRegistrarS.setText("Actualizar");
                    modoActual = ModoOperacion.EDITAR;
                } else {
                    JOptionPane.showMessageDialog(SistemaForm.this, response.getMensaje());
                }
            }
        });


        btnEliminarS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbSistema.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(SistemaForm.this, "Por favor, seleccione un módulo para eliminar.");
                    return;
                }

                 idSistema = (Long) tableModel.getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(SistemaForm.this, "¿Está seguro de eliminar este módulo?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    Response<Boolean> response = sistemaService.eliminarSistema(idSistema);
                    tableModel.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(SistemaForm.this, response.getMensaje());
                    listarSistema();
                }
            }
        });
        // Listar los sistemas al iniciar el formulario
        listarSistema();

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

    private void listarSistema() {
        tableModel.setRowCount(0);
        // Aquí deberías obtener la lista de sistemas desde el servicio
        Response<List<Sistema>> response = sistemaService.listarSistemas();
        List<Sistema> lstSistemas = response.getData();
        if (lstSistemas != null && !lstSistemas.isEmpty()) {
            for (Sistema m : lstSistemas) {
                tableModel.addRow(new Object[]{m.getId(), m.getCodigo(), m.getNombre()});
            }
        } else {
            JOptionPane.showMessageDialog(SistemaForm.this, response.getMensaje());
        }
        tbSistema.revalidate();
        tbSistema.repaint();
    }

}
