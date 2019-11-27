package micro.servicio.servicioproductos.orden;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrdenRepository extends JpaRepository<Orden, Long> {
    Orden findByIdAndAbiertaTrueAndActivaTrue(int id);
    Orden findByIdAndAbiertaFalseAndActivaTrue(int id);
    List<Orden> findAllByAbiertaTrueAndActivaTrue();
    List<Orden> findAllByAbiertaFalseAndActivaTrue();

    List<Orden> findAllByUserIdAndAbiertaTrueAndActivaTrue(int userId);
    List<Orden> findAllByUserIdAndAbiertaFalseAndActivaTrue(int userId);
    Orden findByUserIdAndIdAndAbiertaTrueAndActivaTrue(int userId, int id);
    Orden findByUserIdAndIdAndAbiertaFalseAndActivaTrue(int userId, int id);
    @Query(value = "SELECT * FROM orden orden0_ " +
            "WHERE orden0_.activa = 1 " +
            "and orden0_.abierta = 1 " +
            "and date(orden0_.fecha_creacion) = date(?)", nativeQuery = true)
    List<Orden> findAllByFechaCreacionEqualsAndAbiertaTrueAndActivaTrue(Date fechaInicio);
}
