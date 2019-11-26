package micro.servicio.clienteweb.controladores;

import micro.servicio.clienteweb.entidades.productos.Plan;
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

        modelo.addAttribute("planes", RestUtil.getInstance().getPlanes());
        return "index";
    }

    @RequestMapping("/{planNombre}")
    public String indexAgregarCarrito(@PathVariable String planNombre, Model modelo, HttpSession httpSession){
        List<Plan> planes = new ArrayList<>();

        if(httpSession.getAttribute("planes") != null){
            planes = (List<Plan>) httpSession.getAttribute("planes");
            planes.add(RestUtil.getInstance().getPlanPorNombre(planNombre));
            modelo.addAttribute("cantidad", planes.size());
            modelo.addAttribute("planesCarrito", planes);
        }else{
            planes.add(RestUtil.getInstance().getPlanPorNombre(planNombre));
            httpSession.setAttribute("planes", planes);
            modelo.addAttribute("cantidad", planes.size());
            modelo.addAttribute("planesCarrito", planes);
        }

        modelo.addAttribute("planes", RestUtil.getInstance().getPlanes());
        return "redirect:/";
    }

    @RequestMapping("/carrito")
    public String carrito(Model modelo, HttpSession httpSession){
        List<Plan> planes = new ArrayList<>();
        if(httpSession.getAttribute("planes") != null){
            planes = (List<Plan>) httpSession.getAttribute("planes");
            modelo.addAttribute("planesCarrito", planes);
            modelo.addAttribute("cantidad", planes.size());
        }

        return "carrito";
    }

    //TODO REALIZAR EL CHECKOUT
    @RequestMapping("/checkout")
    public String checkout(Model modelo, HttpSession httpSession){
        return "checkout";
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
}
