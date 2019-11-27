package micro.servicio.clienteweb.utilidades;

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

    private String notificacionToken;

    private RestUtil(){ }

    public static RestUtil getInstance(){
        if(restUtil == null){
            restUtil = new RestUtil();
            restTemplate = new RestTemplate();
        }

        return restUtil;
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

    //TODO AGREGAR MANEJO DE TOKEN
    //servicio-productos
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

}
