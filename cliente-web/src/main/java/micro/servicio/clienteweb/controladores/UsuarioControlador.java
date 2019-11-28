package micro.servicio.clienteweb.controladores;

import micro.servicio.clienteweb.entidades.notificaciones.Notificacion;
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

                //formamos el correo
                Notificacion notificacion = new Notificacion();
                notificacion.setEmisor(RestUtil.getInstance().getNotificacionEmisor());
                notificacion.setReceptor(usuario.getEmail());
                notificacion.setAsunto("[Matrix Foto Estudio] Creación de Empleado " + usuario.getNombre());
                notificacion.setCuerpo(
                        "<h2>Hola</h2> <h4>"+usuario.getNombre()+"</h4>\n\n" +
                                "<p>Ya eres parte de nuestro equipo.\n" +
                                "\n\n" +
                                "<h2>Información del usuario:</h2>\n" +
                                "<p>Usuario: " + usuario.getUsername() + " </p>\n"+
                                "<p>Contraseña: "+ usuario.getPassword() +" </p>\n\n" +
                                "<h3>Inicia Sesión y Cambia tu contraseña</h3>\n"+
                                "<a href='http://localhost/login'>Matrix Foto Estudio</a>");

                Notificacion notificacionEmail = RestUtil.getInstance().crearCorreo(notificacion);

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
