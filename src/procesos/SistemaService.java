package procesos;

import modelos.Sistema;
import utilerias.Response;

import java.util.List;

public interface SistemaService {
    Response<Boolean> registrarSistema(Sistema sistema);
    Response<Sistema> findById(Long id);
    Response<Boolean> eliminarSistema(Long id);
    Response<List<Sistema>> listarSistemas();

}
