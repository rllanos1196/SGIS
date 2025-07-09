package procesos;

import dao.ModuloDAO;
import modelos.Modulo;

import java.util.List;

public class ModuloServiceImpl implements ModuloService{
    ModuloDAO modDAO = new ModuloDAO();

    @Override
    public boolean insert(Modulo modulo) {
        Boolean res = modDAO.insert(modulo);
        if (res) {
            System.out.println("Módulo registrado correctamente.");
        } else {
            System.out.println("Error al registrar el módulo.");
        }
        return res;
    }

    @Override
    public boolean update(Modulo modulo) {
        Boolean res = modDAO.update(modulo);
        if (res) {
            System.out.println("Módulo actualizado correctamente.");
        } else {
            System.out.println("Error al actualizar el módulo.");
        }
        return res;
    }

    @Override
    public boolean delete(Long id) {
        Boolean res = modDAO.delete(id);
        if (res) {
            System.out.println("Módulo eliminado correctamente.");
        } else {
            System.out.println("Error al eliminar el módulo.");
        }
        return res;
    }

    @Override
    public Modulo findById(Long id) {
        Modulo modulo = modDAO.findById(id);
        if (modulo != null) {
            System.out.println("Módulo encontrado: " + modulo);
        } else {
            System.out.println("Módulo no encontrado.");
        }
        return modulo;
    }

    @Override
    public List<Modulo> getAll() {
        List<Modulo> modulos = modDAO.getAll();
        if (modulos != null && !modulos.isEmpty()) {
            System.out.println("Módulos encontrados: " + modulos);
        } else {
            System.out.println("No se encontraron módulos.");
        }
        return modulos;
    }
}
