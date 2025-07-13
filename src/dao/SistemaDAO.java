package dao;

import modelos.Modulo;
import modelos.Sistema;
import utilerias.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SistemaDAO {

    public boolean registrarSistema(Sistema sis) {
        String sql = "INSERT INTO sistema(CODIGO,NOMBRE,FECHA_REGISTRO,ID_USUARIO_REGISTRO,ESTADO) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, sis.getCodigo());
            ps.setString(2, sis.getNombre());
            ps.setObject(3, sis.getFechaRegistro());
            ps.setLong(4, sis.getIdUsuarioRegistro());
            ps.setBoolean(5, sis.getEstado());
            ps.executeUpdate();
            return Boolean.TRUE;
        } catch (SQLException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public boolean actualizarSistema(Sistema sis) {
        String sql = "UPDATE sistema SET CODIGO = ?, NOMBRE = ?, FECHA_MODIFICA = ?, ID_USUARIO_MODIFICA = ? WHERE ID = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, sis.getCodigo());
            ps.setString(2, sis.getNombre());
            ps.setObject(3, sis.getFechaModifica());
            ps.setLong(4, sis.getIdUsuarioModifica());
            ps.setLong(5, sis.getId());
            ps.executeUpdate();
            return Boolean.TRUE;
        } catch (SQLException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public Sistema findById(Long id) {
        Sistema sistema = null;
        String sql = "SELECT * FROM sistema WHERE ID = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            var rs = ps.executeQuery();
            if (rs.next()) {
                sistema = new Sistema(rs.getLong("ID"));
                sistema.setCodigo(rs.getString("CODIGO"));
                sistema.setNombre(rs.getString("NOMBRE"));
                sistema.setFechaRegistro(rs.getObject("FECHA_REGISTRO", java.time.LocalDateTime.class));
                sistema.setIdUsuarioRegistro(rs.getLong("ID_USUARIO_REGISTRO"));
                sistema.setFechaModifica(rs.getObject("FECHA_MODIFICA", java.time.LocalDateTime.class));
                sistema.setIdUsuarioModifica(rs.getLong("ID_USUARIO_MODIFICA"));
                sistema.setEstado(rs.getBoolean("ESTADO"));
                sistema.setIdDependencia(rs.getLong("ID_DEPENDENCIA"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sistema;
    }
    public boolean eliminarSistema(Long id) {
        String sql = "UPDATE sistema SET ESTADO = 0 WHERE ID = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
            return Boolean.TRUE;
        } catch (SQLException e) {
            return Boolean.FALSE;
        }
    }
    public List<Sistema> getAll() {
        List<Sistema> lista = new ArrayList<>();
        String sql = "SELECT * FROM sistema WHERE ESTADO = 1";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             var rs = ps.executeQuery()) {
            while (rs.next()) {
                Sistema sistema = new Sistema(rs.getLong("ID"));
                sistema.setCodigo(rs.getString("CODIGO"));
                sistema.setNombre(rs.getString("NOMBRE"));
                sistema.setFechaRegistro(rs.getObject("FECHA_REGISTRO", java.time.LocalDateTime.class));
                sistema.setIdUsuarioRegistro(rs.getLong("ID_USUARIO_REGISTRO"));
                sistema.setFechaModifica(rs.getObject("FECHA_MODIFICA", java.time.LocalDateTime.class));
                sistema.setIdUsuarioModifica(rs.getLong("ID_USUARIO_MODIFICA"));
                sistema.setEstado(rs.getBoolean("ESTADO"));
                sistema.setIdDependencia(rs.getLong("ID_DEPENDENCIA"));
                lista.add(sistema);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

}
