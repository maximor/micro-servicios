package micro.servicio.clienteweb.entidades.productos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Orden {
    private int id;
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private String email;
    private float montoTotal;
    private boolean abierta;
    private boolean activa;
    private int userId;

    private List<Plan> planes;

    public Orden() {
    }

    public Orden(Date fechaCreacion, Date fechaActualizacion, String email, float montoTotal, boolean abierta, boolean activa, int userId, List<Plan> planes) {
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.email = email;
        this.montoTotal = montoTotal;
        this.abierta = abierta;
        this.activa = activa;
        this.userId = userId;
        this.planes = planes;
    }

    public Orden(int id, Date fechaCreacion, Date fechaActualizacion, String email, float montoTotal, boolean abierta, boolean activa, int userId, List<Plan> planes) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.email = email;
        this.montoTotal = montoTotal;
        this.abierta = abierta;
        this.activa = activa;
        this.userId = userId;
        this.planes = planes;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(float montoTotal) {
        this.montoTotal = montoTotal;
    }

    public boolean isAbierta() {
        return abierta;
    }

    public void setAbierta(boolean abierta) {
        this.abierta = abierta;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Plan> getPlanes() {
        return planes;
    }

    public void setPlanes(List<Plan> planes) {
        this.planes = planes;
    }
}
