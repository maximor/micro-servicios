package micro.servicio.servicioproductos;

import micro.servicio.servicioproductos.orden.Orden;
import micro.servicio.servicioproductos.orden.OrdenRepository;
import micro.servicio.servicioproductos.plan.Plan;
import micro.servicio.servicioproductos.plan.PlanRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ServicioProductosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicioProductosApplication.class, args);
    }

    @Bean
    public CommandLineRunner cargarDatos(PlanRepository planRepository, OrdenRepository ordenRepository){
        return args -> {

            List<Plan> planes = Arrays.asList(
                    new Plan("fotos", 550, "plan de prueba",true),
                    new Plan("marcos", 200, "plan de prueba ", true),
                    new Plan("Pre-Boda", 1000, "30 fotos para antes de la boda", true),
                    new Plan("Boda", 5000, "50 fotos de la boda", true),
                    new Plan("Cumpleaños", 3000, "50 fotos de tu cumpleaños", true),
                    new Plan("Video para Eventos", 4000, "Un video de 40 minutos tu evento", true));
            planes = planRepository.saveAll(planes);
           ordenRepository.save(new Orden("test@test.com", 750, 0, planes));
        };
    }
}
