package procesos;

import modelos.Persona;
import modelos.Sistema;
import utilerias.Response;

import java.util.List;

public interface PersonaService {
    Response<Boolean> registrarSistema(Persona persona);
    Response<Persona> findById(Long id);
    Response<Boolean> eliminarPersona(Long id);
    Response<List<Persona>> listarPersonas();
}
