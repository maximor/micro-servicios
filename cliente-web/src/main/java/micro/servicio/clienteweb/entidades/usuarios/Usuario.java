package micro.servicio.clienteweb.entidades.usuarios;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Usuario {

    private int id;
    private String username;
    private String password;
    private String nombre;
    private String email;
    private boolean activo;

    private Set<Rol> roles;

    public Usuario() {
    }

    public Usuario(String username, String password, String nombre, String email, boolean activo, Set<Rol> roles) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.email = email;
        this.activo = activo;
        this.roles = roles;
    }

    public Usuario(int id, String username, String password, String nombre, String email, boolean activo, Set<Rol> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.email = email;
        this.activo = activo;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}
