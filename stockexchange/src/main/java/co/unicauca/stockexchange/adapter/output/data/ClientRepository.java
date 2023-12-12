package co.unicauca.stockexchange.adapter.output.data;

import co.unicauca.stockexchange.commons.domain.Client;
import co.unicauca.stockexchange.port.output.data.IClientRepository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Ledy Mayerly Astudillo Calderon
 * @author Santiago Nieto Guaca
 * @author Harold Andres Molano Rosero
 * @author Janier Yulder Gomez Galindez
 */
@Repository
public class ClientRepository implements IClientRepository {

    /**
     * Lista que almacena objetos Client representando a los clientes en el repositorio.
     */
    private final ArrayList<Client> clients;

    /**
     * Repositorio de clientes que gestiona la información de los clientes y sus acciones en la bolsa de valores. Se inicializa con un conjunto predefinido de clientes al crear una nueva instancia.
     *
     */
    public ClientRepository() {

        clients = new ArrayList<>();
        initialize();
    }

    /**
     * Inicializa el repositorio de clientes con un conjunto predefinido de clientes. Cada cliente creado durante la inicialización también puede tener acciones asociadas.
     */
    public final void initialize() {
        clients.add(new Client("C01", "Daniel", "+57 3215896587"));
        clients.add(new Client("C02", "Camila", "+57 3219874585"));
        clients.add(new Client("C03", "Santiago", "+57 3122478963"));
    }

    @Override
    public Client findClient(String idClient) {
        for (Client client : clients) {
            if (client.getClientId().equals(idClient.toUpperCase())) {
                return client;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Client> getClients() {
        return clients;
    }

    @Override
    public boolean addClient(Client newClient) {
        if (findClient(newClient.getClientId()) != null) {
            return false;
        }
        clients.add(newClient);
        return true;
    }

}
