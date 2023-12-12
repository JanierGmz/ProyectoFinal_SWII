/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.stockexchange.service;


import co.unicauca.stockexchange.port.simulator.ISimulator;
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
 * Clase de servicio para la simulación de cambios en el precio de las acciones.
 */
@Service
public class SimulatorService {

    @Autowired
    private ISimulator simulator;

    /**
     * Inicia la simulación de cambios en el precio de las acciones.
     */
    public synchronized void simulate() {
        simulator.simulate();
    }
}
