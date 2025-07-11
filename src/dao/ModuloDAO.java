package dao;

import modelos.Modulo;
import utilerias.Conexion;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ModuloDAO {

    public boolean insert(Modulo mod ) {
        String sql = "INSERT INTO modulo(CODIGO,NOMBRE, DESCRIPCION,ORDEN,ESTADO,FECHA_REGISTRO,ID_USUARIO_REGISTRO) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, mod.getCodigo());
            ps.setString(2, mod.getNombre());
            ps.setString(3, mod.getDescripcion());
            ps.setInt(4, mod.getOrden());
            ps.setBoolean(5, mod.getEstado());
            ps.setObject(6, mod.getFechaRegistro());
            ps.setLong(7,mod.getIdUsuarioRegistro());
            ps.executeUpdate();
            return Boolean.TRUE;
        } catch (SQLException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }
    public List<Modulo> getAll() {
        List<Modulo> lista = new ArrayList<>();
        String sql = "SELECT * FROM modulo where EStADO = 1 ORDER BY ORDEN";
        try (Connection con = Conexion.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Modulo m = new Modulo();
                m.setId(rs.getLong("ID"));
                m.setCodigo(rs.getString("CODIGO"));
                m.setNombre(rs.getString("NOMBRE"));
                m.setDescripcion(rs.getString("DESCRIPCION"));
                m.setOrden(rs.getInt("ORDEN"));
                m.setEstado(rs.getBoolean("ESTADO"));
                m.setFechaRegistro(rs.getObject("FECHA_REGISTRO", LocalDateTime.class));
                lista.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Integer getOrden() {
        Integer orden = 0;
        String sql = "select ORDEN from modulo  order by ID desc limit 1";
        try (Connection con = Conexion.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Modulo m = new Modulo();
                orden = rs.getInt("ORDEN");
                return orden;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orden;
    }


    public Modulo findById(long id) {
        String sql = "SELECT * FROM modulo WHERE ID = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Modulo m = new Modulo();
                    m.setId(rs.getLong("ID"));
                    m.setCodigo(rs.getString("CODIGO"));
                    m.setNombre(rs.getString("NOMBRE"));
                    m.setDescripcion(rs.getString("DESCRIPCION"));
                    m.setOrden(rs.getInt("ORDEN"));
                    m.setEstado(rs.getBoolean("ESTADO"));
                    m.setFechaRegistro(rs.getObject("FECHA_REGISTRO", LocalDateTime.class));
                    return m;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Modulo m) {
        String sql = "UPDATE modulo SET CODIGO=?, NOMBRE=?, DESCRIPCION=? WHERE ID=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getCodigo());
            ps.setString(2, m.getNombre());
            ps.setString(3, m.getDescripcion());
            ps.setLong(4, m.getId());
            ps.executeUpdate();
            return Boolean.TRUE;
        } catch (SQLException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }
    public Boolean delete(Long id) {
        String sql = "DELETE FROM modulo WHERE ID=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();return Boolean.TRUE;
        } catch (SQLException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }


}
