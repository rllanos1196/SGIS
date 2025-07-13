package procesos;

import dao.PersonaDAO;
import modelos.Persona;
import modelos.Sistema;
import utilerias.Messages;
import utilerias.Response;

import java.util.ArrayList;
import java.util.List;

public class PersonaServiceImpl implements PersonaService{
    PersonaDAO personaDAO = new PersonaDAO();
    @Override
    public Response<Boolean> registrarSistema(Persona persona) {
        // Implementación del método para registrar una persona
        return null; // Retornar una respuesta adecuada
    }

    @Override
    public Response<Persona> findById(Long id) {
        // Implementación del método para encontrar una persona por ID
        return null; // Retornar una respuesta adecuada
    }

    @Override
    public Response<Boolean> eliminarPersona(Long id) {
        // Implementación del método para eliminar una persona por ID
        return null; // Retornar una respuesta adecuada
    }

    @Override
    public Response<List<Persona>> listarPersonas() {
        List<Persona> personas = new ArrayList<>();
        try {
            personas = personaDAO.listarPersonas();
            if (personas != null && !personas.isEmpty()) {
                return new Response<>(Messages.BUSCAR_REGISTTRO, true, personas);
            } else {
                return new Response<>(Messages.ERROR_BUSCAR_REGISTRO, false, personas);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(Messages.ERROR_PROCESO + ": ", false, personas);
        }
    }
}
