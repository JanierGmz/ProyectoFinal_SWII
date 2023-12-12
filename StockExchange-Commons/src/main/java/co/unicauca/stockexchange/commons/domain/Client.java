/*
 * Clase que representa a un cliente en el mercado financiero
 */
package co.unicauca.stockexchange.commons.domain;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
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
public class Client {

    // Identificador único del cliente
    private String clientId;
    
    // Nombre del cliente
    private String clientName;
    
    // Número de teléfono del cliente
    private String phone;
    
    // Lista de acciones asociadas al cliente
    private List<Action> actions = new ArrayList<>();

    /*
     * Constructor principal utilizado para crear un cliente con todos los atributos.
     */
    public Client(String clientId, String clientName, String phone) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.phone = phone;
    }

    /*
     * Agrega una acción a la lista de acciones asociadas al cliente.
     */
    public void addAction(Action myAction) {
        actions.add(myAction);
    }

    /*
     * Elimina una acción de la lista de acciones asociadas al cliente.
     */
    public void deleteAction(Action myAction) {
        actions.remove(myAction);
    }

    /*
     * Obtiene una acción de la lista de acciones asociadas al cliente por su identificador.
     * Retorna la acción si se encuentra, o null si no existe.
     */
    public Action getActionById(String actionId) {
        if (actions == null) {
            return null;
        }
        for (Action myAction : actions) {
            if (myAction.getActionId().equals(actionId)) {
                return myAction;
            }
        }
        return null;
    }
}
