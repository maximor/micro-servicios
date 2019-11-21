package micro.servicionotificaciones.entidades;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
    Notificacion findById(int id);
    Notificacion findByUserId(int id);
    Notificacion findAllByUserId(int id);
}
