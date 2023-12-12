/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.stockexchange.adapter.output.simulator;

import co.unicauca.stockexchange.commons.domain.Action;
import co.unicauca.stockexchange.commons.domain.Client;
import co.unicauca.stockexchange.port.output.data.IClientRepository;
import co.unicauca.stockexchange.port.output.data.IStockExchangeRepository;
import co.unicauca.stockexchange.port.simulator.ISimulator;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class Simulator implements ISimulator {

    private IClientRepository repositoryClient;
    private IStockExchangeRepository repositoryStock;

    // Constructor que recibe los repositorios como parámetros de dependencia
    public Simulator(IClientRepository repositoryClient, IStockExchangeRepository repositoryStock) {
        this.repositoryClient = repositoryClient;
        this.repositoryStock = repositoryStock;
    }

    // Método para simular cambios en el precio de las acciones
    @Override
    public void simulate() {
        // Obtener la lista de clientes y acciones de la bolsa de valores
        List<Client> clients = repositoryClient.getClients();
        List<Action> actionsStockExchange = repositoryStock.getActions();

        // Obtener la primera y última acción de la bolsa de valores
        Action myAction = actionsStockExchange.get(0);
        Action myAction2 = actionsStockExchange.get(actionsStockExchange.size() - 1);

        // Establecer umbrales para las acciones
        myAction.setLowerThreshold(80000.0);
        myAction.setUpperThreshold(120000.0);
        myAction2.setLowerThreshold(100000.0);
        myAction2.setUpperThreshold(120000.0);

        // Agregar acciones a los primeros dos clientes
        clients.get(0).addAction(myAction);
        clients.get(1).addAction(myAction2);

        // Simular cambios en el precio actual de las acciones
        clients.get(0).getActionById(myAction.getActionId()).updateCurrentPrice(70000.0);
        clients.get(1).getActionById(myAction2.getActionId()).updateCurrentPrice(130000.0);
    }
}
