package micro.servicio.serviciousuarios.usuario;

import micro.servicio.serviciousuarios.modelos.MensajeError;
import micro.servicio.serviciousuarios.modelos.PeticionAutenticacion;
import micro.servicio.serviciousuarios.modelos.RespuestaAutenticacion;
import micro.servicio.serviciousuarios.rol.Rol;
import micro.servicio.serviciousuarios.rol.RolRepository;
import micro.servicio.serviciousuarios.utilidades.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/servicio-usuarios")
public class UsuarioControlador {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioDetailsServices usuarioDetailsServices;

    @Autowired
    private JwtUtil jwtTokenUtil;

    private Usuario usuario;

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

    @RequestMapping("/usuarios")
    public List<Usuario> getUsuarios(){
        return usuarioRepository.findAll();
    }

    @RequestMapping("/usuario/{name}")
    public ResponseEntity getUsuarioPorNombre(@PathVariable String name){
        usuario = usuarioRepository.findByUsername(name);

        if(usuario == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MensajeError(
                            404,
                            new Date(),
                            "No Encontrado",
                            "Recurso no encontrado ["+name+"]",
                            "/usuario/"+name));
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/usuario/crear")
    public ResponseEntity crear(@RequestBody Usuario busuario){
        if(busuario.getNombre() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeError(
                            400,
                            new Date(),
                            "Bad Request",
                            "debe de llenar el campo [nombre]",
                            "/usuario/crear"

                    ));
        }else if(busuario.getUsername() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeError(
                            400,
                            new Date(),
                            "Bad Request",
                            "debe de llenar el campo [username]",
                            "/usuario/crear"

                    ));
        }else if(busuario.getEmail() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeError(
                            400,
                            new Date(),
                            "Bad Request",
                            "debe de llenar el campo [email]",
                            "/usuario/crear"

                    ));
        }else if(busuario.getPassword() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeError(
                            400,
                            new Date(),
                            "Bad Request",
                            "debe de llenar el campo [password]",
                            "/usuario/crear"

                    ));
        }else if(busuario.getRoles() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeError(
                            400,
                            new Date(),
                            "Bad Request",
                            "debe de llenar el campo [roles]",
                            "/usuario/crear"

                    ));
        }
        //se busca el rol en la base de datos
        HashSet<Rol> roles = new HashSet<>();
        for(Rol rol :  busuario.getRoles()){
            roles.add(rolRepository.findByNombre(rol.getNombre()));
        }
        busuario.setRoles(roles);
        busuario.setPassword(new BCryptPasswordEncoder().encode(busuario.getPassword()));

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(busuario));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/usuario/actualizar")
    public ResponseEntity actualizar(@RequestBody Usuario busuario){
        if(busuario.getUsername() != null){
            usuario = usuarioRepository.findByUsername(busuario.getUsername());
        }else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeError(
                            400,
                            new Date(),
                            "Bad Request",
                            "debe de llenar el campo [username]",
                            "/usuario/actualizar"
                    ));
        }

        if(busuario.getNombre() != null
                && busuario.getNombre().length() > 0
                && !busuario.getNombre().equals(usuario.getNombre())){
            usuario.setNombre(busuario.getNombre());
        }

        if(busuario.getEmail() != null
                && busuario.getEmail().length() > 0
                && !busuario.getEmail().equals(usuario.getEmail())){
            usuario.setEmail(busuario.getEmail());
        }

        if(busuario.isActivo() != usuario.isActivo()){
            usuario.setActivo(busuario.isActivo());
        }

        usuarioRepository.save(usuario);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/usuario/actualizar/{oldUsername}/{newUsername}")
    public ResponseEntity actualizarUsername(@PathVariable String oldUsername, @PathVariable String newUsername){
        usuario = usuarioRepository.findByUsername(oldUsername);
        Usuario nuevoUsuario = usuarioRepository.findByUsername(newUsername);

        if(usuario == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MensajeError(
                            404,
                            new Date(),
                            "Not Found",
                            "no se encontró el usuario ["+oldUsername+"] que se quiere modificar",
                            "/usuario/actualizar/"+oldUsername+"/"+newUsername
                    ));
        }else{
            if(nuevoUsuario != null){
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new MensajeError(
                                400,
                                new Date(),
                                "Bad Request",
                                "Error, no se puede modificar ["+oldUsername+"] - ["+newUsername+"] ya existe.",
                                "/usuario/actualizar/"+oldUsername+"/"+newUsername
                        ));
            }else{
                usuario.setUsername(newUsername);
                usuarioRepository.save(usuario);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
            }
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/usuario/actualizar/{username}/{oldPassword}/{newPassword}")
    public ResponseEntity actualizarPassword(@PathVariable String username,
                                             @PathVariable String oldPassword,
                                             @PathVariable String newPassword){
        usuario = usuarioRepository.findByUsername(username);
        if(usuario == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MensajeError(
                            404,
                            new Date(),
                            "Not Found",
                            "Error, no se encontró el usuario ["+username+"] que se quiere modificar",
                            "/usuario/actualizar/"+username+"/oldPassword/newPassword"
                    ));
        }

        if(new BCryptPasswordEncoder().matches(oldPassword, usuario.getPassword())){
            System.out.println("LA CONTRASENA HA SIDO MODIFICADA...");
            usuario.setPassword(new BCryptPasswordEncoder().encode(newPassword));
            usuarioRepository.save(usuario);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        }else{
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeError(
                            400,
                            new Date(),
                            "Bad Request",
                            "Error, la vieja contraseña que esta tratando de modificar es incorrecta",
                            "/usuario/actualizar/"+username+"/oldPassword/newPassword"
                    ));
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/usuario/crear/empleado")
    public ResponseEntity crearUsuarioConRolEmpleado(@RequestBody Usuario busuario){
        if(busuario.getNombre() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeError(
                            400,
                            new Date(),
                            "Bad Request",
                            "debe de llenar el campo [nombre]",
                            "/usuario/crear"

                    ));
        }else if(busuario.getUsername() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeError(
                            400,
                            new Date(),
                            "Bad Request",
                            "debe de llenar el campo [username]",
                            "/usuario/crear"

                    ));
        }else if(busuario.getEmail() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeError(
                            400,
                            new Date(),
                            "Bad Request",
                            "debe de llenar el campo [email]",
                            "/usuario/crear"

                    ));
        }else if(busuario.getPassword() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeError(
                            400,
                            new Date(),
                            "Bad Request",
                            "debe de llenar el campo [password]",
                            "/usuario/crear"

                    ));
        }

        //se busca el rol de empleados en la base de datos
        busuario.setRoles(new HashSet<Rol>(Arrays.asList(rolRepository.findByNombre("ROLE_EMPLEADO"))));
        busuario.setPassword(new BCryptPasswordEncoder().encode(busuario.getPassword()));
        busuario.setActivo(true);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(busuario));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/usuario/crear/cliente")
    public ResponseEntity crearUsuarioConRolUsuario(@RequestBody Usuario busuario){
        if(busuario.getNombre() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeError(
                            400,
                            new Date(),
                            "Bad Request",
                            "debe de llenar el campo [nombre]",
                            "/usuario/crear"

                    ));
        }else if(busuario.getUsername() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeError(
                            400,
                            new Date(),
                            "Bad Request",
                            "debe de llenar el campo [username]",
                            "/usuario/crear"

                    ));
        }else if(busuario.getEmail() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeError(
                            400,
                            new Date(),
                            "Bad Request",
                            "debe de llenar el campo [email]",
                            "/usuario/crear"

                    ));
        }else if(busuario.getPassword() == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeError(
                            400,
                            new Date(),
                            "Bad Request",
                            "debe de llenar el campo [password]",
                            "/usuario/crear"

                    ));
        }

        //se busca el rol de empleados en la base de datos
        busuario.setRoles(new HashSet<Rol>(Arrays.asList(rolRepository.findByNombre("ROLE_CLIENTE"))));
        busuario.setPassword(new BCryptPasswordEncoder().encode(busuario.getPassword()));
        busuario.setActivo(true);
        Usuario usuarioAux = null;

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(busuario));
    }
}
