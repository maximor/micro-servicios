package micro.servicionotificaciones.entidades;

public class Notificacion {
    private String emisor;
    private String receptor;
    private String asunto;
    private String cuerpo;

    public Notificacion() { }

    public Notificacion(String emisor, String receptor, String asunto, String cuerpo) {
        this.emisor = emisor;
        this.receptor = receptor;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
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
}
