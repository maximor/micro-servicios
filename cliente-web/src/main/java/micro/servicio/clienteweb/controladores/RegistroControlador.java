package micro.servicio.clienteweb.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class RegistroControlador {

    @RequestMapping("/registro")
    public String registro(Principal principal, Model modelo){
        if(principal != null){
            return "index";
        }
        return "registro";
    }
}
