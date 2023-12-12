package co.unicauca.stockexchange.port.output.data;

import co.unicauca.stockexchange.commons.domain.Client;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Repository;
/**
 *
 * @author Ledy Mayerly Astudillo Calderon
 * @author Santiago Nieto Guaca
 * @author Harold Andres Molano Rosero
 * @author Janier Yulder Gomez Galindez
 */
@Repository
public interface IClientRepository {

    /**
     * Busca un cliente por su ID.
     *
     * @param idClient ID del cliente a buscar.
     * @return Cliente correspondiente al ID proporcionado, o null si no se encuentra.
     */
    Client findClient(String idClient);

    /**
     * Obtiene la lista de todos los clientes en el repositorio.
     *
     * @return Lista de todos los clientes almacenados en el repositorio.
     */
    List<Client> getClients();

    /**
     * Agrega un nuevo cliente al repositorio.
     *
     * @param newClient Cliente a agregar.
     * @return `true` si el cliente se agregó correctamente, `false` en caso contrario.
     */
    boolean addClient(Client newClient);
}

