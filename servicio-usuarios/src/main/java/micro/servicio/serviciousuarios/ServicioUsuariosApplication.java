package micro.servicio.serviciousuarios;

import micro.servicio.serviciousuarios.rol.Rol;
import micro.servicio.serviciousuarios.rol.RolRepository;
import micro.servicio.serviciousuarios.usuario.Usuario;
import micro.servicio.serviciousuarios.usuario.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
public class ServicioUsuariosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicioUsuariosApplication.class, args);
    }

    @Bean
    public CommandLineRunner cargarDatos(UsuarioRepository usuarioRepository, RolRepository rolRepository){
        return args -> {
            System.out.println("Creando los datos por defecto...");
            System.out.println("Creando ROLES:");
            System.out.println("1- ROLE_ANDMIN\n2- ROLE_CLIENTE\n3- ROLE_EMPLEADO");
            Rol rolAdmin = rolRepository.save(new Rol("ROLE_ADMIN"));
            rolRepository.saveAll(Arrays.asList(
                    new Rol("ROLE_CLIENTE"),
                    new Rol("ROLE_EMPLEADO")
            ));

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
