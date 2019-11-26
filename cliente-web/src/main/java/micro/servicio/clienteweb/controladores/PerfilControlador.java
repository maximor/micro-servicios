package micro.servicio.clienteweb.controladores;

import micro.servicio.clienteweb.entidades.usuarios.CambiarContrasena;
import micro.servicio.clienteweb.entidades.usuarios.Usuario;
import micro.servicio.clienteweb.utilidades.RestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class PerfilControlador {

    @RequestMapping("/perfil")
    public String perfil(Principal principal, Model modelo){

        modelo.addAttribute("usuario", RestUtil.getInstance().getUsuario(principal.getName()));
        return "perfil";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/perfil")
    public String perfilPost(Usuario usuario, CambiarContrasena cambiarContrasena, Principal principal, Model modelo){
        Usuario usuarioa = RestUtil.getInstance().getUsuario(principal.getName());
        if((!(usuarioa.getNombre().equals(usuario.getNombre())) || !(usuarioa.getEmail().equals(usuario.getEmail())))
                && cambiarContrasena.getNewPassword() == null){
            usuarioa.setNombre(usuario.getNombre());
            usuarioa.setEmail(usuario.getEmail());
            RestUtil.getInstance().actualizarUsuario(usuarioa);

            modelo.addAttribute("mensajeUsuario", "Usuario actualizado exitosamente");
        }

        if(cambiarContrasena.getNewPassword() != null){
            System.out.println("Entro aqui...");
            try{
                RestUtil.getInstance().cambiarContrasena(principal.getName(), cambiarContrasena);
                modelo.addAttribute("mensaje", "Contraseña actualizada exitosamente");
            }catch (Exception e){
                modelo.addAttribute("mensajeError", "Error, la vieja contraseña que esta tratando de modificar es incorrecta");
            }
        }

        modelo.addAttribute("usuario", RestUtil.getInstance().getUsuario(principal.getName()));

        return "perfil";
    }
}
