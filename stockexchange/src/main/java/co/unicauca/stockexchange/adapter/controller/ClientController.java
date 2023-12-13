package co.unicauca.stockexchange.adapter.controller;

import co.unicauca.stockexchange.commons.domain.Client;
import co.unicauca.stockexchange.port.input.data.IClientData;
import co.unicauca.stockexchange.service.ClientService;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ledy Mayerly Astudillo Calderon
 * @author Santiago Nieto Guaca
 * @author Harold Andres Molano Rosero
 * @author Janier Yulder Gomez Galindez
 */
@RestController
@RequestMapping("/client")
public class ClientController implements IClientData {

    // Inyección de dependencia del servicio de clientes
    @Autowired
    private ClientService clientService;

    // Método para obtener la lista de todos los clientes
    //USO: http://localhost:8080/client
    @Operation(description = "Obtiene la lista de todos los clientes en el repositorio.")
    @ApiResponse(responseCode = "200", description = "Lista de todos los clientes almacenados en el repositorio.")
    @GetMapping
    @Override
    public List<Client> getClients() {
        return clientService.getClients();
    }

    // Método para buscar un cliente por su ID
    //USO:http://localhost:8080/client/C01
    @Operation(description = "Busca un cliente por su ID.")
    @ApiResponse(responseCode = "200", description = "Cliente correspondiente al ID proporcionado.")
    @GetMapping("/{id}")
    @Override
    public Client findClient(@Parameter(description = "ID del cliente a buscar.") @PathVariable String id) {
        return clientService.findClient(id);
    }

    // Método para registrar un nuevo cliente
    //USO:http://localhost:8080/client/register/C10/Xamir/+57 3249875463
    @Operation(description = "Registra un cliente.")
    @ApiResponse(responseCode = "200", description = "Cliente Agregado Exitosamente.")
    @GetMapping("register/{clientId}/{clientName}/{phone}")
    @Override
    public String registerClient(
            @PathVariable String clientId,
            @PathVariable String clientName,
            @PathVariable String phone
    ) {
        // Intenta agregar el cliente y devuelve el mensaje correspondiente
        if (!clientService.addClient(clientId.toUpperCase(), clientName, phone)) {
            return "Cliente no agregado exitosamente";
        }
        return "Cliente agregado exitosamente";
    }
}
