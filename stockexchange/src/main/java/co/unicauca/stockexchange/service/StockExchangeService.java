package co.unicauca.stockexchange.service;

import co.unicauca.stockexchange.port.output.data.IStockExchangeRepository;
import co.unicauca.stockexchange.commons.domain.Action;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ledy Mayerly Astudillo Calderon
 * @author Santiago Nieto Guaca
 * @author Harold Andres Molano Rosero
 * @author Janier Yulder Gomez Galindez
 */
/**
 * Clase de servicio para la bolsa de valores.
 */
@Service
public class StockExchangeService {

    private final IStockExchangeRepository repositoryStock;

    @Autowired
    public StockExchangeService(IStockExchangeRepository repositoryStock) {
        this.repositoryStock = repositoryStock;
    }

    /**
     * Obtiene la lista completa de acciones en el repositorio de la bolsa de valores.
     *
     * @return Lista de todas las acciones almacenadas en el repositorio.
     */
    public synchronized List<Action> getActions() {
        return repositoryStock.getActions();
    }

    /**
     * Busca una acción por su ID en el repositorio de la bolsa de valores.
     *
     * @param idAction ID de la acción.
     * @return La acción correspondiente al ID proporcionado.
     */
    public synchronized Action findAction(String idAction) {
        return repositoryStock.findAction(idAction);
    }
}
