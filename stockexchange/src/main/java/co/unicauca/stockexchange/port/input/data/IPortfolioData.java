/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.unicauca.stockexchange.port.input.data;

import co.unicauca.stockexchange.commons.domain.Action;
import java.util.List;

/**
 *
 * @author Ledy Mayerly Astudillo Calderon
 * @author Santiago Nieto Guaca
 * @author Harold Andres Molano Rosero
 * @author Janier Yulder Gomez Galindez
 */
public interface IPortfolioData {
     /**
     * Obtiene la lista de acciones asociadas a un cliente.
     *
     * @param idClient ID del cliente del cual se desean obtener las acciones.
     * @return Lista de acciones asociadas al cliente especificado.
     */
    public List<Action> getActions(String idClient);

    /**
     * Busca una acción asociada a un cliente por los IDs del cliente y la
     * acción.
     *
     * @param idClient ID Cliente.
     * @param idAction ID de la acción a buscar.
     * @return Acción correspondiente a los IDs proporcionados, o null si no se
     * encuentra.
     */
    public Action findAction(String idClient, String idAction);

    /**
     * Agrega una nueva acción asociada a un cliente con umbrales especificados.
     *
     * @param idClient ID Cliente al cual se agregará la acción.
     * @param idAction ID Acción a agregar.
     * @param lowerThreshold Umbral Inferior.
     * @param upperThreshold Umbral Superior.
     * @return Mensaje de confirmación.
     */
    public String addAction(String idClient, String idAction, Double lowerThreshold, Double upperThreshold);

    /**
     * Actualiza los umbrales de una acción asociada a un cliente.
     *
     * @param idClient ID Cliente al cual pertenece la acción.
     * @param idAction ID Acción a actualizar.
     * @param lowerThreshold Nuevo umbral inferior.
     * @param upperThreshold Nuevo umbral superior.
     * @return Mensaje de confirmación.
     */
    public String updateThresholds(String idClient, String idAction, Double lowerThreshold, Double upperThreshold);

    /**
     * Elimina una acción asociada a un cliente.
     *
     * @param idClient ID Cliente al cual pertenece la acción.
     * @param idAction ID Acción a eliminar.
     * @return Mensaje de confirmación.
     */
    public String deleteAction(String idClient, String idAction);
}
