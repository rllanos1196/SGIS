package procesos;

import modelos.Modulo;

import java.util.List;

public interface ModuloService {
    boolean insert(Modulo modulo);
    boolean update(Modulo modulo);
    boolean delete(Long id);
    Modulo findById(Long id);
    List<Modulo> getAll();
}
