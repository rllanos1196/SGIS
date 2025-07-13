package modelos;

public class Cargo {
    private long id;
    private String nombre;

    public Cargo(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Cargo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
