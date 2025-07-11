package procesos;

import modelos.Modulo;

import java.util.List;

public interface ModuloService {
    boolean insertModulo(Modulo modulo);
    boolean updateModulo(Modulo modulo);
    boolean deleteModulo(Long id);
    Modulo findById(Long id);
    List<Modulo> getAll();
}
