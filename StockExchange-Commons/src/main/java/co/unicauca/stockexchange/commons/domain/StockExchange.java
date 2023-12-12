/*
 * Clase que representa una bolsa de valores
 */
package co.unicauca.stockexchange.commons.domain;

import lombok.Data;
import java.util.ArrayList;
/**
 *
 * @author Ledy Mayerly Astudillo Calderon
 * @author Santiago Nieto Guaca
 * @author Harold Andres Molano Rosero
 * @author Janier Yulder Gomez Galindez
 */
/**
 * La anotación Lombok es utilizada para generar automáticamente
 * los métodos equals, hashCode, toString, getters y setters.
 */
@Data
public class StockExchange {

    // Identificador único de la bolsa de valores
    private String stockExchangeId;
    
    // Nombre de la bolsa de valores
    private String stockExchangeName;
    
    // Lista de acciones asociadas a la bolsa de valores
    private ArrayList<Action> actions = new ArrayList<>();

}
