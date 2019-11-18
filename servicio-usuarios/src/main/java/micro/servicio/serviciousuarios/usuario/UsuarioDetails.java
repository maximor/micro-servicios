package micro.servicio.serviciousuarios.usuario;

import micro.servicio.serviciousuarios.rol.Rol;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UsuarioDetails implements UserDetails {
    private String username;
    private String password;
    private boolean activo;
    private List<GrantedAuthority> authorities;

    public UsuarioDetails() {
    }

    public UsuarioDetails(Usuario usuario) {
        username = usuario.getUsername();
        password = usuario.getPassword();
        activo = usuario.isActivo();
        authorities = new ArrayList<>();
        for(Rol rol : usuario.getRoles() ){
            authorities.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return activo;
    }
}
