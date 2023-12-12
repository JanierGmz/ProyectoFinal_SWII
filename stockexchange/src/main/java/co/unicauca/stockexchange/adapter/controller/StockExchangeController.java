package co.unicauca.stockexchange.adapter.controller;

import co.unicauca.stockexchange.commons.domain.Action;
import co.unicauca.stockexchange.port.input.data.IStockExchangeData;
import co.unicauca.stockexchange.service.StockExchangeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
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
@RequestMapping("/stockExchange")
public class StockExchangeController implements IStockExchangeData {

    @Autowired
    private StockExchangeService stockExchangeService;

    // Método para obtener la lista completa de acciones en el repositorio de la bolsa de valores
    //USO: http://localhost:8080/stockExchange
    @Operation(description = "Obtiene la lista completa de acciones en el repositorio de la bolsa de valores.")
    @ApiResponse(responseCode = "200", description = "Lista de todas las acciones almacenadas en el repositorio.")
    @GetMapping
    @Override
    public List<Action> getActions() {
        return stockExchangeService.getActions();
    }

    // Método para buscar una acción por su ID
    //USO: http://localhost:8080/stockExchange/A01
    @Operation(description = "Busca una acción por su ID.")
    @ApiResponse(responseCode = "200", description = "La acción correspondiente al ID proporcionado")
    // Uso: http://localhost:8080/stockExchange/A01
    @GetMapping("/{id}")
    @Override
    public Action findAction(@Parameter(description = "ID de la acción.") @PathVariable String id) {
        return stockExchangeService.findAction(id);
    }
}
