package co.unicauca.stockexchange.service;

import co.unicauca.stockexchange.commons.domain.Action;
import co.unicauca.stockexchange.commons.domain.Client;
import co.unicauca.stockexchange.port.output.data.IClientRepository;
import co.unicauca.stockexchange.port.output.data.IStockExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Ledy Mayerly Astudillo Calderon
 * @author Santiago Nieto Guaca
 * @author Harold Andres Molano Rosero
 * @author Janier Yulder Gomez Galindez
 */
/**
 * Clase de servicio para operaciones relacionadas con el portafolio de acciones de los clientes.
 */
@Service
public class PortfolioService {

    @Autowired
    private IClientRepository repositoryClient;

    @Autowired
    private IStockExchangeRepository repositoryStock;

    /**
     * Obtiene la lista de acciones asociadas a un cliente.
     *
     * @param idClient ID del cliente del cual se desean obtener las acciones.
     * @return Lista de acciones asociadas al cliente especificado.
     */
    public synchronized List<Action> getActions(String idClient) {
        Client myClient = repositoryClient.findClient(idClient);
        if (myClient == null) {
            return null;
        }
        return myClient.getActions();
    }

    /**
     * Busca una acción asociada a un cliente por los IDs del cliente y la acción.
     *
     * @param idClient ID del cliente.
     * @param idAction ID de la acción a buscar.
     * @return Acción correspondiente a los IDs proporcionados, o null si no se encuentra.
     */
    public synchronized Action findAction(String idClient, String idAction) {
        Client myClient = repositoryClient.findClient(idClient);
        if (myClient == null) {
            return null;
        }
        List<Action> myActions = myClient.getActions();
        for (Action action : myActions) {
            if (action.getActionId().equals(idAction.toUpperCase())) {
                return action;
            }
        }
        return null;
    }

    /**
     * Agrega una nueva acción asociada a un cliente con umbrales especificados.
     *
     * @param idClient ID del cliente.
     * @param idAction ID de la acción a agregar.
     * @param lowerThreshold Umbral inferior.
     * @param upperThreshold Umbral superior.
     * @return Mensaje de confirmación.
     */
    public synchronized String addAction(String idClient, String idAction, Double lowerThreshold, Double upperThreshold) {
        Action foundAction = repositoryStock.findAction(idAction);
        Client myClient = repositoryClient.findClient(idClient);
        if (myClient == null) {
            return "El cliente con id " + idClient + " no existe.";
        }
        if (foundAction == null) {
            return "La acción con id " + idAction + " no existe.";
        }
        Action myAction = new Action(foundAction);
        if (!myAction.updateLowerThreshold(lowerThreshold) || !myAction.updateUpperThreshold(upperThreshold)) {
            return "Los umbrales están mal definidos";
        }
        myClient.addAction(myAction);
        return "Acción agregada exitosamente";
    }

    /**
     * Actualiza los umbrales de una acción asociada a un cliente.
     *
     * @param idClient ID del cliente.
     * @param idAction ID de la acción a agregar.
     * @param lowerThreshold Nuevo umbral inferior.
     * @param upperThreshold Nuevo umbral superior.
     * @return Mensaje de confirmación.
     */
    public synchronized String updateThresholds(String idClient, String idAction, Double lowerThreshold, Double upperThreshold) {
        Client myClient = repositoryClient.findClient(idClient);
        if (myClient == null) {
            return "El cliente con id " + idClient + " no existe.";
        }
        Action myAction = findAction(idClient, idAction);
        if (!myAction.updateLowerThreshold(lowerThreshold) || !myAction.updateUpperThreshold(upperThreshold)) {
            return "Los umbrales están mal definidos";
        }
        return "Umbrales de la acción actualizados exitosamente";
    }

    /**
     * Elimina una acción asociada a un cliente.
     *
     * @param idClient ID del cliente.
     * @param idAction ID de la acción a eliminar.
     * @return Mensaje de confirmación.
     */
    public synchronized String deleteAction(String idClient, String idAction) {
        Client myClient = repositoryClient.findClient(idClient);
        if (myClient == null) {
            return "El cliente con id " + idClient + " no existe.";
        }
        Action myAction = findAction(idClient, idAction);
        if (myAction == null) {
            return "La acción con id " + idAction + " no existe.";
        }
        myClient.deleteAction(myAction);
        return "Acción eliminada exitosamente";
    }
}
