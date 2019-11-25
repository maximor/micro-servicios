package micro.servicio.clienteweb.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class LoginControlador {
    @RequestMapping("/")
    public String index(Model modelo){

        return "index";
    }

    @GetMapping("/login")
    public String login(Principal principal, Model modelo){
        if(principal != null)
            return "index";
        return "login";
    }
}
