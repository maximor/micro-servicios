package micro.servicio.clienteweb.controladores;

import micro.servicio.clienteweb.entidades.usuarios.Usuario;
import micro.servicio.clienteweb.utilidades.RestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(method = RequestMethod.POST, value = "/registro")
    public String registro(Usuario usuario, Model modelo) {

        try{
            Usuario usuario1 = RestUtil.getInstance().getUsuario(usuario.getUsername());
            modelo.addAttribute("mensajeError", "Error, Nombre de usuario ["+usuario1.getUsername()+"] ya existe");
            return "registro";
        }catch (Exception e){
            try {
                RestUtil.getInstance().crearUsuarioCliente(usuario);
                modelo.addAttribute("mensajeExitoso", "Guardado exitosamente");
                return "registro";

            } catch (Exception ee) {
                ee.getStackTrace();
            }
        }
        return "registro";
    }
}
