package micro.servicio.servicioproductos.orden;

import micro.servicio.servicioproductos.Estadisticas;
import micro.servicio.servicioproductos.MensajeError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/servicio-productos")
public class OrdenController {
    @Autowired
    private OrdenRepository ordenRepository;
    private Orden orden;

    @RequestMapping("/orden/abierta/{id}")
    public ResponseEntity ordenAbirta(@PathVariable int id){
        orden = ordenRepository.findByIdAndAbiertaTrueAndActivaTrue(id);
        if(orden != null){
            return ResponseEntity.status(HttpStatus.OK).body(orden);
        }else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MensajeError(
                            404,
                            new Date(),
                            "No Encontrado",
                            "Recuerso no encontrado con id:"+String.valueOf(id),
                            "/servicio-productos/orden/abierta/"+id));
        }
    }

    @RequestMapping("/orden/cerrada/{id}")
    public ResponseEntity ordenCerrada(@PathVariable int id){
        orden = ordenRepository.findByIdAndAbiertaFalseAndActivaTrue(id);
        if(orden != null){
            return ResponseEntity.status(HttpStatus.OK).body(orden);
        }else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MensajeError(
                            404,
                            new Date(),
                            "Not Found",
                            "Recuerso no encontrado con id:"+String.valueOf(id),
                            "/servicio-productos/orden/abierta/"+id));
    }
    }

    @RequestMapping("/ordenes")
    public List<Orden> ordenes(){return ordenRepository.findAll();}

    @RequestMapping("/ordenes/abiertas")
    public List<Orden> ordenesAbiertas(){
        return ordenRepository.findAllByAbiertaTrueAndActivaTrue();
    }

    @RequestMapping("/ordenes/cerradas")
    public List<Orden> ordenesCerradas(){
        return ordenRepository.findAllByAbiertaFalseAndActivaTrue();
    }


    @RequestMapping(method = RequestMethod.POST, value = "/orden")
    public ResponseEntity crear(@RequestBody Orden orden){
        if(orden.getMontoTotal() == 0.0f){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeError(
                            400,
                            new Date(),
                            "Bad Request",
                            "Error, No puede dejar el campo [montoTotal] vacío.",
                            "/servicio-productos/orden"));
        }else if(orden.getEmail() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeError(
                            400,
                            new Date(),
                            "Bad Request",
                            "Error, No puede dejar el campo [email] vacío.",
                            "/servicio-productos/orden"));
        }else if(orden.getPlanes() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeError(
                            400,
                            new Date(),
                            "Bad Request",
                            "Error, No puede crear orden sin elegir un plan.",
                            "/servicio-productos/orden"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenRepository.save(orden));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/orden/{id}")
    public ResponseEntity crear(@PathVariable int id){
        orden = ordenRepository.findByIdAndAbiertaTrueAndActivaTrue(id);
        if(orden != null){
            orden.setActiva(false);
            ordenRepository.save(orden);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        }else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MensajeError(
                            404,
                            new Date(),
                            "Not Found",
                            "Error, Recurso no encontrado, No es posible eliminarlo",
                            "/servicio-productos/orden/"+id));
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/orden/cerrar/{id}")
    public ResponseEntity cerrarOrden(@PathVariable int id){
        orden = ordenRepository.findByIdAndAbiertaTrueAndActivaTrue(id);
        if(orden != null){
            orden.setAbierta(false);
            ordenRepository.save(orden);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        }else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MensajeError(
                            404,
                            new Date(),
                            "Bad Request",
                            "Error, Recurso no encontrado, No es posible cerrar la orden",
                            "/servicio-productos/orden/cerrar/"+id));
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/orden/abrir/{id}")
    public ResponseEntity abrirOrden(@PathVariable int id){
        orden = ordenRepository.findByIdAndAbiertaFalseAndActivaTrue(id);
        if(orden != null){
            orden.setAbierta(true);
            ordenRepository.save(orden);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        }else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MensajeError(
                            404,
                            new Date(),
                            "Not Found",
                            "Error, Recurso no encontrado, No es posible abrir la orden",
                            "/servicio-productos/orden/abrir/"+id));
        }
    }

    //TODO: CORREGIR ERROR DE LOGICA PARA SELECCIONAR LA FECHA
    @RequestMapping("/estadistica")
    public Estadisticas estadisticas(){
        int comprasRealizadas = 0;
        int comprasPendientes = 0;

        List<Orden> ordenes = ordenRepository.findAllByAbiertaFalseAndActivaTrue();
        comprasRealizadas = ordenes.size();
        ordenes = ordenRepository.findAllByAbiertaTrueAndActivaTrue();
        comprasPendientes = ordenes.size();
        ordenes = ordenRepository
                .findAllByFechaCreacionEqualsAndAbiertaTrueAndActivaTrue(new Date());
        return new Estadisticas(comprasRealizadas, comprasPendientes, ordenes.size());
    }
}
