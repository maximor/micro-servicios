package micro.servicio.clienteweb.servicios;

import micro.servicio.clienteweb.entidades.usuarios.Usuario;
import micro.servicio.clienteweb.entidades.usuarios.UsuarioDetails;
import micro.servicio.clienteweb.utilidades.RestUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsServices implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = RestUtil.getInstance().getUsuario(username);
        if(usuario == null){
            throw new UsernameNotFoundException("Usuario No encontrado: " + username);
        }

        return new UsuarioDetails(usuario);
    }
}
