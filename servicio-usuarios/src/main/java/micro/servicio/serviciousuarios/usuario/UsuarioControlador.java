package micro.servicio.serviciousuarios.usuario;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioControlador {
    @RequestMapping("/")
    public String casa(){
        return "Bienvenido a usuario";
    }
}
