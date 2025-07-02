package procesos;

import modelos.Sistema;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SistemaServiceImpl implements SistemaService{
    private static List<Sistema> listaSistemas = new ArrayList<>();

    public  boolean registrarSistema(Sistema sistema) {
        if (sistema.getCodigo() == null || sistema.getCodigo().isEmpty()) return false;
        sistema.setFechaRegistro(LocalDateTime.now());
        sistema.setEstado(1);
        listaSistemas.add(sistema);
        return true;
    }

}
