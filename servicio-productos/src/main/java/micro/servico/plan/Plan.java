package micro.servicio.servicioproductos.plan;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @CreatedDate
    @CreationTimestamp
    private Date fechaCreacion;
    @LastModifiedDate
    @UpdateTimestamp
    private Date fechaActualizacion;
    @Column(nullable = false, unique = true)
    private String nombre;
    @Column(nullable = false)
    private float monto;
    private String descripcion;
    private boolean activo = true;
    private int userId;

    public Plan() {
    }

    public Plan(int id){
        this.id = id;
    }

    public Plan(String nombre, float monto, String descripcion, boolean activo) {
        this.nombre = nombre;
        this.monto = monto;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public Plan(String nombre, float monto, String descripcion, boolean activo, int userId) {
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
