package ui;

import modelos.*;
import modelos.Sistema;
import procesos.PersonaService;
import procesos.PersonaServiceImpl;
import procesos.SistemaService;
import procesos.SistemaServiceImpl;
import utilerias.Response;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioForm extends JPanel{
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
    private DefaultTableModel tableModel;

    public UsuarioForm (){
        sisService = new SistemaServiceImpl();
        perService = new PersonaServiceImpl();
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


        llenarComboBoxCompania(comboBox1);
        llenarComboBoxDependencia(comboBox4);
        llenarComboBoxCargo(comboBox2);
        llenarComboBoxPersona(comboBox5);
        llenarComboBoxSistema(comboBox3);

    }

    private void llenarComboBoxCompania(JComboBox<Compania> combo) {
        combo.removeAllItems();
        List<Compania>lstCompania = new ArrayList<>();
        combo.addItem(new Compania(0L,"Seleccione una Compañía", ""));
        Compania com= null;
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
        List<Dependencia>lstDep = new ArrayList<>();
        combo.addItem(new Dependencia(0L,"Seleccione una Dependencia", ""));
        Dependencia dep= null;
        dep = new Dependencia(
                1L, "Dirección Regional de Educación", "Tacna - Tacna - Perú"
        );
        lstDep.add(dep);
        dep = new Dependencia(
                2L, "Dirección Regional de Salud", "Tacna - Tacna - Perú"
        );
        lstDep.add(dep);
        dep = new Dependencia(
                3L, "Dirección Regional de Transportes", "Tacna - Tacna - Perú"
        );
        lstDep.add(dep);
        for (Dependencia d : lstDep) {
            combo.addItem(d);
        }
        combo.setSelectedIndex(0);
        combo.revalidate();
        combo.repaint();
    }

    private void llenarComboBoxCargo(JComboBox<Cargo> combo) {
        combo.removeAllItems();
        List<Cargo>lstCargo = new ArrayList<>();
        combo.addItem(new Cargo(0L,"Seleccione un Cargo"));
        Cargo car= null;
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
        List<Persona>lstPer = new ArrayList<>();
        combo.addItem(new Persona(0L,"Seleccione una Persona","",""));
        Response<List<Persona>>res = perService.listarPersonas();
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
        List<Sistema>lstSistem = new ArrayList<>();
        combo.addItem(new Sistema(0L,"","Seleccione un Sistema"));
        Response<List<Sistema>>res = sisService.listarSistemas();
        lstSistem = res.getData();

        for (Sistema s : lstSistem) {
            combo.addItem(s);
        }
        combo.setSelectedIndex(0);
        combo.revalidate();
        combo.repaint();
    }



}
