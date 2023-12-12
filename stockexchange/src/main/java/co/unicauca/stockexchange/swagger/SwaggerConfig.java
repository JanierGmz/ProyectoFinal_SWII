package co.unicauca.stockexchange.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Configuration;
/**
 *
 * @author Ledy Mayerly Astudillo Calderon
 * @author Santiago Nieto Guaca
 * @author Harold Andres Molano Rosero
 * @author Janier Yulder Gomez Galindez
 */
/**
 * Configuración de Swagger para la documentación de la API.
 */
@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    /**
     * Configura la información de la API para Swagger.
     *
     * @return Instancia de OpenAPI con la información de la API.
     */
    public OpenAPI api() {
        return new OpenAPI();
    }
}
