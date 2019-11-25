package micro.servicio.clienteweb.servicios;

import micro.servicio.clienteweb.entidades.usuarios.Usuario;
import micro.servicio.clienteweb.entidades.usuarios.UsuarioDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UsuarioDetailsServices implements UserDetailsService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${servicio-usuarios.token}")
    private String usuarioToken;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String url = "http://localhost:8080/servicio-usuarios/usuario/"+username;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+usuarioToken);
        HttpEntity<Usuario> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<Usuario> result = restTemplate.postForEntity(url, requestEntity, Usuario.class);

        System.out.println("USUARIO: "+result.getBody().getUsername());
        if(result.getBody() == null){
            throw new UsernameNotFoundException("Usuario No encontrado: " + username);
        }

        return new UsuarioDetails(result.getBody());
    }
}
