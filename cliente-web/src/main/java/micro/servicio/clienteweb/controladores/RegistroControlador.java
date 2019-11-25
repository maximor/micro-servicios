package micro.servicio.clienteweb.controladores;

import micro.servicio.clienteweb.entidades.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

@Controller
public class RegistroControlador {
    @Value("${servicio-usuarios.token}")
    private String usuarioToken;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/registro")
    public String registro(Principal principal, Model modelo){
        if(principal != null){
            return "index";
        }
        return "registro";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/registro")
    public String registro(Usuario usuario, Model modelo) {
        String url = "http://localhost:8080/servicio-usuarios/usuario/crear/cliente";
        String urlUsuario = "http://localhost:8080/servicio-usuarios/usuario/"+usuario.getUsername();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + usuarioToken);

        try{
            //Antes verificar si el usuario existe
            HttpEntity<Usuario> requestEntityExistenciaUsuario = new HttpEntity<>(null, headers);
            ResponseEntity resultadoUsuario = restTemplate.exchange(urlUsuario, HttpMethod.GET, requestEntityExistenciaUsuario, String.class);
            System.out.println(resultadoUsuario.getBody());
            modelo.addAttribute("mensajeError", "Error, Nombre de usuario ["+usuario.getUsername()+"] ya existe");
            return "registro";
        }catch (Exception e){
            HttpEntity<Usuario> requestEntity = new HttpEntity<>(usuario, headers);
            try {
                ResponseEntity<String> resultado = restTemplate.postForEntity(url, requestEntity, String.class);

                if (resultado.getStatusCode() == HttpStatus.CREATED) {
                    modelo.addAttribute("mensajeExitoso", "Guardado exitosamente");
                    return "registro";
                }

            } catch (Exception ee) {
                ee.getStackTrace();
            }
        }
        return "registro";
    }
}
