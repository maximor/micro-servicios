package micro.servicionotificaciones;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.responses.Response;
import micro.servicionotificaciones.entidades.Notificacion;
import micro.servicionotificaciones.entidades.NotificacionRepository;
import micro.servicionotificaciones.entidades.PeticionAutenticacion;
import micro.servicionotificaciones.entidades.RespuestaAutenticacion;
import micro.servicionotificaciones.entidades.seguridad.*;
import micro.servicionotificaciones.utilidades.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
@RestController
@RequestMapping("/servicio-notificaciones")
public class ServicioNotificacionesApplication {
    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioDetailsServices usuarioDetailsServices;

    @Autowired
    private JwtUtil jwtTokenUtil;

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

    @RequestMapping(method = RequestMethod.POST, value = "/token")
    public ResponseEntity<?> token(@RequestBody PeticionAutenticacion peticionAutenticacion) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(peticionAutenticacion.getUsername(), peticionAutenticacion.getPassword())
            );
        }catch(BadCredentialsException e){
            throw new Exception("Incorrect username or password");
        }

        final UserDetails userDetails = usuarioDetailsServices.loadUserByUsername(peticionAutenticacion.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new RespuestaAutenticacion(jwt));
    }

    @Bean
    public CommandLineRunner cargarDatos(UsuarioRepository usuarioRepository, RolRepository rolRepository){
        return args -> {
            System.out.println("Creando los datos por defecto...");
            System.out.println("Creando ROLES:");
            System.out.println("1- ROLE_ANDMIN");
            Rol rolAdmin = rolRepository.save(new Rol("ROLE_ADMIN"));

            System.out.println("Creando Usuario Administrador...");
            System.out.println("Usuario: admin");
            Usuario usuario = new Usuario();
            usuario.setNombre("Admin Admin");
            usuario.setUsername("admin");
            usuario.setPassword(new BCryptPasswordEncoder().encode("admin"));
            usuario.setEmail("admin@admin.com");
            usuario.setRoles(new HashSet<>(Arrays.asList(rolAdmin)));
            usuarioRepository.save(usuario);
        };
    }
}
