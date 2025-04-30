package com.ecommerce.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Clase de configuración para el manejo de CORS (Cross-Origin Resource Sharing) en la aplicación.
 * Permite configurar las políticas de acceso entre el backend y otros orígenes (frontend u otros clientes).
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Aplica las reglas a todas las rutas bajo "/api/**"
        .allowedOrigins("https://tomcat.dmunoz.es") // Permite solicitudes desde ambos orígenes
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
                .allowedHeaders("*") // Permite todos los encabezados en la solicitud
                .allowCredentials(true); // Permite el uso de cookies o credenciales en las solicitudes
    }
}