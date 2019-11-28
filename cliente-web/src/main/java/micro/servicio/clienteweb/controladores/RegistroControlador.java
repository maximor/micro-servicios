package micro.servicio.clienteweb.controladores;

import micro.servicio.clienteweb.entidades.notificaciones.Notificacion;
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
                modelo.addAttribute("mensajeExitoso", "Usuario creado exitosamente.");
                //formamos el correo
                Notificacion notificacion = new Notificacion();
                notificacion.setEmisor(RestUtil.getInstance().getNotificacionEmisor());
                notificacion.setReceptor(usuario.getEmail());
                notificacion.setAsunto("Gracias Por Ser Parte de Nosotros [Matrix Foto Estudio]");
                notificacion.setCuerpo(
                        "<h2>Hola</h2> <h4>"+usuario.getNombre()+"</h4>\n\n" +
                        "<p>Ponemos a tu disposición todos nuestros servicios de fotografía profesional tanto personal como empresarial.\n" +
                        "En Foto Estudio Matrix nuestro objetivo no es sólo ofrecer la máxima calidad digital de tus productos, " +
                                "sino también minimizar los tiempos de entrega para que tengas lo más pronto posible tu producto final.</p>" +
                                "\n\n" +
                                "<h2>Gracias por tu registro</h2>\n" +
                                "<p>Para iniciar sesión entrar al enlace de abajo</p>" +
                                "<a href='http://localhost/login'>Matrix Foto Estudio</a>");

                Notificacion notificacionEmail = RestUtil.getInstance().crearCorreo(notificacion);
                return "registro";

            } catch (Exception ee) {
                ee.getStackTrace();
            }
        }
        return "registro";
    }
}
