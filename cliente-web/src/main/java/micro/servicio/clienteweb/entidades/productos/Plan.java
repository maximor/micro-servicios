package micro.servicio.clienteweb.entidades.productos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Plan {
    private int id;
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private String nombre;
    private float monto;
    private String descripcion;
    private boolean activo;
    private int userId;

    public Plan() {
    }

    public Plan(Date fechaCreacion, Date fechaActualizacion, String nombre, float monto, String descripcion, boolean activo, int userId) {
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.nombre = nombre;
        this.monto = monto;
        this.descripcion = descripcion;
        this.activo = activo;
        this.userId = userId;
    }

    public Plan(int id, Date fechaCreacion, Date fechaActualizacion, String nombre, float monto, String descripcion, boolean activo, int userId) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.nombre = nombre;
        this.monto = monto;
        this.descripcion = descripcion;
        this.activo = activo;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
