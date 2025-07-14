package procesos;

import dao.UsuarioDAO;
import modelos.Usuario;
import utilerias.ArchivoHelper;
import utilerias.Messages;
import utilerias.Response;

import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {
    UsuarioDAO userDAO = new UsuarioDAO();

    // Implementación de los métodos de UsuarioService
    @Override
    public Response<Boolean> registrarUsuario(Usuario usuario) {
        // Lógica para registrar un usuario
        Boolean res = Boolean.FALSE;
        try {
            if (usuario.getId() == null) {
                usuario.setPassword(ArchivoHelper.encriptarMD5(usuario.getPassword()));
                usuario.setEstado(Boolean.TRUE);
                usuario.setFechaIngreso(java.time.LocalDateTime.now());
                usuario.setIdUsuarioRegistro(1L);
                res = userDAO.registrarUsuario(usuario);
                return new Response<>(Messages.CREATED, true, res);
            } else {
                Usuario us = userDAO.findById(usuario.getId());
                us.setIdCompania(usuario.getIdCompania());
                us.setIdDependencia(usuario.getIdDependencia());
                us.setIdCargo(usuario.getIdCargo());
                us.setIdPersona(usuario.getIdPersona());
                us.setSistema(usuario.getSistema());
                us.setNombre(usuario.getNombre());
                us.setIdUsuarioRegistro(1L);
                res = userDAO.actualizarUsuario(us);
                return new Response<>(Messages.UPDATED, false, res);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(Messages.ERROR_PROCESO + ": ", false, res);
        }

    }

    @Override
    public Response<Usuario> findById(Long id) {
        // Lógica para encontrar un usuario por ID
        Usuario usuario = userDAO.findById(id);
        if (usuario != null) {
            return new Response<>(Messages.BUSCAR_REGISTTRO, true, usuario);
        } else {
            return new Response<>(Messages.ERROR_BUSCAR_REGISTRO, false, null);
        }
    }

    @Override
    public Response<Boolean> eliminarUsuario(Long id) {
        // Lógica para eliminar un usuario
        try {
            Boolean res = userDAO.eliminarUsuario(id, Boolean.FALSE);
            if (res) {
                return new Response<>(Messages.DELETED, true, res);
            } else {
                return new Response<>(Messages.ERROR_DELETED, false, res);
            }
        } catch (Exception e) {
            e.printStackTrace();//imprimir en logs
            return new Response<>(Messages.ERROR_PROCESO + ": ", false, Boolean.FALSE);
        }
    }

    @Override
    public Response<Usuario> autenticarUsuario(String user, String password) {
        // Lógica para autenticar un usuario
        password = ArchivoHelper.encriptarMD5(password);
        try {
            Usuario usuario = userDAO.autenticarUsuario(user, password);
            if (usuario != null) {
                return new Response<>(Messages.ACCESS_CORRECT, true, usuario);
            } else {
                return new Response<>(Messages.USER_NOT_FOUND, false, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(Messages.ERROR_PROCESO + ": ", false, null);
        }
    }

    @Override
    public Response<Boolean> cambiarPassword(Usuario usuario) {
        // Lógica para cambiar la contraseña de un usuario
        try {
            Boolean res = userDAO.cambiarPassword(usuario.getId(), usuario.getPassword());
            if (res) {
                return new Response<>(Messages.UPDATED, true, res);
            } else {
                return new Response<>(Messages.PASSWORD_INCORRECT, false, res);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(Messages.ERROR_PROCESO + ": ", false, Boolean.FALSE);
        }
    }

    @Override
    public Response<Boolean> recuperarPassword(Usuario usuario) {
        // Lógica para recuperar la contraseña de un usuario
        try {
            usuario.setPassword(ArchivoHelper.encriptarMD5(usuario.getPassword()));
            Boolean res = userDAO.recuperarPassword(usuario.getId(), usuario.getPassword());
            if (res) {
                return new Response<>(Messages.PASSWORD_RESET, true, res);
            } else {
                return new Response<>(Messages.ERROR_PROCESO + ": ", false, res);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(Messages.ERROR_PROCESO + ": ", false, Boolean.FALSE);
        }
    }

    @Override
    public Response<Usuario> verificarUsuario(String user) {
        // Lógica para verificar si un usuario existe
        try {
            Usuario usuario = userDAO.verificarUsuario(user);
            if (usuario != null) {
                return new Response<>(Messages.USER_EXIST, true, usuario);
            } else {
                return new Response<>(Messages.USER_NOT_FOUND, false, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(Messages.ERROR_PROCESO + ": ", false, null);
        }
    }

    @Override
    public Response<List<Usuario>> listarUsuarios() {
        // Lógica para listar todos los usuarios
        try {
            List<Usuario> usuarios = userDAO.listarUsuarios();
            if (usuarios != null && !usuarios.isEmpty()) {
                return new Response<>(Messages.BUSCAR_REGISTTRO, true, usuarios);
            } else {
                return new Response<>(Messages.ERROR_BUSCAR_REGISTRO, false, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(Messages.ERROR_PROCESO + ": ", false, null);
        }
    }

}
