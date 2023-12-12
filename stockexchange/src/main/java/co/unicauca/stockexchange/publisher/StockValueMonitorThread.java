/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.stockexchange.publisher;

import co.unicauca.stockexchange.commons.domain.Action;
import co.unicauca.stockexchange.commons.domain.Client;
import co.unicauca.stockexchange.port.output.data.IClientRepository;
import co.unicauca.stockexchange.port.output.data.IStockExchangeRepository;
import co.unicauca.stockexchange.port.output.publisher.INotifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Clase que representa un hilo de monitorización de cambios en los valores de las acciones.
 */
public class StockValueMonitorThread extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(StockValueMonitorThread.class);

    // Intervalo de tiempo de pausa entre verificaciones
    private static final long SLEEP_INTERVAL = 5000;

    private IStockExchangeRepository actions;
    private IClientRepository clients;
    private INotifier notifier;

    /**
     * Constructor de la clase StockValueMonitorThread.
     *
     * @param actions Repositorio de acciones de la bolsa de valores.
     * @param clients Repositorio de clientes.
     * @param notifier Objeto encargado de notificar cambios a los clientes.
     */
    public StockValueMonitorThread(IStockExchangeRepository actions, IClientRepository clients, INotifier notifier) {
        this.actions = actions;
        this.clients = clients;
        this.notifier = notifier;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            logger.info("Revisando cambios...");
            checkStockValues();
            try {
                // Pausa del hilo
                Thread.sleep(SLEEP_INTERVAL);
            } catch (InterruptedException ex) {
                logger.error("Error durante la pausa del hilo", ex);
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Comprueba los valores de las acciones y notifica a los clientes si es necesario.
     */
    private void checkStockValues() {
        List<Action> currentStocks = actions.getActions();
        List<Client> currentClients = clients.getClients();

        for (Client myClient : currentClients) {
            if (myClient.getActions() == null) {
                continue;
            }
            for (Action globalAction : currentStocks) {
                Action clientAction = myClient.getActionById(globalAction.getActionId());
                if (clientAction == null) {
                    continue;
                }
                if (isValueBeyondThresholds(clientAction, globalAction) && !clientAction.isNotified()) {
                    String msg = String.format("Estimado(a) %s, el valor de la acción (%s) está fuera de los umbrales definidos.",
                            myClient.getClientName(), clientAction.getActionName());
                    if (notifier.sendMessage(myClient.getClientId(), msg)) {
                        clientAction.setNotified(true);
                    }
                }
            }
        }
    }

    /**
     * Verifica si el valor de una acción está fuera de los umbrales definidos.
     *
     * @param clientAction Acción del cliente.
     * @param globalAction Acción en el mercado global.
     * @return true si el valor está fuera de los umbrales, false de lo contrario.
     */
    private boolean isValueBeyondThresholds(Action clientAction, Action globalAction) {
        return globalAction.getCurrentPrice() < clientAction.getLowerThreshold() ||
               globalAction.getCurrentPrice() > clientAction.getUpperThreshold();
    }

    /**
     * Detiene el hilo de monitorización.
     */
    public void shutdown() {
        interrupt();
    }
}