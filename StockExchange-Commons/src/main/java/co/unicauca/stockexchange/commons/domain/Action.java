/*
 * Clase que representa una acción en el mercado financiero
 */
package co.unicauca.stockexchange.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Action {

    // Identificador único de la acción
    private String actionId;
    
    // Nombre de la acción
    private String actionName;
    
    // Precio anterior de la acción
    private Double previusPrice;
    
    // Precio actual de la acción
    private Double currentPrice;
    
    // Umbral inferior para notificaciones
    private Double lowerThreshold;
    
    // Umbral superior para notificaciones
    private Double upperThreshold;
    
    // Indica si la acción ha sido notificada
    private boolean isNotified;

    /*
     * Constructor principal utilizado para crear una acción con todos los atributos.
     */
    public Action(String actionId, String actionName, Double previusPrice, Double currentPrice) {
        this.actionId = actionId;
        this.actionName = actionName;
        this.previusPrice = previusPrice;
        this.currentPrice = currentPrice;
        this.lowerThreshold = 0.0;
        this.upperThreshold = 0.0;
        this.isNotified = false;
    }

    /*
     * Constructor de copia utilizado para crear una nueva acción copiando los atributos de otra acción.
     */
    public Action(Action original) {
        this.actionId = original.actionId;
        this.actionName = original.actionName;
        this.previusPrice = original.previusPrice;
        this.currentPrice = original.currentPrice;
        this.lowerThreshold = original.lowerThreshold;
        this.upperThreshold = original.upperThreshold;
        this.isNotified = false;
    }

    /*
     * Actualiza el precio actual de la acción, almacenando el valor anterior.
     */
    public void updateCurrentPrice(Double newPrice) {
        this.previusPrice = this.currentPrice;
        this.currentPrice = newPrice;
    }

    /*
     * Actualiza el umbral inferior para notificaciones.
     * Retorna true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean updateLowerThreshold(Double newThreshold) {
        if (newThreshold != null) {
            if (newThreshold >= this.currentPrice) {
                return false;
            } else {
                this.lowerThreshold = newThreshold;
            }
            return true;
        }
        return false;
    }

    /*
     * Actualiza el umbral superior para notificaciones.
     * Retorna true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean updateUpperThreshold(Double newThreshold) {
        if (newThreshold != null) {
            if (newThreshold <= this.currentPrice) {
                return false;
            } else {
                this.upperThreshold = newThreshold;
            }
            return true;
        }
        return false;
    }
}
