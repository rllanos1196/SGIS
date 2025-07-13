package dao;

import modelos.Modulo;
import modelos.Usuario;
import utilerias.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    // Aquí irían los métodos para interactuar con la base de datos
    // por ejemplo, registrar, autenticar, eliminar usuarios, etc.

    // Ejemplo de método para registrar un usuario
    public boolean registrarUsuario(Usuario user) {
        String sql = "INSERT INTO usuario(ID_COMPANIA,ID_DEPENDENCIA, ID_CARGO, ID_PERSONA,ID_SISTEMA,NOMBRE,PASSWORD,ESTADO,";
        sql = sql +"FECHA_INGRESO,FECHA_CADUCA,FECHA_REGISTRO,ID_USUARIO_REGISTRO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, user.getIdCompania());
            ps.setLong(2, user.getIdDependencia());
            ps.setLong(3, user.getIdCargo());
            ps.setLong(4, user.getIdPersona());
            ps.setLong(5, user.getSistema().getId());
            ps.setString(6, user.getNombre());
            ps.setString(7,user.getPassword());
            ps.setBoolean(8, user.getEstado());
            ps.setObject(9, user.getFechaIngreso());
            ps.setObject(10, user.getFechaCaduca());
            ps.setObject(11, user.getFechaRegistro());
            ps.setLong(12, user.getIdUsuarioRegistro());
            ps.executeUpdate();
            return Boolean.TRUE;
        } catch (SQLException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }


    // Otros métodos como autenticarUsuario, eliminarUsuario, etc. se agregarían aquí
    public Usuario findById(Long id) {
        String sql = "SELECT * FROM usuario WHERE ID = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario m = new Usuario();
                    m.setId(rs.getLong("ID"));
                    m.setPassword(rs.getString("PASSWORD"));
                    m.setNombre(rs.getString("NOMBRE"));
                    m.setIdCompania(rs.getLong("ID_COMPANIA"));
                    m.setIdDependencia(rs.getLong("ID_DEPENDENCIA"));
                    m.setIdCargo(rs.getLong("ID_CARGO"));
                    m.setIdPersona(rs.getLong("ID_PERSONA"));
                    m.setSistema(new modelos.Sistema(rs.getLong("ID_SISTEMA")));
                    m.setEstado(rs.getBoolean("ESTADO"));
                    m.setFechaIngreso(rs.getObject("FECHA_INGRESO", LocalDateTime.class));
                    m.setFechaCaduca(rs.getObject("FECHA_CADUCA", LocalDateTime.class));
                    m.setFechaRegistro(rs.getObject("FECHA_REGISTRO", LocalDateTime.class));
                    m.setIdUsuarioRegistro(rs.getLong("ID_USUARIO_REGISTRO"));
                    return m;
                }
            }
        } catch (SQLException e) {
            return null;
        }
        return null; // Placeholder
    }
    public boolean eliminarUsuario(Long id,Boolean estado) {
        String sql = "UPDATE usuario SET ESTADO=? WHERE ID = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, estado);
            ps.setLong(2, id);
            ps.executeUpdate();
            return Boolean.TRUE;
        } catch (SQLException e) {
            return Boolean.FALSE;
        }
    }
    public boolean actualizarUsuario(Usuario user) {
        String sql = "UPDATE usuario SET ID_COMPANIA=?, ID_DEPENDENCIA=?, ID_CARGO=?, ID_PERSONA=?, ID_SISTEMA=?, NOMBRE=?, PASSWORD=?, ESTADO=?, FECHA_INGRESO=?, FECHA_CADUCA=?, FECHA_REGISTRO=?, ID_USUARIO_REGISTRO=? WHERE ID=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, user.getIdCompania());
            ps.setLong(2, user.getIdDependencia());
            ps.setLong(3, user.getIdCargo());
            ps.setLong(4, user.getIdPersona());
            ps.setLong(5, user.getSistema().getId());
            ps.setString(6, user.getNombre());
            ps.setString(7, user.getPassword());
            ps.setBoolean(8, user.getEstado());
            ps.setObject(9, user.getFechaIngreso());
            ps.setObject(10, user.getFechaCaduca());
            ps.setObject(11, user.getFechaRegistro());
            ps.setLong(12, user.getIdUsuarioRegistro());
            ps.setLong(13, user.getId());
            ps.executeUpdate();
            return Boolean.TRUE;
        } catch (SQLException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }
    public Usuario autenticarUsuario(String nombre, String password) {
        String sql = "SELECT * FROM usuario WHERE NOMBRE = ? AND PASSWORD = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario user = new Usuario();
                    user.setId(rs.getLong("ID"));
                    user.setNombre(rs.getString("NOMBRE"));
                    user.setPassword(rs.getString("PASSWORD"));
                    user.setIdCompania(rs.getLong("ID_COMPANIA"));
                    user.setIdDependencia(rs.getLong("ID_DEPENDENCIA"));
                    user.setIdCargo(rs.getLong("ID_CARGO"));
                    user.setIdPersona(rs.getLong("ID_PERSONA"));
                    user.setSistema(new modelos.Sistema(rs.getLong("ID_SISTEMA")));
                    user.setEstado(rs.getBoolean("ESTADO"));
                    user.setFechaIngreso(rs.getObject("FECHA_INGRESO", LocalDateTime.class));
                    user.setFechaCaduca(rs.getObject("FECHA_CADUCA", LocalDateTime.class));
                    user.setFechaRegistro(rs.getObject("FECHA_REGISTRO", LocalDateTime.class));
                    user.setIdUsuarioRegistro(rs.getLong("ID_USUARIO_REGISTRO"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean cambiarPassword(Long id, String newPassword) {
        String sql = "UPDATE usuario SET PASSWORD = ? WHERE ID = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setLong(2, id);
            ps.executeUpdate();
            return Boolean.TRUE;
        } catch (SQLException e) {
            return Boolean.FALSE;
        }
    }
    public boolean recuperarPassword(Long id, String newPassword) {
        String sql = "UPDATE usuario SET PASSWORD = ? WHERE ID = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setLong(2, id);
            ps.executeUpdate();
            return Boolean.TRUE;
        } catch (SQLException e) {
            return Boolean.FALSE;
        }
    }
    public Usuario verificarUsuario(String nombre) {
        String sql = "SELECT * FROM usuario WHERE NOMBRE = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario user = new Usuario();
                    user.setId(rs.getLong("ID"));
                    user.setNombre(rs.getString("NOMBRE"));
                    user.setPassword(rs.getString("PASSWORD"));
                    user.setIdCompania(rs.getLong("ID_COMPANIA"));
                    user.setIdDependencia(rs.getLong("ID_DEPENDENCIA"));
                    user.setIdCargo(rs.getLong("ID_CARGO"));
                    user.setIdPersona(rs.getLong("ID_PERSONA"));
                    user.setSistema(new modelos.Sistema(rs.getLong("ID_SISTEMA")));
                    user.setEstado(rs.getBoolean("ESTADO"));
                    user.setFechaIngreso(rs.getObject("FECHA_INGRESO", LocalDateTime.class));
                    user.setFechaCaduca(rs.getObject("FECHA_CADUCA", LocalDateTime.class));
                    user.setFechaRegistro(rs.getObject("FECHA_REGISTRO", LocalDateTime.class));
                    user.setIdUsuarioRegistro(rs.getLong("ID_USUARIO_REGISTRO"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
    public List<Usuario> listarUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE ESTADO = TRUE";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getLong("ID"));
                user.setNombre(rs.getString("NOMBRE"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setIdCompania(rs.getLong("ID_COMPANIA"));
                user.setIdDependencia(rs.getLong("ID_DEPENDENCIA"));
                user.setIdCargo(rs.getLong("ID_CARGO"));
                user.setIdPersona(rs.getLong("ID_PERSONA"));
                user.setSistema(new modelos.Sistema(rs.getLong("ID_SISTEMA")));
                user.setEstado(rs.getBoolean("ESTADO"));
                user.setFechaIngreso(rs.getObject("FECHA_INGRESO", LocalDateTime.class));
                user.setFechaCaduca(rs.getObject("FECHA_CADUCA", LocalDateTime.class));
                user.setFechaRegistro(rs.getObject("FECHA_REGISTRO", LocalDateTime.class));
                user.setIdUsuarioRegistro(rs.getLong("ID_USUARIO_REGISTRO"));
                lista.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

}
