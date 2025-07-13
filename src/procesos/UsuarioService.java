package procesos;

import modelos.Usuario;
import utilerias.Response;
import java.util.List;

public interface UsuarioService {
    Response<Boolean> registrarUsuario(Usuario usuario);
    Response<Usuario> findById(Long id);
    Response<Boolean> eliminarUsuario(Long id);
    Response<Usuario> autenticarUsuario(String user, String password);
    Response<Boolean> cambiarPassword(Usuario usuario);
    Response<Boolean> recuperarPassword(Usuario usuario);
    Response<Usuario> verificarUsuario(String user);
    Response<List<Usuario>> listarUsuarios();

}
