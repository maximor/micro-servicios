package micro.servicio.clienteweb.controladores;

import micro.servicio.clienteweb.entidades.usuarios.Usuario;
import micro.servicio.clienteweb.utilidades.RestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UsuarioControlador {

    @RequestMapping("/usuarios")
    private String usuarios(Model modelo){
        return "administrador/manejo-usuarios";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/usuarios")
    private String usuarios(Usuario usuario, Model modelo){
        try{
            Usuario usuario1 = RestUtil.getInstance().getUsuario(usuario.getUsername());
            modelo.addAttribute("mensajeError", "Error, el Usuario ["+usuario1.getUsername()+"] ya existe");
            return "administrador/manejo-usuarios";
        }catch(Exception e){
            System.out.println("Entro aqui: ");
            try{
                RestUtil.getInstance().crearUsuarioEmpleado(usuario);
                modelo.addAttribute("mensajeExitoso", "Empleado Creado Exitosamente");
                return "administrador/manejo-usuarios";
            }catch (Exception ee){
                ee.getStackTrace();
            }
        }
        return "administrador/manejo-usuarios";
    }
}
