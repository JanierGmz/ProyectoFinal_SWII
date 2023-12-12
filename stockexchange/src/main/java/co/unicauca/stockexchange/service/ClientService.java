package co.unicauca.stockexchange.service;

import co.unicauca.stockexchange.port.output.data.IClientRepository;
import co.unicauca.stockexchange.commons.domain.Client;
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
 * Clase de servicio para operaciones relacionadas con los clientes.
 */
@Service
public class ClientService {

    @Autowired
    private IClientRepository repositoryClient;

    /**
     * Busca un cliente por su ID.
     *
     * @param idClient ID del cliente a buscar.
     * @return Cliente correspondiente al ID proporcionado, o null si no se encuentra.
     */
    public synchronized Client findClient(String idClient) {
        return repositoryClient.findClient(idClient);
    }

    /**
     * Obtiene la lista de todos los clientes.
     *
     * @return Lista de todos los clientes almacenados.
     */
    public synchronized List<Client> getClients() {
        return repositoryClient.getClients();
    }

    /**
     * Agrega un nuevo cliente al repositorio.
     *
     * @param clientId   ID del cliente.
     * @param clientName Nombre del cliente.
     * @param phone      Número de teléfono del cliente.
     * @return true si el cliente se agregó con éxito, false de lo contrario.
     */
    public synchronized boolean addClient(String clientId, String clientName, String phone) {
        return repositoryClient.addClient(new Client(clientId, clientName, phone));
    }
}
