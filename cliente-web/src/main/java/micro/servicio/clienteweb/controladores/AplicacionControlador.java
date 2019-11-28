package micro.servicio.clienteweb.controladores;

import micro.servicio.clienteweb.entidades.notificaciones.Notificacion;
import micro.servicio.clienteweb.entidades.productos.Orden;
import micro.servicio.clienteweb.entidades.productos.Plan;
import micro.servicio.clienteweb.entidades.usuarios.Usuario;
import micro.servicio.clienteweb.utilidades.RestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AplicacionControlador {
    @RequestMapping("/")
    public String index(Principal principal, Model modelo, HttpSession httpSession){
        if(httpSession.getAttribute("planes") != null){
            List<Plan> planes = (List<Plan>) httpSession.getAttribute("planes");
            modelo.addAttribute("cantidad", planes.size());
            modelo.addAttribute("planesCarrito", planes);
        }

        modelo.addAttribute("estadistica", RestUtil.getInstance().getOrdenEstadisticas());
        modelo.addAttribute("planes", RestUtil.getInstance().getPlanes());
        modelo.addAttribute("usuario", RestUtil.getInstance().getUsuario(principal.getName()));
        return "index";
    }

    @RequestMapping("/{planNombre}")
    public String indexAgregarCarrito(@PathVariable String planNombre, Model modelo, HttpSession httpSession){
        List<Plan> planes = new ArrayList<>();

        if(httpSession.getAttribute("planes") != null){
            planes = (List<Plan>) httpSession.getAttribute("planes");
            planes = calcularCantidadPlanes(planes, planNombre);
//            Plan plan = RestUtil.getInstance().getPlanPorNombre(planNombre);
//            plan.setSeleccionado(true);
//            planes.add(plan);
            modelo.addAttribute("cantidad", planes.size());
            modelo.addAttribute("planesCarrito", planes);
        }else{
            Plan plan = RestUtil.getInstance().getPlanPorNombre(planNombre);
            plan.setCantidad(1);
            planes.add(plan);
            httpSession.setAttribute("planes", planes);
            modelo.addAttribute("cantidad", planes.size());
            modelo.addAttribute("planesCarrito", planes);
        }
        httpSession.setAttribute("planes", planes);
        modelo.addAttribute("planes", RestUtil.getInstance().getPlanes());
        return "redirect:/";
    }

    @RequestMapping("/carrito")
    public String carrito(Principal principal, Model modelo, HttpSession httpSession){
        List<Plan> planes = new ArrayList<>();
        if(httpSession.getAttribute("planes") != null){
            planes = (List<Plan>) httpSession.getAttribute("planes");
            httpSession.setAttribute("planes", planes);
            modelo.addAttribute("planesCarrito", planes);
            modelo.addAttribute("cantidad", planes.size());

        }
        modelo.addAttribute("usuario", RestUtil.getInstance().getUsuario(principal.getName()));
        return "carrito";
    }

    @RequestMapping("/eliminarCarrito/{indice}")
    public String eliminarCarrito(@PathVariable int indice, HttpSession httpSession){
        List<Plan> planes = new ArrayList<>();

        if(httpSession.getAttribute("planes") != null){
            planes = (List<Plan>) httpSession.getAttribute("planes");
            planes.remove(indice-1);
        }

        return "redirect:/carrito";
    }

    //TODO REALIZAR EL CHECKOUT Y PAGO VIA PAYPAL
    @RequestMapping("/checkout")
    public String checkout(Principal principal, Model modelo, HttpSession httpSession){
        List<Plan> planes = (List<Plan>) httpSession.getAttribute("planes");

        if(planes != null && planes.size() > 0){
            if(httpSession.getAttribute("orden") != null){
                Orden orden = (Orden) httpSession.getAttribute("orden");
                orden.setMontoTotal(0);
                planes.forEach(plan -> {
                    orden.setMontoTotal(orden.getMontoTotal()+plan.getMonto());
                });
                httpSession.setAttribute("orden", orden);
                modelo.addAttribute("orden", orden);
            }else{
                Orden orden = new Orden();
                orden.setMontoTotal(0);
                Usuario usuario = RestUtil.getInstance().getUsuario(principal.getName());
                planes.forEach(plan -> {
                    orden.setMontoTotal(orden.getMontoTotal()+plan.getMonto());
                });
                orden.setEmail(usuario.getEmail());
                orden.setUserId(usuario.getId());
                orden.setPlanes(planes);
                httpSession.setAttribute("orden", orden);
                modelo.addAttribute("orden", orden);
                modelo.addAttribute("cantidad", planes.size());
            }


        }else{
            return "redirect:/";
        }

        modelo.addAttribute("usuario", RestUtil.getInstance().getUsuario(principal.getName()));
        return "checkout";
    }

    @RequestMapping("/pagar")
    public String pagar(Principal principal, Model modelo, HttpSession httpSession){
        Orden orden = (Orden) httpSession.getAttribute("orden");

        if(orden != null){
            try{
                orden.setAbierta(true);
                orden.setActiva(true);
               orden = (Orden) RestUtil.getInstance().crearOrden(orden).getBody();
               httpSession.removeAttribute("orden");
               httpSession.removeAttribute("planes");

               modelo.addAttribute("mensaje", "Se ha generado una orden, gracias por elegirnos");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }else {
            return "redirect:/";
        }
        return "pagar";
    }

    @RequestMapping("/mis-pedidos")
    public String misPedidos(Principal principal, Model modelo){
        Usuario usuario = RestUtil.getInstance().getUsuario(principal.getName());
        modelo.addAttribute("ordenesAbiertas", RestUtil.getInstance().getOrdenesAbiertasPorUsuarios(usuario.getId()));
        modelo.addAttribute("ordenesCerradas", RestUtil.getInstance().getOrdenesCerradasPorUsuarios(usuario.getId()));
        modelo.addAttribute("usuario", usuario);

        return "mis-pedidos";
    }


    //CONTROL PARA LOS USUARIOS EMPLEADOS
    @RequestMapping("/cerrar-pedido")
    public String asignarPedidos(Principal principal, Model modelo){
        Usuario usuario = RestUtil.getInstance().getUsuario(principal.getName());

        modelo.addAttribute("ordenes", RestUtil.getInstance().getOrdenesAbiertas());
        modelo.addAttribute("ordenesCerradas", RestUtil.getInstance().getOrdenesCerradas());
        modelo.addAttribute("usario", usuario);
        return "empleados/asignar-pedidos";
    }

    @RequestMapping("/cerrar-pedido/{id}")
    public String cerrarPedido(@PathVariable int id, Principal principal){
        Usuario usuario = RestUtil.getInstance().getUsuario(principal.getName());
        Orden orden = RestUtil.getInstance().getOrdenPorId(id);
        RestUtil.getInstance().cerrarOrden(id);

        //formamos el correo
        Notificacion notificacion = new Notificacion();
        notificacion.setEmisor(RestUtil.getInstance().getNotificacionEmisor());
        notificacion.setReceptor(orden.getEmail());
        notificacion.setAsunto("[Matrix Foto Estudio] Cierre de Orden #" + orden.getId());
        notificacion.setCuerpo(
                "<h2>Hola</h2> <h4>"+usuario.getNombre()+"</h4>\n\n" +
                        "<p>Se ha Cerrado la orden #"+orden .getId() +
                        "\n\n" +
                        "<h2>Gracias por usar nuestros servicios</h2>");

        Notificacion notificacionEmail = RestUtil.getInstance().crearCorreo(notificacion);
        return "redirect:/cerrar-pedido";
    }


    @RequestMapping("/denegado")
    public String denegado(){
        return "denegado";
    }
    //ideal cuando planes existe solo en la sesion
    private List<Plan> calcularCantidadPlanes(List<Plan> planes, String nombrePlan){
        boolean estado = false;
        int indice = -1;
        int i = 0;
        while(i < planes.size()){
            if(planes.get(i).getNombre().equals(nombrePlan)){
                estado = true;
                indice = i;
                break;
            }
            i++;
        }

        if(estado){
            planes.get(indice).setCantidad(planes.get(indice).getCantidad()+1);
        }else{
            planes.add(RestUtil.getInstance().getPlanPorNombre(nombrePlan));
        }

        return planes;
    }
}
