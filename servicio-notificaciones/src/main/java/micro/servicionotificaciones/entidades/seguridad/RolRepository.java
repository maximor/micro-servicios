package micro.servicionotificaciones.entidades.seguridad;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    Rol findByNombre(String nombre);
}
