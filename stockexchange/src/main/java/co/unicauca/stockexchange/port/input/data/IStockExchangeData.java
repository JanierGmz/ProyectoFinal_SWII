/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.unicauca.stockexchange.port.input.data;

import co.unicauca.stockexchange.commons.domain.Action;
import java.util.List;

/**
 *
 * @author Ledy Mayerly Astudillo Calderon
 * @author Santiago Nieto Guaca
 * @author Harold Andres Molano Rosero
 * @author Janier Yulder Gomez Galindez
 */
public interface IStockExchangeData {

    /**
     * Obtiene la lista completa de acciones en el repositorio de la bolsa de valores.
     *
     * @return Lista de todas las acciones almacenadas en el repositorio.
     */
    List<Action> getActions();

    /**
     * Busca una acción por su ID.
     *
     * @param id ID de la acción a buscar.
     * @return La acción correspondiente al ID proporcionado.
     */
    Action findAction(String id);
}
