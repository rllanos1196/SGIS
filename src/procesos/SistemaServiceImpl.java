package procesos;

import dao.SistemaDAO;
import modelos.Sistema;
import utilerias.Messages;
import utilerias.Response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SistemaServiceImpl implements SistemaService{

    SistemaDAO sistemaDAO = new SistemaDAO();

    @Override
    public Response<Boolean> registrarSistema(Sistema sistema) {
        Boolean res=Boolean.FALSE;
        try {
            if(sistema.getId()== null){
                res = sistemaDAO.registrarSistema(sistema);
                return new Response<>(Messages.CREATED, true, res);
            }else {
                res = sistemaDAO.actualizarSistema(sistema);
                return new Response<>(Messages.UPDATED, false, res);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(Messages.ERROR_PROCESO + ": ", false, res);
        }
    }

    @Override
    public Response<Sistema> findById(Long id) {
        try {
            Sistema sistema = sistemaDAO.findById(id);
            if (sistema != null) {
                return new Response<>(Messages.BUSCAR_REGISTTRO, true, sistema);
            } else {
                return new Response<>(Messages.ERROR_BUSCAR_REGISTRO, false, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(Messages.ERROR_PROCESO + ": ", false, null);
        }
    }

    @Override
    public Response<Boolean> eliminarSistema(Long id) {
        try {
            Boolean res = sistemaDAO.eliminarSistema(id);
            if (res) {
                return new Response<>(Messages.DELETED, true, res);
            } else {
                return new Response<>(Messages.ERROR_DELETED, false, res);
            }
        } catch (Exception e) {
            e.printStackTrace(); // imprimir en logs
            return new Response<>(Messages.ERROR_PROCESO + ": ", false, Boolean.FALSE);
        }
    }

    @Override
    public Response<List<Sistema>> listarSistemas() {
        List<Sistema> sistemas = new ArrayList<>();
        try {
            sistemas = sistemaDAO.getAll();
            if (sistemas != null && !sistemas.isEmpty()) {
                return new Response<>(Messages.BUSCAR_REGISTTRO, true, sistemas);
            } else {
                return new Response<>(Messages.ERROR_BUSCAR_REGISTRO, false, sistemas);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(Messages.ERROR_PROCESO + ": ", false, sistemas);
        }
    }

}
