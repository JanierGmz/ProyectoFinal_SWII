package co.unicauca.stockexchange.adapter.output.data;

import co.unicauca.stockexchange.commons.domain.Action;
import co.unicauca.stockexchange.commons.domain.StockExchange;
import co.unicauca.stockexchange.port.output.data.IStockExchangeRepository;

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
public class StockExchangeRepository implements IStockExchangeRepository {

    /**
     * Instancia de la bolsa de valores asociada a este repositorio. Se inicializa con una nueva instancia de StockExchange por defecto.
     */
    private StockExchange myStockExchange = new StockExchange();

    /**
     * Constructor de la clase StockExchangeRepository. Inicializa la instancia de StockExchange llamando al método de inicialización.
     */
    public StockExchangeRepository() {
        initialize();
    }

    /**
     * Inicializa la instancia de StockExchange con valores predeterminados. Establece el ID y el nombre de la bolsa de valores, así como las acciones iniciales.
     */
    public final void initialize() {
        myStockExchange = new StockExchange();
        myStockExchange.setStockExchangeId("01");
        myStockExchange.setStockExchangeName("Bolsa de Valores de Popayán");
        initializeActions();
    }

    /**
     * Inicializa la lista de acciones con valores predeterminados. Agrega varias acciones a la bolsa de valores durante la inicialización.
     */
    public void initializeActions() {
        ArrayList<Action> actions = new ArrayList<>();

        actions.add(new Action("A01", "Cafe Juan el Pez", 80000.0, 100000.0));
        actions.add(new Action("A02", "Cafe Margarita", 70000.0, 120000.0));
        actions.add(new Action("A03", "Cafe Sello Azul", 75000.0, 105000.0));
        actions.add(new Action("A04", "Cafe Pez Grande", 80000.0, 109000.0));
        actions.add(new Action("A05", "Cafe El Colombiano", 90000.0, 115000.0));

        myStockExchange.setActions(actions);
    }

    @Override
    public Action findAction(String idAction) {
        for (Action action : myStockExchange.getActions()) {
            if (action.getActionId().equals(idAction.toUpperCase())) {
                return action;
            }
        }
        return null;
    }

    @Override
    public List<Action> getActions() {
        return myStockExchange.getActions();
    }

}
