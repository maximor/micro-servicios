package micro.servicio.servicioproductos.orden;

import micro.servicio.servicioproductos.plan.Plan;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;

@Entity
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @CreatedDate
    @CreationTimestamp
    private Date fechaCreacion;
    @LastModifiedDate
    @UpdateTimestamp
    private Date fechaActualizacion;
    @Email
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private float montoTotal;
    private boolean abierta = true;
    private boolean activa = true;
    private int userId;

    @OneToMany
    private List<Plan> planes;

    public Orden() {
    }

    public Orden(@Email String email, float montoTotal, int userId) {
        this.email = email;
        this.montoTotal = montoTotal;
        this.userId = userId;
    }

    public Orden(@Email String email, float montoTotal, int userId, List<Plan> planes) {
        this.email = email;
        this.montoTotal = montoTotal;
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

    public long getUserId() {
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

    public void calcularMontoTotal() throws Exception {
        if(planes.size() > 0){
            montoTotal = 0.0f;
            for(int i = 0; i < planes.size(); i++){
                montoTotal += planes.get(i).getMonto();
            }
        }else{
            throw new Exception("Error, No puedes calcular el monto sin ordenes");
        }
    }
}
