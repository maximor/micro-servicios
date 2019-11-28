package micro.servicionotificaciones.entidades.seguridad;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByUsername(String username);
}
