package micro.servicio.serviciousuarios.usuario;

import micro.servicio.serviciousuarios.modelos.PeticionAutenticacion;
import micro.servicio.serviciousuarios.modelos.RespuestaAutenticacion;
import micro.servicio.serviciousuarios.utilidades.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioControlador {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioDetailsServices usuarioDetailsServices;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @RequestMapping("/")
    public String casa(){
        return "Bienvenido a usuario";
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

    @RequestMapping("/usuarios")
    public List<Usuario> getUsuarios(){
        return usuarioRepository.findAll();
    }
}
