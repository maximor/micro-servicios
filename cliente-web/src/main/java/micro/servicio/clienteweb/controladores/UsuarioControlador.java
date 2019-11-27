package micro.servicio.clienteweb.controladores;

import micro.servicio.clienteweb.entidades.usuarios.Usuario;
import micro.servicio.clienteweb.utilidades.RestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/ver-usuarios")
    public String verUsuarios(Model modelo){
        modelo.addAttribute("usuarios", RestUtil.getInstance().getUsuarios());
        return "administrador/ver-usuario";
    }

    @RequestMapping("/ver-usuarios/{username}/{codigo}")
    public String activarODesactivarUsuarios(@PathVariable String username, @PathVariable int codigo){
        Usuario usuario = RestUtil.getInstance().getUsuario(username);
        if(codigo == 0){
            usuario.setActivo(false);
        }else{
            usuario.setActivo(true);
        }
        RestUtil.getInstance().actualizarUsuario(usuario);
        return "redirect:/ver-usuarios";
    }
}
