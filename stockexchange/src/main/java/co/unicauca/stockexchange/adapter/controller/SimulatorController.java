/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.stockexchange.adapter.controller;

import co.unicauca.stockexchange.service.SimulatorService;
import co.unicauca.stockexchange.port.simulator.ISimulator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/simulator")
public class SimulatorController implements ISimulator {

    @Autowired(required = false)
    SimulatorService simulator;

    // Método para simular un cambio en el precio de las acciones
    //USO: http://localhost:8080/simulator
    @Operation(description = "Simula un cambio el precio de las acciones.")
    @ApiResponse(responseCode = "200", description = "")
    @GetMapping
    @Override
    public void simulate() {
        simulator.simulate();
    }

}
