package dao;

import modelos.Modulo;
import modelos.Persona;
import utilerias.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {

    public boolean insert(Persona per ) {
        String sql = "INSERT INTO modulo(NOMBRE,APELLIDO_PATERNO, APELLIDO_MATERNO,NRO_DOCUMENTO,FECHA_NACIMIENTO,SEXO,ESTADO,FECHA_REGISTRO,ID_USUARIO_REGISTRO) VALUES (?, ?, ?, ?, ?, ?, ?,?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, per.getNombre());
            ps.setString(2, per.getApePaterno());
            ps.setString(3, per.getApeMaterno());
            ps.setString(4, per.getNroDocumento());
            ps.setObject(5, per.getFechaNacimiento());
            ps.setBoolean(6, per.getSexo());
            ps.setBoolean(7, per.getEstado());
            ps.setObject(8, per.getFechaRegistro());
            ps.setLong(9, per.getIdUsuarioRegistro());
            ps.executeUpdate();
            return Boolean.TRUE;
        } catch (SQLException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }
    public boolean update(Persona per) {
        String sql = "UPDATE modulo SET NOMBRE = ?, APELLIDO_PATERNO = ?, APELLIDO_MATERNO = ?, NRO_DOCUMENTO = ?, FECHA_NACIMIENTO = ?, SEXO = ?, ESTADO = ? WHERE ID = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, per.getNombre());
            ps.setString(2, per.getApePaterno());
            ps.setString(3, per.getApeMaterno());
            ps.setString(4, per.getNroDocumento());
            ps.setObject(5, per.getFechaNacimiento());
            ps.setBoolean(6, per.getSexo());
            ps.setBoolean(7, per.getEstado());
            ps.setLong(8, per.getId());
            ps.executeUpdate();
            return Boolean.TRUE;
        } catch (SQLException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }
    public boolean delete(long id) {
        String sql = "UPDATE persona SET ESTADO=? WHERE ID=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, Boolean.FALSE);
            ps.setLong(2, id);
            ps.executeUpdate();return Boolean.TRUE;
        } catch (SQLException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }
    public Persona findById(long id) {
        String sql = "SELECT * FROM persona WHERE ID = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Persona p = new Persona();
                    p.setId(rs.getLong("ID"));
                    p.setNombre(rs.getString("NOMBRE"));
                    p.setApePaterno(rs.getString("APELLIDO_PATERNO"));
                    p.setApeMaterno(rs.getString("APELLIDO_MATERNO"));
                    p.setNroDocumento(rs.getString("NRO_DOCUMENTO"));
                    p.setFechaNacimiento(rs.getObject("FECHA_NACIMIENTO", LocalDateTime.class));
                    p.setSexo(rs.getBoolean("SEXO"));
                    p.setEstado(rs.getBoolean("ESTADO"));
                    return p;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
    public List<Persona> listarPersonas() {
        List<Persona> lista = new ArrayList<>();
        String sql = "SELECT * FROM persona WHERE ESTADO = TRUE";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Persona p = new Persona();
                p.setId(rs.getLong("ID"));
                p.setNombre(rs.getString("NOMBRE"));
                p.setApePaterno(rs.getString("APELLIDO_PATERNO"));
                p.setApeMaterno(rs.getString("APELLIDO_MATERNO"));
                p.setNroDocumento(rs.getString("NRO_DOCUMENTO"));
                p.setFechaNacimiento(rs.getObject("FECHA_NACIMIENTO", LocalDateTime.class));
                p.setSexo(rs.getBoolean("SEXO"));
                p.setEstado(rs.getBoolean("ESTADO"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

}
