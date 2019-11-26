package micro.servicio.clienteweb.entidades.notificaciones;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Notificacion {
    private int id;
    private String emisor;
    private String receptor;
    private String asunto;
    private String cuerpo;
    private int userId;

    public Notificacion() {
    }

    public Notificacion(int id, String emisor, String receptor, String asunto, String cuerpo) {
        this.id = id;
        this.emisor = emisor;
        this.receptor = receptor;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
    }

    public Notificacion(int id, String emisor, String receptor, String asunto, String cuerpo, int userId) {
        this.id = id;
        this.emisor = emisor;
        this.receptor = receptor;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
