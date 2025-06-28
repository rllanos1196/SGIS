package Modelos.Seguridad;


import java.util.Date;

public class Menu {


    private Long id;

    private String titulo;

    private String url;

    private String observacion;

    private Boolean estado;

    private Long orden;

    private Boolean esSuperior;

    private Long menuSuperior;

    private Date fechaRegistro;

    private Long userRegistro;

    private Modulo modulo;

    private String labelEstado;

    private String classEstado;

    private String observacionCorta;

    public Menu() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Long getOrden() {
        return orden;
    }

    public void setOrden(Long orden) {
        this.orden = orden;
    }

    public Boolean getEsSuperior() {
        return esSuperior;
    }

    public void setEsSuperior(Boolean esSuperior) {
        this.esSuperior = esSuperior;
    }

    public Long getMenuSuperior() {
        return menuSuperior;
    }

    public void setMenuSuperior(Long menuSuperior) {
        this.menuSuperior = menuSuperior;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getUserRegistro() {
        return userRegistro;
    }

    public void setUserRegistro(Long userRegistro) {
        this.userRegistro = userRegistro;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public String getLabelEstado() {
        return labelEstado;
    }

    public void setLabelEstado(String labelEstado) {
        this.labelEstado = labelEstado;
    }

    public String getClassEstado() {
        return classEstado;
    }

    public void setClassEstado(String classEstado) {
        this.classEstado = classEstado;
    }

    public String getObservacionCorta() {
        return observacionCorta;
    }

    public void setObservacionCorta(String observacionCorta) {
        this.observacionCorta = observacionCorta;
    }
}
