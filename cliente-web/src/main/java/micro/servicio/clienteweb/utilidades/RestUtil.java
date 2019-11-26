package micro.servicio.clienteweb.utilidades;

import micro.servicio.clienteweb.entidades.usuarios.CambiarContrasena;
import micro.servicio.clienteweb.entidades.usuarios.Usuario;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestUtil {

    private static RestUtil restUtil;
    private RestTemplate restTemplate;
    private HttpHeaders headers;

    private String usuarioToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiJ9.a3Eut26boxzlWFPn7l7JOWDcJAN_o024HJpWMjtjjt4";

    private String productoToken;

    private String notificacionToken;

    private RestUtil(){ }

    public static RestUtil getInstance(){
        if(restUtil == null){
            restUtil = new RestUtil();
        }

        return restUtil;
    }

    public Usuario getUsuario(String username){
        String url = "http://localhost:8080/servicio-usuarios/usuario/"+username;

        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+usuarioToken);
        HttpEntity<Usuario> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<Usuario> result = restTemplate.postForEntity(url, requestEntity, Usuario.class);
        return result.getBody();
    }

    public void actualizarUsuario(Usuario usuario){
        String url = "http://localhost:8080/servicio-usuarios/usuario/actualizar";

        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+usuarioToken);
        HttpEntity<Usuario> requestEntity = new HttpEntity<>(usuario, headers);

       ResponseEntity<Usuario> resultado = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Usuario.class);
       System.out.println(resultado.getBody());
    }

    public ResponseEntity cambiarContrasena(String username, CambiarContrasena cambiarContrasena){
        String url = "http://localhost:8080/servicio-usuarios/usuario/actualizar/"+username+"/"
                +cambiarContrasena.getOldPassword()+"/"+cambiarContrasena.getNewPassword();

        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+usuarioToken);
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> resultado = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        return resultado;
    }

    public ResponseEntity crearUsuarioCliente(Usuario usuario){
        String url = "http://localhost:8080/servicio-usuarios/usuario/crear/cliente";

        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + usuarioToken);
        HttpEntity<Usuario> requestEntity = new HttpEntity<>(usuario, headers);
        ResponseEntity<String> resultado = restTemplate.postForEntity(url, requestEntity, String.class);
        return resultado;
    }

}
