package ui;

import modelos.*;
import modelos.Sistema;
import procesos.*;
import utilerias.ModoOperacion;
import utilerias.Response;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UsuarioForm extends JPanel {
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
    private JScrollPane scrollPane;

    //Frame contenido del panel
    SistemaService sisService;
    PersonaService perService;
    UsuarioService userService;
    private DefaultTableModel tableModel;
    private Long idUsuario;
    private ModoOperacion modoActual = ModoOperacion.REGISTRAR;

    List<Cargo> lstCargo = new ArrayList<>();
    List<Persona> lstPer = new ArrayList<>();
    List<Compania>lstCompania = new ArrayList<>();
    List<Dependencia>lstDependencia = new ArrayList<>();
    List<Sistema>lstSistema = new ArrayList<>();

    public UsuarioForm() {
        sisService = new SistemaServiceImpl();
        perService = new PersonaServiceImpl();
        userService = new UsuarioServiceImpl();
        setLayout(new BorderLayout());
        add(jpUsuario, BorderLayout.CENTER);


        tableModel = new DefaultTableModel(new Object[]{"ID", "Cargo", "Persona", "Usuario"}, 0);
        tbUserF.setModel(tableModel);
        tbUserF.getTableHeader().repaint();
        tbUserF.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbUserF.setDefaultEditor(Object.class, null); // Deshabilita la edición directa en la tabla
        tbUserF.getColumnModel().getColumn(0).setPreferredWidth(50); // Ajusta el ancho de la columna ID
        tbUserF.getColumnModel().getColumn(1).setPreferredWidth(100); // Ajusta el ancho de la columna Código
        tbUserF.getColumnModel().getColumn(2).setPreferredWidth(150); // Ajusta el ancho de la columna Nombre
        tbUserF.getColumnModel().getColumn(3).setPreferredWidth(80); // Ajusta el ancho de la columna Descripción

        tbUserF.setRowHeight(30); // Ajusta la altura de las filas
        tbUserF.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Deshabilita el ajuste automático de columnas
        scrollPane.setViewportView(tbUserF); // Asegúrate de que el JScrollPane esté configurado correctamente


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

        modoActual = ModoOperacion.REGISTRAR;
        llenarComboBoxCompania(comboBox1);
        llenarComboBoxDependencia(comboBox4);
        llenarComboBoxCargo(comboBox2);
        llenarComboBoxPersona(comboBox5);
        llenarComboBoxSistema(comboBox3);

        btnRegistrarUF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar campos
                if (comboBox1.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(jpUsuario, "Debe seleccionar una Compañía", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (comboBox2.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(jpUsuario, "Debe seleccionar un Cargo", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (comboBox4.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(jpUsuario, "Debe seleccionar una Dependencia", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (comboBox5.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(jpUsuario, "Debe seleccionar una Persona", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (txtUserF.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(jpUsuario, "El campo Usuario no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (pwUserF.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(jpUsuario, "El campo Contraseña no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Crear el usuario y agregarlo a la tabla
                Usuario usuario = new Usuario();
                usuario.setId(idUsuario);
                Compania compa = (Compania) comboBox1.getSelectedItem();
                usuario.setIdCompania(compa.getId());
                Cargo car = (Cargo) comboBox2.getSelectedItem();
                usuario.setIdCargo(car.getId());
                Dependencia dep = (Dependencia) comboBox4.getSelectedItem();
                usuario.setIdDependencia(dep.getId());
                Persona pe = (Persona) comboBox5.getSelectedItem();
                usuario.setIdPersona(pe.getId());
                Sistema sys = (Sistema) comboBox3.getSelectedItem();
                usuario.setSistema(sys);
                usuario.setNombre(txtUserF.getText().trim());
                usuario.setPassword(new String(pwUserF.getPassword()));
                Response<Boolean> respo = userService.registrarUsuario(usuario);

                JOptionPane.showMessageDialog(UsuarioForm.this, respo.getMensaje(), "Información", JOptionPane.INFORMATION_MESSAGE);

                // Limpiar campos
                comboBox1.setSelectedIndex(0);
                comboBox2.setSelectedIndex(0);
                comboBox4.setSelectedIndex(0);
                comboBox5.setSelectedIndex(0);
                comboBox3.setSelectedIndex(0);
                txtUserF.setText("");
                pwUserF.setText("");
                // Actualizar la tabla
                btnRegistrarUF.setText("Registrar");
                modoActual = ModoOperacion.REGISTRAR;
                listarUsuarios();

            }

        });

        btnModificarUF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbUserF.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(jpUsuario, "Debe seleccionar un usuario para modificar", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Obtener el ID del usuario seleccionado
                idUsuario = (Long) tableModel.getValueAt(selectedRow, 0);

                // Obtener los datos del usuario seleccionado
                Response<Usuario> response = userService.findById(idUsuario);
                Usuario usuario = response.getData();
                if (usuario != null) {
                    comboBox1.setSelectedItem(getCompaniaById(usuario.getIdCompania()));
                    comboBox4.setSelectedItem(getDependenciaById(usuario.getIdDependencia()));
                    Cargo cargo = getCargoById(usuario.getIdCargo());
                    comboBox2.setSelectedItem(cargo);
                    comboBox5.setSelectedItem(getPersonaById(usuario.getIdPersona()));
                    comboBox3.setSelectedItem(getSistemaById(usuario.getSistema().getId()));
                    txtUserF.setText(usuario.getNombre());
                    pwUserF.setText(usuario.getPassword());
                    btnRegistrarUF.setText("Actualizar");
                    modoActual = ModoOperacion.EDITAR;
                    pwUserF.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(jpUsuario, "Usuario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnEliminarUF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbUserF.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(jpUsuario, "Debe seleccionar un usuario para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Obtener el ID del usuario seleccionado
                idUsuario = (Long) tableModel.getValueAt(selectedRow, 0);

                // Confirmar la eliminación
                int confirm = JOptionPane.showConfirmDialog(jpUsuario, "¿Está seguro de que desea eliminar este usuario?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    Response<Boolean> response = userService.eliminarUsuario(idUsuario);
                    if (response.isEstado()) {
                        JOptionPane.showMessageDialog(jpUsuario, response.getMensaje(), "Información", JOptionPane.INFORMATION_MESSAGE);
                        listarUsuarios(); // Actualizar la lista de usuarios
                    } else {
                        JOptionPane.showMessageDialog(jpUsuario, response.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                listarUsuarios();
            }
        });
        listarUsuarios();
    }

    private void listarUsuarios() {
        tableModel.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos
        Response<List<Usuario>> response = userService.listarUsuarios();
        if (response.isEstado()) {
            List<Usuario> usuarios = response.getData();
            for (Usuario u : usuarios) {
                Persona dtoPer = getPersonaById(u.getIdPersona());
                tableModel.addRow(new Object[]{u.getId(), getCargoById(u.getIdCargo()).getNombre(), dtoPer.getNombre() + " " + dtoPer.getApePaterno() + " " + dtoPer.getApeMaterno(), u.getNombre()});
            }
        } else {
            JOptionPane.showMessageDialog(this, response.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void llenarComboBoxCompania(JComboBox<Compania> combo) {
        combo.removeAllItems();
        combo.addItem(new Compania(0L, "Seleccione una Compañía", ""));
        Compania com = null;
        com = new Compania(
                1L, "GORES - Gobierno Regional de Tacna", "Tacna - Tacna - Perú"
        );
        lstCompania.add(com);
        com = new Compania(
                2L, "GORES - Gobierno Regional de Moquegua", "Moquegua - Moquegua - Perú"
        );
        lstCompania.add(com);
        com = new Compania(
                3L, "GOMUS - Gobierno Municipal de Sullana", "Sullana - Piura - Perú"
        );
        lstCompania.add(com);
        for (Compania c : lstCompania) {
            combo.addItem(c);
            System.out.println("DEBUG: Usuario añadido al combo: " + c.toString());
        }
        combo.setSelectedIndex(0);
        combo.revalidate();
        combo.repaint();
    }

    private void llenarComboBoxDependencia(JComboBox<Dependencia> combo) {
        combo.removeAllItems();
        combo.addItem(new Dependencia(0L, "Seleccione una Dependencia", ""));
        Dependencia dep = null;
        dep = new Dependencia(
                1L, "Dirección Regional de Educación", "Tacna - Tacna - Perú"
        );
        lstDependencia.add(dep);
        dep = new Dependencia(
                2L, "Dirección Regional de Salud", "Tacna - Tacna - Perú"
        );
        lstDependencia.add(dep);
        dep = new Dependencia(
                3L, "Dirección Regional de Transportes", "Tacna - Tacna - Perú"
        );
        lstDependencia.add(dep);
        for (Dependencia d : lstDependencia) {
            combo.addItem(d);
        }
        combo.setSelectedIndex(0);
        combo.revalidate();
        combo.repaint();
    }

    private void llenarComboBoxCargo(JComboBox<Cargo> combo) {
        combo.removeAllItems();
        lstCargo = new ArrayList<>();
        combo.addItem(new Cargo(0L, "Seleccione un Cargo"));
        Cargo car = null;
        car = new Cargo(
                1L, "Gerente General"
        );
        lstCargo.add(car);
        car = new Cargo(
                2L, "Gerente de Sistemas"
        );
        lstCargo.add(car);
        car = new Cargo(
                3L, "Gerente de Recursos Humanos"
        );
        lstCargo.add(car);
        car = new Cargo(
                4L, "Gerente de Finanzas"
        );
        lstCargo.add(car);
        for (Cargo c : lstCargo) {
            combo.addItem(c);
        }
        combo.setSelectedIndex(0);
        combo.revalidate();
        combo.repaint();
    }

    private void llenarComboBoxPersona(JComboBox<Persona> combo) {
        combo.removeAllItems();
        lstPer = new ArrayList<>();
        combo.addItem(new Persona(0L, "Seleccione una Persona", "", ""));
        Response<List<Persona>> res = perService.listarPersonas();
        lstPer = res.getData();

        for (Persona pe : lstPer) {
            combo.addItem(pe);
        }
        combo.setSelectedIndex(0);
        combo.revalidate();
        combo.repaint();
    }

    private void llenarComboBoxSistema(JComboBox<Sistema> combo) {
        combo.removeAllItems();
        combo.addItem(new Sistema(0L, "", "Seleccione un Sistema"));
        Response<List<Sistema>> res = sisService.listarSistemas();
        lstSistema = res.getData();

        for (Sistema s : lstSistema) {
            combo.addItem(s);
        }
        combo.setSelectedIndex(0);
        combo.revalidate();
        combo.repaint();
    }

    private Cargo getCargoById(Long idCargo) {
        return lstCargo.stream()
                .filter(c -> c.getId() == idCargo)
                .findFirst()
                .orElse(null);
    }

    private Persona getPersonaById(Long idPersona) {
        return lstPer.stream()
                .filter(p -> p.getId() == idPersona)
                .findFirst()
                .orElse(null);
    }
    private Compania getCompaniaById(Long idCompania) {
        return lstCompania.stream()
                .filter(c -> c.getId() == idCompania)
                .findFirst()
                .orElse(null);
    }
    private Dependencia getDependenciaById(Long idDependencia) {
        return lstDependencia.stream()
                .filter(d -> d.getId() == idDependencia)
                .findFirst()
                .orElse(null);
    }
    private Sistema getSistemaById(Long idSistema) {
        return lstSistema.stream()
                .filter(s -> s.getId() == idSistema)
                .findFirst()
                .orElse(null);
    }

}
