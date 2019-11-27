package micro.servicio.servicioproductos.plan;

import micro.servicio.servicioproductos.MensajeError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/servicio-productos")
public class PlanController {
    @Autowired
    private PlanRepository planRepository;

    @RequestMapping("/plan/{nombre}")
    public ResponseEntity plan(@PathVariable String nombre){
        Plan plan = planRepository.findByNombreAndActivoTrue(nombre);
        if(plan == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MensajeError(
                            404,
                            new Date(),
                            "Not Found",
                            "Error, Recurso no encontrado",
                            "/servicio-productos/plan/"+nombre));
        }
        return ResponseEntity.status(HttpStatus.OK).body(plan);
    }

    @RequestMapping("/planes")
    @ResponseStatus(HttpStatus.OK)
    public List<Plan> Planes(){
        return planRepository.findAllByActivoTrue();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/plan")
    public ResponseEntity crear(@RequestBody Plan plan){
        if(plan.getNombre() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeError(
                            400,
                            new Date(),
                            "Bad Request",
                            "Error, No puede dejar el campo [nombre] vacío",
                            "/servicio-productos/plan"));
        }else if(plan.getMonto() == 0.0f){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeError(
                            400,
                            new Date(),
                            "Bad Request",
                            "Error, No puede dejar el campo [monto] vacío",
                            "/servicio-productos/plan"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(planRepository.save(plan));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/plan")
    public ResponseEntity update(@RequestBody Plan plan){
        Plan planAux;
        if(plan.getId() != 0d){
            planAux = planRepository.findByIdAndActivoTrue(plan.getId());
            if(planAux.getNombre() != null){
                if(plan.getNombre() != null){
                    planAux.setNombre(plan.getNombre());
                } else if(plan.getMonto() != 0.0f){
                    planAux.setMonto(plan.getMonto());
                }else if(plan.getDescripcion() != null){
                    planAux.setDescripcion(plan.getDescripcion());
                }
                try{
                    planRepository.save(planAux);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
                }catch (Exception e){
                    return ResponseEntity
                            .status(HttpStatus.BAD_REQUEST)
                            .body(new MensajeError(
                                    400,
                                    new Date(),
                                    "Bad Request",
                                    e.getMessage(),
                                    "/servicio-productos/plan"));
                }
            }else{
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new MensajeError(
                                404,
                                new Date(),
                                "Not Found",
                                "Error, Recurso no encontrado, No es posible actualizarlo",
                                "/servicio-productos/plan"));
            }
        }else{
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeError(
                            400,
                            new Date(),
                            "Bad Request",
                            "Error, No puede dejar el campo [id] vacío",
                            "/servicio-productos/plan"));
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/plan/{id}")
    public ResponseEntity borrar(@PathVariable int id){
        Plan plan = planRepository.findByIdAndActivoTrue(id);
        if(plan != null){
            plan.setActivo(false);
            planRepository.save(plan);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        }else {
           return ResponseEntity
                   .status(HttpStatus.NOT_FOUND)
                   .body(new MensajeError(
                           404,
                           new Date(),
                           "Not Found",
                           "Error, Recurso no encontrado, No es posible eliminarlo",
                           "/servicio-productos/plan/"+id));
        }
    }
}
