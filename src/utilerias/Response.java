package utilerias;
import java.util.List;

public class Response<T> {
    private String mensaje;
    private Boolean estado;
    private T data;

    public Response() {}

    public Response(String mensaje, boolean estado, T data) {
        this.mensaje = mensaje;
        this.estado = estado;
        this.data = data;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}