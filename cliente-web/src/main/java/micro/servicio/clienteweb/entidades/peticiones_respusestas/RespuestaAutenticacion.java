package micro.servicio.clienteweb.entidades.peticiones_respusestas;

public class RespuestaAutenticacion {
    private String jwt;

    public RespuestaAutenticacion() {
    }

    public RespuestaAutenticacion(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
