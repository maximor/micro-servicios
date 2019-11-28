package micro.servicio.clienteweb.utilidades;

import micro.servicio.clienteweb.entidades.notificaciones.Notificacion;
import micro.servicio.clienteweb.entidades.productos.Estadisticas;
import micro.servicio.clienteweb.entidades.productos.Orden;
import micro.servicio.clienteweb.entidades.productos.Plan;
import micro.servicio.clienteweb.entidades.usuarios.CambiarContrasena;
import micro.servicio.clienteweb.entidades.usuarios.Usuario;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class RestUtil {

    private static RestUtil restUtil;
    private static RestTemplate restTemplate;
    private HttpHeaders headers;
    private String host = "http://localhost:8080/";

    private String usuarioToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiJ9.a3Eut26boxzlWFPn7l7JOWDcJAN_o024HJpWMjtjjt4";

    private String productoToken;

    private String notificacionToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiJ9.u3ptgDCEC_kTry8VWMRW2ooZjqzcZEmNA4MRyXDn6FU";

    //Este es el correro que skarkPost utiliza para enviar los correos
    private String notificacionEmisor = "20102172@maximorodriguez.me";

    private RestUtil(){ }

    public static RestUtil getInstance(){
        if(restUtil == null){
            restUtil = new RestUtil();
            restTemplate = new RestTemplate();
        }

        return restUtil;
    }

    public String getNotificacionEmisor() {
        return notificacionEmisor;
    }

    public void setNotificacionEmisor(String notificacionEmisor) {
        this.notificacionEmisor = notificacionEmisor;
    }

    public Usuario getUsuario(String username){
        String url = host+"servicio-usuarios/usuario/"+username;

        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+usuarioToken);
        HttpEntity<Usuario> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<Usuario> result = restTemplate.postForEntity(url, requestEntity, Usuario.class);
        return result.getBody();
    }

    public void actualizarUsuario(Usuario usuario){
        String url = host+"servicio-usuarios/usuario/actualizar";

        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+usuarioToken);
        HttpEntity<Usuario> requestEntity = new HttpEntity<>(usuario, headers);

       ResponseEntity<Usuario> resultado = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Usuario.class);
       System.out.println(resultado.getBody());
    }

    public ResponseEntity cambiarContrasena(String username, CambiarContrasena cambiarContrasena){
        String url = host+"servicio-usuarios/usuario/actualizar/"+username+"/"
                +cambiarContrasena.getOldPassword()+"/"+cambiarContrasena.getNewPassword();

        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+usuarioToken);
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> resultado = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        return resultado;
    }

    public ResponseEntity crearUsuarioCliente(Usuario usuario){
        String url = host+"servicio-usuarios/usuario/crear/cliente";

        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + usuarioToken);
        HttpEntity<Usuario> requestEntity = new HttpEntity<>(usuario, headers);
        ResponseEntity<String> resultado = restTemplate.postForEntity(url, requestEntity, String.class);
        return resultado;
    }

    public ResponseEntity crearUsuarioEmpleado(Usuario usuario){
        String url = host+"servicio-usuarios/usuario/crear/empleado";

        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + usuarioToken);
        HttpEntity<Usuario> requestEntity = new HttpEntity<>(usuario, headers);
        ResponseEntity<String> resultado = restTemplate.postForEntity(url, requestEntity, String.class);
        return resultado;
    }

    public List<Usuario> getUsuarios(){
        String url = host+"servicio-usuarios/usuarios";

        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+usuarioToken);
        HttpEntity<Usuario> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<Usuario[]> resultado = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Usuario[].class);
        return Arrays.asList(resultado.getBody());

    }

    //TODO AGREGAR MANEJO DE TOKEN
    //SERVICIO-PRODUCTOS
    public List<Plan> getPlanes(){
        String url = host+"servicio-productos/planes";

        ResponseEntity<Plan[]> resultado = restTemplate.getForEntity(url, Plan[].class);
        return Arrays.asList(resultado.getBody());
    }

    public Plan getPlanPorNombre(String nombre){
        String url = host+"servicio-productos/plan/"+nombre;

        ResponseEntity<Plan> resultado = restTemplate.getForEntity(url, Plan.class);
        return resultado.getBody();
    }

    public ResponseEntity crearOrden(Orden orden){
        String url = host+"servicio-productos/orden";

        ResponseEntity<Orden> resultado = restTemplate.postForEntity(url, orden, Orden.class);
        return resultado;
    }

    public List<Orden> getOrdenesAbiertasPorUsuarios(int userId){
        String url = host+"servicio-productos/ordenes/abiertas/"+userId;

        ResponseEntity<Orden[]> resultado = restTemplate.getForEntity(url, Orden[].class);
        return Arrays.asList(resultado.getBody());
    }

    public List<Orden> getOrdenesCerradasPorUsuarios(int userId){
        String url = host+"servicio-productos/ordenes/cerradas/" + userId;

        ResponseEntity<Orden[]> resultado = restTemplate.getForEntity(url, Orden[].class);
        return Arrays.asList(resultado.getBody());
    }

    public List<Orden> getOrdenesAbiertas(){
        String url = host+"servicio-productos/ordenes/abiertas";

        ResponseEntity<Orden[]> resultado = restTemplate.getForEntity(url, Orden[].class);
        return Arrays.asList(resultado.getBody());
    }

    public List<Orden> getOrdenesCerradas(){
        String url = host+"servicio-productos/ordenes/cerradas";

        ResponseEntity<Orden[]> resultado = restTemplate.getForEntity(url, Orden[].class);
        return Arrays.asList(resultado.getBody());
    }

    public Orden getOrdenPorId(int id){
        String url = host+"servicio-productos/orden/abierta/"+id;

        ResponseEntity<Orden> resultado = restTemplate.getForEntity(url, Orden.class);
        return resultado.getBody();
    }

    public void cerrarOrden(int id){
        String url = host+"servicio-productos/orden/cerrar/"+id;
        Orden orden = getOrdenPorId(id);

        headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Orden> requestEntity = new HttpEntity<>(orden, headers);
        ResponseEntity<String> resultado = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
    }

    public Estadisticas getOrdenEstadisticas(){
        String url = host+"servicio-productos/estadistica";

        ResponseEntity<Estadisticas> resultado = restTemplate.getForEntity(url, Estadisticas.class);
        return resultado.getBody();
    }



    //SERVICIO-NOTIFICACIONES
    public Notificacion crearCorreo(Notificacion notificacion){
        String url = host+"servicio-notificaciones/notificacion";

        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + notificacionToken);
        HttpEntity<Notificacion> requestEntity = new HttpEntity<>(notificacion, headers);
        ResponseEntity<Notificacion> resultado = restTemplate.postForEntity(url, requestEntity, Notificacion.class);
        System.out.println(resultado.getBody().getEmisor());
        return resultado.getBody();
    }

}
