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

    private String productoToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiJ9.nRar0a9irX7g_8UMj5A7zxZ0OX0e8OBJhGT6rZZtDxw";

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

    //SERVICIO-PRODUCTOS
    public List<Plan> getPlanes(){
        String url = host+"servicio-productos/planes";

        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+productoToken);
        HttpEntity<Usuario> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<Plan[]> resultado = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Plan[].class);
        return Arrays.asList(resultado.getBody());
    }

    public Plan getPlanPorNombre(String nombre){
        String url = host+"servicio-productos/plan/"+nombre;

        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+productoToken);
        HttpEntity<Usuario> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<Plan> resultado = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Plan.class);
        return resultado.getBody();
    }

    public ResponseEntity crearOrden(Orden orden){
        String url = host+"servicio-productos/orden";

        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+productoToken);
        HttpEntity<Orden> requestEntity = new HttpEntity<>(orden, headers);
        ResponseEntity<Orden> resultado = restTemplate.postForEntity(url, requestEntity, Orden.class);
        return resultado;
    }

    public List<Orden> getOrdenesAbiertasPorUsuarios(int userId){
        String url = host+"servicio-productos/ordenes/abiertas/"+userId;

        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+productoToken);
        HttpEntity<Usuario> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<Orden[]> resultado = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Orden[].class);
        return Arrays.asList(resultado.getBody());
    }

    public List<Orden> getOrdenesCerradasPorUsuarios(int userId){
        String url = host+"servicio-productos/ordenes/cerradas/" + userId;

        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+productoToken);
        HttpEntity<Usuario> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<Orden[]> resultado = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Orden[].class);
        return Arrays.asList(resultado.getBody());
    }

    public List<Orden> getOrdenesAbiertas(){
        String url = host+"servicio-productos/ordenes/abiertas";

        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+productoToken);
        HttpEntity<Usuario> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<Orden[]> resultado = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Orden[].class);
        return Arrays.asList(resultado.getBody());
    }

    public List<Orden> getOrdenesCerradas(){
        String url = host+"servicio-productos/ordenes/cerradas";

        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+productoToken);
        HttpEntity<Usuario> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<Orden[]> resultado = restTemplate.exchange(url, HttpMethod.GET, requestEntity,  Orden[].class);
        return Arrays.asList(resultado.getBody());
    }

    public Orden getOrdenPorId(int id){
        String url = host+"servicio-productos/orden/abierta/"+id;

        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+productoToken);
        HttpEntity<Usuario> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<Orden> resultado = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Orden.class);
        return resultado.getBody();
    }

    public void cerrarOrden(int id){
        String url = host+"servicio-productos/orden/cerrar/"+id;
        Orden orden = getOrdenPorId(id);

        headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer "+productoToken);
        HttpEntity<Orden> requestEntity = new HttpEntity<>(orden, headers);
        ResponseEntity<String> resultado = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
    }

    public Estadisticas getOrdenEstadisticas(){
        String url = host+"servicio-productos/estadistica";

        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+productoToken);
        HttpEntity<Usuario> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<Estadisticas> resultado = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Estadisticas.class);
        return resultado.getBody();
    }



    //SERVICIO-NOTIFICACIONES
    public Notificacion crearCorreo(Notificacion notificacion){
        String url = host+"servicio-notificaciones/notificacion";

        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + notificacionToken);
        HttpEntity<Notificacion> requestEntity = new HttpEntity<>(notificacion, headers);
        ResponseEntity<Notificacion> resultado = restTemplate.postForEntity(url, requestEntity, Notificacion.class);
        return resultado.getBody();
    }

}
