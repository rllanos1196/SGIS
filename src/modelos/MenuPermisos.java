package modelos;

import java.time.LocalDateTime;

public class MenuPermisos {
    private Long id;
    private Menu menu;
    private Rol rol;
    private Boolean lectura;
    private Boolean creacion;
    private Boolean edicion;
    private Boolean eliminacion;
    private Boolean impresion;
    private LocalDateTime fechaRegistro;
    private Long idUsuarioRegistro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Boolean getLectura() {
        return lectura;
    }

    public void setLectura(Boolean lectura) {
        this.lectura = lectura;
    }

    public Boolean getCreacion() {
        return creacion;
    }

    public void setCreacion(Boolean creacion) {
        this.creacion = creacion;
    }

    public Boolean getEdicion() {
        return edicion;
    }

    public void setEdicion(Boolean edicion) {
        this.edicion = edicion;
    }

    public Boolean getEliminacion() {
        return eliminacion;
    }

    public void setEliminacion(Boolean eliminacion) {
        this.eliminacion = eliminacion;
    }

    public Boolean getImpresion() {
        return impresion;
    }

    public void setImpresion(Boolean impresion) {
        this.impresion = impresion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdUsuarioRegistro() {
        return idUsuarioRegistro;
    }

    public void setIdUsuarioRegistro(Long idUsuarioRegistro) {
        this.idUsuarioRegistro = idUsuarioRegistro;
    }
}
