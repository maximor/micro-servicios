package micro.servicio.servicioproductos.rol;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    Rol findByNombre(String nombre);
}
