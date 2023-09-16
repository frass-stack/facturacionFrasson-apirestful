package com.springboot.facturacionfrasson.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Ruta de la API que deseas habilitar CORS
                .allowedOrigins("http://localhost:5173") // Origen permitido
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
                .allowedHeaders("*");
//                .allowCredentials(false); // Permitir el envío de cookies y encabezados de autenticación
    }
}

