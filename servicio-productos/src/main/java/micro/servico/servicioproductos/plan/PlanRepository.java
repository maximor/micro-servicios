package micro.servicio.servicioproductos.plan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    Plan findByIdAndActivoTrue(int id);
    Plan findByNombreAndActivoTrue(String nombre);
    List<Plan> findAllByActivoTrue();
}