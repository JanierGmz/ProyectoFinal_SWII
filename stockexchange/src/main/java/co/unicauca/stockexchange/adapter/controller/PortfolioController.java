package co.unicauca.stockexchange.adapter.controller;

import co.unicauca.stockexchange.commons.domain.Action;
import co.unicauca.stockexchange.port.input.data.IPortfolioData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.unicauca.stockexchange.service.PortfolioService;
import java.util.List;

/**
 *
 * @author Ledy Mayerly Astudillo Calderon
 * @author Santiago Nieto Guaca
 * @author Harold Andres Molano Rosero
 * @author Janier Yulder Gomez Galindez
 */
@RestController
@RequestMapping("/portfolio")
public class PortfolioController implements IPortfolioData {

    @Autowired
    private PortfolioService portfolioService;

    // Método para obtener la lista de acciones asociadas a un cliente
    //USO: http://localhost:8080/portfolio/actions/C01
    @Operation(description = "Obtiene la lista de acciones asociadas a un cliente.")
    @ApiResponse(responseCode = "200", description = "Lista de acciones asociadas al cliente especificado.")
    @GetMapping("/actions/{idClient}")
    @Override
    public List<Action> getActions(
            @Parameter(description = "ID del Cliente del cual se desean obtener las acciones.") @PathVariable String idClient) {
        return portfolioService.getActions(idClient);
    }

    // Método para buscar una acción asociada a un cliente por los IDs del cliente y la acción
    //USO: http://localhost:8080/portfolio/action/C01/A01
    @Operation(description = "Busca una acción asociada a un cliente por los IDs del cliente y la acción.")
    @ApiResponse(responseCode = "200", description = "Acción correspondiente a los IDs proporcionados.")
    @GetMapping("/action/{idClient}/{idAction}")
    @Override
    public Action findAction(
            @Parameter(description = "ID del cliente.") @PathVariable String idClient,
            @Parameter(description = "ID de la acción a buscar.") @PathVariable String idAction) {
        return portfolioService.findAction(idClient, idAction);
    }

    // Método para agregar una nueva acción asociada a un cliente con umbrales especificados
    //USO: http://localhost:8080/portfolio/addAction/C01/A01/80000/150000
    @Operation(description = "Agrega una nueva acción asociada a un cliente con umbrales especificados.")
    @ApiResponse(responseCode = "200", description = "Mensaje de confirmación.")
    @PostMapping("/addAction/{idClient}/{idAction}/{lowerThreshold}/{upperThreshold}")
    @Override
    public String addAction(
            @Parameter(description = "ID del cliente.") @PathVariable String idClient,
            @Parameter(description = "ID de la acción a agregar.") @PathVariable String idAction,
            @Parameter(description = "Umbral inferior.") @PathVariable Double lowerThreshold,
            @Parameter(description = "Umbral superior.") @PathVariable Double upperThreshold) {
        return portfolioService.addAction(idClient, idAction, lowerThreshold, upperThreshold);
    }

    // Método para actualizar los umbrales de una acción asociada a un cliente
    //USO: http://localhost:8080/portfolio/updateAction/C01/A01/85000/155000
    @Operation(description = "Actualiza los umbrales de una acción asociada a un cliente.")
    @ApiResponse(responseCode = "200", description = "Mensaje de confirmación.")
    @PutMapping("/updateAction/{idClient}/{idAction}/{lowerThreshold}/{upperThreshold}")
    @Override
    public String updateThresholds(
            @Parameter(description = "ID del cliente.") @PathVariable String idClient,
            @Parameter(description = "ID de la acción a agregar.") @PathVariable String idAction,
            @Parameter(description = "Nuevo umbral inferior.") @PathVariable Double lowerThreshold,
            @Parameter(description = "Nuevo umbral superior.") @PathVariable Double upperThreshold) {
        return portfolioService.updateThresholds(idClient, idAction, lowerThreshold, upperThreshold);
    }

    // Método para eliminar una acción asociada a un cliente
    //USO: http://localhost:8080/portfolio/deleteAction/C01/A01
    @Operation(description = "Elimina una acción asociada a un cliente.")
    @ApiResponse(responseCode = "200", description = "Mensaje de confirmación.")
    @DeleteMapping("/deleteAction/{idClient}/{idAction}")
    @Override
    public String deleteAction(
            @Parameter(description = "ID del cliente.") @PathVariable String idClient,
            @Parameter(description = "ID de la acción a eliminar.") @PathVariable String idAction) {
        return portfolioService.deleteAction(idClient, idAction);
    }
}
