package modelos;


import java.time.LocalDateTime;
import java.util.Date;

public class Menu {

    private Long id;
    private Modulo modulo; // Enlace directo al objeto Modulo
    private Menu menuSuperior; // Opcional: para representar jerarquía
    private String titulo;
    private String url;
    private String observacion;
    private Integer orden;
    private Boolean esSuperior;
    private Integer estado;
    private LocalDateTime fechaRegistro;
    private Long idUsuarioRegistro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public Menu getMenuSuperior() {
        return menuSuperior;
    }

    public void setMenuSuperior(Menu menuSuperior) {
        this.menuSuperior = menuSuperior;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Boolean getEsSuperior() {
        return esSuperior;
    }

    public void setEsSuperior(Boolean esSuperior) {
        this.esSuperior = esSuperior;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
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
