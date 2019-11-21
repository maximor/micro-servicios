package micro.servicionotificaciones;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.responses.Response;
import micro.servicionotificaciones.entidades.Notificacion;
import micro.servicionotificaciones.entidades.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class ServicioNotificacionesApplication {
    @Autowired
    private NotificacionRepository notificacionRepository;

    private String SPARKPOSTKEY = "02a7736586c58daa816df2f3043211467c8019a1";

    public static void main(String[] args) {
        SpringApplication.run(ServicioNotificacionesApplication.class, args);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/notificacion")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity enviarNotificacion(@RequestBody Notificacion notificacion) throws SparkPostException {
        Client client = new Client(SPARKPOSTKEY);
        Response result = client.sendMessage(
                notificacion.getEmisor(),
                notificacion.getReceptor(),
                notificacion.getAsunto(),
                notificacion.getCuerpo(),
                "<p>" + notificacion.getCuerpo() + "</p>");

        if (result.getResponseCode() == 200) {
            return ResponseEntity.status(HttpStatus.OK).body(notificacionRepository.save(notificacion));
        }

        return ResponseEntity
                .status(result.getResponseCode())
                .body(result.getResponseMessage());
    }
}
