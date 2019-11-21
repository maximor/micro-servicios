package micro.servicionotificaciones.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String emisor;
    private String receptor;
    private String asunto;
    private String cuerpo;
    private int userId;

    public Notificacion() {
    }

    public Notificacion(String emisor, String receptor, String asunto, String cuerpo) {
        this.emisor = emisor;
        this.receptor = receptor;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
    }

    public Notificacion(String emisor, String receptor, String asunto, String cuerpo, int userId) {
        this.emisor = emisor;
        this.receptor = receptor;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.userId = userId;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
