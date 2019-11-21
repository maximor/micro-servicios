package micro.servicio.apigateway.configuracion;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r -> r.path("/servicio-notificaciones/**")
                            .uri("http://localhost:2128/")
                            .id("servicio-notificaciones"))
                .route(r -> r.path("/servicio-productos/**")
                            .uri("http://localhost:8128/")
                            .id("servicio-productos"))
                .route(r -> r.path("/servicio-usuarios/**")
                            .uri("http://localhost:1328/")
                            .id("servicio-usuarios"))
                .build();
    }
}
