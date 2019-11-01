package micro.servicionotificaciones;

import com.google.gson.Gson;
import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.responses.Response;
import micro.servicionotificaciones.entidades.Notificacion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class ServicioNotificacionesApplication{
    private String SPARKPOSTKEY = "02a7736586c58daa816df2f3043211467c8019a1";

    public static void main(String[] args) {
        SpringApplication.run(ServicioNotificacionesApplication.class, args);
    }

    @RequestMapping(method=RequestMethod.POST, value="/notificacion")
    @ResponseStatus(HttpStatus.OK)
    public String enviarNotificacion(@RequestBody Notificacion notificacion) throws SparkPostException {
        Client client = new Client(SPARKPOSTKEY);
        Response result = client.sendMessage(
                notificacion.getEmisor(),
                notificacion.getReceptor(),
                notificacion.getAsunto(),
                notificacion.getCuerpo(),
                "<p>"+notificacion.getCuerpo()+"</p>");

        if(result.getResponseCode() == 200){
            return new Gson().toJson(notificacion, Notificacion.class);
        }
        return result.getResponseBody();
    }

}
